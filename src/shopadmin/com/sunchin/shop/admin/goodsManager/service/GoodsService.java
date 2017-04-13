package com.sunchin.shop.admin.goodsManager.service;

import java.util.List;
import java.util.Map;

import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;
import com.sunchin.shop.admin.pojo.ScGoods;

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
	
	/**
	 * 根据ID查询商品
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	Map loadGoods(ScGoods goodsVO) throws Exception;
	
	/**
	 * 查询商品图片
	 * @param goodsVO
	 * @return
	 * @throws Exception
	 */
	List loadGoodsImages(ScGoods goodsVO) throws Exception;
	
	/**
	 * 查询子商品列表
	 * @param goodsVO
	 * @return
	 * @throws Exception
	 */
	List loadChildGoods(ScGoods goodsVO) throws Exception;
}