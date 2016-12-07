package com.pedigree.R;

public class RmathImp implements Rmath{

	/*		colSums of double[][]
	 * @see com.pedigree.R.Rmath#colSums(double[][])
	 */
	public double[] colSums (double[][] num) {
	      
		double[] result = new double[num[0].length];
	      
	      for (int i = 0; i < num[0].length; i++) {
	         for (int j = 0; j < num.length; j++) {
	        	 result[i] += num[j][i];
	        	 //result[i] += Math.abs(num[i][j]);
	         }
	      }
	      return result;
	 }
	
	/*		rowSums of double[][]
	 * @see com.pedigree.R.Rmath#rowSums(double[][])
	 */
	public double[] rowSums (double[][] num) {
	      
		double[] result = new double[num[0].length];
	      
	      for (int i = 0; i < num.length; i++) {
	         for (int j = 0; j < num[0].length; j++) {
	        	 result[i] += num[i][j];
	        	 //result[i] += Math.abs(num[i][j]);
	         }
	      }
	      return result;
	 }

	
	/*		log of double[][]
	 * @see com.pedigree.R.Rmath#log(double[][])
	 */
	public double[][] log(double[][] num) {
		double[][] num1 = new double[num.length][num[0].length];
		for(int i=0; i<num1.length; i++){
			for(int j=0; j<num1[0].length; j++){
				num1[i][j] = Math.log(num[i][j]);
			}
		}
		return num1;
	}
	
	/*		log of double[]
	 * @see com.pedigree.R.Rmath#log(double[])
	 */
	public double[] log(double[] num){
		double[] num1 = new double[num.length];
		for(int i=0; i<num.length; i++){
			num1[i] = Math.log(num[i]);
		}
		return num;
	}

	
	/*		max of double[]
	 * @see com.pedigree.R.Rmath#max(double[])
	 */
	public double max(double[] num) {
		
		double num1 = num[0];
		for(int i=1; i<num.length; i++){
			if(num1 < num[i]){
				num1 = num[i];
			}
		}
		return num1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
