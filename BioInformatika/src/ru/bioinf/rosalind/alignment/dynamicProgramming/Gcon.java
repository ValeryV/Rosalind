package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.common.Blosum62;
import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.NumberUtils;

/**
 * @see <a href = http://rosalind.info/problems/gcon/> Global Alignment with Constant Gap Penalty. </a>
 * 
 * @author vveprinsky
 */
public class Gcon {

	// КОНСТАНТНОЕ УВЕЛИЧЕНИЕ ШТРАФА!
	private static final int gapInst = -5/*-3*/;	//gap penalty за открытие нового
	private static final int gapExt = 0/*-1*/;	//gap penalty за продолжение
	
	public static void main(String[] args) {
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_gcon.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); // вертикальная (первая)
			char[] st2 = strDNAs.get(1).toCharArray();	// горизонтальная (вторая)
			st1 = "PLEASANTLY".toCharArray();
			st2 = "MEANLY".toCharArray();

			//st1 = "ALTGTGGLR".toCharArray();
			//st2 = "ATGGGR".toCharArray(); // из крутой книжки http://www.ecs.umass.edu/~mettu/ece597m/readings/TextChapter1.pdf
			
			
			int[][] score = new int[st1.length+1][st2.length+1];
			
			// расстояния между одной строкой и другой пустой
			for (int i = 1; i <= st1.length; i++) {
				score[i][0] = gapInst + gapExt*i; //TODO 1
			}
			for (int i = 1; i <= st2.length; i++) {
				score[0][i] = gapInst + gapExt*i;	//TODO 1
			}
			
			
			// заполним матрицу (кроме нулевой строки и нулевого столбца)
			for (int i = 0; i < st1.length; i++) {
				for (int j = 0; j < st2.length; j++) {
					// оцениваемая ячейка i+1,j+1
					
					
					int substScore = Blosum62.getScore(st1[i],st2[j]);	// счёт для подстановки
					int subScore = score[i][j] + substScore;			// замена символа (match\mismatch)					
					
					/* тоже книжный вариант
					if (st1[i] == st2[j])
						subScore = score[i][j] + 3;
					else
						subScore = score[i][j] + 1;
					*/
					
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
						hor[j-k] = score[i+1][j+1-k] + gapInst + gapExt*k;//TODO 1
					}
					h = NumberUtils.getMax(hor);
					
					for (int l = 1; l <= i; l++){
						vert[i-l] = score[i+1-l][j+1] + gapInst  + gapExt*l;//TODO 1
					}
					v = NumberUtils.getMax(vert);
					
					score[i+1][j+1] =  NumberUtils.getMax(subScore, h, v);
				}
			}
			
			System.out.println("  " + Arrays.toString(score[0]));
			for (int i = 1; i < score.length; i++) {			
				System.out.println(st1[i-1] +" " + Arrays.toString(score[i]));
			}

			System.out.println(score[st1.length][st2.length]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
