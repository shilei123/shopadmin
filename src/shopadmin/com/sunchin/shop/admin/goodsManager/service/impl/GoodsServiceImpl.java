package com.sunchin.shop.admin.goodsManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunchin.shop.admin.dict.AuditStsEnum;
import com.sunchin.shop.admin.dict.BusiTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.GoodsStsEnum;
import com.sunchin.shop.admin.dict.PublishTypeEnum;
import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;
import com.sunchin.shop.admin.goodsManager.dao.ScChildGoodsDAO;
import com.sunchin.shop.admin.goodsManager.dao.ScGoodsCatePropPropValDAO;
import com.sunchin.shop.admin.goodsManager.dao.ScGoodsDAO;
import com.sunchin.shop.admin.goodsManager.dao.ScGoodsImageDAO;
import com.sunchin.shop.admin.goodsManager.dao.ScImageUseRecDAO;
import com.sunchin.shop.admin.goodsManager.dao.ScRepertoryDAO;
import com.sunchin.shop.admin.goodsManager.service.GoodsService;
import com.sunchin.shop.admin.pojo.ScChildGoods;
import com.sunchin.shop.admin.pojo.ScGoods;
import com.sunchin.shop.admin.pojo.ScGoodsCatePropPropVal;
import com.sunchin.shop.admin.pojo.ScGoodsImage;
import com.sunchin.shop.admin.pojo.ScImageUseRec;
import com.sunchin.shop.admin.pojo.ScRepertory;

