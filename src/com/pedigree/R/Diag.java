package com.pedigree.R;

public class Diag {
	
	private static int[][] array;
	public static int[][] diag(int n){
		array = new int[n][n];
		for(int i=0; i<n; i++){
			array[i][i]=1;
		}
		return array;
	}
}
