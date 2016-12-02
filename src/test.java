
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
		Matrix m1 = new Matrix(a);
		Matrix m2 = new Matrix(b);
		Matrix m3 = null ;
		Matrix m4 = Matrix.random(3,1);
		
		//Matrix x = m2.inverse();
		
		
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
		
		
	}
}
