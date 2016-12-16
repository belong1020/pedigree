package com.pedigree.operate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileGenealogy {

	
	public static void main(String[] args) throws IOException{
		String file = "D:\\2015student\\11-17\\张斌2\\系谱数据.txt";
		ArrayList num = read(file);
		for (int i = 0; i < num.size(); i++) {
			
			System.out.print(num.get(i)+" ");
			
			if ((i + 1) % 15 == 0.0  ) {
				System.out.println();
			}
		}
		
	}
	
	
	/**	读系谱、 三列变十五列 
	 * 
	 *	file		文件地址
	 *	ArrayList	返回n行 15列值
	 *
	 * @param file
	 * @return 
	 * @throws IOException
	 */
	public static ArrayList read(String file) throws IOException {
		
		String[][] stringArray = null ;
		//存储编号和父母序号
		ArrayList liststr = new ArrayList();	
		BufferedReader br = null;
		String[] zero = {"0","0","0","0","0","0"};
		try{
		br = new BufferedReader(new InputStreamReader(new FileInputStream( file )));
		String line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] str = line.split("\t");
			liststr.add(str[0]);
			boolean count1 = false ;
			boolean count2 = false ;
			//将父母编号在已存的第一列编号中匹配  相同时add 对应序号
			for(int i=0; i<(liststr.size()+14)/15; i++){
				if( liststr.get(i*15).equals(str[2]) ){
					liststr.add(String.valueOf(i+1));
					//当在已存编号里找到父母时  添0补齐十五列
					for(String z:zero){
						liststr.add(z);
					}
					count2 = true;
					continue;
				}
			}
			if(!count2) {
				liststr.add("0");	
				// 未找到添0 补齐十五列
				for(String z:zero){
					liststr.add(z);
				}
			}
			for(int i=0; i<(liststr.size()+14)/15; i++){
				if(liststr.get(i*15).equals(str[1])){
					liststr.add(String.valueOf(i+1));
					//当在已存编号里找到父母时  添0补齐十五列
					for(String z:zero){
						liststr.add(z);
					}
					count1 = true;
					continue;
				}
			}
			if(!count1) {
				liststr.add("0");	
				// 未找到添0 补齐十五列
				for(String z:zero){
					liststr.add(z);
				}
			}
			
		}
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			br.close();
			return liststr;
		}
	}
}
