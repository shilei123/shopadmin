package framework.action.system;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import framework.action.FenyeBase;
import framework.bean.UserMsg;
import framework.db.DBUtil;
import framework.db.pojo.TXtMessage;
import framework.helper.RequestHelper;
import framework.util.BeanUtils;
import framework.util.DateUtils;

@SuppressWarnings({"unchecked","rawtypes","deprecation"})
public class MessageAction extends FenyeBase{
	private final static Logger log = Logger.getLogger(MessageAction.class);
	private TXtMessage message;
	private String userIds;
	
	public String users(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select e.user_id, e.user_name , o.org_name "+
			"from t_xt_emp e left join t_xt_org o on e.org_id=o.id where 1=1 ");
		List params = new ArrayList(1);
		if(this.queryParams!=null){
			DBUtil.sqlAddByLike(sql, params, "e.user_name", queryParams.get("user"));
			DBUtil.sqlAddByLike(sql, params, "o.org_name", queryParams.get("org"));
		}
		this.total = db.queryCountBySQL(sql.toString(), params);
		this.query(sql.toString(), params, db);
		return "json";
	}
	
	public String save(){
		DBUtil db = DBUtil.getInstance();
		if(userIds!=null && !userIds.equals("")){
			String batchId = UUID.randomUUID().toString();
			String[] ids = userIds.split(",");
			try {
				for(String id : ids){
					TXtMessage pojo = new TXtMessage();
					BeanUtils.copyPojo(message, pojo);
					pojo.setId(UUID.randomUUID().toString());
					pojo.setBatchId(batchId);
					pojo.setToUserId(id);
					pojo.setMessageStatus(BigDecimal.valueOf(0));
					db.insert(pojo);
				}
			} catch (Exception e) {
				//db.rollback();
				log.error("",e);
				return "error";
			}
		}
		if(message.getToUserId()!=null && !"".equals(message.getToUserId())){
			message.setId(UUID.randomUUID().toString());
			message.setMessageStatus(BigDecimal.valueOf(0));
			db.insert(message);
		}
		
		return "json";
	}
	
	public String list(){
		DBUtil db = DBUtil.getInstance();
		UserMsg user = RequestHelper.getUser();
		
		StringBuffer sql = new StringBuffer("select m.id , m.batch_id, m.reply_message_id, m.title, m.message_type, m.message_status, "+
			" m.message_level,m.url, e1.user_name create_user_name, e2.user_name to_user_name, m.create_time, "+
			"m.create_user_id "+
			"from t_xt_message m , t_xt_emp e1, t_xt_emp e2 "+
			"where m.to_user_id=e1.user_id "+
			"and m.create_user_id=e2.user_id "+
			"and m.flag='1' ");
		List params = new ArrayList();
		if(this.queryParams==null){
			queryParams = new HashMap(0);
		}
		
		if(queryParams.get("status")==null || "".equals(queryParams.get("status"))){
			sql.append(" and m.message_status=0 ");
		}else if(!"all".equals(queryParams.get("status"))){
			DBUtil.sqlAdd(sql, params, "m.message_status", queryParams.get("status"));
		}
		
		if("receiver".equals(queryParams.get("query"))){
			sql.append(" and m.to_user_id=? ");
			params.add(user.getUId());
		}else if("sender".equals(queryParams.get("query"))){
			sql.append(" and m.create_user_id=? ");
			params.add(user.getUId());
		}else{
			sql.append(" and (m.to_user_id=? or m.create_user_id=?)");
			params.add(user.getUId());
			params.add(user.getUId());
		}
		
		DBUtil.sqlAdd(sql, params, "m.message_type", queryParams.get("type"));
		DBUtil.sqlAddByLike(sql, params, "e1.user_name", queryParams.get("sender"));
		DBUtil.sqlAddByLike(sql, params, "e2.user_name", queryParams.get("receiver"));
		if(queryParams.get("beginDate")!=null && !"".equals(queryParams.get("beginDate"))){
			sql.append(" and m.create_time>=? ");
			Timestamp t = DateUtils.parseTimestamp(queryParams.get("beginDate").toString());
			params.add(t);
		}
		if(queryParams.get("endDate")!=null && !"".equals(queryParams.get("endDate"))){
			sql.append(" and m.create_time<? ");
			Timestamp t = DateUtils.parseTimestamp(queryParams.get("endDate").toString());
			t.setDate(t.getDate()+1);
			params.add(t);
		}
		if(queryParams.get("keyword")!=null && !"".equals(queryParams.get("keyword"))){
			sql.append(" and (m.title like ? or m.message like ?) ");
			params.add("%"+queryParams.get("keyword")+"%");
			params.add("%"+queryParams.get("keyword")+"%");
		}
		
		this.total = db.queryCountBySQL(sql.toString(), params);
		sql.append("order by m.message_level, m.create_time desc");
		this.query(sql.toString(), params, db);
		return "json";
	}
	
	public String messageCount(){
		String sql = "select count(1) message_Count from t_xt_message where flag='1' and message_status=0 and to_user_id=? ";
		List<Map> list = DBUtil.getInstance().queryBySQL(sql, RequestHelper.getUser().getUId());
		try {
			RequestHelper.wirte(String.valueOf(list.get(0).get("messageCount")));
		} catch (IOException e) {
			log.error("",e);
		}
		return null;
	}
	
	public String info(){
		initMessage();
		this.message.setMessageStatus(BigDecimal.valueOf(1));//设置为已读
		DBUtil.getInstance().update(message);
		return "json";
	}
	
	public String delete(){
		initMessage();
		DBUtil.getInstance().delete(this.message);
		return "json";
	}
	
	//--------------------数据加载查询 begin
	private void initMessage(){
		if(message==null || message.getId()==null || "".equals(message.getId())){
			return;
		}
		DBUtil db = DBUtil.getInstance();
		this.message = (TXtMessage)db.get(TXtMessage.class, message.getId());
	}
	
	
	//--------------------数据加载查询 end
	
	//-------------------getset begin
	public TXtMessage getMessage() {
		return message;
	}
	public void setMessage(TXtMessage message) {
		this.message = message;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	//-------------------getset end
}
