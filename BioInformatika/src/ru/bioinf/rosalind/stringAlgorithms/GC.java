package ru.bioinf.rosalind.stringAlgorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Проблема №5
 * @see <a href = http://rosalind.info/problems/gc/> Computing GC Content. </a>
 * 
 * @author vveprinsky
 */
public class GC {

	
	public static void main(String[] args) throws IOException {
		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new FileReader("d:/temp/rosalind_gc(1).txt"));
		Map<String, String> strDNAs = new HashMap<String, String>();	// набор строк DNA
		Map<String, Double> gcDNAs = new HashMap<String, Double>();		// содержание GC
		
		// разбор файла
		String stringDNA;
		StringBuffer nameDNA = null;
		StringBuffer strDNA = null;
		while ((stringDNA = inputStream.readLine()) != null) {
			if (stringDNA.startsWith(">")){
				// запись предыдущей
				if (strDNA != null) { // все кроме первой
					strDNAs.put(nameDNA.toString(), strDNA.toString());
					gcDNAs.put(nameDNA.toString(), computeGC(strDNA.toString()));
				}
				nameDNA = new StringBuffer(stringDNA);
				strDNA = new StringBuffer();
			} else {
				strDNA.append(stringDNA);
			}
		}
		// запись последней
		strDNAs.put(nameDNA.toString(), strDNA.toString());
		gcDNAs.put(nameDNA.toString(), computeGC(strDNA.toString()));
		
		inputStream.close();
		
		// вычислим максимальный
		String maxKey = null;
		double maxValue = -1;
		for (String st : gcDNAs.keySet()) {
			if (maxValue < gcDNAs.get(st)){
				maxValue = gcDNAs.get(st);
				maxKey = st;
			}
		}
		System.out.println(maxKey.substring(1));
		System.out.println(String.format("%.6f", gcDNAs.get(maxKey)).replace(',','.'));
	}

	public static double computeGC(String strDNA){
		char[] dna = strDNA.toCharArray();
		int countC = 0;
		int countG = 0;
		for (int i = 0; i < dna.length; i++) {
			switch (dna[i]) {
				case 'C': countC++; break;
				case 'G': countG++; break;
			}
		}
		double result = ((countC + countG) / (double) dna.length)*100;
		return result;
	}
}
