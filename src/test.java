
import com.pedigree.R.ginv;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class test {

	public static void main(String [] args){
		
		String str = "123456";
		int i=123456;
		
		//System.out.println(str.getBytes().length);
		//System.out.println(String.valueOf(i).length());
		
		
		double[] a={0.13533665310380152,	
				0.13803361011615645	,
				0.1187641831715871	,
				0.14206991074951913	,
				0.11292450775121293	,
				0.12532362627321084	,
				0.11580896354506831	,
				0.11173854528944377
		};
		double[][] b={
		{1,5,9,13},{2,6,10,14},
		{3,7,11,15},{4,8,12,16}
		};
		double[][] c = { { 1, 4, 7 }, { 2, 5, 8, }, { 3, 6, 9 } };
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
		Matrix m1 = new Matrix(y);
		Matrix m2 = new Matrix(y);
		Matrix m3 = null ;
		Matrix m4 = Matrix.random(3,1);
		
		//solve 求逆
		//Matrix x = m2.inverse();
		//ginv 求矩阵广义逆
		//Matrix x2 ;
		//double[][] num = x.getArray();//ginv.ginvrun(y);
		
		
		//eign
		//double[][] cvalue1  = new Matrix(c).eig().getD().getArray();
		double[][] cvalue3  = new EigenvalueDecomposition(new Matrix(c)).getV().getArray();
		double[][] cvalue2  = new Matrix(c).eig().getV().getArray();		
		
		for(int k=0; k<c.length; k++ ){
			for(int j=0; j<c[0].length; j++){
				System.out.print(c[k][j]+" ");
				
			}System.out.println();
		}
		System.out.println("--------------------------------------");
		/*
		for(int k=0; k<cvalue1.length; k++ ){
			for(int j=0; j<cvalue1[0].length; j++){
				System.out.print(cvalue1[k][j]+" ");
				
			}System.out.println();
		}*/
		System.out.println("--------------------------------------");
		
		for(int k=0; k<cvalue2.length; k++ ){
			for(int j=0; j<cvalue2[0].length; j++){
				System.out.print(cvalue2[k][j]+" ");
				
			}System.out.println();
		}
		for(int k=0; k<cvalue3.length; k++ ){
			for(int j=0; j<cvalue3[0].length; j++){
				System.out.print(cvalue3[k][j]+" ");
				
			}System.out.println();
		}
		
		/*
		for(int k=0; k<num.length; k++ ){
			for(int j=0; j<num[0].length; j++){
				System.out.print(num[k][j]+" ");
				
			}
			System.out.println();
		}
		*/
		
		
		
		
	}
}
