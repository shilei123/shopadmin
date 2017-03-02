package framework.action.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import framework.action.FenyeBase;
import framework.db.DBUtil;
import framework.db.pojo.TXtWorkday;
import framework.util.DateUtils;
import common.Logger;

/**
 * 工作日管理
 * @author youka
 */
@SuppressWarnings({"unchecked","rawtypes","unused","deprecation"})
public class WorkdayAction extends FenyeBase{
	private final static Logger log = Logger.getLogger(WorkdayAction.class);
	private TXtWorkday workday;
	
	public String save(){
		DBUtil db = DBUtil.getInstance();
		
		TXtWorkday pojo = (TXtWorkday)db.get(TXtWorkday.class, workday.getDay());
		pojo.setWorkday(workday.getWorkday());
		pojo.setRemark(workday.getRemark());
		db.update(pojo);
		
		return "success";
	}
	
	public String list(){
		String date = this.queryParams.get("day").toString();
		workdayInit(date);
		
		DBUtil db = DBUtil.getInstance();
		String sql = "select day, workday, remark from T_Xt_Workday where day like ?";
		List params = new ArrayList(1);
		params.add(date.substring(0, 7)+"-%");
		
		this.rows = 32;
		this.total = db.queryCountBySQL(sql, params);
		this.query(sql, params, db);
		
		String firstDay = ((Map)this.getDataRows().get(0)).get("day").toString();
		Date date1 = DateUtils.parseDate(firstDay);
		int start = date1.getDay()==0?6:date1.getDay()-1;
		for (int i = 0; i < start; i++) {
			this.getDataRows().add(0, null);
		}
		
		return "success";
	}
	
	public void workdayInit(String date){
		//查询改年是否有数据，没有的加够全年数据
		DBUtil db = DBUtil.getInstance();
		TXtWorkday pojo = (TXtWorkday)db.get(TXtWorkday.class, date);
		if(pojo==null){
			int year = Integer.valueOf(date.substring(0,4));
			Date index = new Date(year-1900,1,1);
			Date endDate = new Date((year-1900+1),1,1);
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			while(index.getTime()<endDate.getTime()){
				TXtWorkday tmp = new TXtWorkday();
				tmp.setDay(dataFormat.format(index));
				tmp.setWorkday((index.getDay()==0||index.getDay()==6)?"0":"1");
				db.insert(tmp);
				index.setDate(index.getDate()+1);
				System.out.println(index.getTime()+"\t"+endDate.getTime()+"\t"+tmp.getDay());
			}
			//db.commit();
			
			System.out.println("end");
		}
	}

	public TXtWorkday getWorkday() {
		return workday;
	}

	public void setWorkday(TXtWorkday workday) {
		this.workday = workday;
	}
}
