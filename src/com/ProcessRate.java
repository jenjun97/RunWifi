package com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Bidi;

/**
 * 進度
 */
public class ProcessRate {

	private String psStr;

	private int psSize;

	// 次數累計
	private BigDecimal num;

	// 最大次數
	private BigDecimal den;

	// 建構子
	public ProcessRate(String psStr, int psSize) {
		this.psStr = psStr;
		this.psSize = psSize;
		init();
	}

	// 初始化
	private void init() {
		num = BigDecimal.ZERO;
		den = new BigDecimal(psStr.length());

		// 算出最大次數
		BigDecimal tempDen = den;
		for (int i = 1; i < psSize; i++) {
			tempDen = tempDen.multiply(den);
		}
		den = tempDen;

	}

	// 進度
	public String getRate() {
		num = num.add(BigDecimal.ONE);
		String str = "次數>" + num.toString() + "\t進度"
				+ (num.divide(den, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100))) + "%";
		return str;
	}

}
