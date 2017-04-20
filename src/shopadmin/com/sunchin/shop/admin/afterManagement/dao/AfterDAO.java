package com.sunchin.shop.admin.afterManagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sunchin.shop.admin.dict.DictionaryTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScBill;

import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.db.PageDAO;

@Repository("afterDAO")
public class AfterDAO extends PageDAO{

	public int queryBillCount(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.BILL_KIND.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.BILL_STATUS.getType());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return DBUtil.getInstance().queryCountBySQL(sql, params);
	}

	@SuppressWarnings("unchecked")
	public List<ScBill> queryBillPagination(PageBean pageBean) {
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.BILL_KIND.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.BILL_STATUS.getType());
		params.add(FlagEnum.ACT.getCode());
		String sql = this.buildWhereSql(pageBean, params);
		return this.query(sql, params, DBUtil.getInstance(), pageBean);
	}

	private String buildWhereSql(PageBean pageBean, List<String> params) {
		// 拼接查询条件
		StringBuffer sql = new StringBuffer(" select t1.id,t1.code,t1.name,t1.order_code,t1.reason,t1.content,t1.result,to_char(t1.create_time,'yyyy-mm-dd hh24:mi:ss') create_time,t2.name kind,t3.name bill_status,t1.create_user_id ");
		sql.append(" from sc_bill t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.kind ");
		sql.append(" left join sc_dictionary t3 on t3.code=t1.bill_status ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t2.type=? ");
		sql.append(" and t2.flag=? ");
		sql.append(" and t3.type=? ");
		sql.append(" and t3.flag=? ");
		if (pageBean.getQueryParams() != null && !pageBean.getQueryParams().isEmpty()) {
			String billType = pageBean.getQueryParams().get("billType");
			if (StringUtils.isNotBlank(billType)){
				params.add(billType);
				sql.append(" and t1.bill_type=? ");
			}
			String name = pageBean.getQueryParams().get("name");
			if (StringUtils.isNotBlank(name)){
				params.add(name+"%");
				sql.append(" and t1.name like ? ");
			}
			String kind = pageBean.getQueryParams().get("kind");
			if (StringUtils.isNotBlank(kind) && !"-1".equals(kind)){
				params.add(kind);
				sql.append(" and t1.kind=? ");
			}
			String code = pageBean.getQueryParams().get("code");
			if (StringUtils.isNotBlank(code)){
				params.add(code+"%");
				sql.append(" and t1.code like ? ");
			}
			String orderCode = pageBean.getQueryParams().get("orderCode");
			if (StringUtils.isNotBlank(orderCode)){
				params.add(orderCode+"%");
				sql.append(" and t1.order_code like ? ");
			}
			String startTime = pageBean.getQueryParams().get("startRegTime");
			if (StringUtils.isNotBlank(startTime)){
				params.add(startTime);
				sql.append(" and t1.create_time >= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
			String endTime = pageBean.getQueryParams().get("endRegTime");
			if (StringUtils.isNotBlank(endTime)){
				params.add(endTime+" 23:59:59 ");
				sql.append(" and t1.create_time <= to_date(?,'yyyy-MM-dd hh24:mi:ss')");
			}
		}
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryBillById(String id) {
		StringBuffer sql = new StringBuffer(" select t1.id,t1.name,t1.code,t2.name stsus,t1.order_code,t1.reason,t1.content,t1.result,to_char(t1.create_time, 'yyyy-mm-dd hh24:mi:ss') create_time,");
		sql.append(" t3.purchase_price,t3.done_price,t3.numbs,t4.goods_name,t4.goods_no,t6.user_name,t6.phone,t6.sex,t6.mail,t7.name kind ");
		sql.append(" from sc_bill t1 ");
		sql.append(" left join sc_dictionary t2 on t2.code=t1.bill_status ");
		sql.append(" left join sc_bill_tetail t3 on t1.code=t3.bill_code ");
		sql.append(" left join sc_goods t4 on t3.goods_id=t4.id ");
		sql.append(" left join sc_order t5 on t5.order_code=t1.order_code ");
		sql.append(" left join sc_user_base t6 on  t6.user_id=t5.user_id ");
		sql.append(" left join sc_dictionary t7 on t7.code=t1.kind ");
		sql.append(" left join sc_child_goods t8 on t8.id=t3.goods_detail_id ");
		sql.append(" where t1.flag=? ");
		sql.append(" and t1.id=? ");
		sql.append(" and t2.flag=? ");
		sql.append(" and t2.type=? ");
		sql.append(" and t5.flag=? ");
		sql.append(" and t7.type=? ");
		sql.append(" and t7.flag=? ");
		List<String> params = new ArrayList<String>();
		params.add(FlagEnum.ACT.getCode());
		params.add(id);
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.BILL_STATUS.getType());
		params.add(FlagEnum.ACT.getCode());
		params.add(DictionaryTypeEnum.BILL_KIND.getType());
		params.add(FlagEnum.ACT.getCode());
		List<Map<String,Object>> lists = DBUtil.getInstance().queryBySQL(sql.toString(),params);
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ScBill findBillById(String id){
		Map<String, Object> params = new HashMap<>();
		params.put("flag",FlagEnum.ACT.getCode());
		params.put("id",id);
		List<ScBill> lists = DBUtil.getInstance().queryByPojo(ScBill.class, params);
		if(lists != null && !lists.isEmpty()){
			return  lists.get(0);
		}
		return null;
	}
	
}
