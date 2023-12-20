package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectWifi {

	/**
	 * 
	 * @param profilePath Wi-Fi 配置檔路徑
	 * @param profileName 配置檔名稱
	 * @throws Exception
	 */
	public static void toConn(String profilePath, String profileName) throws Exception {

		// 導入配置檔
		String addProfileCommand = "netsh wlan add profile filename=\"" + profilePath + "\"";
		executeCommand(addProfileCommand);

		// 連接 Wi-Fi
		String connectCommand = "netsh wlan connect name=\"" + profileName + "\"";

		executeCommand(connectCommand);
	}

	private static void executeCommand(String command) throws IOException {
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "BIG5"));
		String line;
		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
		}
	}
}
