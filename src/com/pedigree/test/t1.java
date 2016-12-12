package com.pedigree.test;

import java.util.ArrayList;

import com.pedigree.R.CrossImp;

public class t1 {
public static void main(String[] args){
	/*
	ArrayList l1 = new ArrayList();
	l1.add("15gezifuchangduzi");
	l1.add(0);
	l1.add(0);
	l1.add(1);
	ArrayList l2=new ArrayList();
	l2.add(l1.get(0));
	l2.add(l1.get(1));
	l2.add(l1.get(2));
	l2.add(l1.get(3));
	
	
	System.out.println(l1);
	System.out.println(l2);
	*/
		CrossImp CI = new CrossImp();
		double[] x = { 1, 4, 7, 10, 13 };
		
		double[][] y = { 
				{ 1, 6, 11, 16, 21 }, 
				{ 2, 7, 12, 17, 22 },
				{ 3, 8, 13, 18, 23 }, 
				{ 4, 9, 14, 19, 24 },
				{ 5, 10, 15, 20, 25 } 
				};
		double[] num = CI.crossprod(x, y);
		
	//	for(double[] i:num){
			for(double j:num){
				System.out.print(j+" ");
			}System.out.println();
	//	}
	
	
}
}
