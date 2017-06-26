package com.sunchin.shop.admin.freight.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.IsuseEnum;
import com.sunchin.shop.admin.freight.dao.FreightDAO;
import com.sunchin.shop.admin.freight.service.IFreightService;
import com.sunchin.shop.admin.pojo.ScExpressAndFreight;
import com.sunchin.shop.admin.pojo.ScFreight;
import com.sunchin.shop.admin.pojo.ScUserFreight;
import framework.bean.PageBean;
import framework.db.DBUtil;
import framework.util.CommonUtils;

@Repository("freightService")
public class FreightServiceImpl implements IFreightService{

	@Resource(name="freightDAO")
	private FreightDAO freightDAO;
	@Resource(name="dbUtil")
	private DBUtil db;
	
	/**
	 * 查询
	 */
	@Override
	public PageBean queryFreightList(PageBean pageBean) throws Exception {
		int total = freightDAO.queryFreightCount(pageBean);
		pageBean.setTotal(total);
		List<ScFreight> pageData = freightDAO.queryFreightPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 编辑
	 */
	@Override
	public List<Map<String, Object>> findFreightList(String id) throws Exception {
		List<Map<String, Object>> lists = freightDAO.findFreightList(id);
		return lists;
	}

	
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public void deleteFreight(String id) throws Exception {
		ScFreight freight =freightDAO.findFreight(id);
		freight.setFlag(FlagEnum.HIS.getCode());
		DBUtil.getInstance().update(freight);
	}

	/**
	 * 保存
	 */
	@Override
	@Transactional
	public void save(ScFreight fre, String childUserFreightList) throws Exception {
		if(StringUtils.isBlank(fre.getId())) {
			this.saveFre(fre,childUserFreightList);
		} else {
			this.update(fre,childUserFreightList);
		}
		
	}
	
	public void saveFre(ScFreight fre, String childUserFreightList){
		JSONArray childFreightArr = null;
		if(StringUtils.isNotBlank(childUserFreightList)){
			childFreightArr = JSON.parseArray(childUserFreightList);
		}
		
		DBUtil db = DBUtil.getInstance();
		String id = UUID.randomUUID().toString();
		fre.setId(id);
		fre.setIsuse(FlagEnum.HIS.getCode());
		fre.setCreateTime(new Date());
		fre.setFlag(FlagEnum.ACT.getCode());
		fre.setUnit(fre.getValuation());
		db.insert(fre);
		
		if(childFreightArr != null && !childFreightArr.isEmpty()) {
			List<ScUserFreight> childFreightList = new ArrayList<ScUserFreight>(childFreightArr.size());
			for(int i = 0; i < childFreightArr.size(); i++) {
	            JSONObject freightChildJson = (JSONObject) childFreightArr.get(i);
	            String mode = CommonUtils.getString(freightChildJson.get("mode"));
	            String range = CommonUtils.getString(freightChildJson.get("range"));
	            Double initialInt = CommonUtils.getDouble(freightChildJson.get("initialInt"));
	            Double initialPrice = CommonUtils.getDouble(freightChildJson.get("initialPrice"));
	            Double stackInt = CommonUtils.getDouble(freightChildJson.get("stackInt"));
	            Double stackPrice = CommonUtils.getDouble(freightChildJson.get("stackPrice"));
	            ScUserFreight childUserFreightVo = new ScUserFreight();
	            childUserFreightVo.setTransportMode(mode);
	            childUserFreightVo.setTransportRange(range);
	            childUserFreightVo.setInitialInt(initialInt);
	            childUserFreightVo.setInitialPrice(initialPrice);
	            childUserFreightVo.setStackInt(stackInt);
	            childUserFreightVo.setStackPrice(stackPrice);
	            childFreightList.add(childUserFreightVo);
			}
			
			for(int i=0;i<childFreightList.size();i++){
				ScUserFreight userFreight = new ScUserFreight();
				ScUserFreight  vo = (ScUserFreight) childFreightList.get(i);
				String ID = UUID.randomUUID().toString();
				String tranId = UUID.randomUUID().toString();
				userFreight.setId(ID);
				userFreight.setFreightId(id);
				userFreight.setTransportMode(vo.getTransportMode());
				userFreight.setTransportRange(vo.getTransportRange());
				userFreight.setInitialInt(vo.getInitialInt());
				userFreight.setInitialPrice(vo.getInitialPrice());
				userFreight.setStackInt(vo.getStackInt());
				userFreight.setStackPrice(vo.getStackPrice());
				userFreight.setTransportId(tranId);
				userFreight.setCreateTime(new Date());
				userFreight.setFlag(FlagEnum.ACT.getCode());
				DBUtil.getInstance().insert(userFreight);
			}
		}
	}
	
	public void update(ScFreight fre, String childUserFreightList){
		ScFreight vo = (ScFreight) DBUtil.getInstance().get(ScFreight.class, fre.getId());
		vo.setValuation(fre.getValuation());
		vo.setTemplateName(fre.getTemplateName());
		vo.setUnit(fre.getValuation());
		vo.setUpdateTime(new Date());
		DBUtil.getInstance().update(vo);
		
		JSONArray childFreightArr = null;
		if(StringUtils.isNotBlank(childUserFreightList)){
			childFreightArr = JSON.parseArray(childUserFreightList);
		}
		
		if(childFreightArr != null && !childFreightArr.isEmpty()) {
			List<ScUserFreight> childFreightList = new ArrayList<ScUserFreight>(childFreightArr.size());
			for(int i = 0; i < childFreightArr.size(); i++) {
	            JSONObject freightChildJson = (JSONObject) childFreightArr.get(i);
	            String mode = CommonUtils.getString(freightChildJson.get("mode"));
	            String range = CommonUtils.getString(freightChildJson.get("range"));
	            Double initialInt = CommonUtils.getDouble(freightChildJson.get("initialInt"));
	            Double initialPrice = CommonUtils.getDouble(freightChildJson.get("initialPrice"));
	            Double stackInt = CommonUtils.getDouble(freightChildJson.get("stackInt"));
	            Double stackPrice = CommonUtils.getDouble(freightChildJson.get("stackPrice"));
	            String userFreightId = CommonUtils.getString(freightChildJson.get("userFreightId"));
	            ScUserFreight childUserFreightVo = new ScUserFreight();
	            if(!userFreightId.equals("")){
	            	childUserFreightVo.setId(userFreightId);
	            }
	            childUserFreightVo.setTransportMode(mode);
	            childUserFreightVo.setTransportRange(range);
	            childUserFreightVo.setInitialInt(initialInt);
	            childUserFreightVo.setInitialPrice(initialPrice);
	            childUserFreightVo.setStackInt(stackInt);
	            childUserFreightVo.setStackPrice(stackPrice);
	            childFreightList.add(childUserFreightVo);
			}
			
			for(int i=0;i<childFreightList.size();i++){
				ScUserFreight userFreight = new ScUserFreight();
				ScUserFreight  vo1 = (ScUserFreight) childFreightList.get(i);
				if(vo1.getId() != null){
					ScUserFreight userFreights = freightDAO.findUserFreight(vo1.getId());
					userFreights.setTransportMode(vo1.getTransportMode());
					userFreights.setTransportRange(vo1.getTransportRange());
					userFreights.setInitialInt(vo1.getInitialInt());
					userFreights.setInitialPrice(vo1.getInitialPrice());
					userFreights.setStackInt(vo1.getStackInt());
					userFreights.setStackPrice(vo1.getStackPrice());
					userFreights.setUpdateTime(new Date());
					DBUtil.getInstance().update(userFreights);
				}else{
					String ID = UUID.randomUUID().toString();
					String tranId = UUID.randomUUID().toString();
					userFreight.setId(ID);
					userFreight.setFreightId(fre.getId());
					userFreight.setTransportMode(vo1.getTransportMode());
					userFreight.setTransportRange(vo1.getTransportRange());
					userFreight.setInitialInt(vo1.getInitialInt());
					userFreight.setInitialPrice(vo1.getInitialPrice());
					userFreight.setStackInt(vo1.getStackInt());
					userFreight.setStackPrice(vo1.getStackPrice());
					userFreight.setTransportId(tranId);
					userFreight.setCreateTime(new Date());
					userFreight.setFlag(FlagEnum.ACT.getCode());
					DBUtil.getInstance().insert(userFreight);
				}
			}
		}
		
	}

	/**
	 * 删除运费子表
	 */
	@Override
	@Transactional
	public void delUserFreight(String id) throws Exception {
		ScUserFreight userFreight = freightDAO.findUserFreight(id);
		userFreight.setFlag(FlagEnum.HIS.getCode());
		DBUtil.getInstance().update(userFreight);
	}

	/**
	 * 是否默认
	 */
	@Override
	@Transactional
	public void saveIsuse(String id) throws Exception {
		List<ScFreight> lsits = freightDAO.findFreightIsuse();
		if(lsits != null && !lsits.isEmpty()){
		   for(int i=0;i<lsits.size();i++){
			   ScFreight freight  = lsits.get(i);
			   freight.setIsuse(IsuseEnum.INVALID.getCode());
			   DBUtil.getInstance().update(freight);
		   }
		}
		if(id != null && !id.equals("")){
			ScFreight freights = freightDAO.findFreight(id);
			freights.setIsuse(IsuseEnum.VALID.getCode());
			DBUtil.getInstance().update(freights);
		}
	}

	/**
	 * 查询服务商列表
	 */
	@Override
	public PageBean queryFreAndExpList(PageBean pageBean) throws Exception {
		int total = freightDAO.queryFreAndExpCount(pageBean);
		pageBean.setTotal(total);
		List<ScFreight> pageData = freightDAO.queryFreAndExpPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 查询选中的服务商、运费关系
	 */
	@Override
	public List<Map<String, Object>> queryFreAndExpCheck(PageBean pageBean) throws Exception {
		String freightId = CommonUtils.getString(pageBean.getQueryParams().get("freightId"));
		List<Map<String, Object>> lists = freightDAO.queryFreAndExpByFreightId(freightId);
		if(lists != null && !lists.isEmpty()){
			return lists;
		}
		return null;
	}

	/**
	 * 保存服务商、运费关系
	 */
	@Override
	public void saveFreightAndExpress(PageBean pageBean) throws Exception {
		String freightId = CommonUtils.getString(pageBean.getQueryParams().get("freightId"));
		String checkExpressIds = CommonUtils.getString(pageBean.getQueryParams().get("checkExpressIds"));
		List<Map<String, Object>> list = freightDAO.queryFreAndExpByFreightId(freightId);//查询和运费相关的所有服务商
		if( checkExpressIds==null || freightId==null
				|| ("".equals(checkExpressIds) && (list==null || list.isEmpty())) ) {//异常情况
			return;
		}
		
		//这里待补充：需要查询该条运费-服务商关系是否被引用， 如果被引用就逻辑删除
		
		
		//传入为空，全删
		if("".equals(checkExpressIds)){
			for (int i = 0; i < list.size(); i++) {
				String expressId = CommonUtils.getString(list.get(i).get("expressId"));
				this.delFreAndExp(freightId, expressId);
			}
			return;
		}
		//结果集为空，全加
		String[] arr = checkExpressIds.split(",");
		if(list==null || list.isEmpty()){
			for (int i = 0; i < arr.length; i++) {
				this.addFreAndExp(freightId, arr[i]);
			}
			return;
		}
		//传入和结果集都不为空（传入有，结果集无）
		for (int i = 0; i < arr.length; i++) {
			boolean addFlag = true;
			for (int j = 0; j < list.size(); j++) {
				String expressId = CommonUtils.getString(list.get(j).get("expressId"));
				if(arr[i].equals(expressId)){
					addFlag = false;
					break;
				}
			}
			if(addFlag){
				this.addFreAndExp(freightId, arr[i]);
			}
		}
		//传入和结果集都不为空（传入无，结果集有）
		for (int i = 0; i < list.size(); i++) {
			boolean delFlag = true;
			String expressId = CommonUtils.getString(list.get(i).get("expressId"));
			for (int j = 0; j < arr.length; j++) {
				if(expressId.equals(arr[j])){
					delFlag = false;
					break;
				}
			}
			if(delFlag){
				this.delFreAndExp(freightId, expressId);
			}
		}
		
	}

	private void addFreAndExp(String freightId, String expressId) {
		ScExpressAndFreight FreAndExp = new ScExpressAndFreight();
		FreAndExp.setId(UUID.randomUUID().toString());
		FreAndExp.setFreightId(freightId);
		FreAndExp.setExpressId(expressId);
		FreAndExp.setCreateTime(new Date());
		FreAndExp.setFlag(FlagEnum.ACT.getCode());
		db.insert(FreAndExp);
	}

	private void delFreAndExp(String freightId, String expressId) {
		freightDAO.delFreAndExp(freightId,expressId);
	}
}
