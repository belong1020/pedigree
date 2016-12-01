
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
		{1,2,3},{4,5,6},
		{7,8,9},{10,11,12}
		};
		Matrix m1 = new Matrix(a);
		Matrix m2 = new Matrix(b);
		Matrix m3 = null ;
		Matrix m4 = Matrix.random(3,1);
		
		Matrix x = m2.inverse();
		
		double[][] cvalue = x.getArray();
		
		
		
		for(int k=0; k<cvalue.length; k++ ){
			for(int j=0; j<cvalue[0].length; j++){
				System.out.print(cvalue[k][j]+" ");
				
			}System.out.println();
		}
		
		
	}
}
