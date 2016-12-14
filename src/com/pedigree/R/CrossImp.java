package com.pedigree.R;

public class CrossImp implements Cross{

	int[][] intArray;
	double[][] doubleArray;
	int[] intArrayLine;
	double[] doubleArrayLine;
	double sum;
	
	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#crossprod(int[][])
	 */
	@Override
	public int[][] crossprod(int[][] x) {
		
		return crossprod(x,x);
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#crossprod(int[][], int[][])
	 */
	@Override
	public int[][] crossprod(int[][] x, int[][] y) {

		intArray = new int[x[0].length][y[0].length];
		for(int i=0; i<x[0].length; i++){
			for(int j=0; j<y[0].length; j++){
				for(int k=0; k<x.length; k++){
					intArray[i][j] += x[k][i] * y[k][j];
				}
			}
		}
		return intArray;
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#tcrossprod(int[][])
	 */
	@Override
	public int[][] tcrossprod(int[][] x) {

		return tcrossprod(x,x);
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#tcrossprod(int[][], int[][])
	 */
	@Override
	public int[][] tcrossprod(int[][] x, int[][] y) {

		intArray = new int[x.length][y.length];
		for(int i=0; i<x.length; i++){
			for(int j=0; j<y.length; j++){
				for(int k=0; k<x[0].length; k++){
				intArray[i][j] += x[i][k] * y[j][k];
				}
			}
		}
		return intArray;
	}
	
	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#crossprod(double[][])
	 */
	@Override
	public double[][] crossprod(double[][] x) {

		return crossprod(x,x);
	}
	
	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#crossprod(double[], double[])
	 */
	@Override
	public double crossprod(double[] x, double[] y) {
		
		double result = 0.0;
		for(int i=0; i<x.length; i++){			
			result += x[i] * y[i];

		}
		return result;
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#crossprod(double[][], double[])
	 */
	@Override
	public double[] crossprod(double[][] x, double[] y) {
		
		doubleArrayLine = new double[x.length];
		for(int i=0; i<x.length; i++){
			for(int j=0; j<x[0].length; j++){
				doubleArrayLine[i] += x[i][j] * y[j];
			}
		}
		return doubleArrayLine;
	}
	
	@Override
	public double[] crossprod(double[] x, double[][] y){
		
		doubleArrayLine = new double[y[0].length];
		for(int i=0; i<y[0].length ; i++){
			for(int j=0; j<x.length ; j++){
				doubleArrayLine[i] += x[j] * y[j][i];
			}
		}
		
		
		return doubleArrayLine;
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#crossprod(double[][], double[][])
	 */
	@Override
	public double[][] crossprod(double[][] x, double[][] y) {

		doubleArray = new double[x[0].length][y[0].length];
		for(int i=0; i<x[0].length; i++){
			for(int j=0; j<y[0].length; j++){
				for(int k=0; k<x.length; k++){
					doubleArray[i][j] += x[k][i] * y[k][j];
				}
			}
		}
		return doubleArray;
		
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#tcrossprod(double[][])
	 */
	@Override
	public double[][] tcrossprod(double[][] x) {

		return tcrossprod(x,x);
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#tcrossprod(double[], double[][])
	 */
	@Override
	public double[] tcrossprod(double[] x, double[][] y) {

		doubleArrayLine = new double[y.length];
			for(int j=0; j<y.length; j++){
				for(int k=0; k<x.length; k++){
					doubleArrayLine[j] += x[k] * y[j][k];
				}
			}
		
		return doubleArrayLine;
	}

	/* 		tcrossprod by x[],y[] print [][]
	 * @see com.pedigree.R.Cross#tcrossprod(double[], double[])
	 */
	@Override
	public double[][] tcrossprod(double[] x, double[] y) {

		doubleArray = new double[x.length][y.length];
		for(int i=0; i<x.length; i++){
			for(int j=0; j<y.length; j++){
				doubleArray[i][j]=x[i]*y[j];
			}			
		}
		return doubleArray;
	}
	
	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#tcrossprod(double[][], double[][])
	 */
	@Override
	public double[][] tcrossprod(double[][] x, double[][] y) {

		doubleArray = new double[x.length][y.length];
		for(int i=0; i<x.length; i++){
			for(int j=0; j<y.length; j++){
				for(int k=0; k<x[0].length; k++){
					doubleArray[i][j] += x[i][k] * y[j][k];
				}
			}
		}
		return doubleArray;
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#ncrossprod(double, double[][])
	 */
	public double[][] ncrossprod(double x, double[][] y){
		doubleArray = new double[y.length][y[0].length];
		for(int i=0; i<y.length; i++){
			for(int j=0; j<y[0].length; j++){
				doubleArray[i][j] = x * y[i][j];
			}
		}
		return doubleArray;
	}
	
	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#ncrossprod(double, double[])
	 */
	@Override
	public double[] ncrossprod(double x, double[] y) {

		doubleArrayLine = new double[y.length];
		for(int i=0; i<y.length; i++){
			doubleArrayLine[i] = x * y[i];
		}
		return doubleArrayLine;
	}

	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#ncrossprod(double[], double[])
	 */
	@Override
	public double ncrossprod(double[] x, double[] y) {

		double result = 0;
		for(int i=0; i<x.length; i++){
			result += x[i]* y[i];
		}
		return result;
	}	
	
	/* ncrossprod = x[][] %*% y[][]
	 * 
	 */
	@Override
	public double[][] ncrossprod(double[][] x, double[][] y) {

		doubleArray = new double[x.length][y[0].length];
		for(int i=0; i<y[0].length; i++){		//y[0].length
			for(int j=0; j<x.length; j++){		//x.length
				for(int k=0; k<x[0].length; k++){		//x[0].length
					doubleArray[j][i] += x[j][k] * y[k][i];
				}
			}
		}
		return doubleArray;
	}

	/*  ncrossprod = x[] %*% y[][]
	 *  
	 */
	/* （非 Javadoc）
	 * @see com.pedigree.R.Cross#ncrossprod(double[], double[][])
	 */
	@Override
	public double[] ncrossprod(double[] x, double[][] y) {

		doubleArrayLine = new double[x.length];
		for(int i=0; i<y[0].length; i++){		//y[0].length
			for(int j=0; j<x.length; j++){		//x.length
				doubleArrayLine[i] += x[j] * y[j][i];
			}
		}
		return doubleArrayLine;
	}

	/*  ncrossprod = x[][] %*% y[]
	 * 
	 */
	@Override
	public double[] ncrossprod(double[][] x, double[] y) {

		doubleArrayLine = new double[x.length];
		for(int i=0; i<x[0].length; i++){		//y[0].length
			for(int j=0; j<y.length; j++){		//x.length
				doubleArrayLine[i] += y[j] * x[j][i];
			}
		}
		return doubleArrayLine;
	}
	
}
