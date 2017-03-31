package com.sunchin.shop.admin.catePropPropval.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.catePropPropval.dao.CatePropPropvalDAO;
import com.sunchin.shop.admin.catePropPropval.service.CatePropPropvalService;
import com.sunchin.shop.admin.category.dao.CategoryDAO;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.pojo.ScCatePropPropval;
import com.sunchin.shop.admin.property.dao.PropPropValueDAO;

import framework.db.DBUtil;
import framework.util.CommonUtils;

@SuppressWarnings("rawtypes")
@Service("catePropPropvalService")
public class CatePropPropvalServiceImpl implements CatePropPropvalService {

	@Resource(name="catePropPropvalDAO")
	private CatePropPropvalDAO catePropPropvalDAO;
	@Resource(name="propPropValueDAO")
	private PropPropValueDAO propPropValueDAO;
	@Resource(name="categoryDAO")
	private CategoryDAO categoryDAO;
	
	/**
	 *	@param propPropValIds	所有选中的属性属性值关系
	 */
	public void saveCatePropPropVal(String cateId, String propPropValIds) throws Exception {
		List<Map> list = catePropPropvalDAO.queryCatePropPropValByCateId(cateId);//该类别对应的所有 类别-属性属性值关系
		//数据传入异常、传入和数据库都为空
		if(cateId==null || propPropValIds==null
				|| ("".equals(propPropValIds) && (list==null || list.isEmpty())) ) return;
		
		//如果分类-属性属性值关系有被引用就逻辑删除(商品录入可能会用到)
		
		//传入为空，全删
		if(propPropValIds.isEmpty()){
			this.delAllCatePropPropValue(cateId);
			return;
		}
		String[] arr = propPropValIds.split(",");
		//数据库为空，全增
		if(list==null || list.isEmpty()){
			for (int i = 0; i < arr.length; i++) {
				this.addCatePropPropValue(cateId, arr[i]);
			}
			return;
		}
		//传入和数据库都不为空（传入有，数据库无，增）
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].isEmpty() || arr[i]==null) continue;
			boolean addFlag = true;
			for (int j = 0; j < list.size(); j++) {
				String proppropvalId = CommonUtils.getString(list.get(j).get("propPropvalId"));
				if(arr[i].equals(proppropvalId)){
					addFlag = false;
					break;
				}
			}
			if(addFlag){
				this.addCatePropPropValue(cateId, arr[i]);
			}
		}
		//传入无，数据库有，删
		for (int i = 0; i < list.size(); i++) {
			boolean delFlag = true;
			String proppropvalId = CommonUtils.getString(list.get(i).get("propPropvalId"));
			for (int j = 0; j < arr.length; j++) {
				if(proppropvalId.equals(arr[j])){
					delFlag = false;
					break;
				}
			}
			if(delFlag){
				this.delCatePropPropValue(cateId, proppropvalId);
			}
		}
	}
	
	private void delAllCatePropPropValue(String cateId){
		catePropPropvalDAO.delAllCatePropPropValue(cateId);
	}
	
	private void delCatePropPropValue(String cateId, String proppropvalId){
		if(!proppropvalId.isEmpty() || proppropvalId!=null){
			catePropPropvalDAO.delCatePropPropValue(cateId, proppropvalId);
		}
	}
	
	private void addCatePropPropValue(String cateId, String ppvId){
		ScCatePropPropval catePropPropVal = new ScCatePropPropval();
		catePropPropVal.setId(UUID.randomUUID().toString());
		catePropPropVal.setCreateTime(new Date());
		catePropPropVal.setFlag(FlagEnum.ACT.getCode());
		catePropPropVal.setCateId(cateId);
		catePropPropVal.setPpvId(ppvId);
		//catePropPropVal.setOrders(i);
		DBUtil.getInstance().insert(catePropPropVal);
	}

	@Override
	public List queryListCheck(String cateId) throws Exception {
		List<ScCatePropPropval> list = catePropPropvalDAO.findPojo(cateId);
		return list;
	}

	/**
	 * 根据类别查询类别-属性-属性值关系 add by yangchaowen
	 * @param cateId
	 * @return
	 * @date 2017-03-30
	 */
	@Override
	public List queryByCateId(String cateId) throws Exception {
		return catePropPropvalDAO.queryByCateId(cateId);
	}

}
