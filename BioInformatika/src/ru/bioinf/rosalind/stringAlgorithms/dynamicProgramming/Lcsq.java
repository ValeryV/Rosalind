package ru.bioinf.rosalind.stringAlgorithms.dynamicProgramming;

import java.io.IOException;
import java.util.List;
import ru.bioinf.rosalind.common.FileUtils;

/**
 * Finding a Shared Spliced Motif. (longest common subsequence)
 * @see <a href = http://rosalind.info/problems/lcsq/> Locating Motifs Despite Introns. </a>
 * 
 * @author vveprinsky
 */
public class Lcsq {

	
	public static void main(String[] args) {

		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_lcsq.txt");

			//String st1 = "GAC"; // вертикальная
			//String st2 = "AGCAT";
			//st1 = "XMJYAUZ"; // вертикальная
			//st2 = "MZJAWXU";	
			String st1 = strDNAs.get(0);
			String st2 = strDNAs.get(1);
			
			
			// динамическим программированием заполним матрицу со счётом(Score) и backtracing
			int[][] score = new int[st1.length()+1][st2.length()+1];
			int[][] backtrack = new int[st1.length()+1][st2.length()+1]; // можно обойтись и без неё (0 - по диагонали, 1 влево, 2 вверх)
			
			for (int i = 0; i < st1.length(); i++) {
				for (int j = 0; j < st2.length(); j++) {
					if (st1.charAt(i) == st2.charAt(j)) {
						score[i+1][j+1] = score[i][j] + 1;
					} else {
						int scoreLink = score[i+1][j];
						int scoreTopt = score[i][j+1];
						if (scoreLink >= scoreTopt) {
							score[i+1][j+1] = scoreLink;
							backtrack[i+1][j+1] = 1;
						} else {
							score[i+1][j+1] = scoreTopt;
							backtrack[i+1][j+1] = 2;
						}
					}
				}
			}
			
			// тестовый вывод матриц
			/*
			for (int i = 0; i < score.length; i++) {			
				System.out.println(Arrays.toString(score[i]));
			}
			System.out.println();
			for (int i = 0; i < backtrack.length; i++) {			
				System.out.println(Arrays.toString(backtrack[i]));
			}*/
			
			// пройдём по бектракингу и вернём результат
			StringBuffer res = new StringBuffer();
			int i = st1.length();
			int j = st2.length();
			while ((i>0)&&(j>0)) {
				//System.out.println("(" + i + "," + j + ") = " + backtrack[i][j]);
				if (backtrack[i][j] == 0){
					//System.out.println(st1.charAt(i-1));
					//System.out.println(st2.charAt(j-1));
					res.insert(0, st2.charAt(j-1)); // совпадение
					j--;
					i--;
				} else {
					if (backtrack[i][j] == 1)
						j--;
					else
						i--;
				}
			}
			
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
