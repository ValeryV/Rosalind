package ru.bioinf.rosalind.stringAlgorithms;

import java.io.IOException;
import java.util.Map;
import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.StringsUtils;


/**
 * Finding a Motif in DNA.
 * @see <a href = http://rosalind.info/problems/subs/> Finding a Motif in DNA. </a>
 * 
 * @author vveprinsky
 */
public class Subs {

	
	public static void main(String[] args) {

		try {
			Map<String, String> strDNAs = FileUtils.readFileFASTA("d:/temp/cons.txt");
			int size = StringsUtils.getLenght(strDNAs);
			long[][] profile = new long[4][size]; // профиль A C G T
			for (String st : strDNAs.keySet()) {
				char[] dna = strDNAs.get(st).toCharArray();
				for (int i = 0; i < dna.length; i++) {
					switch (dna[i]) {
						case 'A': profile[0][i]++; break;
						case 'C': profile[1][i]++; break;
						case 'G': profile[2][i]++; break;
						case 'T': profile[3][i]++; break;
						default: System.out.println("unknown symbol = " + dna[i]);break;
					}
				}
			}
			
			StringBuffer consensus = new StringBuffer(size);
			for (int i = 0; i < size; i++) {
				long max = profile[0][i];
				char maxAA = 'A';
				if (max < profile[1][i]) {
					max = profile[1][i];
					maxAA = 'C';
				}
				if (max < profile[2][i]) {
					max = profile[2][i];
					maxAA = 'G';
				}
				if (max < profile[3][i]) {
					max = profile[3][i];
					maxAA = 'T';
				}
				consensus.append(maxAA);
			}
			
			System.out.println(consensus);
			System.out.print("A:");
			for (int i = 0; i < size; i++) {
				System.out.print(" " + profile[0][i]);
			}
			System.out.println();
			System.out.print("C:");
			for (int i = 0; i < size; i++) {
				System.out.print(" " + profile[1][i]);
			}
			System.out.println();
			System.out.print("G:");
			for (int i = 0; i < size; i++) {
				System.out.print(" " + profile[2][i]);
			}
			System.out.println();
			System.out.print("T:");
			for (int i = 0; i < size; i++) {
				System.out.print(" " + profile[3][i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
