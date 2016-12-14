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
	public static double[] operateD(double[][] eigD){
		double[] eigLine = new double[eigD.length];
		for(int i=0; i<eigD.length; i++){
			eigLine[eigD.length - i-1] = eigD[i][i];
		}
		return eigLine;
	}
	/**
	 * @param eigV
	 * @return
	 */
	public static double[][] operateV(double[][] eigV){
		double[][] eig = new double[eigV.length][eigV[0].length];
		for(int i=0; i<eigV.length; i++){
			for(int j=0; j<eigV[0].length; j++){
				eig[i][eigV[0].length-j-1] = eigV[i][j];
			}
		}
		return eig;
	}
	
}
