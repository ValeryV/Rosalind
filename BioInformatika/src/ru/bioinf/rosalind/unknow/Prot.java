package ru.bioinf.rosalind.unknow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ru.bioinf.rosalind.common.CodonTable;

/**
 * Translating RNA into Protein
 * @see <a href = http://rosalind.info/problems/prot/> Translating RNA into Protein. </a>
 * 
 * @author vveprinsky
 */
public class Prot {

	
	public static void main(String[] args) throws IOException {
		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new FileReader("d:/temp/rosalind_prot.txt"));
		// разбор файла
		String str;
		StringBuffer proteinString = new StringBuffer();
		while ((str = inputStream.readLine()) != null) {
			proteinString.append(str);
		}
		StringBuffer protein = new StringBuffer(); // результат
		for (int i = 0; i <= proteinString.length()-3; i=i+3) {
			char[] codon = new char[3];
			proteinString.getChars(i, i+3, codon, 0); 
			String aa = CodonTable.CODONS.get(new String(codon)); // аминокислота
			if (!aa.equals("Stop"))
				protein.append(aa);
		}	
		System.out.println(protein);
		inputStream.close();
	}
}
