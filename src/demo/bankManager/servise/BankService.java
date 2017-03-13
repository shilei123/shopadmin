package demo.bankManager.servise;

import demo.bankManager.pojo.TBankInfo;

import framework.bean.PageBean;

public interface BankService {
	
	/**
	 * 查询
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public PageBean queryBankInfoList(PageBean pageBean) throws Exception; 
	
	/**
	 * 删除
	 * @param bank
	 * @throws Exception
	 */
	public void deleteBankInfo(String id) throws Exception;
	
	/**
	 * 保存
	 * @param bank
	 * @return 
	 * @throws Exception
	 */
	public void saveBankInfo(TBankInfo bankInfo);

	/**
	 * 修改
	 * @param id
	 * @return
	 */
	public TBankInfo getBankInfo(String id);
}