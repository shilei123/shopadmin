package framework.helper;

import java.math.BigDecimal;
import java.util.UUID;

import framework.db.DBUtil;
import framework.db.pojo.TXtMessage;
import framework.util.BeanUtils;

public class MessageHelper {
	
	/**
	 * 发送消息
	 * @param title
	 * @param content
	 * @param toUserId
	 * @param userId
	 * @param db
	 * @throws Exception 
	 */
	public static void send(TXtMessage message, String[] toUserIds, DBUtil db) throws Exception{
		if(toUserIds!=null && toUserIds.length>0){
			String batchId = UUID.randomUUID().toString();
			for(String id : toUserIds){
				TXtMessage pojo = new TXtMessage();
				BeanUtils.copyPojo(message, pojo);
				pojo.setId(UUID.randomUUID().toString());
				pojo.setBatchId(batchId);
				pojo.setToUserId(id);
				pojo.setMessageStatus(BigDecimal.valueOf(0));
				db.insert(pojo);
			}
		}
	}
	
	public static void send(TXtMessage message, DBUtil db){
		if(message==null){return;}
		if(message.getToUserId()!=null && !"".equals(message.getToUserId())){
			message.setId(UUID.randomUUID().toString());
			message.setMessageStatus(BigDecimal.valueOf(0));
			db.insert(message);
		}
	}
}
