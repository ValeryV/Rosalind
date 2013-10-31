package ru.bioinf.rosalind.stringAlgorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.StringsUtils;


/**
 * Finding a Motif in DNA. (longest common substring)
 * @see <a href = http://rosalind.info/problems/lcsm/> Searching Through the Haystack. </a>
 * 
 * @author vveprinsky
 */
public class Lcsm {

	
	public static void main(String[] args) {

		try {
			Map<String, String> strDNAs = FileUtils.readFileFASTA("d:/temp/rosalind_lcsm.txt");
			// имеет смысл выбрать самую короткую строку, которая теоритически может быть самым длинным мотивом
			String minStr = StringsUtils.getMinString(strDNAs);
			int size = minStr.length();
			List<String> motifs = new ArrayList<String>();
			for (int i = 0; i < size - 1; i++) {
				//System.out.println("итерация - " + i + " мотивы для обработки:");
				for (int j = 0; j < size-i; j++) {
					String motif = minStr.substring(i, size-j);
					// c каждым шагом длина мотива уменьшается, поэтому первый найденый максимален
					//System.out.print(motif + " ");
					if (StringsUtils.isSubstringOfdnas(motif, strDNAs)){
						motifs.add(motif);
						break;
					}
				}
				//System.out.println();
			}

			/*System.out.println("Мотивы");
			for (String st : motifs) {
				System.out.println(st);
			}
			System.out.println();*/
			//String maxMotif = Collections.max(motifs);
			String maxMotif = StringsUtils.getMaxStringFromList(motifs);
			System.out.println(maxMotif);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
