package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.NumberUtils;

/**
 * @see <a href = http://rosalind.info/problems/ctea/> Counting Optimal Alignments. </a>
 * 
 * @author vveprinsky
 */
public class CTEA {

	// А вообще это единственный алгоритм, который я не понял.
	// Версия лучше лежит тут - https://github.com/watashi/AlgoSolution/blob/master/rosalind/pl/ctea.pl
	
	public static void main(String[] args) {
	
		
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_ctea.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); // вертикальная
			char[] st2 = strDNAs.get(1).toCharArray();	// горизонтальная
			//st1 = "PLEASANTLY".toCharArray();
			//st2 = "MEANLY".toCharArray();
			
			int[][] score = calcScoreMatrix(st1, st2);
			
			
			//for (int i = 0; i < score.length; i++) {			
			//	System.out.println(Arrays.toString(score[i]));
			//}
			System.out.println("счёт = " + score[st1.length][st2.length]);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Вычисление матрицы расстояний между двумя последовательностями.
	 */
	public static int[][] calcScoreMatrix(char[] st1, char[] st2) {
		int[][] score = new int[st1.length+1][st2.length+1];
		long[][] count = new long[st1.length+1][st2.length+1];
		// расстояния между одной строкой и другой пустой
		for (int i = 0; i <= st1.length; i++) {
			score[i][0] = i;
			count[i][0] = 1;
		}
		for (int i = 0; i <= st2.length; i++) {
			score[0][i] = i;
			count[0][i] = 1;
		}

		// заполним матрицу (кроме нулевой строки и нулевого столбца)
		for (int i = 0; i < st1.length; i++) {
			for (int j = 0; j < st2.length; j++) {
				// минимальный счёт
				int delScore = score[i][j + 1] + 1;
				int instScore = score[i + 1][j] + 1;
				int subScore = score[i][j] + (st1[i] == st2[j] ? 0 : 1);
				
				score[i + 1][j + 1] = NumberUtils.getMin(delScore, instScore, subScore);
				
				if (delScore == score[i + 1][j + 1]) count[i+1][j+1] = count[i+1][j+1] + count[i][j+1];
				if (instScore == score[i + 1][j + 1]) count[i+1][j+1] = count[i+1][j+1] + count[i+1][j];
				if (subScore == score[i + 1][j + 1]) count[i+1][j+1] = count[i+1][j+1] + count[i][j];
			}
		}
				
		/*System.out.println("количество матрица:");
		for (int i = 0; i < count.length; i++) {			
			System.out.println(Arrays.toString(count[i]));
		}*/
		System.out.println("(" + (count[st1.length][st2.length]) + ") количество: " + (count[st1.length][st2.length] % 134217727));
		
		return score;
	}
}
