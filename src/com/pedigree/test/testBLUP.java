package com.pedigree.test;

import java.io.IOException;

import com.pedigree.main.A;
import com.pedigree.main.BLUP;
import com.pedigree.operate.FilePhe;

public class testBLUP {
	public static void main(String [] args) throws IOException{
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file.csv" ;
		String phefile = "D:\\2015student\\11-17\\standard pedigree file2.csv";
		String file = "D:\\2015student\\11-17\\张斌2\\系谱数据.txt";
		
		boolean standard_id = false;
		boolean file_output = false;
		double[][] Aall = A.A(pedigreepath, standard_id, file_output);
		double[][] phe = FilePhe.read(phefile);
		double[] phe1 = FilePhe.operate(phe);
		double[] B = BLUP.BLUP(phe1, Aall);
		
		for(double[] num:phe){
			for(double num1:num){
			System.out.print(num1+"\t\t");
			}System.out.println();
		}
		
		for(double[] num:Aall){
			for(double num1:num){
			System.out.print(num1+"\t\t");
			}System.out.println();
		}
		
		for(double num:B){
			System.out.println(num+" ");
		}
		
		
		
	}
}
