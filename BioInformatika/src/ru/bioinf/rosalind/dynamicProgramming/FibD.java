package ru.bioinf.rosalind.dynamicProgramming;

import java.math.BigDecimal;

/**
 * Проблема №10
 * @see <a href = http://rosalind.info/problems/fib/> Mortal Fibonacci Rabbits.</a>
 * 
 * @author vveprinsky
 */
public class FibD {

	public static void main(String[] args) {
		int n = 95; 	// число месяцев
		int m = 20;		// число месяцев жизни
		
		BigDecimal[] f_n = new BigDecimal[n+1]; // общее количество
		BigDecimal[] f_y = new BigDecimal[n+1]; // количество молодых
		BigDecimal[] f_o = new BigDecimal[n+1]; // количество старых
		
		f_n[1] = new BigDecimal(1);	f_n[2] = new BigDecimal(1);
		f_y[1] = new BigDecimal(1);	f_y[2] = new BigDecimal(0);
		f_o[1] = new BigDecimal(0);	f_o[2] = new BigDecimal(1);
		
		for (int i = 3; i <= n; i++) {
			f_y[i] = f_o[i-1]; 				// молодых в новый месяц
			f_o[i] = f_y[i-1].add(f_o[i-1]);	// старых в новый месяц
			if (i > m)
				f_o[i] = f_o[i].subtract(f_y[i-m]);	// старых померло
			
			f_n[i] = f_y[i].add(f_o[i]); // общее количество
		}
		//for (int i = 1; i < f_n.length; i++) {
		//	System.out.println(" месяц - " + (i) + " кол-во = " + f_n[i]);
		//}
		System.out.println(f_n[n]);	
	}
}
