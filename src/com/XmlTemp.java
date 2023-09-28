package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XmlTemp {

	// 不設建構子
	private XmlTemp() {
	}

	static String getXmlTemp(String WIFI_NAME, String PASS_WORD, String xmlPath) throws IOException {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("<?xml version=\"1.0\"?>");
		strBuf.append("\n <WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\">");
		strBuf.append("\n <name>WIFI_NAME</name>");
		strBuf.append("\n <SSIDConfig>");
		strBuf.append("\n <SSID>");
		strBuf.append("\n <name>WIFI_NAME</name>");
		strBuf.append("\n </SSID>");
		strBuf.append("\n </SSIDConfig>");
		strBuf.append("\n <connectionType>ESS</connectionType>");
		strBuf.append("\n <connectionMode>manual</connectionMode>");
		strBuf.append("\n <MSM>");
		strBuf.append("\n <security>");
		strBuf.append("\n <authEncryption>");
		strBuf.append("\n <authentication>WPA2PSK</authentication>");
		strBuf.append("\n <encryption>AES</encryption>");
		strBuf.append("\n <useOneX>false</useOneX>");
		strBuf.append("\n </authEncryption>");
		strBuf.append("\n <sharedKey>");
		strBuf.append("\n <keyType>passPhrase</keyType>");
		strBuf.append("\n <protected>false</protected>");
		strBuf.append("\n <keyMaterial>PASS_WORD</keyMaterial>");
		strBuf.append("\n </sharedKey>");
		strBuf.append("\n </security>");
		strBuf.append("\n </MSM>");
		strBuf.append("\n <MacRandomization xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v3\">");
		strBuf.append("\n <enableRandomization>false</enableRandomization>");
		strBuf.append("\n </MacRandomization>");
		strBuf.append("\n </WLANProfile>");

		File file = new File(xmlPath);

		String xml = strBuf.toString().replace("WIFI_NAME", WIFI_NAME).replace("PASS_WORD", PASS_WORD);

		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(xml);
		output.flush();
		output.close();

		return file.getPath();
	}
}
