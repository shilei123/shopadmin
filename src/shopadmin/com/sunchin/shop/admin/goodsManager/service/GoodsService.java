package com.sunchin.shop.admin.goodsManager.service;

import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;

import framework.bean.PageBean;

public interface GoodsService {
	
	/**
	 * 保存商品
	 * @param goods
	 * @throws Exception
	 */
	void saveGoods(GoodsBean goods) throws Exception;
	
	/**
	 * 分页查询商品
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	PageBean queryGoods(PageBean pageBean) throws Exception;
}