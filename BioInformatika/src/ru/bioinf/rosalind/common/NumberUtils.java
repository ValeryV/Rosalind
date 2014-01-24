package ru.bioinf.rosalind.common;

import java.util.List;
import java.util.Map;

/** полезные числовые функции*/
public class NumberUtils {

	/** максимальное из аргументов */
	public static int getMax(int num1, int num2, int num3){
		int max = num1;
		if (max < num2) max = num2;
		if (max < num3) max = num3;
		return max;
	}
	/** максимальное из аргументов */
	public static int getMin(int num1, int num2, int num3){
		int min = num1;
		if (min > num2) min = num2;
		if (min > num3) min = num3;
		return min;
	}
}
