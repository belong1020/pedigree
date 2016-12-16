

import Jama.Matrix;

import com.pedigree.domain.Eigen;
import com.pedigree.main.A;
import com.pedigree.main.BLUP;
import com.pedigree.operate.FilePhe;


public class testeigen {

	public static void main(String [] args){
		
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file1.csv" ;
		String phefile = "D:\\2015student\\11-17\\standard pedigree file2.csv";

		boolean standard_id = false;
		boolean file_output = false;
		double[][] Aall = A.A(pedigreepath, standard_id, file_output);
		double[][] phe = FilePhe.read(phefile);
		double[] phe1 = FilePhe.operate(phe);
		
		double[][] y = { 
				{  1,    1,    1,    1,    1,    1,    1,    1 },
				{  2,    2,    2,    2,    2,    2,    2,    2 },
				{  3,    3,    3,    3,    3,    3,    3,    3 },
				{  4,    4,    4,    4,    4,    4,    4,    4 },
				{  1,    1,    1,    1,    1,    1,    1,    1 },
				{  2,    2,    2,    2,    2,    2,    2,    2 },
				{  3,    3,    3,    3,    3,    3,    3,    3 },
				{  4,    4,    4,    4,    4,    4,    4,    4 }

		};
		
		
		double[] X0 = new double[8];
		for(int i=0; i<X0.length; i++)
			X0[i] = 1;
		double[] X = X0;
		
		double[][] eigd = new Matrix(Aall).eig().getD().getArray()     ;//
		Eigen e = BLUP.emma_eigen_R_wo_Z(Aall, X);
		double[][] eigv = new Matrix(Aall).eig().getV().getArray();
		
		//double[] eigD= Eigen.operateD(eigd);
		//eigv = Eigen.operateV(eigv);
	//	for(double[] j:e.getEigD()){
			for(double k:e.getEigD()){
			System.out.print(k+"\t\t ");
		}System.out.println();
	//	}
		System.out.println();
		
		/*
		for(double[] j:e.getEigV()){
			for(double k:j){
			System.out.print(k+"\t\t ");
		}System.out.println();
		}
		*/
		
		for(int i=0; i<e.getEigV().length; i++){
			for(int j=0; j<e.getEigV()[0].length; j++){
			int x=1;
			if(j<3){	
				x=-1;
			}
			System.out.print(e.getEigV()[i][j]*x+"\t\t ");
			
			}System.out.println();
		}
		
		
		
		
		/*
		for(double[] i:e.getEigD()){
		for(double j:e.getEigD()){
			System.out.print(j+"\t\t ");
		}System.out.println();
		}
		System.out.println();
		
		for(double[] i :e.getEigV()){
			for(double j:i){
				System.out.print(j+"\t\t ");
			}System.out.println();
		}*/
		
		
		
		
		
		
		
	}
}
