package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WifiInfo {
	public static String getInfo() throws IOException {
		// 執行 netsh 命令以獲取網絡狀態
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "netsh wlan show interfaces");
		Process process = processBuilder.start();

		// 讀取命令的輸出
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "BIG5"));
		String line;

		while ((line = reader.readLine()) != null) {

			// 檢查是否有 "狀態" 行並且它表示已連接狀態
			if (line.trim().startsWith("狀態")) {
				line = line.trim().replace(" ", "");
				break;
			}
		}

		return line;
	}
}
