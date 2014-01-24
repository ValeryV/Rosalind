package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.common.Blosum62;
import ru.bioinf.rosalind.common.FileUtils;

/**
 * @see <a href = http://rosalind.info/problems/gcon/> Global Alignment with Constant Gap Penalty. </a>
 * 
 * @author vveprinsky
 */
public class Gcon {

	// КОНСТАНТНОЕ УВЕЛИЧЕНИЕ ШТРАФА!
	private static final int gap = -5; //gap penalty 
	
	public static void main(String[] args) {
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_gcon.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); // вертикальная (первая)
			char[] st2 = strDNAs.get(1).toCharArray();	// горизонтальная (вторая)
			st1 = "PLEASANTLY".toCharArray();
			st2 = "MEANLY".toCharArray();
			
			int[][] score = new int[st1.length+1][st2.length+1];
			//int[][] diag = new int[st1.length+1][st2.length+1];
			//int[][] hor = new int[st1.length+1][st2.length+1];
			//int[][] vert = new int[st1.length+1][st2.length+1];
			
			// инициализация
			diag[1][1] = Blosum62.getScore(st1[0],st2[0]);
			for (int i = 0; i < st2.length; i++) {
				diag[0][i] = Blosum62.getScore(st1[0],st2[i]) + gap;
				hor[0][i] = 2*gap;
			}
			for (int i = 0; i < st1.length; i++) {
				diag[i][0] = Blosum62.getScore(st1[i],st2[0]) + gap;
				vert[i][0] = 2*gap;
			}
			
			boolean wasGapDel = false;	// предыдущий шаг был вставкой (удаление\замена)
			boolean wasGapIns = false;
			
			// заполним матрицу (кроме нулевой строки и нулевого столбца)
			for (int i = 0; i < st1.length; i++) {
				for (int j = 0; j < st2.length; j++) {
					/*
					int substScore = Blosum62.getScore(st1[i],st2[j]);	// счёт для подстановки
					int subScore = score[i][j] + substScore;			// замена символа (match\mismatch)					
					
					int delScore;				// удаление из первой строки (сдвиг вверх)
					int instScore;				// вставка во вторую строку (сдвиг влево)
					
					System.out.println(i);
					if (wasGapDel) {
						//предыдущий шаг был вставкой пробела! Новых штрафов не требуется.
						delScore = score[i][j+1];
						System.out.println("wasgapdel");
					} else {
						delScore = score[i][j+1] + gap;
						System.out.println("normdel");
					}
					
					if (wasGapIns) {
						//предыдущий шаг был вставкой пробела! Новых штрафов не требуется.
						instScore = score[i+1][j];
						System.out.println("wasgapins");
					} else {
						instScore = score[i+1][j] + gap;
						System.out.println("norminst");
					}
					
					int max = subScore;		// максимальный счёт
					wasGapIns = false;
					wasGapDel = false;
					if(max < delScore){
						max = delScore;
						wasGapDel = true;
						System.out.println("del");
					}
					if(max < instScore){
						max = instScore;
						wasGapIns = true;
						System.out.println("inst");
					}
					score[i+1][j+1] = max;
				*/}
			}
			
			//for (int i = 0; i < score.length; i++) {			
			//	System.out.println(Arrays.toString(score[i]));
			//}

			System.out.println(score[st1.length][st2.length]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
