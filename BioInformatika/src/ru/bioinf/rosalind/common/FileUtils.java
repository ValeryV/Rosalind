package ru.bioinf.rosalind.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Работа с файлами.
 * @author vveprinsky
 *
 */
public class FileUtils {

	/**
	 * Колекция (HashMap) DNA-строк с названиями из файла FASTA-формата.
	 * @param fName целевой файл.
	 * @throws IOException 
	 */
	public static Map<String, String> readFileFASTA(String fName) throws IOException{
		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new FileReader(fName));
		Map<String, String> strDNAs = new HashMap<String, String>();	// набор строк DNA
		
		String stringDNA;
		StringBuffer nameDNA = null;
		StringBuffer strDNA = null;
		while ((stringDNA = inputStream.readLine()) != null) {
			if (stringDNA.startsWith(">")){
				// запись предыдущей
				if (strDNA != null) { // все кроме первой
					strDNAs.put(nameDNA.deleteCharAt(0).toString(), strDNA.toString());
				}
				nameDNA = new StringBuffer(stringDNA);
				strDNA = new StringBuffer();
			} else {
				strDNA.append(stringDNA);
			}
		}
		// запись последней
		strDNAs.put(nameDNA.deleteCharAt(0).toString(), strDNA.toString());
		inputStream.close();
		return strDNAs;
	}
	
	/**
	 * Колекция (ArrayList) DNA-строк без названий но в порядке следования из файла FASTA-формата.
	 * @param fName целевой файл.
	 * @throws IOException 
	 */
	public static List<String> readFileFASTA2(String fName) throws IOException{
		BufferedReader inputStream = null;
		inputStream = new BufferedReader(new FileReader(fName));
		List<String> strDNAs = new ArrayList<String>();	// набор строк DNA
		
		String stringDNA;

		StringBuffer strDNA = null;
		while ((stringDNA = inputStream.readLine()) != null) {
			if (stringDNA.startsWith(">")){
				// запись предыдущей
				if (strDNA != null) { // все кроме первой
					strDNAs.add(strDNA.toString());
				}
				strDNA = new StringBuffer();
			} else {
				strDNA.append(stringDNA);
			}
		}
		// запись последней
		strDNAs.add(strDNA.toString());
		inputStream.close();
		return strDNAs;
	}
}
