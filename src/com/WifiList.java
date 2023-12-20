package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WifiList {

	private static final Logger logger = LogManager.getLogger(WifiList.class);

	private final static String txtPath = "D:/WifiList.txt";

	public static void main(String[] args) throws IOException {
		Process process = Runtime.getRuntime().exec("netsh wlan show networks mode=Bssid");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream(), Charset.forName("BIG5")));

		BufferedWriter output = new BufferedWriter(new FileWriter(new File(txtPath)));
		String line;

		while ((line = reader.readLine()) != null) {
			// 解析數據
			output.append(line.trim() + "\n");
			System.out.println(line.trim() + "\n");
			logger.info(line);
		}

		reader.close();
		output.flush();
		output.close();
	}
}
