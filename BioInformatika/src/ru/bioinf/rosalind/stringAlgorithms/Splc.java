package ru.bioinf.rosalind.stringAlgorithms;

import java.io.IOException;
import java.util.List;
import ru.bioinf.rosalind.common.CodonTable;
import ru.bioinf.rosalind.common.FileUtils;


/**
 * RNA Splicing.
 * @see <a href = http://rosalind.info/problems/splc/> Genes are Discontiguous. </a>
 * 
 * @author vveprinsky
 */
public class Splc {

	
	public static void main(String[] args) {
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_splc.txt");
			StringBuffer dnaString = new StringBuffer(strDNAs.get(0));// RNA строка
			
			// удаление всех интронов
			for (int i = 1; i < strDNAs.size(); i++) {
				dnaString = new StringBuffer(dnaString.toString().replaceAll(strDNAs.get(i), ""));
			}

			// кодирование протеина
			StringBuffer protein = new StringBuffer();
			for (int i = 0; i <= dnaString.length()-3; i=i+3) {
				char[] codon = new char[3];
				dnaString.getChars(i, i+3, codon, 0); 
				
				String aa = CodonTable.CODONS.get((new String(codon)).replaceAll("T", "U")); // аминокислота
				if (!aa.equals("Stop"))
					protein.append(aa);
			}
			System.out.println(protein);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
