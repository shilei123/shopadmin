package framework.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;

import framework.bean.UserMsg;
import framework.config.SysDict;
import framework.db.DBUtil;
import framework.db.pojo.TAuditLog;
import framework.db.pojo.TXtEmp;
import framework.db.pojo.TXtOrg;
import framework.db.pojo.TXtRoleUser;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.logger.AuditLogger;
import framework.util.WorkDayUtils;

@SuppressWarnings("unchecked")
public class LoginAction {
	private String code;
	private TXtUser user;
	private boolean success;
	private String msg;
	private String pcUserInfo;
	private String verifyCode;
	private AuditLogger logger = AuditLogger.getLogger();
	@Autowired
	private DBUtil dbUtil;
	@Autowired
	private WorkDayUtils workDayUtils;
	
	/**
	 * 用户退出登录 清理session
	 * @return
	 */
	public String userOut(){
		RequestHelper.getSession().removeAttribute("user");
		return Action.SUCCESS; 
	}
	
	/**
	 * 用户登录验证
	 * @return
	 */
	public String checkUser() {
		//获取验证码
		/*Object sessionVerifyCode = RequestHelper.getSession().getAttribute("VerifyCode");
		if(!verifyCode.equals(sessionVerifyCode)){
			this.msg = "验证码错误";
			return Action.SUCCESS;
		}*/
		//System.out.println(workDayUtils.getNextWorkDay("20170126", 5));
		//查询用户，比对密码
		UserMsg usermsg = queryLoginUserMsg(user.getUId(), dbUtil);
		if(usermsg != null && usermsg.getUPwd().equals(user.getUPwd())) {
			usermsg.setUPwd("");
			RequestHelper.getSession().setAttribute("user", usermsg); //当前操作用户信息
			this.success = true;
		} else {
			this.msg = "用户名或密码错误";
			if(usermsg!=null){
				usermsg.setUPwd("");
			}
		}
		TAuditLog message = new TAuditLog(user.getUId(), "登录成功！");
		logger.info(message);
		return Action.SUCCESS; 
	}
	
	/**
	 * 查询登录用户信息
	 * @param swdm
	 * @param db
	 * @return
	 */
	public static UserMsg queryLoginUserMsg(String uid, DBUtil db){
		Map<String, String> params = new HashMap<String, String>(1);
		params.put("userId", uid);
		List<TXtEmp> empList = db.queryByPojo(TXtEmp.class, params);
		if(empList == null || empList.isEmpty()) {
			return null;
		}
		
		//员工信息
		TXtEmp emp = empList.get(0);
		TXtOrg dept = emp.getOrg();
		if(dept == null){
			dept = new TXtOrg();
		}
		
		//上级机构信息
		TXtOrg org = null;
		if(dept.getParentOrgId() != null && !"".equals(dept.getParentOrgId())) {
			params = new HashMap<String, String>(1);
			params.put("orgId", dept.getParentOrgId());
			List<TXtOrg> orgList = db.queryByPojo(TXtOrg.class, params);
			if(orgList != null && !orgList.isEmpty()){
				org = orgList.get(0);
			} else { //root机构的时候查不到机构信息
				org = new TXtOrg();
			}
		} else {
			org = new TXtOrg();
		}
		
		//角色信息
		Map<String, String> role = new HashMap<String, String>();
		params = new HashMap<String, String>(2);
		params.put("id.UId", uid);
		params.put("flag", SysDict.FLAG_ACT);
		List<TXtRoleUser> roleUserList = db.queryByPojo(TXtRoleUser.class, params);
		for(TXtRoleUser t : roleUserList) {
			role.put(t.getId().getRoleId(), t.getId().getRoleId());
		}
		
		UserMsg user = new UserMsg();
		user.setUId(emp.getUserId());
		user.setUPwd(emp.getUserPwd());
		user.setUName(emp.getUserName());
		user.setDeptId(dept.getId());
		user.setDeptName(dept.getOrgName());
		user.setDeptShortName(dept.getShortName());
		user.setOrgPath(dept.getOrgPath());
		user.setOrgLevel(dept.getOrgLevel());//直属的机构等级
		user.setParentOrgRowId(org.getId());
		user.setParentOrgId(org.getOrgId());
		user.setParentOrgName(org.getOrgName());
		//机构等级等于0时，代表是根节点，则机构id和部门id取一致。
		if(org.getOrgLevel()==0) {
			user.setOrgId(dept.getId());
			user.setOrgOrgId(dept.getOrgId());
			user.setOrgName(dept.getOrgName());
			user.setOrgShortName(dept.getShortName());
		} else {
			user.setOrgId(org.getId());
			user.setOrgOrgId(org.getOrgId());
			user.setOrgName(org.getOrgName());
			user.setOrgShortName(org.getShortName());
		}
		
		user.setOrgCode(dept.getOrgId());
		
		//优先取部门代码
		if(dept.getOrgCode()!=null && !"".equals(dept.getOrgCode())) {
			user.setOrgCode(dept.getOrgCode());
		} else {
			if(org.getOrgCode()!=null && !"".equals(org.getOrgCode())) {
				user.setOrgCode(org.getOrgCode());
			} 
		}
		user.setRole(role);
		return user;
	}
	
	public String getMsg() {
		return msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public TXtUser getUser() {
		return user;
	}
	
	public void setUser(TXtUser user) {
		this.user = user;
	}
	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getPcUserInfo() {
		return pcUserInfo;
	}
}