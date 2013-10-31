package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ru.bioinf.rosalind.common.FileUtils;

/**
 * @see <a href = http://rosalind.info/problems/edta//> Edit Distance Alignment. </a>
 * 
 * @author vveprinsky
 */
public class Edta {

	
	public static void main(String[] args) {
	
		
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_edta.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); // вертикальная
			char[] st2 = strDNAs.get(1).toCharArray();	// горизонтальная
			//st1 = "sunday".toCharArray();
			//st2 = "saturday".toCharArray();
			
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
					if(st1[i]==st2[j])// не нужно операции никакой
						score[i+1][j+1] = score[i][j];
					else {
						// минимальный счёт
						int delScore = score[i][j+1] + 1;	// удаление из первой строки (сдвиг вверх)
						int instScore = score[i+1][j] + 1;	// вставка во вторую строку (сдвиг влево)
						int subScore = score[i][j] +1;		// замена
						int tmp = subScore;
						if(tmp>delScore){
							tmp = delScore;
						}
						if(tmp>instScore){
							tmp = instScore;
						}
						score[i+1][j+1] = tmp;
					}
				}
			}
			/*for (int i = 0; i < score.length; i++) {			
				System.out.println(Arrays.toString(score[i]));
			}*/

			System.out.println(score[st1.length][st2.length]);
			
			// сформируем выравнивание
			StringBuffer res1 = new StringBuffer();
			StringBuffer res2 = new StringBuffer();
			int i = st1.length;
			int j = st2.length;
			
			while ((i>0)&&(j>0)) {
				// рассматриваем текущее положение и смотрим в какую сторону двикаться для backtracing
				int diag = score[i-1][j-1];
				int left = score[i][j-1];
				int top = score[i-1][j];
				int min = diag;
				if (min > left)
					min = left;
				if (min > top)
					min = top;
				
				if (min==diag) {
					// движение по диагонали
					res1.insert(0, st1[i-1]);
					res2.insert(0, st2[j-1]);
					i--;
					j--;
				} else {
					if (min==left) {
						// движение влево
						res1.insert(0, "-");
						res2.insert(0, st2[j-1]);
						j--;
					} else {
						// движение вверх (min==top)
						res1.insert(0, st1[i-1]);
						res2.insert(0, "-");
						i--;
					}
				}
			}
			// Если достигли края не в (0,0)
			if (i > 0)
				for (int k = i; k > 0; k--) {
					res1.insert(0, st1[k-1]);
					res2.insert(0, "-");
				}
			if (j > 0)
				for (int k = j; k > 0; k--) {
					res1.insert(0, "-");
					res2.insert(0, st2[j-1]);
				}
			System.out.println(res1);
			System.out.println(res2);
			
			PrintWriter fout = new PrintWriter("d:/temp/rosalind_edta_res.txt");
			fout.println(score[st1.length][st2.length]);
			fout.println(res1);
			fout.println(res2);
			fout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
