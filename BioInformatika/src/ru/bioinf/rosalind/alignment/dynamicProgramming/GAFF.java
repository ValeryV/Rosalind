package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.common.Blosum62;
import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.NumberUtils;

/**
 * @see <a href = http://rosalind.info/problems/gaff/> Global Alignment with Scoring Matrix and Affine Gap Penalty. </a>
 * 
 * @author vveprinsky
 */
public class GAFF {

	// АФФИННОЕ УВЕЛИЧЕНИЕ ШТРАФА!
	private static final int gapInst = 11;	// gap open penalty (за открытие нового)
	private static final int gapExt = 1;	// gap extension penalty (за продолжение)
	private static int[][] score;			// матрица счетов
	
	public static void main(String[] args) {
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_gaff.txt");
			char[] st1 = strDNAs.get(0).toCharArray();	// вертикальная (первая)
			char[] st2 = strDNAs.get(1).toCharArray();	// горизонтальная (вторая)
			st1 = "PRTEINS".toCharArray();
			st2 = "PRTWPSEIN".toCharArray();
			
			score = new int[st1.length+1][st2.length+1];
			
			fillScoreMatrix(st1, st2);

			System.out.println(score[st1.length][st2.length]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private static void fillScoreMatrix(char[] st1, char[] st2) {
		// расстояния между одной строкой и другой пустой
		for (int i = 1; i <= st1.length; i++) {
			score[i][0] = -(gapInst + gapExt*(i-1));
		}
		for (int i = 1; i <= st2.length; i++) {
			score[0][i] = -(gapInst + gapExt*(i-1));
		}
		
		
		// заполним матрицу (кроме нулевой строки и нулевого столбца)
		for (int i = 0; i < st1.length; i++) {
			for (int j = 0; j < st2.length; j++) {
				// оцениваемая ячейка i+1,j+1
					
				int substScore = Blosum62.getScore(st1[i],st2[j]);	// счёт для подстановки
				int subScore = score[i][j] + substScore;			// замена символа (match\mismatch)					
				
				
				int[] hor = new int[st2.length+1];	//горизонтальный путь до оцениваемой ячейки
				int[] vert = new int[st1.length+1];	//вертикальный путь до оцениваемой ячейки
				for (int k = 0; k <= st1.length; k++) {
					vert[k] = Integer.MIN_VALUE;
				}
				for (int k = 0; k <= st2.length; k++) {
					hor[k] = Integer.MIN_VALUE;
				}
				
				
				int h = gapInst;		// лучший счёт для горизонтальных вставок
				int v = gapInst;		// лучший счёт для вертикальных вставок
				
				for (int k = 1; k <= j; k++){
					hor[j-k] = score[i+1][j+1-k] - (gapInst + gapExt*(k-1));
				}
				h = NumberUtils.getMax(hor);
				
				for (int l = 1; l <= i; l++){
					vert[i-l] = score[i+1-l][j+1] - (gapInst  + gapExt*(l-1));
				}
				v = NumberUtils.getMax(vert);
				
				score[i+1][j+1] =  NumberUtils.getMax(subScore, h, v);
			}
		}
		
		System.out.println("  " + Arrays.toString(score[0]));
		for (int i = 1; i < score.length; i++) {			
			System.out.println(st1[i-1] +" " + Arrays.toString(score[i]));
		}
	}

	private static void createAlignment(char[] st1, char[] st2) {
		
	}
}
