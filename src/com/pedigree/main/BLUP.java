package com.pedigree.main;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

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
		
		
		
		int n = y.length;
		int t = K.length;
		int q = X.length;
		
		
		
		
		
		
		
		

	}
	
	/**
	 * @param logdelta
	 * @param lambda
	 * @param etas
	 * @return
	 */
	private static double emma_delta_REML_LL_wo_Z(double logdelta,double[] lambda,double[] etas){
	
		int nq = etas.length;
		double delta = Math.exp(logdelta);
		double sum1 = 0.0;
		for(int i=0; i<etas.length; i++) sum1 += etas[i]*etas[i]/(lambda[i]+delta);
		double sum2 = 0.0;
		for(int i=0; i<lambda.length; i++) sum2+=Math.log(lambda[i]+delta);

		// lambda  矩阵？		
		return ( 0.5*(nq*(Math.log(nq/(2*Math.PI))-1-Math.log(sum1))-sum2) );
	}
	
	/**
	 * @param logdelta
	 * @param lambda
	 * @param etas
	 * @return
	 */
	private static double emma_delta_REML_dLL_wo_Z(double logdelta,double[] lambda,double[] etas){
		
		int nq = etas.length;
		double delta = Math.exp(logdelta);
		double[] etasq = new double[etas.length];
		for(int i=0; i<etas.length; i++){
			etasq[i] = etas[i] * etas[i];
		}
		double[] ldelta = new double[lambda.length];
		for(int i=0; i<lambda.length; i++){
			ldelta[i] = lambda[i] +delta;
		}
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		double sum3 = 0.0;
		
		for(int i=0; i<ldelta.length ; i++){
			sum1 += etasq[i]/(ldelta[i] * ldelta[i]);
		}
		for(int i=0; i<ldelta.length ; i++){
			sum2 += etasq[i]/ldelta[i];
		}
		for(int i=0; i<ldelta.length ; i++){
			sum3 += 1/ldelta[i];
		}
		return( 0.5*(nq*sum1/sum2-sum3) );
	}

	/**
	 * @param K
	 * @param X
	 * @return
	 */
	private static double[] emma_eigen_R_wo_Z(double[][] K,double[][] X) {
		int n = X.length;
		int q = X[0].length;
		Matrix m1 = new Matrix(CI.crossprod(X,X));
		double[][] solveArray = m1.inverse().getArray();
		double[][] N = Diag.diag(n);
		double[][] N1 = CI.tcrossprod(CI.ncrossprod(X,solveArray ),X);		//中转
		double[][] S = new double[n][n];
		//S <- diag(n) - X %*% solve(crossprod(X,X)) %*% t(X)
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
					S[i][j] = N[i][j] - N1[i][j];
			}
		}
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
					N1[i][j] = K[i][j] + N1[i][j];
			}
		}
		double[][] eig = CI.ncrossprod(CI.ncrossprod(S,N1),S);
		// .eig() 求特征值矩阵 --- return 对角线有数的二维数组
		double[][] eigD = new Matrix(eig).eig().getD().getArray();	//特征值
		double[][] eigV = new Matrix(eig).eig().getV().getArray();	//特征向量
		double[] eigLine =new double[eig.length];
		for(int i=0; i<eig.length; i++){
			eigLine[i] = eigD[i][i];
		}
		//# 判断是否复数   a+bi     保留、？？？？
		//stopifnot(!is.complex(eig$values))
		//# 数据打包 return 
		return eigLine;
		
	}
	
	
	//k不为空  kship 无法达到
	/*
	private static void kinship(double[][] geno){
		if(!is.matrix(M)) M <- as.matrix(M)
		n <- nrow(M)
		if(is.null(cpu)){
			Pi <- 0.5*colMeans(M)
			Z <- t(t(M)-2*Pi)
			SUM <- sum(Pi*(1-Pi))
				if(is.null(weight)){
					K <- 0.5*tcrossprod(Z,Z)/SUM
					}else{
					MX <- t(t(Z)*as.vector(weight))
					K <- 0.5*tcrossprod(MX,Z)/SUM
					}
			return(K)
		}
	}
	 */
	
	/**
	 * @param phe
	 * @param k
	 */
	public static void BLUP(double[] phe, double[][] K) {
		String pedigreepath = "D:\\2015student\\11-17\\standard pedigree file1.csv" ;
		boolean standard_id = false;
		boolean file_output = false;
		double[] CV ;
		double lambda ;
		CrossImp CI = new CrossImp(); 
		int n = phe.length;
		double[] X0;
		double[] ys; 
		double[][] Z;
		double[][] ZZ;
		int byl = 1;
		boolean go;
		double[][] iZZ_K ;
		double[][] Z_iZZ_K_tZ ;
		double[][] v;
		double[] itX_v;
		double iXvX;
		double[] tXv;
		double beta;
		double[] y_d;
		double[][] tZZ_K ;
		double[] BLUP_ebv ;
		
		//if( K == null )	K = Kinship(geno);			//k 不为空
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
		ZZ = CI.crossprod(Z,Z);
		byl = 1 ;
		go = true;
		while(go==true){
			
			double[] esp ={0,1e-10,1e-6,1e-2,0.11,0.1,0.11,0.5};
			double[][] myK = K ;
			for(int i=0; i<myK.length; i++){
				myK[i][i] += esp[byl];
			}
			//solve
			
			//ik = try(solve(myK),silent=TRUE);
			//go = inherits(ik,"try-error");
			
			byl ++;
			if(byl==9 && go==true){
				System.out.println("Replacing the inverse of K with generalized inverse...");
				//library(MASS);
				//ik<-ginv(K);		//求广义逆矩阵
				go = false;
			}
		}
		//iZZ_K = solve(ZZ + ik*lambda);
		Z_iZZ_K_tZ = CI.tcrossprod(iZZ_K,Z);
		
		//v <- Z - Z %*% Z.iZZ.K.tZ		//#矩阵
		
		
		
		
		
		double[][] temp1 = CI.ncrossprod(Z, Z_iZZ_K_tZ);
		v = new double[Z.length][Z[0].length];
		for(int i=0; i<Z.length; i++){
			for(int j=0; j<Z[0].length; j++){
				v[i][j] = Z[i][j] - temp1[i][j];
			}
		}
		
		
		itX_v = CI.crossprod(X0,v);
		
		//iXvX = solve(CI.ncrossprod(itX_v , X0));		//#矩阵
		
		tXv = CI.crossprod(X0,v);
		
		//beta<- iXvX %*% tXv %*% ys
		beta = CI.ncrossprod( CI.ncrossprod(iXvX, tXv),ys);
		
		//y.d <- ys - X0 %*% beta
		double[] temp2 = CI.ncrossprod(X0, beta);
		y_d = new double[ys.length];
		for(int i=0; i<ys.length; i++){
			for(int j=0; j<ys[0].length; j++){
				y_d[i][j] = ys[i][j] - temp2[i][j];
			}
		}
		
		tZZ_K = CI.tcrossprod(iZZ_K,Z);	//#矩阵
		BLUP_ebv = CI.ncrossprod(tZZ_K , y_d);
		
		
		
		return (list(beta=beta,ebv=BLUP_ebv));
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	


	
	

}
