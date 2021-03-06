package com.pedigree.main;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Jama.Matrix;

import com.pedigree.R.CrossImp;
import com.pedigree.R.Diag;
import com.pedigree.R.RmathImp;
import com.pedigree.R.Uniroot;
import com.pedigree.domain.Eigen;


public class BLUP{
	private static CrossImp CI = new CrossImp();
	private static RmathImp RI = new RmathImp();
	private static double emma_REMLE(double[] y,double[] X,double[][] K){
		
		int ngrids = 100 ;
		int llim = -10 ;
		int ulim = 10 ;
		double esp = 1e-10 ;
		
		Eigen e = emma_eigen_R_wo_Z(K, X);
		/*	
		for(double num1:e.getEigD()){
			System.out.print(num1+"	");
		}System.out.println();
		System.out.println();
		for(double[] num:e.getEigV()){
			for(double num1:num){
				System.out.print(num1+"	");
			}System.out.println();
		}*/
		
		int n = y.length;
		int t = K.length;
		int q = 1;
		if(K[0].length != t) System.exit(1);
		if(X.length != n) System.exit(1);
		
		double det = CI.crossprod(X,X);				//一个数的行列式是本身  
		
		if( det == 0 ) {
			System.out.println("X is singular");
			int REML=0;
			double delta=0;
			int ve=0;
			int vg=0;
			return delta;
		}
		double[][] eigv = e.getEigV();
		
		//CI.crossprod(e.getEigV(), y);	//eigen.V 错值起点
		double[] etas = new double[eigv[0].length];
		for(int i=0; i<eigv[0].length; i++){
			for(int j=0; j<eigv.length; j++){
				etas[i] += eigv[j][i]*y[j];
			}
		}
		
		double[] logdelta ={
				-10.0,  -9.8,  -9.6,  -9.4,  -9.2,  -9.0,  -8.8,  -8.6,  -8.4,  -8.2,
				 -8.0,  -7.8,  -7.6,  -7.4,  -7.2,  -7.0,  -6.8,  -6.6,  -6.4,  -6.2,
				 -6.0,  -5.8,  -5.6,  -5.4,  -5.2,  -5.0,  -4.8,  -4.6,  -4.4,  -4.2,
				 -4.0,  -3.8,  -3.6,  -3.4,  -3.2,  -3.0,  -2.8,  -2.6,  -2.4,  -2.2,
				 -2.0,  -1.8,  -1.6,  -1.4,  -1.2,  -1.0,  -0.8,  -0.6,  -0.4,  -0.2,
				  0.0,   0.2,   0.4,   0.6,   0.8,   1.0,   1.2,   1.4,   1.6,   1.8,
				  2.0,   2.2,   2.4,   2.6,   2.8,   3.0,   3.2,   3.4,   3.6,   3.8,
				  4.0,   4.2,   4.4,   4.6,   4.8,   5.0,   5.2,   5.4,   5.6,   5.8,
			 	  6.0,   6.2,   6.4,   6.6,   6.8,   7.0,   7.2,   7.4,   7.6,   7.8,
				  8.0,   8.2,   8.4,   8.6,   8.8,   9.0,   9.2,   9.4,   9.6,   9.8,
				  10.0	
		};
				
		int m = logdelta.length;
		double[] delta = new double[logdelta.length];
		for(int i=0; i<ngrids+1; i++){
			delta[i] = Math.exp(logdelta[i]);
		}

		double[] eigD = e.getEigD();
		double[][] Lambdas = new double[n-q][m];
		for(int i=0; i<Lambdas.length; i++ ){
			for(int j=0; j<Lambdas[0].length; j++){
				Lambdas[i][j] = eigD[i]+ delta[j];
			}
		}
		double[][] Etasq = new double[n-q][m];
		for(int i=0; i<Etasq.length; i++){
			for(int j=0; j<Etasq[0].length; j++){
				Etasq[i][j] = etas[i] * etas[i];	//eigen.V 错值
			}
		}
		// LL 数组 不参与运算
		/*double[] templog = RI.log(RI.colSums(temp));//	log(colSums(Etasq/Lambdas))
		double[] tempcol = RI.colSums(RI.log(Lambdas));//	colSums(log(Lambdas))
		double nqlog = Math.log((n-q)/(2*Math.PI));//		log((n-q)/(2*pi))
		double[] LL = new double[templog.length] ;		
		for(int i=0; i<LL.length; i++){		
			LL[i] = 0.5*((n-q)*(nqlog-1-templog[i])-tempcol[i]);// LL = ;
		}*/
		
		double[] dLL = new double[delta.length] ;
		
		double[][] Lambdas2 = new double[Lambdas.length][Lambdas[0].length];//	Etasq/Lambdas*Lambdas
		for(int i=0; i<Lambdas2.length; i++){
			for(int j=0; j<Lambdas2[0].length; j++){
				Lambdas2[i][j] = Etasq[i][j]/(Lambdas[i][j] * Lambdas[i][j]);
			}
		}
		double[][] temp = new double[Etasq.length][Etasq[0].length];//	Etasq/Lambdas
		for(int i=0; i<Etasq.length; i++){
			for(int j=0; j<Etasq[0].length; j++){
				temp[i][j] = Etasq[i][j]/ Lambdas[i][j];
			}				
		}
		double[][] Lambdas1 = new double[Lambdas.length][Lambdas[0].length];//	1/Lambdas
		for(int i=0; i<Lambdas2.length; i++){
			for(int j=0; j<Lambdas2[0].length; j++){
				Lambdas1[i][j] = 1/Lambdas[i][j] ;
			}
		}
		double[] temp2col1 = RI.colSums(Lambdas2);//colSums(Etasq/(Lambdas * Lambdas))
		double[] temp2col2 = RI.colSums(temp);
		double[] temp2col3 = RI.colSums(Lambdas1);
				
		for(int i=0; i<dLL.length; i++){
			dLL[i] = 0.5*delta[i]*((n-q)*temp2col1[i]/temp2col2[i]-temp2col3[i]);
		}
		
		/*
		for(double num:dLL){
			System.out.println(num+"\t");
		}
		*/
				
		
		
		
		//	类似list
		ArrayList<Double> optlogdelta = new ArrayList<Double>();
		ArrayList<Double> optLL = new ArrayList<Double>();
		
		if( dLL[1] < esp ) {
			optlogdelta.add((double)llim);
			optLL.add(emma_delta_REML_LL_wo_Z(llim, e.getEigD(), etas));
		}
		if( dLL[m-1] > 0-esp ) {
			optlogdelta.add((double)ulim);
			optLL.add(emma_delta_REML_LL_wo_Z(ulim, e.getEigD(), etas));
		}
		
		for(int i=0; i<m-2; i++){
			if( ( dLL[i]*dLL[i+1] < 0 ) && ( dLL[i] > 0 ) && ( dLL[i+1] < 0 )   ) {//lock++
				
				//求解方程根公式
				double r = Uniroot.uniroot(logdelta[i], logdelta[i+1], e.getEigD(), etas);
				//System.out.println(r+" rrrr ");
				optlogdelta.add(r);
				optLL.add(emma_delta_REML_LL_wo_Z(r,e.getEigD(), etas)); //

			}
		}
		//System.out.println(optlogdelta);
		//System.out.println(optLL);
		double maxdelta = Math.exp(optlogdelta.get(RI.max(optLL)));
	//	optLL=replaceNaN(optLL)   
		double maxLL = RI.max(optLL);
	//	maxva <- sum(etas*etas/(eig.R$values+maxdelta))/(n-q)    
	//	maxve <- maxva*maxdelta
	//	double REML=maxLL;
		double delta1 = maxdelta;
	//	ve=maxve;
	//	vg=maxva;
		
		return delta1;
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
	public static Eigen emma_eigen_R_wo_Z(double[][] K,double[] X) {
		
		Eigen e = new Eigen();
		
		int n = X.length;
		double solveX = 1/CI.crossprod(X, X);

		double[][] N = Diag.diag(n);
		double[]   NN = CI.ncrossprod(solveX,X);
		double[][] N1 = new double[X.length][X.length];		//中转
		for(int i=0; i<X.length; i++){
			for(int j=0; j<X.length; j++){
				N1[i][j] = NN[i] * X[j];
			}
		}
		
		//S <- diag(n) - X %*% solve(crossprod(X,X)) %*% t(X)
		double[][] S = new Matrix(N).minus(new Matrix(N1)).getArray();// new double[n][n];
		double[][] K1 = new double[K.length][K[0].length];
		for(int i=0; i<K1.length; i++){
			for(int j=0; j<K1[0].length; j++){
				if(i==j)
					K1[i][j]=K[i][j]+1;
				else
					K1[i][j]=K[i][j];
			}
		}
				
		for(double[] S1:S){
			for(double S2:S1){
				System.out.print(S2+"\t");
			}System.out.println();
		}	System.out.println();
		for(double[] S1:K1){
			for(double S2:S1){
				System.out.print(S2+"\t");
			}System.out.println();
		}	
		
			double[][] eig = CI.ncrossprod(CI.ncrossprod(S,K1),S);
		
		
		// .eig() 求特征值矩阵 --- return 对角线有数的二维数组
		//symmetric=TRUE  去掉特征值最后一个矩阵 一列
		double[][] eigD = new Matrix(eig).eig().getD().getArray();	//特征值
		double[] eigLine = Eigen.operateD(eigD);
		double[][] eigV = Eigen.operateV(new Matrix(eig).eig().getV().getArray());	
		e.setEigD(eigLine);
		e.setEigV(eigV);	//特征向量          ！！！矩阵与R中符号不全相同   ！！！
		//# 判断是否复数   a+bi     保留、？？？？
		//stopifnot(!is.complex(eig$values))
		//# 数据打包 return 		
		return e;
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
	/*
	private static double[] replaceNaN(double[] LL) {
		ArrayList<Integer> num = new ArrayList<Integer>();
		int count = 0;
		for(int i=0; i<LL.length; i++){
			if(LL[i]==0){
				num.add(i);
				count++;
			}
		}
		if(!num.isEmpty()){
			for(int i=0; i<num.size(); i++){
				LL[num.get(i)]=count;
			}
		}
		return LL;
	}
	*/
	
	/**
	 * @param phe
	 * @param k
	 * @return 
	 */
	public static double[] BLUP(double[] phe, double[][] K) {
		
		boolean standard_id = false;
		boolean file_output = false;
		double[] CV ;
		double lambda ;
		CrossImp CI = new CrossImp(); 
		int n;
		double[] X0;
		double[] ys; 
		double[][] Z;
		double[][] ZZ;
		int byl = 1;
		boolean go;
		double[][] ik = null;
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
		
		
		n = phe.length;
		X0 = new double[n];
		for(int i=0; i<X0.length; i++)
			X0[i] = 1;
		lambda = emma_REMLE(phe,X0, K);
		System.out.println(lambda+" --- lambda   ");

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
			try{
				ik = new Matrix(myK).inverse().getArray();
				for(int i=0; i<ik.length; i++){			//强制去ik中 无效数 —————— 绝对值小于0.1 
					for(int j=0; j<ik[0].length; j++){
						if( Math.abs(ik[i][j])<0.1 )
							ik[i][j] = 0.0;
					}
				}
				go = false;
			}catch (Exception e){
				System.out.println("   算了不止一次 ");
				go = true;
			}
			byl ++;
			if(byl==9 && go==true){
				System.out.println("Replacing the inverse of K with generalized inverse...");
				//library(MASS);
				//ik<-ginv(K);		//求广义逆矩阵
				ik = new Matrix(K).inverse().getArray();
				
				//ik = ginv.pinv(new Matrix(K)).getArray();
				go = false;
			}
		}
		
		//iZZ_K = solve(ZZ + ik*lambda);
		Matrix matiZZ = new Matrix(ZZ);
		Matrix matiik = new Matrix(ik);
		//lambda   3.648755 上面大函数算出的值在这里运算		
		iZZ_K = matiZZ.plus(matiik.times(lambda)).inverse().getArray() ;
		Z_iZZ_K_tZ = CI.tcrossprod(iZZ_K,Z);
		
		//v <- Z - Z %*% Z.iZZ.K.tZ
		double[][] temp1 = CI.ncrossprod(Z, Z_iZZ_K_tZ);
		v = new double[Z.length][Z[0].length];
		for(int i=0; i<Z.length; i++){
			for(int j=0; j<Z[0].length; j++){
				v[i][j] = Z[i][j] - temp1[i][j];
			}
		}
		
		itX_v = CI.crossprod(X0,v);
		iXvX = 1/CI.ncrossprod(itX_v , X0);
		tXv = CI.crossprod(X0,v);
		//beta<- iXvX %*% tXv %*% ys
		beta = CI.ncrossprod( CI.ncrossprod(iXvX, tXv),ys);
		
		//y.d <- ys - X0 %*% beta
		y_d = new double[ys.length];
		for(int i=0; i<ys.length; i++){
				y_d[i] = ys[i] - X0[i]*beta;
		}
		tZZ_K = CI.tcrossprod(iZZ_K,Z);
		BLUP_ebv = CI.ncrossprod(tZZ_K , y_d);
		
		/*
		for(double[] x:iZZ_K){
			for(double i:x){
			System.out.print(i+"\t\t");
			}System.out.println();
		}*/
		
		
		
		return BLUP_ebv;// + beta
		
	}

}
