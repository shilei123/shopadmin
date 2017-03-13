package demo.bankManager.action;

import javax.annotation.Resource;
import com.opensymphony.xwork2.Action;
import demo.bankManager.pojo.TBankInfo;
import demo.bankManager.servise.BankService;
import framework.action.PageAction;
import framework.bean.PageBean;

public class BankAction extends PageAction {
	@Resource(name = "bankService")
	private BankService bankService;
	private TBankInfo bankInfo;
	
	/**
	 * 查询
	 * @return
	 */
	public String query() {
		try {
			PageBean resultData = bankService.queryBankInfoList(this.getPageBean());
			this.setTotal(resultData.getTotal());
			this.setDataRows(resultData.getPageData());
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
		try {
			bankService.deleteBankInfo(bankInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 修改
	 * @return
	 */
	public String queryBankInfo() {
		try {
			bankInfo = bankService.getBankInfo(bankInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String saveAgency() {
		try {
			bankService.saveBankInfo(bankInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public TBankInfo getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(TBankInfo bankInfo) {
		this.bankInfo = bankInfo;
	}

}