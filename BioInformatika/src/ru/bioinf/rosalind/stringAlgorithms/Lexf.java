package ru.bioinf.rosalind.stringAlgorithms;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Enumerating k-mers Lexicographically.
 * @see <a href = http://rosalind.info/problems/lexf/> Enumerating k-mers Lexicographically. </a>
 * 
 * @author vveprinsky
 */
public class Lexf {

	String alphabetInput = "A C G T";	// алфавит
	int k = 4;	// размерность подслов (k-mer)
	char[] alphabet;
	PrintWriter fout;
	
	public Lexf(){
		alphabet = alphabetInput.replaceAll(" ", "").toCharArray();
		try {
			 fout = new PrintWriter("d:/temp/rosalind_lexf.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Lexf().start();		
		System.out.println("done");
	}
	
	public void start(){
		for (int i = 0; i < alphabet.length; i++) {
			sympolsAppend(""+alphabet[i], 1);
		}
		fout.close();
	}
	
	public void sympolsAppend(String prefix, int pos) {
		if (pos == k-1) {
			endSympolsAppend(prefix);
		} else {
			for (int i = 0; i < alphabet.length; i++) {
				sympolsAppend(prefix + alphabet[i], pos+1);
			}
		}
	}
	
	public void endSympolsAppend(String prefix) {
		for (int i = 0; i < alphabet.length; i++) {
			fout.println(prefix + alphabet[i]);
			//System.out.println(prefix + alphabet[i]);
		}
	}
}
