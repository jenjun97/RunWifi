package com;

import java.util.ArrayList;
import java.util.List;

public class PassWord {

	// 字元範圍
	private String[] rangeAry;

	// 字元長度
	private int rangeSize;

	// 最大位元數
	private int maxSize;

	// 最小位元數
	private int minSize;

	// 開始值
	private List<Integer> numList;

	// 最大值
	private List<Integer> maxList;

	// 是否第一次執行
	boolean firstRun = true;

	// 建構子
	public PassWord(String range, int minSize, int maxSize) throws Exception {
		this.rangeAry = range.split("");
		this.rangeSize = range.length();
		this.maxSize = maxSize;
		this.minSize = minSize;

		// 防呆
		if (range.length() < 1) {
			throw new Exception("字元範圍不可為空");
		} else if (maxSize < minSize) {
			throw new Exception("最大位元值不可以「小於」最小位元值");
		} else if (maxSize < 1 || minSize < 1) {
			throw new Exception("最大位元值和最小位元值不可以小於1");
		}

		init(this.rangeAry, minSize, maxSize);
	}

	public String getPw() {
		// 判斷是否已經是最大值
		if (this.numList.equals(this.maxList)) {
			return null;
		}

		// 加1，但第一次執行不用+1
		if (this.firstRun) {
			this.firstRun = false;
		} else {
			add();
		}

		// 組密碼
		String pw = "";
		for (int i = this.numList.size() - 1; i > -1; i--) {
			int num = this.numList.get(i);
			if (num >= 0) {
				pw = pw + this.rangeAry[num];
			}
		}
		return pw;

	}

	private void add() {

		// 第一位+1
		this.numList.set(0, numList.get(0) + 1);

		// 迴圈判斷進位
		for (int i = 0; i < numList.size(); i++) {

			if (this.numList.get(i) == this.rangeSize) {
				this.numList.set(i + 1, this.numList.get(i + 1) + 1);
				this.numList.set(i, 0);
			}
		}
	}

	// 初始化
	private void init(String[] rangeAry, int minSize, int maxSize) {
		// 初始化開始值
		numList = new ArrayList<Integer>();
		for (int i = 0; i < maxSize; i++) {
			if (i < minSize) {
				numList.add(0);
			} else {
				numList.add(-1);
			}
		}

		// 初始化最大值
		maxList = new ArrayList<Integer>();
		for (int i = 0; i < maxSize; i++) {
			maxList.add(rangeSize - 1);
		}
	}

	// 測試用
	public static void main(String[] args) throws Exception {
		PassWord pw = new PassWord("1234", 2, 3);
	}
}
