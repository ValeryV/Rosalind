package ru.bioinf.rosalind.stringAlgorithms;

import java.io.IOException;
import java.util.List;
import ru.bioinf.rosalind.common.FileUtils;


/**
 * Finding a Spliced Motif.
 * @see <a href = http://rosalind.info/problems/sseq/> Motifs Are Rarely Contiguous. </a>
 * 
 * @author vveprinsky
 */
public class Sseq {

	
	public static void main(String[] args) {
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_sseq.txt");
			char[] dnaS = strDNAs.get(0).toCharArray(); // строка для поиска
			char[] dnaT = strDNAs.get(1).toCharArray(); // строка c искомым
			
			int j = 0; // для позиций
			for (int i = 0; i < dnaT.length; i++) {
				while (dnaT[i] != dnaS[j]) {
					j++;
				}
				j++;
				System.out.print(j+" ");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
