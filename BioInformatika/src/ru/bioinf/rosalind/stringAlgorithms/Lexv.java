package ru.bioinf.rosalind.stringAlgorithms;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Ordering Strings of Varying Length Lexicographically.
 * @see <a href = http://rosalind.info/problems/lexv/> Organizing Strings of Different Lengths. </a>
 * 
 * @author vveprinsky
 */
public class Lexv {

	String alphabetInput = "I T S C U V Y H E P";	// алфавит
	int k = 3;	// размерность подслов (k-mer)
	char[] alphabet;
	PrintWriter fout;
	
	public Lexv(){
		alphabet = alphabetInput.replaceAll(" ", "").toCharArray();
		try {
			 fout = new PrintWriter("d:/temp/rosalind_lexv.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Lexv().start();		
		System.out.println("done");
	}
	
	public void start(){
		for (int i = 0; i < alphabet.length; i++) {
			System.out.println(alphabet[i]);
			fout.println(alphabet[i]);
			sympolsAppend(""+alphabet[i], 1);
		}
		fout.close();
	}
	
	public void sympolsAppend(String prefix, int pos) {
		if (pos == k-1) {
			endSympolsAppend(prefix);
		} else {
			for (int i = 0; i < alphabet.length; i++) {
				System.out.println(prefix + alphabet[i]);
				fout.println(prefix + alphabet[i]);
				sympolsAppend(prefix + alphabet[i], pos+1);
			}
		}
	}
	
	public void endSympolsAppend(String prefix) {
		for (int i = 0; i < alphabet.length; i++) {
			fout.println(prefix + alphabet[i]);
			System.out.println(prefix + alphabet[i]);
		}
	}
}
