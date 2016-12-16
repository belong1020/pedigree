package com.pedigree.R;

import java.util.ArrayList;

public class Table {
	/**	去零以后求剩余数出现次数
	 * 	返回double[][] 
	 * 	第一行为序号，第二行为个数
	 * 
	 * @param liststr
	 * @return
	 */
	public static String[][] table(ArrayList liststr) {
		String[][] count = new String[2][liststr.size() / 15];
		int[] count1 = new int[liststr.size() / 15 +1];
		
		for (int i = 0; i < liststr.size() / 15; i++) {
			count[0][i] = (String) liststr.get(i * 15);
		}

		for (int i = 0; i < liststr.size() / 15; i++) {
			for (int j = 1; j < 15; j++) {
				//System.out.println( (String)liststr.get(i * 15 + j) );
				
				//if((String)liststr.get(i * 15 + j)!="0"){
				String num = (String)liststr.get(i * 15 + j);
				count1[Integer.valueOf(num)]++;
				//}
			}
		}
		for (int i=1; i<count1.length; i++) {
			count[1][i-1] = String.valueOf(count1[i]);
		}
		return count;

	}

	public static void writetable() {

	}
}
