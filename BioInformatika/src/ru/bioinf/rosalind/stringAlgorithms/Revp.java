package ru.bioinf.rosalind.stringAlgorithms;

import java.io.IOException;
import java.util.Map;
import ru.bioinf.rosalind.common.FileUtils;
import ru.bioinf.rosalind.common.StringsUtils;


/**
 * Locating Restriction Sites.
 * @see <a href = http://rosalind.info/problems/revp/> Locating Restriction Sites. </a>
 * 
 * @author vveprinsky
 */
public class Revp {

	
	public static void main(String[] args) {

		try {
			// файл с одной строкой
			Map<String, String> strDNAs = FileUtils.readFileFASTA("d:/temp/rosalind_revp.txt");			
			String source = StringsUtils.getMinString(strDNAs);
			//System.out.println(source.length());
			//source = "TCAATGCATGCGGGTCTATATGCAT";
			// palindrome in the string having length between 4 and 12
			for (int i = 0; i <= source.length()-4; i++) {
				for (int j = 12; j >= 4;) {
					if (i <= source.length()-j){
						String subStr = source.substring(i, i+j);
						String revc = Revc.reverseComlement(subStr);
						if (revc.equals(subStr)){
							//System.out.println(j+" = " + revc);
							System.out.println((i+1) + " " + j);
						}
					}
					j = j - 2;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
