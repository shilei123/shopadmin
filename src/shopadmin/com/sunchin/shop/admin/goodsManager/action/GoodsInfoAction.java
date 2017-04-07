package com.sunchin.shop.admin.goodsManager.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.sunchin.shop.admin.goodsManager.bean.GoodsBean;
import com.sunchin.shop.admin.goodsManager.service.GoodsService;

/**
 * @author yangchaowen
 * 2017年3月3日
 * 商品信息的新增，编辑等操作
 */
public class GoodsInfoAction {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	
	private GoodsBean goods;
	
	public String saveGoods() {
		try {
			this.goodsService.saveGoods(goods);
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
	
	/**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuffer sb = new StringBuffer();
                while((lineTxt = bufferedReader.readLine()) != null){
                	sb.append(lineTxt);
                }
                read.close();
                System.out.println(sb.toString());
                
                JSONArray jsonArr = JSON.parseArray(sb.toString());
                System.out.println("json2Array()方法：jsonArr=="+jsonArr.size()); 
                System.out.println(jsonArr.get(0).getClass());
                JSONObject obj = (JSONObject) jsonArr.get(0);
                System.out.println(obj.get("childGoodsId"));
            }else{
            	System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
     
    public static void main(String argv[]){
        String filePath = "c:\\1.txt";
        readTxtFile(filePath);
    }
}
