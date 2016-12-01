package com.pedigree.R;

public class Diag {
	
	private static double[][] array;
	public static double[][] diag(int n){
		array = new double[n][n];
		for(int i=0; i<n; i++){
			array[i][i]=1;
		}
		return array;
	}
}
