package com.pedigree.R;

public class CrossImp implements Cross{

	int[][] intArray;
	double[][] doubleArray;
	double[] doubleArrayLine;
	double sum;
	
	@Override
	public int[][] crossprod(int[][] x) {
		
		return crossprod(x,x);
	}

	@Override
	public int[][] crossprod(int[][] x, int[][] y) {

		intArray = new int[y.length][y[0].length];
		for(int i=0; i<x[0].length; i++){
			for(int j=0; j<x.length; j++){
				intArray[i][j] = x[i][j] * y[i][j];
			}
		}
		return intArray;
	}

	@Override
	public int[][] tcrossprod(int[][] x) {

		return tcrossprod(x,x);
	}

	@Override
	public int[][] tcrossprod(int[][] x, int[][] y) {

		intArray = new int[x.length][x[0].length];
		for(int i=0; i<x.length; i++){
			for(int j=0; j<x[0].length; j++){
				intArray[i][j] = x[i][j] * y[i][j];
			}
		}
		return intArray;
	}
	
	@Override
	public double[][] crossprod(double[][] x) {

		return crossprod(x,x);
	}

	@Override
	public double[][] crossprod(double[][] x, double[][] y) {

		doubleArray = new double[y.length][y[0].length];
		for(int i=0; i<x[0].length; i++){
			for(int j=0; j<x.length; j++){
				doubleArray[i][j] = x[i][j] * y[i][j];
			}
		}
		return doubleArray;
	}

	@Override
	public double[][] tcrossprod(double[][] x) {

		return tcrossprod(x,x);
	}

	@Override
	public double[][] tcrossprod(double[][] x, double[][] y) {

		doubleArray = new double[x.length][x[0].length];
		for(int i=0; i<x.length; i++){
			for(int j=0; j<x[0].length; j++){
				doubleArray[i][j] = x[i][j] * y[i][j];
			}
		}
		return doubleArray;
	}

	/* ncrossprod = x[][] %*% y[][]
	 * 
	 */
	@Override
	public double[][] ncrossprod(double[][] x, double[][] y) {

		doubleArray = new double[x.length][y[0].length];
		double sumOfLine = 0;
		
		for(int i=0; i<y[0].length; i++){		//y[0].length
			for(int j=0; j<x.length; j++){		//x.length
				for(int k=0; k<x[0].length; k++){		//x[0].length
					sumOfLine += x[j][k] * y[k][i];
				}
				doubleArray[j][i]=sumOfLine;
			}
		}
		
		return doubleArray;
	}

	/*  ncrossprod = x[] %*% y[][]
	 *  
	 */
	@Override
	public double[] ncrossprod(double[] x, double[][] y) {

		doubleArrayLine = new double[x.length];
		double sumOfLine = 0;
		
		for(int i=0; i<y[0].length; i++){		//y[0].length
			for(int j=0; j<x.length; j++){		//x.length
					sumOfLine += x[j] * y[j][i];
			}doubleArrayLine[i] = sumOfLine;
		}
		
		return doubleArrayLine;
	}

	/*  ncrossprod = x[][] %*% y[]
	 * 
	 */
	@Override
	public double[] ncrossprod(double[][] x, double[] y) {

		doubleArrayLine = new double[x.length];
		double sumOfLine = 0;
		
		for(int i=0; i<x[0].length; i++){		//y[0].length
			for(int j=0; j<y.length; j++){		//x.length
					sumOfLine += y[j] * x[j][i];
			}doubleArrayLine[i] = sumOfLine;
		}
		
		return doubleArrayLine;
	}

	@Override
	public double[] tcrossprod(double[] x, double[][] y) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public double[] tcrossprod(double[][] x, double[] y) {
		// TODO 自动生成的方法存根
		return null;
	}


	
}
