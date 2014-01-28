package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.alignment.Hamm;
import ru.bioinf.rosalind.common.FileUtils;

/**
 * @see <a href = http://rosalind.info/problems/ctea/> Counting Optimal Alignments. </a>
 * 
 * @author vveprinsky
 */
public class CTEA_old {

	private static int[][] score; // матрица счетов
	private static char[] st1;
	private static char[] st2;
	private static long optAligCount = 0; // количество оптимальных выравниваний
	//private static boolean firstShorter = true;
	//private static boolean secondShorter = true;
	private static int optimalDistanz;
	
	//private static int temp = 0;
	//private static int k = 1;
	
	public static void main(String[] args) {
	
		
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_ctea.txt");
			st1 = strDNAs.get(0).toCharArray(); // вертикальная
			st2 = strDNAs.get(1).toCharArray();	// горизонтальная
			st1 = "PLEASANTLY".toCharArray();
			st2 = "MEANLY".toCharArray();
			
			System.out.println(st1.length +" " +st2.length );
			
		//	if (st1.length >= st2.length) firstShorter = false;
		//	if (st1.length <= st2.length) secondShorter = false;
			
			score = Edit.calcScoreMatrix(st1, st2);			
			for (int i = 0; i < score.length; i++) {			
				System.out.println(Arrays.toString(score[i]));
			}

			// оптимальное расстояние
			optimalDistanz = score[st1.length][st2.length];
			System.out.println("optimalDistanz = " + optimalDistanz);
			
			getAligment(/*new StringBuffer(), new StringBuffer(), */st1.length, st2.length);
			
			System.out.println("optAligCount = " + optAligCount);
			//System.out.println(temp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static void getAligment(/*StringBuffer res1, StringBuffer res2, */int pos_i, int pos_j) {
		// сформируем выравнивание
		int i = pos_i;
		int j = pos_j;
		
		if ((i>0)&&(j>0)) {
			// рассматриваем текущее положение и смотрим в какую сторону двикаться для backtracing
			int diag = score[i-1][j-1];
			int left = score[i][j-1];
			int top = score[i-1][j];
			//int countScore = score[i][j];
			int min = diag;
			if (min > left)
				min = left;
			if (min > top)
				min = top;
			
			if ((diag == min)&&(st1[i-1]==st2[j-1])) {
				// беспрецендентная ветка по диаганали 
				// (остальные будут хуже по счёту!)
				/*StringBuffer res1_ = new StringBuffer();
				res1_.append(res1);
				res1_.insert(0, st1[i-1]);
				StringBuffer res2_ = new StringBuffer();
				res2_.append(res2);
				res2_.insert(0, st2[j-1]);*/				
				//int i_ = i-1;
				//int j_ = j-1;
				getAligment(/*res1_, res2_, i_, j_*/i-1,j-1);
			} else {
				if ((st1[i-1]==st2[j-1])||(diag == min)) {
					// вариант по диаганали
					/*StringBuffer res1_ = new StringBuffer();
					res1_.append(res1);
					res1_.insert(0, st1[i-1]);
					StringBuffer res2_ = new StringBuffer();
					res2_.append(res2);
					res2_.insert(0, st2[j-1]);				
					int i_ = i-1;
					int j_ = j-1;*/
					getAligment(/*res1_, res2_, i_, j_*/i-1,j-1);
				}
				if (left == min) {
					// движение влево
					/*StringBuffer res1_ = new StringBuffer();
					res1_.append(res1);
					res1_.insert(0, "-");
					StringBuffer res2_ = new StringBuffer();
					res2_.append(res2);
					res2_.insert(0, st2[j-1]);
					int j_ = j-1;*/
					getAligment(/*res1_, res2_, */i, j-1);
				}
				if (top == min) {
					// движение вверх
					/*StringBuffer res1_ = new StringBuffer();
					res1_.append(res1);
					res1_.insert(0, st1[i-1]);
					StringBuffer res2_ = new StringBuffer();
					res2_.append(res2);
					res2_.insert(0, "-");
					int i_ = i-1;*/
					getAligment(/*res1_, res2_, */i-1, j);
				}
			}
			
			
			//System.out.println(st1[i-1]+" "+st2[j-1]);
			// все минимумы плюс диаганаль если больше!
			
	/*		
			if ((diag < countScore)||((diag == countScore)&&(st1[i-1]==st2[j-1]))) {
				// движение по диагонали
				//res1.insert(0, st1[i-1]);
				//res2.insert(0, st2[j-1]);
				StringBuffer res1_ = new StringBuffer();
				res1_.append(res1);
				res1_.insert(0, st1[i-1]);
				StringBuffer res2_ = new StringBuffer();
				res2_.append(res2);
				res2_.insert(0, st2[j-1]);
				//i--;
				//j--;				
				int i_ = i-1;
				int j_ = j-1;
				getAligment(res1_, res2_, i_, j_);
				//i = i_;
				//j = j_;
			}
			if ((left <= countScore)&&(firstShorter)) {
				// движение влево
				//res1.insert(0, "-");
				//res2.insert(0, st2[j-1]);
				StringBuffer res1_ = new StringBuffer();
				res1_.append(res1);
				res1_.insert(0, "-");
				StringBuffer res2_ = new StringBuffer();
				res2_.append(res2);
				res2_.insert(0, st2[j-1]);
				//j--;
				int j_ = j-1;
				getAligment(res1_, res2_, i, j_);
				//j = j_;
			}
			if ((top <= countScore)&&(secondShorter)) {
				// движение вверх
				//res1.insert(0, st1[i-1]);
				//res2.insert(0, "-");
				StringBuffer res1_ = new StringBuffer();
				res1_.append(res1);
				res1_.insert(0, st1[i-1]);
				StringBuffer res2_ = new StringBuffer();
				res2_.append(res2);
				res2_.insert(0, "-");
				//i--;
				int i_ = i-1;
				getAligment(res1_, res2_, i_, j);
				//i = i_;
			}
			//i--;
			//j--;
			  
			  
			 */
		} else {
	/*				
		// Если достигли края не в (0,0)
		if (i > 0 && (j==0))
			for (int k = i; k > 0; k--) {
				res1.insert(0, st1[k-1]);
				res2.insert(0, "-");
			}
		if (j > 0 && (i==0))
			for (int k = j; k > 0; k--) {
				res1.insert(0, "-");
				res2.insert(0, st2[k-1]);
			}
		*/
	//	if (Hamm.calcHammingDistance(res1.toString(), res2.toString()) <= optimalDistanz) {
			optAligCount++;
		//	System.out.println("optimum = " + res1);
		//	System.out.println("optimum = " + res2);
		//	System.out.println(optAligCount);
	//	}
	//	if ((optAligCount % 1000000000) == 0) System.out.println(optAligCount);

			
			
		//System.out.println(res1);
		//System.out.println(res2);
		//System.out.println("end iteration");
	//	temp++;
		}
	}
		
		

}
