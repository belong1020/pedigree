package com.pedigree.R;

public class CrossImp implements Cross{

	double[][] array;
	@Override
	public double[][] crossprod(double[][] x) {

		return crossprod(x,x);
	}

	@Override
	public double[][] crossprod(double[][] x, double[][] y) {

		array = new double[y.length][y[0].length];
		for(int i=0; i<x[0].length; i++){
			for(int j=0; j<x.length; j++){
				array[i][j] = x[i][j] * y[i][j];
			}
		}
		return array;
	}

	@Override
	public double[][] tcrossprod(double[][] x) {

		return tcrossprod(x,x);
	}

	@Override
	public double[][] tcrossprod(double[][] x, double[][] y) {

		array = new double[x.length][x[0].length];
		
		for(int i=0; i<x.length; i++){
			for(int j=0; j<x[0].length; j++){
				array[i][j] = x[i][j] * y[i][j];
			}
		}
		return array;
	}
	
}
