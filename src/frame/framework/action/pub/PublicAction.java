package framework.action.pub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import framework.action.FenyeBase;
import framework.db.DBUtil;
import framework.helper.RequestHelper;
import framework.util.ComparatorOrgVO;

public class PublicAction extends FenyeBase{
	private static final Logger log = Logger.getLogger(PublicAction.class);
	private static final String ORG_SQL = "select o.id,o.org_id,o.org_name,o.short_name,o.parent_org_id,o.org_path,o.org_level,o.order_,o.root from t_xt_org o";
	private static final String USER_SQL = "select u.id userid, u.user_id, u.user_name, o.id orgid, o.org_id, o.org_name from t_xt_emp u, t_xt_org o where u.org_id=o.id ";
	
	private List<Map> trees;
	
	public String fileuploadList(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select f.file_id, f.file_name, f.file_type, f.file_size, u.user_name, f.lr_sj create_time, f.group_id "
					+"from t_pb_uploadfile f left join t_xt_emp u on f.lrry_dm=u.user_id where 1=1 ");
		List params = new ArrayList();
		if(this.queryParams==null){
			this.queryParams = new HashMap(0);
		}
		DBUtil.sqlAdd(sql, params, "f.group_id", queryParams.get("groupId"));
		this.total = db.queryCountBySQL(sql.toString(), params);
		this.dataRows = db.queryBySQL(sql.toString(), params,  this.getPage() * this.getRowCount() - this.getRowCount(), this.getRowCount());
		
		return "json";
		
	}
	
	
	/**
	 * 结构树
	 * @return
	 */
	public String orgTree(){
		orgTreeQuery();
		return "json";
	}
	
	/**
	 * 查询用户集合
	 * @return
	 */
	public String queryUsers(){
		DBUtil db = DBUtil.getInstance();
		String type = RequestHelper.getParameter("type");
		String orgId = RequestHelper.getParameter("orgId");
		StringBuffer sql = new StringBuffer(USER_SQL);
		if("like".equals(type)){
			sql.append("and o.org_id like ? || '%'");
			
		}else{
			sql.append("and o.org_id = ?");
		}
		
		List params = new ArrayList();
		params.add(orgId);
		if(this.queryParams!=null){
			db.bind(sql, params, " and u.flag=? " ,queryParams.get("flag"));
		}
		List<Map> list = db.queryBySQL(sql.toString(), params);
		
		try {
			RequestHelper.wirte(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			log.error("", e);
		}
		return null;
	}
	
	private void orgTreeQuery() {
		DBUtil db = DBUtil.getInstance();
		List<Map> list = db.queryBySQL(ORG_SQL);
		Map root = null;
		Map<String,Map> temp = new TreeMap();
		for (Map pojo : list) {
			Map node = new TreeMap();
			node.put("id", pojo.get("orgId")); //机构编码
			node.put("text", pojo.get("orgName")); //机构名称
			node.put("parentId", pojo.get("parentOrgId")); //上机机构编码
			Map attributes = new HashMap(3);
			attributes.put("orgPkId", pojo.get("id")); //机构主键
			attributes.put("shortName", pojo.get("shortName")); //机构简称
			attributes.put("orgPath", pojo.get("orgPath")); //机构全路径
			attributes.put("order", pojo.get("order_")); //排序序号
			attributes.put("orgLevel", pojo.get("orgLevel")); //机构等级 
			node.put("attributes", attributes);
			temp.put(pojo.get("orgId").toString(), node);
			if("root".equals(node.get("parentId"))) {
				root = node;
			}
		}
		
		//循环找出层级关系
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			Object parentId = node.get("parentId");
			if(parentId==null){
				continue;
			}
			Map parentMap = temp.get(parentId);
			if(parentMap != null) {
				if(parentMap.get("children") == null) {
					parentMap.put("children", new ArrayList());
				}
				((ArrayList)parentMap.get("children")).add(node);
			}
		}
		
		//循环判断是否有子节点，是否能展开
		for(String key : temp.keySet()) {
			Map node = temp.get(key);
			if(node.get("children") != null) {
				List childOrgList = (ArrayList) node.get("children");
				if(!childOrgList.isEmpty()) {
					Collections.sort(childOrgList, new ComparatorOrgVO());
					node.put("state", "closed"); //节点状态：关闭
				}
			} 
		}
		
		trees = new ArrayList(1);
		if(root != null) {
			trees.add(root);
		}
	}


	public List<Map> getTrees() {
		return trees;
	}


	public void setTrees(List<Map> trees) {
		this.trees = trees;
	}
	
	
	
}
