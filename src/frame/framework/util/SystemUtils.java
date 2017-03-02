package framework.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemUtils {
	public static String getPhysicalAddress(){
		String msg = null;
		try {
			Process p = Runtime.getRuntime().exec("ipconfig /all");
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String tmp = null;
			while((tmp=br.readLine()) != null){
				if(tmp.indexOf("Physical Address") > -1){
					msg = tmp.split(":")[1].trim();
				}
			}
			br.close();
			isr.close();
		} catch (Exception e) {
			System.out.println("无网卡地址");
		}
		return msg;
	}
}
