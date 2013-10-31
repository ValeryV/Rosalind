package ru.bioinf.rosalind.common;

import java.util.Hashtable;

/**
 * A table indicating the translation of individual RNA codons into amino acids for the purpose of protein creation.
 * @author vveprinsky
 *
 */
public class CodonTable {

	// таблица кодонов RNA
	public static Hashtable<String, String> CODONS = new Hashtable<String, String>(64);
	
	static {
		CODONS.put("UUU", "F");
		CODONS.put("UUC", "F");
		CODONS.put("CUU", "L");
		CODONS.put("UUA", "L");
		CODONS.put("UUG", "L");
		CODONS.put("CUC", "L");
		CODONS.put("CUA", "L");
		CODONS.put("CUG", "L");
		CODONS.put("UCU", "S");
		CODONS.put("UCC", "S");
		CODONS.put("UCA", "S");
		CODONS.put("UCG", "S");
		CODONS.put("GUU", "V");
		CODONS.put("GUC", "V");
		CODONS.put("GUA", "V");
		CODONS.put("GUG", "V");
		CODONS.put("AUU", "I");
		CODONS.put("AUC", "I");
		CODONS.put("AUA", "I");
		CODONS.put("AUG", "M");
		CODONS.put("CCU", "P");
		CODONS.put("CCC", "P");
		CODONS.put("CCA", "P");
		CODONS.put("CCG", "P");
		CODONS.put("ACU", "T");
		CODONS.put("ACC", "T");
		CODONS.put("ACA", "T");
		CODONS.put("ACG", "T");
		CODONS.put("GCU", "A");
		CODONS.put("GCC", "A");
		CODONS.put("GCA", "A");
		CODONS.put("GCG", "A");
		CODONS.put("UAU", "Y");
		CODONS.put("UAC", "Y");
		CODONS.put("CAU", "H");
		CODONS.put("CAC", "H");
		CODONS.put("AAU", "N");
		CODONS.put("AAC", "N");
		CODONS.put("GAU", "D");
		CODONS.put("GAC", "D");
		CODONS.put("UAA", "Stop");
		CODONS.put("UAG", "Stop");
		CODONS.put("CAG", "Q");
		CODONS.put("CAA", "Q");
		CODONS.put("AAA", "K");
		CODONS.put("AAG", "K");
		CODONS.put("GAA", "E");
		CODONS.put("GAG", "E");
		CODONS.put("UGU", "C");
		CODONS.put("UGC", "C");
		CODONS.put("CGU", "R");
		CODONS.put("CGC", "R");
		CODONS.put("AGU", "S");
		CODONS.put("AGC", "S");
		CODONS.put("GGU", "G");
		CODONS.put("GGC", "G");
		CODONS.put("GGA", "G");
		CODONS.put("GGG", "G");
		CODONS.put("AGA", "R");
		CODONS.put("AGG", "R");
		CODONS.put("CGA", "R");
		CODONS.put("CGG", "R");
		CODONS.put("UGA", "Stop");
		CODONS.put("UGG", "W");
	}
}
