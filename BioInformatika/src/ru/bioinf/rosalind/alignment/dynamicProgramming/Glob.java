package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.util.List;

import ru.bioinf.rosalind.common.Blosum62;
import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.NumberUtils;

/**
 * @see <a href = http://rosalind.info/problems/glob/> Global Alignment with Scoring Matrix. </a>
 * 
 * @author vveprinsky
 */
public class Glob {
	
	// ЛИНЕЙНОЕ УВЕЛИЧЕНИЕ ШТРАФА!
	private static final int gap = -5; //gap penalty 
	
	public static void main(String[] args) {
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_glob.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); // вертикальная (первая)
			char[] st2 = strDNAs.get(1).toCharArray();	// горизонтальная (вторая)
			//st1 = "PLEASANTLY".toCharArray();
			//st2 = "MEANLY".toCharArray();
			
			int[][] score = new int[st1.length+1][st2.length+1];
			
			// расстояния между одной строкой и другой пустой
			for (int i = 0; i <= st1.length; i++) {
				score[i][0] = i*gap;
			}
			for (int i = 0; i <= st2.length; i++) {
				score[0][i] = i*gap;
			}
			
			// заполним матрицу (кроме нулевой строки и нулевого столбца)
			for (int i = 0; i < st1.length; i++) {
				for (int j = 0; j < st2.length; j++) {
					
					int substScore = Blosum62.getScore(st1[i],st2[j]);	// счёт для подстановки
					int subScore = score[i][j] + substScore;			// замена символа (match\mismatch)
					int delScore = score[i][j+1] + gap;					// удаление из первой строки (сдвиг вверх)
					int instScore = score[i+1][j] + gap;				// вставка во вторую строку (сдвиг влево)
					int max;											// максимальный счёт
					max = NumberUtils.getMax(subScore, delScore, instScore);
					score[i+1][j+1] = max;
				}
			}
			/*for (int i = 0; i < score.length; i++) {			
				System.out.println(Arrays.toString(score[i]));
			}*/

			System.out.println(score[st1.length][st2.length]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
