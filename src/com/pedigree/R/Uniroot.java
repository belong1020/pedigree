package com.pedigree.R;
import java.math.*;
import java.util.Scanner;

public class Uniroot {
	/*
		public static void main(String[] args){

		double[] lambda = { 1.8459247, 1.3494226, 0.8800137, 0.6062865,
				0.3628673, 0.2748828, 0.2431024 };
		double[] etas = { 0.1289140, 3.9825188, -2.1415553, 1.2084147,
				3.4339298, -2.2483629, 0.1696789 };
		//double[] etas = {0.1289140, 3.9825188, -2.1415553, 1.2084147,3.4339298, -2.2483629	, 0.1696789};
		
		double lower = 0.1;
		double upper = 1 ;
		System.out.println(" 请输入精度  ");
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
		for(int i=0; i<logdelta.length-1 ;i++){
			double r=uniroot( logdelta[i], logdelta[i+1], lambda, etas);
			//System.out.println( r);
			if(r!=-100){
				System.out.println(r);
			}
			
		}
	}
	*/
	/** 解方程 
	 * @param lower
	 * @param upper
	 * @param lambda
	 * @param etas
	 */
	public static double uniroot(double lower,double upper,double[] lambda,double[] etas) {

		int count = 0;
		double y1 = 0, y2 = 0, y3 = 0;
		double x1 = lower;
		double x2 = upper;
		double x3 = (x2 - x1) * 0.5 + x1; // 中点
				
		double jd = 1; // 精度
		F a = new F(); // 初始化函数X
		//y1 = a.Zhi(x1);
		//y2 = a.Zhi(x2);
		//y3 = a.Zhi(x3);
		y1 = a.emma_delta_REML_dLL_wo_Z(x1, lambda, etas);
		y2 = a.emma_delta_REML_dLL_wo_Z(x2, lambda, etas);
		if( (y1>0&&y2>0) || (y1<0&&y2<0) ){
			return -100;
		}
		y3 = a.emma_delta_REML_dLL_wo_Z(x3, lambda, etas);
		
		jd = 6;
		//long startTime = System.nanoTime(); // 获取程序开始时间
		while (Math.abs(y3) > Math.pow(10, (-jd - 1))){
			if(count>1000){
				break;
			}
			if (y3 > 0) {
				x1 = x3;
			} else{
				x2 = x3;
			}
			x3 = (x2 - x1) * 0.5 + x1; // 用黄金中点法向值靠近
			//y3 = a.Zhi(x3);
			y3 = a.emma_delta_REML_dLL_wo_Z(x3, lambda, etas);
			count++;
		}
		//long endTime = System.nanoTime(); // 获得程序结束时间
		//long sum = endTime - startTime;
		
		//String x6 = x3.toString(); // 截取精度
		//String x7 = x6.substring(0, (int) (jd + 2));
		//System.out.println("x的值为:    " + x7);
		//System.out.println("y的逼近值为:     " + y3);
		//System.out.println("运行程序所用的时间为:     " + sum + "   纳秒");
		//System.out.println("黄金分割次数为:   " + count);
		return x3;
	}
}

class F { // 计算F(X)的函数 
	double Zhi(double x) {
		double y = 0;
		y = 1 / x * (1 - 1 / (Math.pow(1 + x, 5))) - 2;
		return y;
	}
	double emma_delta_REML_dLL_wo_Z(double logdelta,double[] lambda,double[] etas){
		
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
	
}
