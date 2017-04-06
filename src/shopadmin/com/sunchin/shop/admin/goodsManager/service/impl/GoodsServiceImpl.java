package com.sunchin.shop.admin.goodsManager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunchin.shop.admin.dict.BusiTypeEnum;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.GoodsStsEnum;
import com.sunchin.shop.admin.dict.PublishTypeEnum;
import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;
import com.sunchin.shop.admin.goodsManager.service.GoodsService;
import com.sunchin.shop.admin.pojo.ScChildGoods;
import com.sunchin.shop.admin.pojo.ScGoods;
import com.sunchin.shop.admin.pojo.ScGoodsCatePropPropVal;
import com.sunchin.shop.admin.pojo.ScGoodsImage;
import com.sunchin.shop.admin.pojo.ScImageUseRec;
import com.sunchin.shop.admin.pojo.ScRepertory;

import framework.bean.UserMsg;
import framework.db.DBUtil;
import framework.helper.RequestHelper;
import framework.util.CommonUtils;
import framework.util.DateUtils;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	
	@Transactional
	@Override
	public void saveGoods(GoodsBean goodsBean) throws Exception {
		if(StringUtils.isBlank(goodsBean.getId())) {
			this.save(goodsBean);
		} else {
			this.update(goodsBean);
		}
	}
	
	private void update(GoodsBean goodsBean) {
		//DBUtil db = DBUtil.getInstance();
	}
	
	private void save(GoodsBean goodsBean) {
		//商品VO
		ScGoods goodsVo = this.buildGoodsVo(goodsBean);
		List<ScChildGoods> childGoodsList = null; //子商品VO
		List<ScGoodsCatePropPropVal> gcppList = null; //商品、类别属性属性值关系VO
		ScRepertory repVo = null; //库存VO
		List<ScGoodsImage> goodsImageList = null; //商品图片vo
		List<ScImageUseRec> imageUseRecList = null; //图片资源占用vo
		
		JSONArray childGoodsArr = null;
		if(StringUtils.isNotBlank(goodsBean.getChildGoods())) 
			childGoodsArr = JSON.parseArray(goodsBean.getChildGoods());
		
		if(childGoodsArr != null && !childGoodsArr.isEmpty()) {
			childGoodsList = new ArrayList<ScChildGoods>(childGoodsArr.size());
            gcppList = new ArrayList<ScGoodsCatePropPropVal>();
            
			for(int i = 0; i < childGoodsArr.size(); i++) {
	            JSONObject goodsChildJson = (JSONObject) childGoodsArr.get(i);
	            Integer availableNum = CommonUtils.getInteger(goodsChildJson.get("availableNum"));
	            String childGoodsId = CommonUtils.getString(goodsChildJson.get("childGoodsId"));
	            String[] childGoodsIds = childGoodsId.split("_");
	            
	            //子商品VO
	            ScChildGoods childGoodsVo = this.buildChildGoodsVo(goodsVo, goodsChildJson);
	            childGoodsList.add(childGoodsVo);
	            
	            //商品、类别属性属性值关系VO
	            for (int j = 0; j < childGoodsIds.length; j++) {
	            	String cppvId = childGoodsIds[j].split(":")[0];
	            	gcppList.add(this.buildGoodsCatePropPropValVo(goodsVo, childGoodsVo, cppvId));
	            }
	            //库存VO
	            repVo = this.buildRepertoryVo(goodsVo, childGoodsVo, availableNum);
			}
		} else {
			//库存VO
            repVo = this.buildRepertoryVo(goodsVo, null, CommonUtils.getInteger(goodsBean.getAvailableNum()));
		}
		
		//图片
		if(StringUtils.isNotBlank(goodsBean.getImgs())) {
			String[] imgIds = goodsBean.getImgs().split(",");
			goodsImageList = new ArrayList<ScGoodsImage>(imgIds.length);
			imageUseRecList = new ArrayList<ScImageUseRec>(imgIds.length);
			for(int i = 0; i < imgIds.length; i++) {
				ScGoodsImage goodsImageVo = this.buildGoodsImageVo(goodsVo);
				ScImageUseRec imageUseRecVo = this.buildImageUseRecVo(goodsVo);
				goodsImageList.add(goodsImageVo);
				imageUseRecList.add(imageUseRecVo);
			}
		}
		
		DBUtil db = DBUtil.getInstance();
		System.out.println("------插入商品");
		db.insert(goodsVo);
		System.out.println("------插入子商品");
		if(childGoodsList != null)
			db.insert(childGoodsList);
		System.out.println("------插入商品、类别-属性-属性值中间表");
		if(gcppList != null) 
			db.insert(gcppList);
		System.out.println("------插入库存表");
		db.insert(repVo);
		System.out.println("------插入商品图片表");
		db.insert(goodsImageList);
		System.out.println("------插入图片资源占用表");
		db.insert(imageUseRecList);
	}
	
	private ScChildGoods buildChildGoodsVo(ScGoods goodsVo, JSONObject goodsChildJson) {
		Double purchasePrice = CommonUtils.getDouble(goodsChildJson.get("purchasePrice"));
        Double marketPrice = CommonUtils.getDouble(goodsChildJson.get("marketPrice"));
        Double salePrice = CommonUtils.getDouble(goodsChildJson.get("salePrice"));
        Double promotionPrice = CommonUtils.getDouble(goodsChildJson.get("promotionPrice"));
        String childNo = CommonUtils.getString(goodsChildJson.get("goodsNo"));
        
		ScChildGoods childGoodsVo = new ScChildGoods();
		childGoodsVo.setId(UUID.randomUUID().toString());
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
	
	private ScGoods buildGoodsVo(GoodsBean goodsBean) {
		UserMsg user = (UserMsg) RequestHelper.getSession().getAttribute("user");
		ScGoods goodsVo = new ScGoods();
		goodsVo.setId(UUID.randomUUID().toString());
		goodsVo.setTitle(goodsBean.getTitle());
		goodsVo.setSubTitle(goodsBean.getSubTitle());
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
		goodsVo.setEmptyStore("");
		goodsVo.setVirtual("");
		goodsVo.setPurchasePrice(CommonUtils.getDouble(goodsBean.getPurchasePrice()));
		goodsVo.setMarketPrice(CommonUtils.getDouble(goodsBean.getMarketPrice()));
		goodsVo.setSalePrice(CommonUtils.getDouble(goodsBean.getSalePrice()));
		goodsVo.setPromotionPrice(CommonUtils.getDouble(goodsBean.getPromotionPrice()));
		goodsVo.setCreateTime(new Date());
		goodsVo.setCreateUserId(user.getUId());
		goodsVo.setFlag(FlagEnum.ACT.getCode());
		
		if(PublishTypeEnum.IN_STORE.getCode().equals(goodsVo.getPublishType())
				|| PublishTypeEnum.DELAY.getCode().equals(goodsVo.getPublishType())) {
			goodsVo.setSts(GoodsStsEnum.NO_ON.getCode());
		} else if(PublishTypeEnum.PUBLISH.getCode().equals(goodsVo.getPublishType())) {
			goodsVo.setSts(GoodsStsEnum.ON.getCode());
		}
		return goodsVo;
	}
	
	private ScGoodsImage buildGoodsImageVo(ScGoods goodsVo) {
		ScGoodsImage goodsImageVo = new ScGoodsImage();
		goodsImageVo.setId(UUID.randomUUID().toString());
		goodsImageVo.setGoodsId(goodsVo.getId());
		goodsImageVo.setCreateTime(goodsVo.getCreateTime());
		goodsImageVo.setCreateUserId(goodsVo.getCreateUserId());
		goodsImageVo.setFlag(FlagEnum.ACT.getCode());
		return goodsImageVo;
	}
	
	private ScImageUseRec buildImageUseRecVo(ScGoods goodsVo) {
		ScImageUseRec imageUseRecVo = new ScImageUseRec();
		imageUseRecVo.setId(UUID.randomUUID().toString());
		imageUseRecVo.setBusiId(goodsVo.getId());
		imageUseRecVo.setBusiType(BusiTypeEnum.GOODS.getCode());
		imageUseRecVo.setCreateTime(goodsVo.getCreateTime());
		imageUseRecVo.setCreateUserId(goodsVo.getCreateUserId());
		imageUseRecVo.setFlag(FlagEnum.ACT.getCode());
		return imageUseRecVo;
	}

}