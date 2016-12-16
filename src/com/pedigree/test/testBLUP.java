package com.pedigree.test;

import com.pedigree.main.A;
import com.pedigree.main.BLUP;
import com.pedigree.operate.FilePhe;

public class testBLUP {
	public static void main(String [] args){
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file1.csv" ;
		String phefile = "D:\\2015student\\11-17\\standard pedigree file2.csv";

		boolean standard_id = false;
		boolean file_output = false;
		double[][] Aall = A.A(pedigreepath, standard_id, file_output);
		double[][] phe = FilePhe.read(phefile);
		double[] phe1 = FilePhe.operate(phe);
		double[] B = BLUP.BLUP(phe1, Aall);
		
		for(double num:B){
			System.out.println(num+" ");
		}
		
		
		
	}
}
