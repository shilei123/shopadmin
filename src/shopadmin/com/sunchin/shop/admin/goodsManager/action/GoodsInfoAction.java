package com.sunchin.shop.admin.goodsManager.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.dict.AuditStsEnum;
import com.sunchin.shop.admin.dict.GoodsStsEnum;
import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;
import com.sunchin.shop.admin.goodsManager.service.GoodsService;
import com.sunchin.shop.admin.pojo.ScGoods;

import framework.action.PageAction;
import framework.bean.PageBean;

/**
 * @author yangchaowen
 * 2017年3月3日
 * 商品信息的新增，编辑等操作
 */
@SuppressWarnings({"rawtypes"})
public class GoodsInfoAction extends PageAction {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	private GoodsBean goods;
	private ScGoods goodsVO;
	private Map goodsMap;
	private List goodsImgList;
	private List childGoodsList;
	
	//保存商品
	public String saveGoods() {
		try {
			this.goodsService.saveGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	//查询商品
	public String loadGoods() {
		try {
			this.goodsMap = this.goodsService.loadGoods(goodsVO);
			this.goodsImgList = this.goodsService.loadGoodsImages(goodsVO);
			this.childGoodsList = this.goodsService.loadChildGoods(goodsVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询待审核商品
	 * @return
	 */
	public String queryNoAuditList() {
		try {
			if(this.getPageBean().getQueryParams() != null) {
				pageBean.getQueryParams().put("auditSts", AuditStsEnum.WAIT.getCode()+","+AuditStsEnum.NO_PASS.getCode());
			}
			PageBean resultData = this.goodsService.queryGoods(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询未上架商品
	 * @return
	 */
	public String queryNoPutawayList() {
		try {
			if(this.getPageBean().getQueryParams() != null) {
				pageBean.getQueryParams().put("auditSts", AuditStsEnum.PASS.getCode());
				pageBean.getQueryParams().put("goodsSts", GoodsStsEnum.IN_STORE.getCode()+","+GoodsStsEnum.TIMER_PUTAWAY.getCode());
			}
			PageBean resultData = this.goodsService.queryGoods(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询已上架商品
	 * @return
	 */
	public String queryPutawayList() {
		try {
			if(this.getPageBean().getQueryParams() != null) {
				pageBean.getQueryParams().put("auditSts", AuditStsEnum.PASS.getCode());
				pageBean.getQueryParams().put("goodsSts", GoodsStsEnum.PUTAWAY.getCode());
			}
			PageBean resultData = this.goodsService.queryGoods(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 查询已下架商品
	 * @return
	 */
	public String querySoldOutList() {
		try {
			if(this.getPageBean().getQueryParams() != null) {
				pageBean.getQueryParams().put("auditSts", AuditStsEnum.PASS.getCode());
				pageBean.getQueryParams().put("goodsSts", GoodsStsEnum.OUT.getCode());
			}
			PageBean resultData = this.goodsService.queryGoods(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 通过
	 * @return
	 */
	public String pass() {
		try {
			this.goodsService.passGoods(this.goods.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 不通过
	 * @return
	 */
	public String noPass() {
		try {
			this.goodsService.noPassGoods(this.goods.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		/*try {
			if(this.getPageBean().getQueryParams() != null) {
				pageBean.getQueryParams().put("auditSts", AuditStsEnum.PASS.getCode());
				pageBean.getQueryParams().put("goodsSts", GoodsStsEnum.OUT.getCode());
			}
			PageBean resultData = this.goodsService.queryGoods(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return Action.SUCCESS;
	}
	
	/**
	 * 上架
	 * @return
	 */
	public String putaway() {
		try {
			this.goodsService.putawayGoods(this.goods.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 下架
	 * @return
	 */
	public String soldOut() {
		try {
			this.goodsService.soldOutGoods(this.goods.getId());
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

	public ScGoods getGoodsVO() {
		return goodsVO;
	}

	public void setGoodsVO(ScGoods goodsVO) {
		this.goodsVO = goodsVO;
	}

	public Map getGoodsMap() {
		return goodsMap;
	}

	public List getGoodsImgList() {
		return goodsImgList;
	}
	
	public List getChildGoodsList() {
		return childGoodsList;
	}
}