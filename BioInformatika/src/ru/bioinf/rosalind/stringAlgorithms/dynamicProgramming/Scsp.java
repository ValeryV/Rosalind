package ru.bioinf.rosalind.stringAlgorithms.dynamicProgramming;

/**
 * Interleaving Two Motifs. (shortest common supersequence)
 * @see <a href = http://rosalind.info/problems/scsp/> Two Motifs, One Gene. </a>
 * 
 * @author vveprinsky
 */
public class Scsp {

	public static void main(String[] args) {

		String st1 = "AAATTTGTCATCGAGTATCTCCATTTGCACTCTGTTCACGGGTCCCTTGCTATTGCCAGCGCTCAGGTAGGCTTACTCCCGTGTCATGAAGCGG"; // вертикальная
		String st2 = "AGATCCGCCGCCAGTTGGGCAGGCTGGGACAGCCGGGCTTGTCGATACCAGAGTCGACGGATCCTCCTGTCTTTTAATTCAATGGTACGCACGG";

		// динамическим программированием заполним матрицу со счётом(Score) и backtracing
		int[][] score = new int[st1.length()+1][st2.length()+1];
		int[][] backtrack = new int[st1.length()+1][st2.length()+1]; // можно обойтись и без неё (0 - по диагонали, 1 влево, 2 вверх)
		
		for (int i = 0; i < st1.length(); i++) {
			for (int j = 0; j < st2.length(); j++) {
				if (st1.charAt(i) == st2.charAt(j)) {
					score[i+1][j+1] = score[i][j]+1;
				} else {
					int scoreLink = score[i+1][j];
					int scoreTop = score[i][j+1];
					if (scoreLink >= scoreTop) {
						score[i+1][j+1] = scoreLink ;
						backtrack[i+1][j+1] = 1;
					} else {
						score[i+1][j+1] = scoreTop ;
						backtrack[i+1][j+1] = 2;
					}
				}
			}
		}
		
		// тестовый вывод матриц
		/*
		System.out.println(Arrays.toString(("0" + st2).toCharArray()));
		for (int i = 0; i < score.length; i++) {			
			System.out.println(Arrays.toString(score[i]));
		}
		
		System.out.println();
		for (int i = 0; i < backtrack.length; i++) {			
			System.out.println(Arrays.toString(backtrack[i]));
		}
		*/
		// пройдём по бектракингу и вернём результат
		StringBuffer res = new StringBuffer();
		int i = st1.length();
		int j = st2.length();
		while ((i>0)||(j>0)) {
			//System.out.println("(" + i + "," + j + ") = " + backtrack[i][j]);
			if (backtrack[i][j] == 0){
				// совпадение
				// смотреть достижение границ!
				if (j>0) { 
					res.insert(0, st2.charAt(j-1)); 
				}else{
					res.insert(0, st1.charAt(i-1));
				}
				if (j>0) j--;
				if (i>0) i--;
			} else {
				if (backtrack[i][j] == 1) {
					res.insert(0, st2.charAt(j-1)); // сдвиг влево
					j--;
				} else {
					res.insert(0, st1.charAt(i-1)); // сдвиг вверх
					i--;
				}
			}
		}
		
		System.out.println(res);
	}
}
