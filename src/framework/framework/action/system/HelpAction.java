package framework.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.action.FenyeBase;
import framework.db.DBUtil;
import framework.db.pojo.TXtHelp;
/*import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;*/

import com.opensymphony.xwork2.Action;

@SuppressWarnings({"unchecked","rawtypes"})
public class HelpAction extends FenyeBase{
	private String menuId;
	private String url;
	private TXtHelp pojo;
	
	public String queryHelpUrl(){
		//TXtUser curruser = (TXtUser)RequestHelper.getSession().getAttribute("user");
		DBUtil db = DBUtil.getInstance();
		Map params = new HashMap(2);
		params.put("id.menuId", menuId);
		params.put("id.userType", "default");//暂时是默认的，以后就使用当前用户类型
		List<TXtHelp> list = db.queryByPojo(TXtHelp.class, params);
		if(list!=null && list.size()>0){
			//url = list.get(0).getUrl();
			this.pojo = list.get(0);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存帮助
	 * @return
	 */
	public String saveHelp(){
		DBUtil db = DBUtil.getInstance();
		TXtHelp tmp = (TXtHelp)db.get(TXtHelp.class, pojo.getId());
		if(tmp==null){
			db.insert(pojo);
		}else{
			tmp.setContent(pojo.getContent());
			db.update(tmp);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 删除帮助
	 * @return
	 */
	public String deleteHelp(){
		DBUtil db = DBUtil.getInstance();
		db.delete(pojo);
		return Action.SUCCESS;
	}
	
	/**
	 * 查询帮助
	 * @return
	 */
	public String findHelps(){
		String sql = "select * from t_xt_help h where 1=1 ";
		ArrayList params = new ArrayList(2);
		if(this.queryParams!=null ){
			
		}
		this.query(sql, params);
		return Action.SUCCESS;
	}
	
	/**
	 * 查询帮助
	 * @return
	 */
	public String findHelp(){
		DBUtil db = DBUtil.getInstance();
		this.pojo = (TXtHelp)db.get(TXtHelp.class, pojo.getId());
		return Action.SUCCESS;
	}

	public String save(){
		return Action.SUCCESS;
	}
	
	public String delete(){
		return Action.SUCCESS;
	}
	
	public String getUrl() {
		return url;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public TXtHelp getPojo() {
		return pojo;
	}

	public void setPojo(TXtHelp pojo) {
		this.pojo = pojo;
	}
	
	
	
}
