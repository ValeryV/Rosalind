package ru.bioinf.rosalind.common;

import java.util.List;
import java.util.Map;

/** полезные функции*/
public class StringsUtils {

	/** минимальная длинна строки в коллекции */
	public static int getMinLenght(Map<String, String> strDNAs){
		int len = 0;
		String minStr = getMinString(strDNAs);
		len = minStr.length();
		return len;
	}
	
	/** строка с минимальной длинной в коллекции */
	public static String getMinString(Map<String, String> strDNAs){
		int len = Integer.MAX_VALUE;
		String str = null;
		for (String st : strDNAs.keySet()) {
			if (len > strDNAs.get(st).length()) {
				len = strDNAs.get(st).length();
				str = strDNAs.get(st);
			}
		}
		return str;
	}
	
	/** строка с максимальной длинной в списке */
	public static String getMaxStringFromList(List<String> strDNAs){
		int len = 0;
		String str = null;
		for (String st : strDNAs) {
			if (len < st.length()) {
				len = st.length();
				str = st;
			}
		}
		return str;
	}
	
	/** длинна первой строки в коллекции */
	public static int getLenght(Map<String, String> strDNAs){
		int len = 0;
		for (String st : strDNAs.keySet()) {
			len = strDNAs.get(st).length();
			break;
		}
		return len;
	}

	
	/** Устанавливает входит ли строка во все строки колекции */
	public static boolean isSubstringOfdnas(String motif, Map<String, String> strDNAs){
		boolean res = false;
		for (String st : strDNAs.keySet()) {
			res = (strDNAs.get(st).indexOf(motif) > -1);
			if (!res) break;
		}
		return res;
	}
}
