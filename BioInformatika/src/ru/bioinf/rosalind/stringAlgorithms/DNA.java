package ru.bioinf.rosalind.stringAlgorithms;

/**
 * Проблема №1
 * @see <a href = http://rosalind.info/problems/dna/> Counting DNA Nucleotides. </a>
 * 
 * @author vveprinsky
 */
public class DNA {

	
	public static void main(String[] args) {
		String stringDNA = "TTTCAAGACCAGCCATACTTAAATCTACAGGGAAGAATAGACCGTACGACATAACTATAAAAGGACGGCCGCGAAGCGCCAACCTTATCCCGCGCGCCGCTTGTGCCAGTTCGAAAGGGTATGATTAGTCCCCCGGGAATCCAACGGTTAATGAACTAGAATCACTTGTTCATGGCGTGTTAGAGGCTTGGCCCTGCCGAAGCGTCCGGTAACAACACTTTCAATCCAAGATAGGCTACTTCAGAATTCCTGGTTGAAGAAGTAGAGTCTGCGTTTACGTTTATTAGTACCCAATGCCCGGCGTTCAGGCTCAAAAAATCTGAGTCAACCGTTCCGTGAAGATCACATAGTGTTTTGCACGGCCCTACGAACTACAAGGCGAACCGTCCGACAGCACCAAATCCTATTATTAATATTTAGAAGCGTTCGCTTCACGCAACGAGATTATTATGTCCACCACGAGGAAACACGCTGTTGGGGGCTCAAATAGAACAAATTATTTGGGGGCACCCGGGTGTCACACAATGTGCTGGGTAAGTACCTAGGTGAACACGACTGTGCATAACCAATATCTAGAAACCGCAAACCTAATCAGTGCAGGCCATCAGGGGGTGCTATTTGGGTGATGATGCCACAACTCTTCTGTTTTACCAACATTGGCAGTAGTGGTTTGAAACCTGCGATATAGACGCAGCAGTATGATTCCCCCGTTGTCCAACTCGTTACAACCCAGTTACTCCACGCGGTTAAGTACTCGGGTGGTTCACGAAAAGGTTTCGTCCACACTGACGGCAGACCTCCGTACCTTGTCGGGCGAAAGGCTTTTCGGTGTAGCGGGGATCGGTTAACAGTAAGTATCATGTGTTGCACCACGCTAGAACGATTCACCGAAACTATGCTTGAGA";
		int countA = 0;
		int countT = 0;
		int countC = 0;
		int countG = 0;
		char[] dna = stringDNA.toCharArray();
		for (int i = 0; i < dna.length; i++) {
			switch (dna[i]) {
				case 'A': countA++; break;
				case 'T': countT++; break;
				case 'C': countC++; break;
				case 'G': countG++; break;
				default: System.out.println("unknown symbol = " + dna[i]);break;
			}
		}
		System.out.println(countA + " " + countC + " " + countG + " " + countT);
	}

}
