package framework.config;

/**
 * @author YANGCW
 * 系统常量词典类
 */
public class SysDict {
	public static final String INIT_PWD = "123456";
	
	//数据库记录状态：有效，1，无效，0
	public static final String FLAG_ACT = "1";
	public static final String FLAG_HIS = "0";
	
	public static final String SEX_MALE = "0";
	public static final String SEX_FEMALE = "1";
	
	public static final String EMPTY_HIS = "0";
	public static final String EMPTY_ACT = "1";
	
	
	/**
	 *	机构制度参数表 
	 */
	public static final String AGENCY_PARAMS_RELEASE_CNT= "release_cnt"; //释放次数
	public static final String AGENCY_PARAMS_PROD_INTEGRAL= "prod_integral"; //产品积分的倍数
	public static final String AGENCY_PARAMS_CONSUME_INTEGRAL= "consume_integral"; //消费积分的倍数
	public static final String AGENCY_PARAMS_WEALTH_INTEGRAL= "wealth_integral"; //财富积分的倍数
	public static final String AGENCY_PARAMS_SALES_AWARD= "sales_award"; //财富积分的倍数
	

	/**
	 *	网点变动表 
	 */
	public static final String NET_CHANGE_TYPE_UP= "1"; //晋升变动
	public static final String NET_CHANGE_TYPE_EXCHANGE= "2"; //置换变动

	/**
	 *	  奖金表
	 */
	public static final String BONUS_SALES_AWARD= "1"; //销售奖、直推奖
	public static final String BONUS_PERF_AWARD= "2"; //业绩奖、对碰奖
	public static final String BONUS_SREV_AWARD= "3"; //服务奖
	
	/**
	 *	积分流水表
	 */
	public static final String INTEGRAL_TYPE_BONUS= "1"; //奖金积分
	public static final String INTEGRAL_TYPE_PROD= "2"; //产品积分
	public static final String INTEGRAL_TYPE_CONSUME= "3"; //消费积分
	public static final String INTEGRAL_TYPE_WEALTH= "4"; //财富积分
	public static final String INTEGRAL_TYPE_REGISTER= "5"; //注册积分
	public static final String INTEGRAL_TYPE_INVALID= "6"; //失效积分
	
	public static final String INTEGRAL_BUSI_TYPE_PURCHASE= "1"; //购买
	public static final String INTEGRAL_BUSI_TYPE_TRADE= "2"; //积分交易
	public static final String INTEGRAL_BUSI_TYPE_PRO_TO_BONUS= "3"; //产->奖
	public static final String INTEGRAL_BUSI_TYPE_BONUS_TO_REG= "4"; //奖->注
	public static final String INTEGRAL_BUSI_TYPE_CONSUME_TO_BONUS= "5"; //消->奖
	public static final String INTEGRAL_BUSI_TYPE_UP= "6"; //晋升奖励消费积分
	
}