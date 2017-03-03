package framework.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.opensymphony.xwork2.Action;

import framework.action.FenyeBase;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TXtRole;
import framework.db.pojo.TXtRoleMenu;
import framework.db.pojo.TXtRoleMenuId;
import framework.util.DateUtils;

@SuppressWarnings({"unchecked","rawtypes","static-access"})
public class RoleAction extends FenyeBase {
	private TXtRole role;
	private String[] treeIds;
	private String message;
	private List<TXtRole> roles;
	
	public String saveRoleMenu(){
		DBUtil db = DBUtil.getInstance();
		
		Map params = new HashMap(1);
		params.put("id.roleId", role.getRoleId());
		List<TXtRoleMenu> rmList = db.queryByPojo(TXtRoleMenu.class, params);
		
		for(TXtRoleMenu pojo : rmList){
			db.delete(pojo);
		}

		if(this.treeIds!=null && this.treeIds.length>0) {
			for (String menuId:treeIds) {
				TXtRoleMenuId id = new TXtRoleMenuId(role.getRoleId(),menuId);
				TXtRoleMenu pojo = new TXtRoleMenu(id, "1", DateUtils.getTimestamp(), null);
				db.insert(pojo);
			}
		}
		return "success";
	}
	
	public String queryRoleMenu(){
		DBUtil db = DBUtil.getInstance();
		
		Map params = new HashMap(1);
		params.put("id.roleId", role.getRoleId());
		List<TXtRoleMenu> rmList = db.queryByPojo(TXtRoleMenu.class, params);
		
		treeIds = new String[rmList.size()];
		for (int i = 0; i < treeIds.length; i++) {
			treeIds[i] = rmList.get(i).getId().getMenuId();
		}
		return "success";
	}
	
	public String saveRoleUser(){
		/*DBUtil db = DBUtil.getInstance();
		db.commit();*/
		return "success";
	}
	
	public String queryRoleUser(){
		/*DBUtil db = DBUtil.getInstance();
		db.commit();*/
		return "success";
	}
	
	public String save(){
		DBUtil db = DBUtil.getInstance();
		if(role.getRoleId()==null || role.getRoleId().length()==0){
			String id = UUID.randomUUID().toString();
			this.role.setRoleId(id);
			this.role.setCreateTime(DateUtils.getTimestamp());
			this.role.setFlag("1");
			db.insert(role);
		}else{
			TXtRole pojo = getTXtRole(role.getRoleId() , db);
			pojo.setRoleName(role.getRoleName());
			pojo.setRoleRemark(role.getRoleRemark());
			pojo.setRoleType(role.getRoleType());
			pojo.setRoleStateValue(role.getRoleStateValue());
			pojo.setUpdateTime(DateUtils.getTimestamp());
			db.update(pojo);
		}
		//db.commit();
		return "success";
	}
	
	public String delete(){
		DBUtil db = DBUtil.getInstance();
		HashMap params = new HashMap();
		params.put("roleId", role.getRoleId());
		StringBuffer msg = new StringBuffer();
		int count = db.queryByHqlCount("select count(*) from TXtRoleMenu tm where tm.flag='1' and tm.id.roleId=:roleId", params);
		if(count>0){
			msg.append("有关联的菜单，请先删除关联菜单<br>");
		}
		count = db.queryByHqlCount("select count(*) from TXtRoleUser ru where ru.flag='1' and ru.id.roleId=:roleId", params);
		if(count>0){
			msg.append("有关联的用户，请先删除关联用户<br>");
		}
		if(msg.length()>0){
			this.message = msg.toString();
			return "success";
		}
		
		TXtRole pojo = getTXtRole(role.getRoleId() , db);
		pojo.setFlag("0");
		pojo.setUpdateTime(DateUtils.getTimestamp());
		db.update(pojo);
		return "success";
	}
	
	public String query(){
		DBUtil db = DBUtil.getInstance();
		StringBuffer sql = new StringBuffer("select role_id, role_name, role_type,create_time from t_xt_role where flag='1'");
		
		List params = new ArrayList(1);
		if(this.getQueryParams()!=null && this.getQueryParams().size()!=0){
			db.bind(sql, params, " and role_name like concat('%',?,'%') ", this.queryParams.get("roleName"));
			db.bind(sql, params, " and role_type= ? ", this.queryParams.get("roleType"));
		}
		
		this.query(sql+" order by create_time desc", params, db);
		int count = db.queryCountBySQL(sql.toString(), params);
		this.setTotal(count);
		return Action.SUCCESS;
	}
	
	public String comboboxRoles() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("flag", SysDict.FLAG_ACT);
		if(this.queryParams!=null){
			if(queryParams.get("roleType")!=null && !"".equals(queryParams.get("roleType"))){
				params.put("roleType", queryParams.get("roleType"));
			}
		}
		roles = DBUtil.getInstance().queryByPojo(TXtRole.class, params);
		return Action.SUCCESS;
	}
	
	public String querySingle(){
		DBUtil db = DBUtil.getInstance();
		this.role = getTXtRole(role.getRoleId(), db);
		return "success";
	}
	
	public static TXtRole getTXtRole(String roleId,DBUtil db){
		Map params = new HashMap(1);
		params.put("roleId", roleId);
		List list = db.queryByPojo(TXtRole.class, params);
		return list.size()==0?null:(TXtRole)list.get(0);
	}
	
	public TXtRole getRole() {
		return role;
	}

	public void setRole(TXtRole role) {
		this.role = role;
	}

	public String[] getTreeIds() {
		return treeIds;
	}

	public void setTreeIds(String[] treeIds) {
		this.treeIds = treeIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<TXtRole> getRoles() {
		return roles;
	}
}
