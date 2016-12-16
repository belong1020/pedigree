
import java.io.IOException;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

import com.pedigree.main.A;;


public class testA {
	public static void main(String[] args) throws IOException {
		//十五列数据
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file.csv" ;
		//三列数据
		String file = "D:\\2015student\\11-17\\张斌2\\系谱数据.txt";
		
		
		
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
