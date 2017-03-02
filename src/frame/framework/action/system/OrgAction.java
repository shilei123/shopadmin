package framework.action.system;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import framework.db.DBUtil;
import framework.db.pojo.TXtOrg;
import framework.util.ComparatorOrgVO;

import com.opensymphony.xwork2.Action;

@SuppressWarnings({"unchecked","rawtypes"})
public class OrgAction {
	private List<Map> trees;
	private TXtOrg tree;
	
	private static final String ORG_SQL = "select o.id,o.org_id,o.org_name,o.short_name,o.parent_org_id,o.org_path,o.org_level,o.order_,o.root from t_xt_org o";
	private static final String MAX_ORG_ID = "select max(o.org_id) max_org_id from t_xt_org o where o.parent_org_id=?";
	
	public String orgTree() {
		orgTreeQuery();
		return Action.SUCCESS;
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
	
	/**
	 * 保存机构
	 */
	public String saveOrg() {
		DBUtil db = DBUtil.getInstance();
		if(this.tree.getId() == null || "".equals(this.tree.getId())) {
			//this.tree.setOrgName(orgName)
			//this.tree.setParentOrgId(parentOrgId)
			//this.tree.setShortName(shortName)
			//this.tree.setOrder(999);
			TXtOrg parentOrg = this.getParentOrg(db);
			if(parentOrg != null) {
				String parentOrgId = parentOrg.getOrgId();
				String maxOrgId = this.getMaxOrgId(db, parentOrgId);
				this.tree.setId(UUID.randomUUID().toString());
				this.tree.setOrgId(maxOrgId);
				this.tree.setRoot(null);
				this.tree.setOrgPath(parentOrg.getOrgPath() + "/" + this.tree.getOrgName());
				this.tree.setOrgLevel(2);
				db.insert(this.tree);
			}
		} else {
			Map params = new HashMap(1);
			params.put("id", tree.getId());
			TXtOrg org = (TXtOrg) db.queryByPojo(TXtOrg.class, params).get(0);
			org.setOrgName(this.tree.getOrgName());
			org.setShortName(this.tree.getShortName());
			org.setOrder(this.tree.getOrder());
			db.update(org);
		}
		return Action.SUCCESS;
	}
	
	private TXtOrg getParentOrg(DBUtil db) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("orgId", this.tree.getParentOrgId());
		List<TXtOrg> list = db.queryByPojo(TXtOrg.class, params);
		if(list != null && !list.isEmpty()) {
			TXtOrg ni = list.get(0);
			return ni;
		}
		return null;
	}
	
	private String getMaxOrgId(DBUtil db, String parentOrgId) {
		List<Map<String,String>> list = db.queryBySQL(MAX_ORG_ID, parentOrgId);
		if(list!=null && !list.isEmpty()) {
			Map<String,String> map = list.get(0);
			String maxOrgId = map.get("maxOrgId");
			if(maxOrgId != null && !"".equals(maxOrgId)) {
				int len = maxOrgId.length();
				int neworgid = Integer.parseInt(maxOrgId);
				neworgid += 1;
				String newMaxOrgId = String.valueOf(neworgid);
				if(newMaxOrgId.length() < len) {
					int diffLen = len - newMaxOrgId.length();
					StringBuffer addZreo = new StringBuffer();
					for(int i = 0; i < diffLen; i++) {
						addZreo.append("0");
					}
					newMaxOrgId = addZreo.toString() + newMaxOrgId;
				}
				return newMaxOrgId;
			}
		}
		return new StringBuffer(parentOrgId).append("001").toString();
	}
	
	public String delete() {
		DBUtil db = DBUtil.getInstance();
		if(this.tree.getOrgId().indexOf("xt")==0){
			return Action.ERROR;
		}
		
		//查询是否有子菜单
		String sql = "select m.id from t_xt_org m where m.parent_org_id=?";
		List<TXtOrg> childMenus = db.queryBySQL(sql, this.tree.getOrgId());
		if(childMenus != null && !childMenus.isEmpty()) {
			return Action.ERROR;
		}
		
		String deleteMenuSql = "delete from t_xt_org where id=?";
		db.executeSQL(deleteMenuSql, this.tree.getId());
		//db.commit();
		return Action.SUCCESS;
	}
	
	public List getTrees() {
		return trees;
	}

	public TXtOrg getTree() {
		return tree;
	}

	public void setTree(TXtOrg tree) {
		this.tree = tree;
	}
}