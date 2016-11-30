package com.pedigree.R;

interface Cross {

	double[][] crossprod(double[][] x);
	double[][] crossprod(double[][] x, double[][] y);
	
	double[][] tcrossprod(double[][] x);
	double[][] tcrossprod(double[][] x, double[][] y);
	
}
