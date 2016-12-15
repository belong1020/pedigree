package com.pedigree.domain;

import Jama.Matrix;

public class Eigen {

	double[] eigD;
	double[][] eigV;
	
	public double[] getEigD() {
		return eigD;
	}
	public void setEigD(double[] eigD) {
		this.eigD = eigD;
	}
	public double[][] getEigV() {
		return eigV;
	}
	public void setEigV(double[][] eigV) {
		this.eigV = eigV;
	}
	/**
	 * @param eigD
	 * @return
	 */
	public static double[] operateD(double[][] eigD){		//symmetric=TRUE  去掉特征值最后一个矩阵 一列
		double[] eigLine = new double[eigD.length-1];
		for(int i=1; i<eigD.length; i++){
			eigLine[eigD.length - i-1] = eigD[i][i]-1;
		}
		return eigLine;
	}
	/**
	 * @param eigV
	 * @return
	 */
	public static double[][] operateV(double[][] eigV){		//
		double[][] eig = new double[eigV.length][eigV[0].length-  1  ];
		for(int i=0; i<eigV.length; i++){
			for(int j = 1   ; j<eigV[0].length; j++){
				eig[i][eigV[0].length-j- 1   ] = eigV[i][j];
			}
		}
		return eig;
	}
	
}
