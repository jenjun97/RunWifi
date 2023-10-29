package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllWifi {

	public static final String CODE_UTF8 = "utf-8";
	public static final String CODE_GBK = "gbk";
	public static final String CODE_BIG5 = "BIG5";
	public static final String DEFAULT_PATH = "D:/wifi";// 默认wifi配置文件生成路径

	public static final String WIFI_LIST = "netsh wlan show networks mode=bssid";// 列出所有可用wifi
	public static final String WIFI_ADDFILE = "netsh wlan add profile filename=";// 添加配置文件,后面需要加上你生成的配置文件名称
	public static final String WIFI_CONNECT = "netsh wlan connect name=";// 连接wifi,后面加上你需要连接的wifi名称

	public static final String TEST_CONNECT = "ping www.baidu.com";// wifi连接后测试是否ping通的一个网址

	public static void main(String[] args) {
		Map<String, String> wifis = getWifi();

		wifis.forEach((key, value) -> {
			System.out.println("key=" + key + "\tvalue=" + value);

		});

	}

	public static Map<String, String> getWifi() {
		Map<String, String> map = new HashMap<>();
		// 这里使用UTF-8去获取，中文名称的wifi不会乱码
		String key = null;
		String value = null;
		boolean saveFlag = false;
		for (String str : execute(WIFI_LIST, null, CODE_BIG5)) {
			if (str.trim().startsWith("SSID")) {
				key = str.substring(9, str.length());
			} else if (str.endsWith("%")) {
				value = str.substring(str.length() - 3, str.length() - 1);
				saveFlag = true;
			}
			if (saveFlag) {
				map.put(key, value);
				saveFlag = false;
			}
		}
		return map;
	}

	public static List<String> execute(String cmd, String filePath, String code) {
		System.out.println("cmd命令加载中……");
		Process process = null;
		List<String> result = new ArrayList<String>();
		try {
			if (filePath != null) {
				process = Runtime.getRuntime().exec(cmd, null, new File(filePath));
			} else {
				process = Runtime.getRuntime().exec(cmd);
			}
			BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream(), code));
			String line = null;
			while ((line = bReader.readLine()) != null) {
				result.add(line);
			}
			System.out.println("cmd命令已经加载成功……");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
