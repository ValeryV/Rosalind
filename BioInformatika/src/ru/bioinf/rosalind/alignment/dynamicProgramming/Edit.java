package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.util.List;

import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.NumberUtils;

/**
 * @see <a href = http://rosalind.info/problems/edit/> Edit Distance. </a>
 * 
 * @author vveprinsky
 */
public class Edit {

	
	public static void main(String[] args) {
	
		
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_edit.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); 
			char[] st2 = strDNAs.get(1).toCharArray();
			int[][] score = calcScoreMatrix(st1, st2);
			
			/*for (int i = 0; i < score.length; i++) {			
				System.out.println(Arrays.toString(score[i]));
			}*/
			System.out.println(score[st1.length][st2.length]);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Вычисление матрицы расстояний между двумя последовательностями.
	 */
	public static int[][] calcScoreMatrix(char[] st1, char[] st2) {
		int[][] score = new int[st1.length+1][st2.length+1];
		// расстояния между одной строкой и другой пустой
		for (int i = 0; i <= st1.length; i++) {
			score[i][0] = i;
		}
		for (int i = 0; i <= st2.length; i++) {
			score[0][i] = i;
		}

		// заполним матрицу (кроме нулевой строки и нулевого столбца)
		for (int i = 0; i < st1.length; i++) {
			for (int j = 0; j < st2.length; j++) {
				if (st1[i] == st2[j])// не нужно операции никакой
					score[i + 1][j + 1] = score[i][j];
				else {
					// минимальный счёт
					int delScore = score[i][j + 1] + 1;
					int instScore = score[i + 1][j] + 1;
					int subScore = score[i][j] + 1;
					score[i + 1][j + 1] = NumberUtils.getMin(delScore, instScore, subScore);
				}
			}
		}
		return score;
	}
}
