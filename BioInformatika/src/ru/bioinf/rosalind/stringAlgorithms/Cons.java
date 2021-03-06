package ru.bioinf.rosalind.stringAlgorithms;


/**
 * Consensus and Profile.
 * @see <a href = http://rosalind.info/problems/cons/> Finding a Most Likely Common Ancestor. </a>
 * 
 * @author vveprinsky
 */
public class Cons {

	
	public static void main(String[] args) {
		String s = "CTTGTTGCTTGTTGGGTGCTCTCCTTGTTGCTTGTTGACTTGTTGCACTTGTTGCTTGTTGTTCGCCGTTGACTTGTTGTACATCTTGTTGCTGTCTTGTTGGTTATTCACCTTGTTGCTTGTTGCCTTGTTGTCTTGTTGCTTGTTGCTTGTTGGTCCGTACTTGTTGGCCTTGTTGCTTGTTGACTCTCATCTTGTTGCTTGTTGCTTGTTGCTTGTTGAGCTACTTGTTGTTCTGCTCGATAACGACTTGTTGCTTGTTGCATGTTGCCTTGTTGCCTTGTTGCCTTGTTGGCTGAGTCTTGTTGTCCTTGTTGAGGACGCCACTAGTGACTTGTTGGGTTCGTACTTGTTGACTTGTTGTCTCTTGTTGGCTTGTTGAACCCTTGTTGGCCTTGTTGTCTTGTTGCTCTTGTTGTCTTGTTGGGTGCACACTTGTTGTTCTTGTTGCGCTTGTTGCAGGCCGGTATAGTTGCGGCTTGTTGGCTTGTTGATTCTTGTTGAGTGCCTTGTTGTCATCTTGTTGAGCTTGTTGTAGCTTGTTGCTTGTTGCTCTTGTTGCATATGTCGCTTGTTGAGCTACTATTAGCTTGTTGCTTGTTGGAGCTTGTTGCTTGTTGGGCTTGTTGCTTGTTGCTTGTTGACTTGTTGCTTGTTGCCTTGTTGCTTGTTGCTTGTTGCCCTGCTTGTTGGTAACGGTATGCCAAGCTTGTTGGCCAAACTTGTTGCAACTCTTGTTGTCTTGTTGTATCCACTTGTTGCTTGTTGTCTTGTTGTCTTGTTGCTTGTTGGCTTGTTGCGCCTTGTTG";
		String t = "CTTGTTGCT";
		int pos = -1;
		int posOld = -1;
		for (int i = 0; i < s.length()-t.length(); i++) {
			pos = s.indexOf(t, i);
			if (pos != -1) {
				if (pos != posOld) {
					System.out.print((pos+1)+" ");
					posOld = pos;
				}
			}
		}
	}
}
