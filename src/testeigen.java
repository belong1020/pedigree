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
		
		double[] X0 = new double[8];
		for(int i=0; i<X0.length; i++)
			X0[i] = 1;
		double[] X = X0;
		
		Eigen e = BLUP.emma_eigen_R_wo_Z(Aall, X);
		
		for(double j:e.getEigD()){
			System.out.print(j+"\t\t ");
		}System.out.println();
		System.out.println();
		
		for(double[] i :e.getEigV()){
			for(double j:i){
				System.out.print(j+"\t\t ");
			}System.out.println();
		}
		
		
		
		
		
		
		
	}
}
