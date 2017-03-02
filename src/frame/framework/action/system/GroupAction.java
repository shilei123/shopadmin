package framework.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import framework.action.FenyeBase;
import framework.db.DBUtil;
import framework.db.pojo.TXtGroup;
import framework.db.pojo.TXtGroupUser;

@SuppressWarnings({"unchecked","rawtypes"})
public class GroupAction extends FenyeBase {
	private TXtGroup group;
	private TXtGroupUser groupUser;
	
	public String saveGroup(){
		DBUtil db = DBUtil.getInstance();
		if(group.getId()==null ||"".equals(group.getId())){
			group.setId(UUID.randomUUID().toString());
			db.insert(group);
		}else{
			TXtGroup tmp = group;
			loadGroup();
			group.setGroupName(tmp.getGroupName());
			db.update(group);
		}
		return "json";
	}
	
	public String deleteGroup(){
		DBUtil db = DBUtil.getInstance();
		loadGroup();
		group.setFlag("0");
		db.update(group);
		
		HashMap params = new HashMap();
		params.put("flag", "1");
		params.put("gorupId", group.getId());
		List<TXtGroupUser> list = db.queryByPojo(TXtGroupUser.class, params);
		for(TXtGroupUser tmp : list){
			tmp.setFlag("0");
		}
		db.update(list);
		
		return "json";
	}
	
	public String findGroup(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select * from t_xt_group where 1=1 ");
		ArrayList params = new ArrayList();
		if(this.queryParams==null){
			this.queryParams = new HashMap();
		}
		DBUtil.sqlAdd(sql, params, "flag", "1");
		DBUtil.sqlAdd(sql, params, "Business_Type", queryParams.get("businessType"));
		DBUtil.sqlAddByLike(sql, params, "group_Name", queryParams.get("groupName"));
		DBUtil.sqlAddDate(sql, params, "create_time", queryParams);
		
		this.total = db.queryCountBySQL(sql.toString(), params);
		this.dataRows = db.queryBySQL(sql.toString()+" order by create_time desc", params);
		return "json";
	}
	
	public String loadGroup(){
		DBUtil db = DBUtil.getInstance();
		group = (TXtGroup)db.get(TXtGroup.class, group.getId());
		return "json";
	}
	
	public String saveGroupUser(){
		DBUtil db = DBUtil.getInstance();
		
		//查询是否已存在记录
		HashMap params = new HashMap();
		params.put("flag", "1");
		params.put("businessType", groupUser.getBusinessType());
		params.put("userId", groupUser.getUserId());
		if(groupUser.getGroupId()!=null&&!"".equals(groupUser.getGroupId())){
			params.put("groupId", groupUser.getGroupId());	
		}else{
			params.put("userType", "1");	
		}
		List<TXtGroupUser> guList = db.queryByPojo(TXtGroupUser.class, params);
		if(guList.size()>0){
			groupUser = guList.get(0);
			return "json";
		}
		
		
		if (groupUser.getId() == null || "".equals(groupUser.getId())) {
			if(groupUser.getGroupId()==null || "".equals(groupUser.getGroupId())){
				groupUser.setUserType("1");
			}else{
				groupUser.setUserType("2");
			}
			groupUser.setId(UUID.randomUUID().toString());
			db.insert(groupUser);
		} else {
			TXtGroupUser tmp = groupUser;
			groupUser.setGroupId(tmp.getGroupId());
			groupUser.setUserType(tmp.getUserType());
			groupUser.setUserId(tmp.getUserId());
			if(groupUser.getGroupId()==null || "".equals(groupUser.getGroupId())){
				groupUser.setUserType("1");
			}else{
				groupUser.setUserType("2");
			}
			db.update(groupUser);
		}
		return "json";
	}
	
	public String deleteGroupUser(){
		DBUtil db = DBUtil.getInstance();
		loadGroupUser();
		groupUser.setFlag("0");
		db.update(groupUser);
		return "json";
	}
	
	public String findGroupUser(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select gu.*, emp.user_name, g.group_name , org.org_name, org.org_id from  "+
					"t_xt_group_user gu "+
					"inner join t_xt_emp emp on gu.user_id=emp.user_id "+
					"left join t_xt_org org on org.id=emp.org_id "+
					"left join t_xt_group g on gu.group_id=g.id  where 1=1  ");
		ArrayList params = new ArrayList();
		if (this.queryParams == null) {
			this.queryParams = new HashMap();
		}
		DBUtil.sqlAdd(sql, params, "gu.flag", "1");
		DBUtil.sqlAddDate(sql, params, "gu.create_time", queryParams);
		DBUtil.sqlAddByLike(sql, params, "emp.user_name", queryParams.get("userName"));
		if("noGroup".equals(queryParams.get("groupId"))){
			sql.append(" and gu.user_Type='1' ");
		}else{
			DBUtil.sqlAdd(sql, params, "g.id", queryParams.get("groupId"));
		}

		this.total = db.queryCountBySQL(sql.toString(), params);
		this.dataRows = db.queryBySQL(sql.toString()+" order by gu.create_time desc", params);
		return "json";
	}
	
	public String loadGroupUser(){
		DBUtil db = DBUtil.getInstance();
		groupUser = (TXtGroupUser)db.get(TXtGroupUser.class, groupUser.getId());
		return "json";
	}

	//---------------getset begin
	public TXtGroup getGroup() {
		return group;
	}

	public void setGroup(TXtGroup group) {
		this.group = group;
	}

	public TXtGroupUser getGroupUser() {
		return groupUser;
	}

	public void setGroupUser(TXtGroupUser groupUser) {
		this.groupUser = groupUser;
	}
	
	//---------------getset end
}
