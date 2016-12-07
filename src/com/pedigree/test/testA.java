package com.pedigree.test;
import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import com.pedigree.main.A;;


public class testA {
	public static void main(String[] args) {
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file1.csv" ;
		boolean standard_id = false;
		boolean file_output = false;
		double[][] Aall = A.A(pedigreepath, standard_id, file_output);
		
		for(int i=0; i<Aall.length; i++){
			for(int j=0; j<Aall[0].length; j++){
				System.out.print(Aall[i][j]+" ");
			}System.out.println();
		}
		
		EigenvalueDecomposition eig = new Matrix(Aall).eig();
		Aall = eig.getD().getArray();
		
		for(int i=0; i<Aall.length; i++){
			for(int j=0; j<Aall[0].length; j++){
				System.out.print(Aall[i][j]+" ");
			}System.out.println();
		}
		
	}
}
