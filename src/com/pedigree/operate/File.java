package com.pedigree.operate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class File {

	public static void main(String[] args) throws IOException {
		String path = "D:\\2015student\\11-17\\standard pedigree file1.csv";
		List num = read(path);
		System.out.println("read");
		System.out.println(num.size());

		for (int i = 0; i < num.size(); i++) {
			
			System.out.print(num.get(i)+" ");
			
			if ((i + 1) % 15 == 0.0  ) {
				System.out.println();
			}
		}
	}

	/**
	 * 读pedigree文件、
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static ArrayList read(String file) {
		ArrayList liststr = new ArrayList();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				line = add0(line);
				//System.out.println(line);
				String[] str = line.split(",");
				StringBuffer sbs = new StringBuffer();
				for (int i = 0; i < str.length; i++) {
					if (str[i] == null || str[i].equals("NA"))
						sbs.append("0,");
					else
						sbs.append(str[i]+",");
				}
				String[] num1 = sbs.toString().split(",");
				sbs.setLength(0);
				for (int count = 0; count < num1.length; count++) {
					liststr.add(num1[count]);
				}
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return liststr;
		}
	}

	/**
	 * 行处理
	 * 
	 * @param line
	 * @return
	 */
	private static String add0(String line) {
		String newString = line;

		for (int count = 0; count < newString.length() - 1; count++) {
			// newString中第count个字符和第count+1个字符同为"," 则在中间加"0"
			//System.out.println(newString.length());
			if (String.valueOf(newString.charAt(count)).equals(
					String.valueOf(newString.charAt(count + 1)))
					&& String.valueOf(newString.charAt(count)).equals(",")) {

				newString = newString.substring(0, count + 1) + "0"
						+ newString.substring(count + 1, line.length());
			}
			if( count == newString.length() - 1
				&&	String.valueOf(newString.charAt(count)).equals(",")){
				newString = newString.substring(0, count + 1) + "0";
				
			}
			
			
		}
		return newString;
	}

}
