package framework.action.system;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Iterator;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import framework.action.FenyeBase;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TPbUploadfile;
import framework.db.pojo.TXtEmp;
import framework.db.pojo.TXtPosition;
import framework.db.pojo.TXtRoleUser;
import framework.db.pojo.TXtRoleUserId;
import framework.db.pojo.TXtUser;
import framework.helper.CachedHelper;
import framework.helper.RequestHelper;

import com.opensymphony.xwork2.Action;

@SuppressWarnings({"unchecked","rawtypes"})
public class UserAction extends FenyeBase {
	private static final Logger log = Logger.getLogger(UserAction.class);
	private TXtRoleUser roleUser;
	private TXtUser xtuser;
	private TXtEmp user;
	private List<TXtPosition> positions;
	private String roleUserMsg = null;
	private String message;
	private static final String SQL_USER = new StringBuffer("select u.*,e.id, o.org_name,o.org_path, p.position_name,e.validate_domain,e.validate_ip")
										   .append(" from t_xt_user u ")
										   .append(" left join t_xt_emp e on e.user_id=u.u_id ")
										   .append(" left join t_xt_org o on o.id=e.org_id")
										   .append(" left join t_xt_position p on p.position_id=e.position_id where 1=1").toString();
	
	private static final String SQL_ROLE_USER = new StringBuffer("select u.u_id, u.u_name, u.lr_sj, ru.role_id, o.org_name")
												/*.append(", replace(o.org_path,'").append(SysDict.ORG_NAME).append("/') org_path")（*/
												.append(", p.position_name")
												.append(" from t_xt_user u left join t_xt_role_user ru on u.u_id=ru.u_id")
												.append(" left join t_xt_emp e on e.user_id=u.u_id ")
												.append(" left join t_xt_org o on o.id=e.org_id")
												.append(" left join t_xt_position p on p.position_id=e.position_id")
												.append(" where ru.flag='").append(SysDict.FLAG_ACT).append("' and ru.role_id=?").toString();
	
	public String queryUserList() {
		StringBuffer sql = new StringBuffer(SQL_USER);
		DBUtil db = DBUtil.getInstance();
		
		sql.append(" and u.flag='1' ");
		List params = new ArrayList(1);
		if(this.getQueryParams()!=null && this.getQueryParams().size()!=0){
			String uname = this.getQueryParams().get("userName");
			if(StringUtils.isNotBlank(uname)) {
				params.add("%"+uname+"%");
				sql.append(" and u.u_name like ?");
			}
			String uid = this.getQueryParams().get("userId");
			if(StringUtils.isNotBlank(uid)) {
				params.add("%"+uid+"%");
				sql.append(" and u.u_id like ?");
			}
			String roleId = this.getQueryParams().get("roleId");
			if(StringUtils.isNotBlank(roleId)) {
				params.add(SysDict.FLAG_ACT);
				params.add(roleId);
				sql.append("and not exists(select * from t_xt_role_user ru where u.u_id=ru.u_id and ru.flag=? and ru.role_Id=?)");
			}
			String positionId = this.getQueryParams().get("positionId");
			if(StringUtils.isNotBlank(positionId)) {
				params.add(positionId);
				sql.append("and e.position_id=?");
			}
			String validateIp = this.getQueryParams().get("validateIp");
			if(StringUtils.isNotBlank(validateIp)) {
				params.add(validateIp);
				sql.append("and instr(e.validate_ip,?)>0");
			}
			DBUtil.sqlAdd(sql, params, "o.org_id", queryParams.get("orgId"));
		}
		
		this.query(sql.toString()+" order by u.create_time desc ", params, db);
		
		int count = db.queryCountBySQL(sql.toString(), params);
		this.setTotal(count);		
		return Action.SUCCESS;
	}
	
	public String queryUserEmp() {
		DBUtil db = DBUtil.getInstance();
		this.user = this.queryTXtEmp(db);
		return Action.SUCCESS;
	}
	
	/**
	 * 编辑前 
	 */
	public String preEditEmp() {
		DBUtil db = DBUtil.getInstance();
		this.user = this.queryTXtEmp(db);
		this.positions = db.queryByPojo(TXtPosition.class);
		return Action.SUCCESS;
	}
	
	/**
	 * 新增前
	 */
	public String preAddEmp() {
		return this.queryPositions();
	}
	
