package com;

import java.util.Arrays;

public class PassWordService {

	// 傳入值
	private int size;

	// 所有組合
	private String[] strCombo;
	// 索引值
	private int[] indexPosition;
	// 最大值
	private int[] maxPosition;
	// 是否還有下一個
	private boolean haveNext;
	// 是否為第一次執行
	private boolean firstRun;

	// 建構子，初始化
	public PassWordService(String allPsStr, int leng) throws Exception {
		// 防呆
		if (allPsStr.isEmpty() || allPsStr.isEmpty()) {
			throw new Exception("字串為空");
		}
		if (leng <= 0) {
			throw new Exception("長度不以為0");
		}

		// 初始化設所有值
		this.strCombo = allPsStr.split("");
		this.indexPosition = new int[leng];
		this.haveNext = true;
		this.firstRun = true;
		this.maxPosition = new int[leng];
		for (int i = 0; i < maxPosition.length; i++) {
			maxPosition[i] = strCombo.length - 1;
		}
	}

	// 取下一個值
	public String next() {

		// 判斷是否還有下一個
		if (!haveNext) {
			return null;
		}

		// 這裡要將索引值+1
		getNextIndex();

		// 判斷是否為最後一個
		if (Arrays.equals(this.indexPosition, this.maxPosition)) {
			haveNext = false;
		}

		// 將索引值變字串
		String str = indexToStr();
		return str;
	}

	// 將索引值變字串
	private String indexToStr() {

		String str = "";
		for (int i = 0; i < this.indexPosition.length; i++) {
			str = str + this.strCombo[this.indexPosition[i]];
		}
		return str;
	}

	// 索引值加1
	private void getNextIndex() {
		// 是否第一次執行
		if (this.firstRun) {
			this.firstRun = false;
			return;
		}

		// +1並且進位(由右往左)
		for (int i = this.indexPosition.length - 1; i >= 0; i--) {
			if (this.indexPosition[i] == strCombo.length - 1) {
				this.indexPosition[i] = 0;
			} else {
				this.indexPosition[i] = this.indexPosition[i] + 1;
				break;
			}
		}
	}

	// 測試
	public static void main(String[] args) throws Exception {
		PassWordService ps = new PassWordService("abcd", 4);
		for (int i = 0; i < 10; i++) {
			System.out.println(ps.next());
		}
	}

	// set & get
	public int[] getIndexPosition() {
		return indexPosition;
	}

}
