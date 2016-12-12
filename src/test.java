
import com.pedigree.R.ginv;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class test {

	public static void main(String [] args){
		
		String str = "123456";
		int i=123456;
		
		//System.out.println(str.getBytes().length);
		//System.out.println(String.valueOf(i).length());
		
		double[][] a={
		{1,2,3},{4,5,6}
		};
		double[][] b={
		{1,5,9,13},{2,6,10,14},
		{3,7,11,15},{4,8,12,16}
		};
		double[][] y = { 
				{1.0,  0.0, 0.00, 0.50, 0.000, 0.00, 0.000, 0.500},
				{0.0,  1.0, 0.00, 0.00, 0.500, 0.00, 0.500, 0.000},
				{0.0,  0.0, 1.00, 0.00, 0.500, 0.50, 0.250, 0.500},
				{0.5,  0.0, 0.00, 1.00, 0.000, 0.00, 0.000, 0.250},
				{0.0,  0.5, 0.50, 0.00, 1.000, 0.25, 0.375, 0.250},
				{0.0,  0.0, 0.50, 0.00, 0.250, 1.00, 0.500, 0.250},
				{0.0,  0.5, 0.25, 0.00, 0.375, 0.50, 1.000, 0.125},
				{0.5,  0.0, 0.50, 0.25, 0.250, 0.25, 0.125, 1.000},

				};
		Matrix m1 = new Matrix(a);
		Matrix m2 = new Matrix(y);
		Matrix m3 = null ;
		Matrix m4 = Matrix.random(3,1);
		
		//solve 求逆
		Matrix x = m2.inverse();
		//ginv 求矩阵广义逆
		Matrix x2 ;
		double[][] num = ginv.ginvrun(y);
		
		/*
		//eign
		double[][] cvalue1  = new Matrix(b).eig().getD().getArray();
		double[][] cvalue2  = new Matrix(b).eig().getV().getArray();		
		
		for(int k=0; k<b.length; k++ ){
			for(int j=0; j<b[0].length; j++){
				System.out.print(b[k][j]+" ");
				
			}System.out.println();
		}
		System.out.println("--------------------------------------");
		
		for(int k=0; k<cvalue1.length; k++ ){
			for(int j=0; j<cvalue1[0].length; j++){
				System.out.print(cvalue1[k][j]+" ");
				
			}System.out.println();
		}
		System.out.println("--------------------------------------");
		
		for(int k=0; k<cvalue2.length; k++ ){
			for(int j=0; j<cvalue2[0].length; j++){
				System.out.print(cvalue2[k][j]+" ");
				
			}System.out.println();
		}
		*/
		
		for(int k=0; k<num.length; k++ ){
			for(int j=0; j<num[0].length; j++){
				System.out.print(num[k][j]+" ");
				
			}System.out.println();
		}
		
	}
}
