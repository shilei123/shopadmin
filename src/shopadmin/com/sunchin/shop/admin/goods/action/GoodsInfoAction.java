package com.sunchin.shop.admin.goods.action;

import com.sunchin.shop.admin.pojo.ScGoodsCatePropPropVal;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.pojo.ScGoods;
import com.sunchin.shop.admin.pojo.ScChildGoods;

/**
 * @author yangchaowen
 * 2017年3月3日
 * 商品信息的新增，编辑等操作
 */
public class GoodsInfoAction {
	private ScGoods goods;
	private ScChildGoods goodsChild;
	private ScGoodsCatePropPropVal gcpp;
	
	public String saveGoods() {
		
		return Action.SUCCESS;
	}
	
	public ScGoods getGoods() {
		return goods;
	}
	
	public void setGoods(ScGoods goods) {
		this.goods = goods;
	}
	
	public ScChildGoods getGoodsChild() {
		return goodsChild;
	}
	
	public void setGoodsChild(ScChildGoods goodsChild) {
		this.goodsChild = goodsChild;
	}

	public ScGoodsCatePropPropVal getGcpp() {
		return gcpp;
	}

	public void setGcpp(ScGoodsCatePropPropVal gcpp) {
		this.gcpp = gcpp;
	}
	
}
