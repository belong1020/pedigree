
import Jama.*;

public class test {

	public static void main(String [] args){
		
		String str = "123456";
		int i=123456;
		
		System.out.println(str.getBytes().length);
		System.out.println(String.valueOf(i).length());
		
		double[][] a={
		{1,2,3},{4,5,6}
		};
		double[][] b={
		{1,2,3},{4,5,6}
		};
		double[][] c = solve(a,b);
		
		
	}
}
