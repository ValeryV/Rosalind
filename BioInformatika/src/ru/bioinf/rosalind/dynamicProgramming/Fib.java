package ru.bioinf.rosalind.dynamicProgramming;

/**
 * Проблема №4
 * @see <a href = http://rosalind.info/problems/fib/> Rabbits and Recurrence Relations.</a>
 * 
 * @author vveprinsky
 */
public class Fib {

	public static void main(String[] args) {
		int n = 32; // число месяцев
		int k = 1; // число потомков
		long f_n, f_n_1, f_n_2;
		f_n = 1;
		f_n_1 = 1;
		f_n_2 = 1;
		for (int i = 3; i <= n; i++) {
			f_n = f_n_1 + k*f_n_2;
			f_n_2 = f_n_1;
			f_n_1 = f_n;
		} 
		System.out.println(f_n);
	}

}
