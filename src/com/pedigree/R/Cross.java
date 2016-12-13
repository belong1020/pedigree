package com.pedigree.R;

interface Cross {
	
	int[][] crossprod(int[][] x);
	int[][] crossprod(int[][] x, int[][] y);
	
	int[][] tcrossprod(int[][] x);
	int[][] tcrossprod(int[][] x, int[][] y);
	
	
	double[][]	crossprod(double[][] x);
	double	crossprod(double[] x, double[] y);
	double[][]	crossprod(double[][] x, double[][] y);
	double[]	crossprod(double[] x, double[][] y);
	double[]	crossprod(double[][] x, double[] y);
	
	
	double[][]	tcrossprod(double[][] x);
	double[][]	tcrossprod(double[][] x, double[][] y);
	double[][]	tcrossprod(double[] x, double[] y);
	double[]	tcrossprod(double[] x, double[][] y);
	
	
	double[]	ncrossprod(double x, double[] y);
	double[][]	ncrossprod(double x, double[][] y);
	double		ncrossprod(double[] x, double[] y);
	double[][]	ncrossprod(double[][] x, double[][] y);
	double[]	ncrossprod(double[] x,double[][] y);
	double[]	ncrossprod(double[][] x,double[] y);
	
	
	
}