import demo.bankManager.pojo.TBankInfo;
import framework.bean.PageBean;
import framework.bean.UserMsg;
import framework.db.DBUtil;
import framework.helper.RequestHelper;
import framework.util.CommonUtils;
import framework.util.DateUtils;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Resource(name = "scGoodsDAO")
	private ScGoodsDAO goodsDAO;
	@Resource(name = "scGoodsImageDAO")
	private ScGoodsImageDAO goodsImageDAO;
	@Resource(name = "scChildGoodsDAO")
	private ScChildGoodsDAO childGoodsDAO;
	@Resource(name = "scGoodsCatePropPropValDAO")
	private ScGoodsCatePropPropValDAO goodsCatePropPropValDAO;
	@Resource(name = "scRepertoryDAO")
	private ScRepertoryDAO repertoryDAO;
	@Resource(name = "scImageUseRecDAO")
	private ScImageUseRecDAO imageUseRecDAO;
	
	/**
	 * 保存商品
	 * @param goods
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void saveGoods(GoodsBean goodsBean) throws Exception {
		this.saveOrUpdate(goodsBean);
	}
	
	/**
	 * 分页查询商品
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean queryGoods(PageBean pageBean) throws Exception {
		int total = goodsDAO.queryGoodsCount(pageBean);
		pageBean.setTotal(total);
		List<TBankInfo> pageData = goodsDAO.queryGoodsPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}
	
	private void saveOrUpdate(GoodsBean goodsBean) {
		/**********************1. 拼装VO对象***************************/
		//商品VO
		ScGoods goodsVo = this.buildGoodsVo(goodsBean);
		List<ScChildGoods> childGoodsList = null; //子商品VO
		List<ScRepertory> repList = null; //库存VO
		List<ScGoodsCatePropPropVal> gcppList = null; //商品、类别属性属性值关系VO
		List<ScGoodsImage> goodsImageList = null; //商品图片vo
		List<ScImageUseRec> imageUseRecList = null; //图片资源占用vo
		
		JSONArray childGoodsArr = null;
		if(StringUtils.isNotBlank(goodsBean.getChildGoods())) {
			childGoodsArr = JSON.parseArray(goodsBean.getChildGoods());
		}
		
		if(childGoodsArr != null && !childGoodsArr.isEmpty()) {
			childGoodsList = new ArrayList<ScChildGoods>(childGoodsArr.size());
			repList = new ArrayList<ScRepertory>(childGoodsArr.size());
            gcppList = new ArrayList<ScGoodsCatePropPropVal>();
            
            //循环子商品
			for(int i = 0; i < childGoodsArr.size(); i++) {
	            JSONObject goodsChildJson = (JSONObject) childGoodsArr.get(i);
	            Integer availableNum = CommonUtils.getInteger(goodsChildJson.get("availableNum"));
	            String cppvStr = CommonUtils.getString(goodsChildJson.get("cppvStr"));
	            String[] cppvInfos = cppvStr.split("_"); //页面对应的子商品ID
	            
	            //子商品VO
	            ScChildGoods childGoodsVo = this.buildChildGoodsVo(goodsVo, goodsChildJson);
	            childGoodsList.add(childGoodsVo);
	            
	            //库存VO
	            repList.add(this.buildRepertoryVo(goodsVo, childGoodsVo, availableNum));
	            
	            //商品、类别属性属性值关系VO
	            for (int j = 0; j < cppvInfos.length; j++) {
	            	String cppvId = cppvInfos[j];
	            	gcppList.add(this.buildGoodsCatePropPropValVo(goodsVo, childGoodsVo, cppvId));
	            }
			}
		} else {
			repList = new ArrayList<ScRepertory>(1);
			//库存VO
			repList.add(this.buildRepertoryVo(goodsVo, null, CommonUtils.getInteger(goodsBean.getAvailableNum())));
		}
		
		//图片
		if(StringUtils.isNotBlank(goodsBean.getImgs())) {
			String[] imgIds = goodsBean.getImgs().split(",");
			goodsImageList = new ArrayList<ScGoodsImage>(imgIds.length);
			imageUseRecList = new ArrayList<ScImageUseRec>(imgIds.length);
			for(int i = 0; i < imgIds.length; i++) {
				goodsImageList.add(this.buildGoodsImageVo(goodsVo,imgIds[i]));
				imageUseRecList.add(this.buildImageUseRecVo(goodsVo,imgIds[i]));
			}
		}
		
		/**********************1. 保存VO对象***************************/
		DBUtil db = DBUtil.getInstance();
		if(StringUtils.isBlank(goodsBean.getId())) {
			db.insert(goodsVo);
			if(childGoodsList != null) {
				db.insert(childGoodsList);
			}
			
			if(gcppList != null) { 
				db.insert(gcppList);
			}
			
			if(repList != null) {
				db.insert(repList);
			}
			
			db.insert(goodsImageList);
			db.insert(imageUseRecList);
			
			goodsBean.setId(goodsVo.getId());
		} else {
			db.update(goodsVo);
			
			//分别判断增加、删除、修改的子商品
			List<ScChildGoods> insertCgList = new ArrayList();
			List<ScChildGoods> updateCgList = new ArrayList();
			List<ScChildGoods> deleteCgList = new ArrayList();
			
			//查询数据库已有的子商品列表
			List<ScChildGoods> dbCgList = this.childGoodsDAO.queryPojoListByGoodsId(goodsBean.getId());
			
			//根据数据库的数据，页面数据，拆分新增、修改，删除的列表
			this.splitChildGoodsIds(childGoodsList, dbCgList, insertCgList, updateCgList, deleteCgList);
			
			if(!insertCgList.isEmpty()) {
				db.insert(insertCgList);
			}
			
			if(!updateCgList.isEmpty()) {
				db.update(updateCgList);
			}
			
			if(!deleteCgList.isEmpty()) {
				db.update(deleteCgList);
			}
			
			//覆盖商品-类别、属性、属性值数据
			if(gcppList != null) { 
				this.goodsCatePropPropValDAO.deleteByGoodsId(goodsVo.getId());
				db.insert(gcppList);
			}
			
			//属该库存数据
			if(repList != null) {
				this.repertoryDAO.deleteByGoodsId(goodsVo.getId());
				db.insert(repList);
			}
			
			//先删图片数据
			this.goodsImageDAO.deleteByGoodsId(goodsVo.getId());
			db.insert(goodsImageList);
			
			//先删图片占用数据
			this.imageUseRecDAO.deleteByBusiTypeAndBusiId(BusiTypeEnum.GOODS.getCode(), goodsVo.getId());
			db.insert(imageUseRecList);
		}
	}
	
	private void splitChildGoodsIds(List<ScChildGoods> pageCgList, List<ScChildGoods> existCgList, 
			List<ScChildGoods> insertCgList, List<ScChildGoods> updateCgList, List<ScChildGoods> deleteCgList) {
		if(existCgList != null && pageCgList != null) {
			for (ScChildGoods pageVo : pageCgList) {
				boolean add = true;
				for (ScChildGoods dbVo : existCgList) {
					if(pageVo.getId().equals(dbVo.getId())) {
						add = false;
						break;
					}
				}
				if(add) {
					insertCgList.add(pageVo);
				}
			}
			
			for (ScChildGoods dbVo : existCgList) {
				boolean del = true;
				for (ScChildGoods pageVo : pageCgList) {
					if(dbVo.getId().equals(pageVo.getId())) {
						del = false;
						dbVo.setPurchasePrice(pageVo.getPurchasePrice());
						dbVo.setPromotionPrice(pageVo.getPromotionPrice());
						dbVo.setChildName(pageVo.getChildName());
						dbVo.setSalePrice(pageVo.getSalePrice());
						dbVo.setMarketPrice(pageVo.getMarketPrice());
						dbVo.setChildNo(pageVo.getChildNo());
						break;
					}
				}
				
				if(del) {
					dbVo.setFlag(FlagEnum.HIS.getCode());
					deleteCgList.add(dbVo);
				} else {
					updateCgList.add(dbVo);
				}
			}
		}
	}
	
	/**
	 * 根据ID查询商品
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map loadGoods(ScGoods goodsVO) throws Exception {
		return this.goodsDAO.queryMapById(goodsVO.getId());
	}
	
	/**
	 * 查询商品图片
	 * @param goodsVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List loadGoodsImages(ScGoods goodsVO) throws Exception {
		return this.goodsImageDAO.queryListByGoodsId(goodsVO.getId());
	}
	
	/**
	 * 查询子商品列表
	 * @param goodsVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public List loadChildGoods(ScGoods goodsVO) throws Exception {
		return this.childGoodsDAO.queryListByGoodsId(goodsVO.getId());
	}
	
	private ScGoods buildGoodsVo(GoodsBean goodsBean) {
		UserMsg user = (UserMsg) RequestHelper.getSession().getAttribute("user");
		ScGoods goodsVo = new ScGoods();
		if(StringUtils.isBlank(goodsBean.getId())) {
			goodsVo.setId(UUID.randomUUID().toString());
		} else {
			goodsVo.setId(goodsBean.getId());
		}
		goodsVo.setTitle(goodsBean.getTitle());
		goodsVo.setSubTitle(goodsBean.getSubTitle());
		goodsVo.setCateId(goodsBean.getCateId());
		goodsVo.setBrandId(goodsBean.getBrandId());
		goodsVo.setDetail(goodsBean.getDetail());
		goodsVo.setParams(goodsBean.getParams());
		goodsVo.setGoodsName(goodsBean.getTitle());
		goodsVo.setGoodsNo(goodsBean.getGoodsNo());
		goodsVo.setGoodsCode(goodsBean.getGoodsCode());
		goodsVo.setFreightType(goodsBean.getFreightType());
		goodsVo.setFreightId(goodsBean.getFreightId());
		goodsVo.setPublishType(goodsBean.getPublishType());
		if(StringUtils.isNotBlank(goodsBean.getPublishTime())) {
			goodsVo.setPublishTime(DateUtils.parseDate(goodsBean.getPublishTime(), DateUtils.PATTERN_DATETIME));
		}
		goodsVo.setEmptyStore(goodsBean.getEmptyStore());
		goodsVo.setVirtual(goodsBean.getVirtual());
		goodsVo.setPurchasePrice(CommonUtils.getDouble(goodsBean.getPurchasePrice()));
		goodsVo.setMarketPrice(CommonUtils.getDouble(goodsBean.getMarketPrice()));
		goodsVo.setSalePrice(CommonUtils.getDouble(goodsBean.getSalePrice()));
		goodsVo.setPromotionPrice(CommonUtils.getDouble(goodsBean.getPromotionPrice()));
		goodsVo.setCreateTime(new Date());
		goodsVo.setCreateUserId(user.getUId());
		goodsVo.setFlag(FlagEnum.ACT.getCode());
		
		if(PublishTypeEnum.IN_STORE.getCode().equals(goodsVo.getPublishType())) {
			goodsVo.setGoodsSts(GoodsStsEnum.IN_STORE.getCode());
		} else if(PublishTypeEnum.TIMER_PUBLISH.getCode().equals(goodsVo.getPublishType())) {
			goodsVo.setGoodsSts(GoodsStsEnum.TIMER_PUTAWAY.getCode());
		} else if(PublishTypeEnum.PUBLISH.getCode().equals(goodsVo.getPublishType())) {
			goodsVo.setGoodsSts(GoodsStsEnum.PUTAWAY.getCode());
		}
		goodsVo.setAuditSts(AuditStsEnum.WAIT.getCode());
		return goodsVo;
	}

	private ScChildGoods buildChildGoodsVo(ScGoods goodsVo, JSONObject goodsChildJson) {
		Double purchasePrice = CommonUtils.getDouble(goodsChildJson.get("purchasePrice"));
        Double marketPrice = CommonUtils.getDouble(goodsChildJson.get("marketPrice"));
        Double salePrice = CommonUtils.getDouble(goodsChildJson.get("salePrice"));
        Double promotionPrice = CommonUtils.getDouble(goodsChildJson.get("promotionPrice"));
        String childNo = CommonUtils.getString(goodsChildJson.get("goodsNo"));
        String pkId = goodsChildJson.getString("pkId");
        
		ScChildGoods childGoodsVo = new ScChildGoods();
		if(StringUtils.isBlank(pkId)) {
			childGoodsVo.setId(UUID.randomUUID().toString());
		} else {
			childGoodsVo.setId(pkId);
		}
        childGoodsVo.setPurchasePrice(purchasePrice);
        childGoodsVo.setMarketPrice(marketPrice);
        childGoodsVo.setSalePrice(salePrice);
        childGoodsVo.setPromotionPrice(promotionPrice);
        childGoodsVo.setChildCode("ChildCode");
        childGoodsVo.setChildNo(childNo);
        childGoodsVo.setGoodsId(goodsVo.getId());
        childGoodsVo.setCreateTime(goodsVo.getCreateTime());
        childGoodsVo.setCreateUserId(goodsVo.getCreateUserId());
        childGoodsVo.setFlag(FlagEnum.ACT.getCode());
        return childGoodsVo;
	}
	
	private ScGoodsCatePropPropVal buildGoodsCatePropPropValVo(ScGoods goodsVo, ScChildGoods childGoodsVo, String cppvId) {
		ScGoodsCatePropPropVal gcpp = new ScGoodsCatePropPropVal();
    	gcpp.setId(UUID.randomUUID().toString());
    	gcpp.setGoodsId(goodsVo.getId());
    	gcpp.setChildGoodsId(childGoodsVo.getId());
    	gcpp.setCppvId(cppvId);
    	gcpp.setCreateTime(new Date());
    	gcpp.setType("SKU");
    	gcpp.setCreateUserId(goodsVo.getCreateUserId());
    	gcpp.setFlag(FlagEnum.ACT.getCode());
    	return gcpp;
	}
	
	private ScRepertory buildRepertoryVo(ScGoods goodsVo, ScChildGoods childGoodsVo, Integer availableNum) {
		ScRepertory repVo = new ScRepertory();
        repVo.setId(UUID.randomUUID().toString());
        repVo.setGoodsId(goodsVo.getId());
        if(childGoodsVo != null) {
        	repVo.setChildGoodsId(childGoodsVo.getId());
        }
        repVo.setAvailableNum(availableNum);
        repVo.setCreateTime(goodsVo.getCreateTime());
        repVo.setCreateUserId(goodsVo.getCreateUserId());
        repVo.setFlag(FlagEnum.ACT.getCode());
		return repVo;
	}
	
	private ScGoodsImage buildGoodsImageVo(ScGoods goodsVo, String imageId) {
		ScGoodsImage goodsImageVo = new ScGoodsImage();
		goodsImageVo.setId(UUID.randomUUID().toString());
		goodsImageVo.setGoodsId(goodsVo.getId());
		goodsImageVo.setImageId(imageId);
		goodsImageVo.setCreateTime(goodsVo.getCreateTime());
		goodsImageVo.setCreateUserId(goodsVo.getCreateUserId());
		goodsImageVo.setFlag(FlagEnum.ACT.getCode());
		return goodsImageVo;
	}
	
	private ScImageUseRec buildImageUseRecVo(ScGoods goodsVo, String imgId) {
		ScImageUseRec imageUseRecVo = new ScImageUseRec();
		imageUseRecVo.setId(UUID.randomUUID().toString());
		imageUseRecVo.setBusiId(goodsVo.getId());
		imageUseRecVo.setImgId(imgId);
		imageUseRecVo.setBusiType(BusiTypeEnum.GOODS.getCode());
		imageUseRecVo.setCreateTime(goodsVo.getCreateTime());
		imageUseRecVo.setCreateUserId(goodsVo.getCreateUserId());
		imageUseRecVo.setFlag(FlagEnum.ACT.getCode());
		return imageUseRecVo;
	}

	@Override
	public void passGoods(String goodsId) throws Exception {
		this.goodsDAO.updateAuditStsById(goodsId, AuditStsEnum.PASS.getCode());
	}

	@Override
	public void noPassGoods(String goodsId) throws Exception {
		this.goodsDAO.updateAuditStsById(goodsId, AuditStsEnum.NO_PASS.getCode());
	}

	@Override
	public void deleteGoods(String goodsId) throws Exception {
		
	}

	@Override
	public void putawayGoods(String goodsId) throws Exception {
		this.goodsDAO.updateGoodsStsById(goodsId, GoodsStsEnum.PUTAWAY.getCode());
	}

	@Override
	public void soldOutGoods(String goodsId) throws Exception {
		this.goodsDAO.updateGoodsStsById(goodsId, GoodsStsEnum.OUT.getCode());
	}
}