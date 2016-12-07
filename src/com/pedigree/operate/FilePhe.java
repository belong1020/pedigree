package com.pedigree.operate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FilePhe {
	public static void main(String[] args){
		String file = "D:\\2015student\\11-17\\standard pedigree file2.csv";
		double[][] phe = read(file);
		for(int i=0; i<phe.length; i++){
			System.out.println(phe[i][0]+", "+phe[i][1]);
		}
		
		
	}

	public static double[][] read(String file) {
		
		double[][] doubleArray = null ;
		ArrayList liststr = new ArrayList();
		
		try{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream( file )));
		String line;
		while ((line = br.readLine()) != null) {
			String[] str = line.split(",");		
			liststr.add(str[0]);
			liststr.add(str[1]);	
			
		}
		doubleArray =new double [liststr.size()/2][2];
		for(int i=0; i<liststr.size()/2; i++){
			doubleArray[i][0] =Double.valueOf((String) liststr.get(i*2));
			doubleArray[i][1] =Double.valueOf((String) liststr.get(i*2+1));
		}
		
		
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			return doubleArray;
		}
		
		
		
		
		
	}
}