	public String queryPositions() {
		DBUtil db = DBUtil.getInstance();
		this.positions = db.queryByPojo(TXtPosition.class);
		return Action.SUCCESS;
	}
	
	private TXtEmp queryTXtEmp(DBUtil db) {
		List<TXtEmp> list = DBUtil.getInstance().queryByHql("from TXtEmp as emp left outer join fetch emp.org left outer join fetch emp.position where emp.userId='"+user.getUserId()+"'");
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	private TXtUser queryTXtUser(DBUtil db) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("UId", user.getUserId());
		List<TXtUser> list = db.queryByPojo(TXtUser.class, params);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	public String save() {
		DBUtil db = DBUtil.getInstance();
		//db.beginTransaction();
		if(user.getId() == null || "".equals(user.getId())) {
			String id = UUID.randomUUID().toString();
			this.user.setId(id);
			//this.user.setUserId(id);
			this.user.setUserPwd(SysDict.INIT_PWD);
			db.insert(this.user);
			
			TXtUser newXtUser = new TXtUser();
			newXtUser.setUId(this.user.getUserId());
			newXtUser.setUName(this.user.getUserName());
			newXtUser.setUPwd(SysDict.INIT_PWD);
			db.insert(newXtUser);
		} else {
		 	TXtEmp pojo = queryTXtEmp(db);
			pojo.setUserName(this.user.getUserName());
			pojo.setOrgId(this.user.getOrgId());
			pojo.setWorkAddr(this.user.getWorkAddr());
			pojo.setTelphone(this.user.getTelphone());
			pojo.setMobile(this.user.getMobile());
			pojo.setFax(this.user.getFax());
			pojo.setSex(this.user.getSex());
			pojo.setEmail(this.user.getEmail());
			pojo.setRemark(this.user.getRemark());
			pojo.setPositionId(this.user.getPositionId());
			pojo.setValidateDomain(this.user.getValidateDomain());
			pojo.setValidateIp(this.user.getValidateIp());
			pojo.setPcusername(this.user.getPcusername());
			db.update(pojo);
			
			TXtUser u = this.queryTXtUser(db);
			u.setUName(this.user.getUserName());
			db.update(u);
		}
		//db.commit();
		CachedHelper.removeCachedTableMap("t_xt_user");
		CachedHelper.removeCachedTableMap("t_xt_emp");
		return Action.SUCCESS;
	}
	
	public String queryRoleUser() {
		StringBuffer sql = new StringBuffer(SQL_ROLE_USER);
		sql.append(" and u.flag='1' ");
		List params = new ArrayList();
		if(roleUser==null || roleUser.getId()==null || roleUser.getId().getRoleId()==null){
			//sql = new StringBuffer(SQL_USER);
			//params.add(roleUser.getId().getRoleId());
			this.setDataRows(new ArrayList());
		} else {
			params.add(roleUser.getId().getRoleId());
			this.query(sql.toString(), params);
			DBUtil db = DBUtil.getInstance();
			int count = db.queryCountBySQL(sql.toString(), params);
			this.setTotal(count);
		}
		return Action.SUCCESS;
	}
	
	public String userSessionMsg(){
		Object obj = RequestHelper.getSession().getAttribute("user");
		if(obj != null){
			this.xtuser = (TXtUser)obj;
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询数据库用户信息
	 * @param uid
	 * @param db
	 */
	public TXtUser queryXtUser(){
		Map params = new HashMap(1);
		params.put("UId", xtuser.getUId());
		List<TXtUser> list = DBUtil.getInstance().queryByPojo(TXtUser.class, params);
		return list.size()>0?list.get(0):null;
	}
	
	public TXtEmp queryUser(){
		Map params = new HashMap(1);
		params.put("UId", user.getUserId());
		List<TXtEmp> list = DBUtil.getInstance().queryByPojo(TXtEmp.class, params);
		return list.size()>0?list.get(0):null;
	}
	
	public String insert(){
		roleUserMsg = "";
		String uid = RequestHelper.getParameter("uId");
		String roleId = RequestHelper.getParameter("roleId");
		if(uid != null && !"".equals(uid) && roleId != null && !"".equals(roleId)) {
			DBUtil db = DBUtil.getInstance();
			String[] ids = uid.split(",");
			for(String id : ids){
				List list = db.queryBySQL("select t.* from t_xt_role_user t where t.u_id=? and t.role_id=?", id, roleId);
				if(list != null && !list.isEmpty()) {
					//Map vo = (Map)list.get(0);
					
					/*int cou = */db.executeSQL("update t_xt_role_user t set t.flag=?,t.update_time=sysdate where t.role_id=? and u_id=?",SysDict.FLAG_ACT, roleId,id);
				} else {
					TXtRoleUserId ruid = new TXtRoleUserId();
					ruid.setRoleId(roleId);
					ruid.setUId(id);
					TXtRoleUser ru = new TXtRoleUser();
					ru.setId(ruid);
					db.insert(ru);
				}
			}
			roleUserMsg = "ok";
		}
		/*if(this.xtuser!=null){
			DBUtil.getInstance().insert(this.xtuser);
		}*/
		return Action.SUCCESS;
	}
	
	public String delete(){
		roleUserMsg = "";
		String uid = RequestHelper.getParameter("uId");
		String roleId = RequestHelper.getParameter("roleId");
		if(uid != null && !"".equals(uid) && roleId != null && !"".equals(roleId)) {
			String[] ids = uid.split(",");
			for(String id : ids){
				DBUtil db = DBUtil.getInstance();
				/*int cou = */db.executeSQL("update t_xt_role_user t set t.flag=?,t.update_time=sysdate where t.role_id=? and u_id=?",SysDict.FLAG_HIS, roleId,id);
			}
			roleUserMsg = "ok";
		}
		/*if(this.xtuser!=null){
			DBUtil.getInstance().delete(this.xtuser);
		}*/
		return Action.SUCCESS;
	}
	
	public String deleteUser(){
		DBUtil db = DBUtil.getInstance();
		String uids = RequestHelper.getParameter("uId");
		String[] uidArr = uids.split(",");
		for(String uid : uidArr){
			TXtUser user = (TXtUser)db.get(TXtUser.class, uid);
			HashMap params = new HashMap();
			params.put("userId", uid);
			List<TXtEmp> emps = db.queryByPojo(TXtEmp.class, params);
			TXtEmp emp = null;
			if(emps!=null && !emps.isEmpty()) {
				emp = (TXtEmp) emps.get(0);
			}
			
			if("admin".equals(uid)){
				this.message = "管理员不可删除";
				return Action.SUCCESS;
			}
			
			HashMap params2 = new HashMap();
			params2.put("id.UId", uid);
			params2.put("flag", SysDict.FLAG_ACT);
			List<TXtRoleUser> roles = db.queryByPojo(TXtRoleUser.class,params2);
			for (TXtRoleUser ru : roles) {
				ru.setFlag(SysDict.FLAG_HIS);
				db.update(ru);
			}
			
			if(emp!=null) {
				emp.setFlag(SysDict.FLAG_HIS);
				db.update(emp);
			}
			user.setFlag(SysDict.FLAG_HIS);
			db.update(user);
		}
		this.message="success";
		return Action.SUCCESS;
	}
	
	/**
	 * 导入用户列表
	 * @return
	 */
	public String importUsers(){
		DBUtil db = DBUtil.getInstance();
		String fileId = RequestHelper.getParameter("fileId");
		TPbUploadfile pojo = (TPbUploadfile)db.get(TPbUploadfile.class, fileId);
		
		StringBuffer msg = new StringBuffer("");
		int rowIndex=0;
		try {
			//机构与职位 map
			//Map<String, Map> orgMap = db.listToMap(db.queryBySQL("select o.id,o.org_name,o.org_path from t_xt_org o"),"orgName");
			List orgList = db.queryBySQL("select o.id,o.org_name,o.org_path from t_xt_org o");
			Map<String, Map> positionMap = DBUtil.listToMap(db.queryBySQL("select p.position_id id,position_name from t_xt_position p"),"positionName");
			
			String[][] data = getExcelData(pojo.getFilePath());
			//0姓名	1单位	2地址	3电话	4手机	5传真	6电子邮箱	7备注	 8职务   9域  10IP
			for(;rowIndex<data.length;rowIndex++){
				try {
					String[] row = data[rowIndex];
					TXtUser user = new TXtUser();
					user.setUId(UUID.randomUUID().toString()); //用户id
					user.setUName(row[0]); //姓名
					user.setUPwd("123456"); //密码
					user.setURemark(row[8]); //描述
					db.insert(user);
					
					TXtEmp emp  = new TXtEmp();
					emp.setId(UUID.randomUUID().toString());
					emp.setUserId(user.getUId()); 
					emp.setUserPwd(user.getUPwd()); 
					emp.setUserName(user.getUName()); 
					emp.setWorkAddr(row[3]); //地址
					emp.setTelphone(row[4]); //工作电话
					emp.setMobile(row[5]); //手机号码
					emp.setFax(row[6]); //传真
					emp.setSex("");
					emp.setEmail(row[7]); //邮箱
					emp.setRemark("");
					emp.setOrgPath("");
					emp.setPositionName("");
					emp.setSexLabel(""); 
					emp.setValidateDomain(row[10]); //域名（主机名）
					emp.setValidateIp(row[11]); //ip
					emp.setPcusername(row[12]); //主机账户
					
					//if(orgMap.get(row[2])==null){
					String pOrgName = row[1]; //上级机构名称
					String orgName = row[2]; //机构名称
					Map orgMap = this.getOrgMap(orgList, pOrgName, orgName);
					if(orgMap == null) {
						msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:找不到匹配的部门<br>");
						continue;
					}
					emp.setOrgId(orgMap.get("id").toString());
					
					if(positionMap.get(row[9])==null){
						msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:找不到匹配的职位<br>");
						continue;
					}
					emp.setPositionId(positionMap.get(row[9]).get("id").toString());
					
					db.insert(emp);
				} catch (Exception e1) {
					msg.append("插入数据异常，发生在"+(rowIndex+2)+"行，异常信息:"+e1.getMessage()+"<br>");
					log.error("",e1);
				}
			}
		} catch (Exception e) {
			msg.append(e.getMessage());
			log.error("",e);
		}
		if(msg.length()>0){
			//db.rollback();
			msg.append("数据库已回滚导入操作!!!<br>");
		}
		try {
			RequestHelper.wirte(msg.toString());
		} catch (IOException e) {
			log.error("",e);
		}
		CachedHelper.removeCachedTableMap("t_xt_user");
		CachedHelper.removeCachedTableMap("t_xt_emp");
		return null;
	}
	
	/**
	 * 获取excel数据
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String[][] getExcelData(String filePath) throws Exception{
		FileInputStream fis = new FileInputStream(filePath);
		String[][] data = null;
		int rowIndex=0, colIndex = 0;
		try {
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			data = new String[rowCount-1][];
			for (int i = 1; i < rowCount; i++) {
				rowIndex = i+1;
				HSSFRow row = sheet.getRow(i);
				//int colCount = row.getLastCellNum();
				int colCount = 13; //固定位13列
				String[] rowdata = new String[colCount];
				for(int j=0;j<colCount;j++){
					colIndex  = j+1;
					if(row.getCell(j)!=null){
						row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
						rowdata[j] = row.getCell(j).getStringCellValue();
					}else{
						rowdata[j] = "";
					}
				}
				data[i-1] = rowdata;
			}
		}catch(Exception e){
			log.error("",e);
			throw new Exception("解析Excel异常，错误发生在"+rowIndex+"行,"+colIndex+"列<br>");
		}finally{
			fis.close();
		}
		return data;
	}

	public Map getOrgMap(List orgList,String pOrgName, String orgName) {
		String tempOrgName = pOrgName+"/"+orgName;
		Iterator iter = orgList.iterator();
		while(iter.hasNext()) {
			Map map = (Map) iter.next();
			String orgPath = map.get("orgPath")+"";
			if(orgPath.indexOf(tempOrgName) > -1) {
				return map;
			}
		}
		return null;
	}
	
	public TXtUser getXtuser() {
		return xtuser;
	}

	public void setXtuser(TXtUser xtuser) {
		this.xtuser = xtuser;
	}

	public TXtEmp getUser() {
		return user;
	}

	public void setUser(TXtEmp user) {
		this.user = user;
	}

	public TXtRoleUser getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(TXtRoleUser roleUser) {
		this.roleUser = roleUser;
	}
	
	public List<TXtPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<TXtPosition> positions) {
		this.positions = positions;
	}
	
	public String getRoleUserMsg() {
		return roleUserMsg;
	}

	public void setRoleUserMsg(String roleUserMsg) {
		this.roleUserMsg = roleUserMsg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
