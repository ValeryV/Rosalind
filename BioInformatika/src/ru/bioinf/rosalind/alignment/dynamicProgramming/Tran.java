package ru.bioinf.rosalind.alignment.dynamicProgramming;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.common.FileUtils;

/**
 * @see <a href = http://rosalind.info/problems/tran/> Transitions and Transversions. </a>
 * 
 * @author vveprinsky
 */
public class Tran {

	
	public static void main(String[] args) {
	
		
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_tran.txt");
			char[] st1 = strDNAs.get(0).toCharArray(); 
			char[] st2 = strDNAs.get(1).toCharArray();
			long transitionCount = 0;
			long transversionCount = 0;
		
			for (int i = 0; i < st1.length; i++) {
				if(st1[i]!= st2[i]){ // различие в основании
					if (isTransition(st1[i], st2[i]))
						transitionCount++;
					else
						transversionCount++;
				}
			}
			System.out.println((double)transitionCount/transversionCount);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// тип мутации transition
	public static boolean isTransition(char s1, char s2){
		if ((s1=='A')&&(s2=='G')) return true;
		if ((s1=='G')&&(s2=='A')) return true;
		if ((s1=='C')&&(s2=='T')) return true;
		if ((s1=='T')&&(s2=='C')) return true;
		return false;
	}
}
