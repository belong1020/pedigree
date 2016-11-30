package com.pedigree.main;

import com.pedigree.R.CrossImp;
import com.pedigree.R.Diag;

//  https://github.com/ElisbanFlores/Paralela/blob/master/main.cpp#L29
public class BLUP{
	private static CrossImp CI = new CrossImp();
	private static void emma_REMLE(double[] y,double[] X,double[][] K){
		int ngrids=100;
		int llim=-10;
		int ulim=10;
		double esp=1e-10;


	}
	/**
	 * @param logdelta
	 * @param lambda
	 * @param etas
	 * @return
	 */
	private static double emma_delta_REML_LL_wo_Z(double logdelta,double lambda,double[] etas){
	
		int nq = etas.length;
		double delta = Math.exp(logdelta);
		double temp = 0.0;
		for(int i=0; i<etas.length; i++) temp += etas[i]*etas[i]/(lambda+delta);
		//lambda+delta  矩阵？
		return ( 0.5*(nq*(Math.log(nq/(2*Math.PI))-1-Math.log(temp))-sum(Math.log(lambda+delta))) );
	}

	/**
	 * @param K
	 * @param X
	 * @return
	 */
	private static double emma_eigen_R_wo_Z(double[][] K,double[][] X) {
		int n = X.length;
		int q = X[0].length;
		double[][] S = diag(n)-CI.tcrossprod(CI.crossprod(X,solve(CI.crossprod(X,X))),X);
		double[][] eig = eigen(CI.crossprod(CI.crossprod(S,(K+diag(1,n))),S),symmetric=TRUE);
		//# 判断是否负数？
		stopifnot(!is.complex(eig$values))
		//# 数据打包 return 
		return(list(values=eig$values[1:(n-q)]-1,vectors=eig$vectors[,1:(n-q)]));
		
	}

	/**
	 * @param phe
	 * @param k
	 */
	public static void BLUP(double[] phe, double[][] k) {
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file1.csv" ;
		boolean standard_id = false;
		boolean file_output = false;
		double[] CV ;
		double lambda ;
		int n = phe.length;
		double[] X0;
		double[] ys; 
		int[][] Z;
		int[][] ZZ;
		//if( k == null )	k = Kinship(geno);			//k 不为空
		if(CV==null){
			X0 = new double[n];
			for(int i=0; i<X0.length; i++)
				X0[i] = 1;
		}else{
			//X0 = cbind(matrix(1,n,1),CV);
		}
		//if(lambda==null){
		lambda = emma_REMLE(phe, X=X0, K=K)$delta;
		//}
		ys = phe;
		Z = Diag.diag(n);
		ZZ = CrossImp.crossprod(Z,Z);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	


	
	

}
