package com.pedigree.R;
import java.math.*;
import java.util.Scanner;

public class Uniroot {
	public static void main(String[] args){

		double[] lambda = { 1.8459247, 1.3494226, 0.8800137, 0.6062865, 0.3628673, 0.2748828, 0.2431024};
		double[] etas = {-1.612543, -2.527643, 1.326051, -1.002887, 2.552209, -1.09735, -4.746428};
		double lower = 0.1;
		double upper = 1 ;
		System.out.println(" �����뾫��  ");
		System.out.println( uniroot( lower, upper, lambda, etas));
}
	/** �ⷽ�� 
	 * @param lower
	 * @param upper
	 * @param lambda
	 * @param etas
	 */
	public static double uniroot(double lower,double upper,double[] lambda,double[] etas) {

		int count = 0;
		double y1 = 0, y2 = 0, y3 = 0, y4 = 0;
		double x1 = lower;
		double x2 = upper;
		Double x3 = (x2 - x1) * 0.618 + x1; // �ƽ��е�
		
		double[][] num1;
		double[]   num2;
		
		double jd = 1; // ����
		F a = new F(); // ��ʼ������X
		//y1 = a.Zhi(x1);
		//y2 = a.Zhi(x2);
		y3 = a.Zhi(x3);
		//y3 = a.emma_delta_REML_dLL_wo_Z(x3, lambda, etas);
		
		
		
		//System.out.println(" �����뾫��  ");
		//Scanner s = new Scanner(System.in); // ��ȡ����ľ���
		//jd = s.nextInt();
		jd = 6;
		long startTime = System.nanoTime(); // ��ȡ����ʼʱ��
		while (Math.abs(y3) > Math.pow(10, (-jd - 1))){
			if(count>1000){
				break;
			}
			if (y3 > 0) {
				x1 = x3;
			} else{
				x2 = x3;
			}
			x3 = (x2 - x1) * 0.618 + x1; // �ûƽ��е㷨��ֵ����
			y3 = a.Zhi(x3);
			count++;
		}
		long endTime = System.nanoTime(); // ��ó������ʱ��
		long sum = endTime - startTime;
		String x6 = x3.toString(); // ��ȡ����
		String x7 = x6.substring(0, (int) (jd + 2));
		//System.out.println("x��ֵΪ:    " + x7);
		//System.out.println("y�ıƽ�ֵΪ:     " + y3);
		//System.out.println("���г������õ�ʱ��Ϊ:     " + sum + "   ����");
		//System.out.println("�ƽ�ָ����Ϊ:   " + count);
		return y3;
	}
}

class F { // ����F(X)�ĺ��� 
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
