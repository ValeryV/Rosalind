package ru.bioinf.rosalind.combinatorics;

import java.util.ArrayList;

/**
 * Проблема №11
 * @see <a href = http://rosalind.info/problems/perm/> Enumerating Gene Orders. </a>
 * 
 * @author vveprinsky
 */
public class Perm {

	public static int i = 5;
	
	public static void main(String[] args) {
		int f = 1;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int j = 0; j < i; j++) {
			arr.add(j+1);
			f = f * (j+1);
		}
	
	//	for (int j = 0; j < arr.size(); j++) {
	//		System.out.print(arr.get(j) + " ");
	//	}
	//	System.out.println("\n");
		
		System.out.println(f);
		permut("", arr);
	}

	
	public static String permut(String suffix, ArrayList<Integer> arr){
		if (arr.size() < 3){
			System.out.println(suffix + leftP(arr.get(0), arr.get(1)));
			System.out.println(suffix + rightP(arr.get(0), arr.get(1)));
		} else {
			for (int j = 0; j < arr.size(); j++) {
				String newSuffix = suffix + arr.get(j)+ " ";
				ArrayList<Integer> newarr = new ArrayList<Integer>(arr);
				newarr.remove(j);
				permut(newSuffix, newarr);
			}
		}
		return "";	
	}
	
	public static String leftP(int left, int right){
		return left + " " + right;
	}
	
	public static String rightP(int left, int right){
		return right + " " + left;
	}
}
