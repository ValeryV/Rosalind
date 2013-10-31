package ru.bioinf.rosalind.stringAlgorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ru.bioinf.rosalind.common.FileUtils;

/**
 * k-Mer Composition.
 * @see <a href = http://rosalind.info/problems/kmer/> k-Mer Composition. </a>
 * 
 * @author vveprinsky
 */
public class Kmer {

	String strDNA; 
	int k = 4;	// размерность подслов (k-mer)
	String[] alphabet = new String[256];	// отсортированные кмеры
	int[] frequencyKmer = new int[256];		// частота встречаемости кмеров в строке

	public Kmer(){
		try {
			List<String> strDNAs = FileUtils.readFileFASTA2("d:/temp/rosalind_kmer.txt");
			strDNA = strDNAs.get(0);
			BufferedReader fin = new BufferedReader(new FileReader("d:/temp/rosalind_kmer_alphabet.txt"));
			String kmer;
			int i = 0;
			while((kmer = fin.readLine())!= null){
				alphabet[i] = kmer;
				i++;
			}
			fin.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Kmer().start();		
	}
	
	public void start(){
		// пройдемся по словарю в лексографическом порядке и посчитаем сколько вхождений
		for (int i = 0; i < alphabet.length; i++) {
			int count = 0;
			int fromIndex = 0;
			int pos = strDNA.indexOf(alphabet[i], fromIndex);
			while (pos>-1) {
				count++;
				fromIndex = pos+1;
				pos = strDNA.indexOf(alphabet[i], fromIndex);
			}
			frequencyKmer[i] = count;
		}
		System.out.println(Arrays.toString(frequencyKmer).replaceAll(",", ""));
	}

}
