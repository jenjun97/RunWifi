package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunWifiDemo {

	// 執行流程
	private void service(String range, int minSize, int maxSize, String wifiName, String xmlPath) throws Exception {
		PassWord password = new PassWord(range, minSize, maxSize);
		String pw = null;
		while ((pw = password.getPw()) != null) {
			String xmlTempPath = XmlTemp.getXmlTemp(wifiName, pw, xmlPath);
		}

	}

	public static void main(String[] args) throws Exception {
		RunWifiDemo demo = new RunWifiDemo();

		String range = "0123456789";
		int minSize = 2;
		int maxSize = 3;
		String wifiName = "abc";
		String xmlPath = "D:/wifi.xml";

		demo.service(range, minSize, maxSize, wifiName, xmlPath);
	}
	
	public static void main2(String[] args) throws IOException, InterruptedException {

		String exportComm = "cmd.exe /c netsh wlan export profile name=\"Jun\" key=clear folder=D:\\";
		String addComm = "cmd.exe /c netsh wlan add profile filename=\"D:\\wifi.xml\"";
		String connComm = "cmd.exe /c netsh wlan connect name=\"Your_WiFi_Name\"";
		String delComm = "cmd.exe /c netsh wlan delete profile name=\"Jun2\"";


		BufferedReader input = null;
		String str;
		Process process = Runtime.getRuntime().exec(delComm);

		input = new BufferedReader(new InputStreamReader(process.getInputStream(), "BIG5"));

		while ((str = input.readLine()) != null) {
			System.out.println(str);
		}

	}
}
