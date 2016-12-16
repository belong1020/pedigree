package com.pedigree.operate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileGenealogy {

	
	public static void main(String[] args) throws IOException{
		String file = "D:\\2015student\\11-17\\�ű�2\\ϵ������.txt";
		ArrayList num = read(file);
		for (int i = 0; i < num.size(); i++) {
			
			System.out.print(num.get(i)+" ");
			
			if ((i + 1) % 15 == 0.0  ) {
				System.out.println();
			}
		}
		
	}
	
	
	/**	��ϵ�ס� ���б�ʮ���� 
	 * 
	 *	file		�ļ���ַ
	 *	ArrayList	����n�� 15��ֵ
	 *
	 * @param file
	 * @return 
	 * @throws IOException
	 */
	public static ArrayList read(String file) throws IOException {
		
		String[][] stringArray = null ;
		//�洢��ź͸�ĸ���
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
			//����ĸ������Ѵ�ĵ�һ�б����ƥ��  ��ͬʱadd ��Ӧ���
			for(int i=0; i<(liststr.size()+14)/15; i++){
				if( liststr.get(i*15).equals(str[2]) ){
					liststr.add(String.valueOf(i+1));
					//�����Ѵ������ҵ���ĸʱ  ��0����ʮ����
					for(String z:zero){
						liststr.add(z);
					}
					count2 = true;
					continue;
				}
			}
			if(!count2) {
				liststr.add("0");	
				// δ�ҵ���0 ����ʮ����
				for(String z:zero){
					liststr.add(z);
				}
			}
			for(int i=0; i<(liststr.size()+14)/15; i++){
				if(liststr.get(i*15).equals(str[1])){
					liststr.add(String.valueOf(i+1));
					//�����Ѵ������ҵ���ĸʱ  ��0����ʮ����
					for(String z:zero){
						liststr.add(z);
					}
					count1 = true;
					continue;
				}
			}
			if(!count1) {
				liststr.add("0");	
				// δ�ҵ���0 ����ʮ����
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
