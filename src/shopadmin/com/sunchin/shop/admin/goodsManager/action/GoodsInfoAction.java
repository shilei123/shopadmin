package com.sunchin.shop.admin.goodsManager.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.AuditStsEnum;
import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;
import com.sunchin.shop.admin.goodsManager.service.GoodsService;

import framework.action.PageAction;
import framework.bean.PageBean;

/**
 * @author yangchaowen
 * 2017年3月3日
 * 商品信息的新增，编辑等操作
 */
public class GoodsInfoAction extends PageAction {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	private GoodsBean goods;
	
	//保存商品
	public String saveGoods() {
		try {
			this.goodsService.saveGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询未上架商品
	 * @return
	 */
	public String queryNoOnGoods() {
		try {
			if(this.getPageBean().getQueryParams() != null) {
				pageBean.getQueryParams().put("audit_sts", AuditStsEnum.WAIT.getCode());
			}
			PageBean resultData = this.goodsService.queryGoods(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public GoodsBean getGoods() {
		return goods;
	}

	public void setGoods(GoodsBean goods) {
		this.goods = goods;
	}
	
}