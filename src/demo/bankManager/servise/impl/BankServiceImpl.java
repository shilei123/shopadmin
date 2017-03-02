package demo.bankManager.servise.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import demo.bankManager.dao.BankInfoDAO;
import demo.bankManager.pojo.TBankInfo;
import demo.bankManager.servise.IBankService;

import framework.bean.PageBean;
import framework.config.SysDict;
import framework.db.DBUtil;

@Service("bankService")
public class BankServiceImpl implements IBankService {
	@Resource(name = "bankInfoDAO")
	private BankInfoDAO bankInfoDAO;

	/**
	 * 查询
	 */
	public PageBean queryBankInfoList(PageBean pageBean) throws Exception {
		int total = bankInfoDAO.queryBankInfoCount(pageBean);
		pageBean.setTotal(total);
		List<TBankInfo> pageData = bankInfoDAO.queryBankInfoPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 删除
	 */
	public void deleteBankInfo(String id) throws Exception {
		DBUtil db = DBUtil.getInstance();
		TBankInfo bankInfo = getBankInfo(id);
		if(bankInfo != null) {
			bankInfo.setUpdateTime(new Date());
			bankInfo.setFlag(SysDict.FLAG_HIS);
			db.update(bankInfo);
		}
	}

	/**
	 * 修改
	 */
	public TBankInfo getBankInfo(String id) {
		Object obj = DBUtil.getInstance().get(TBankInfo.class, id);
		if(obj != null) {
			return (TBankInfo) obj;
		}
		return null;
	}

	/**
	 * 保存
	 */
	public void saveBankInfo(TBankInfo bankInfo) {
		if (bankInfo == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(bankInfo.getId())) {
			String id = UUID.randomUUID().toString();
			bankInfo.setId(id);
			bankInfo.setCreateTime(new Date());
			bankInfo.setFlag(SysDict.FLAG_ACT);
			db.insert(bankInfo);
		} else { // 修改
			TBankInfo bank = (TBankInfo) db.get(TBankInfo.class, bankInfo.getId());
			bank.setBankName(bankInfo.getBankName());
			bank.setUrl(bankInfo.getUrl());
			bank.setLogo(bankInfo.getLogo());
			bank.setTel(bankInfo.getTel());
			bank.setBankDesc(bankInfo.getBankDesc());
			bank.setUpdateTime(new Date());
			bank.setId(bankInfo.getId());
			bank.setFlag(SysDict.FLAG_ACT);
			db.update(bank);
		}
	}
}