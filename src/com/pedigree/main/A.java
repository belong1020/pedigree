package com.pedigree.main;

import java.io.IOException;
import java.util.ArrayList;

import com.pedigree.R.Table;
import com.pedigree.operate.File;
import com.pedigree.operate.FileGenealogy;

public class A {

	/**  ����k���󷽷�
	 * @param pedigreepath �ļ���ַ
	 * @param standard_id
	 * @param file_output
	 * @return
	 * @throws IOException 
	 */
	public static double[][] A(String pedigreepath, boolean standard_id,
			boolean file_output) throws IOException {
		
		//��ȡ�ļ�Ϊʮ���� ����ʱ  ����File.read()
		ArrayList liststr = File.read(pedigreepath);
		
		//��ȡ�ļ�Ϊ��������ʱ  ����FileGenealogy.read()
		//ArrayList liststr = FileGenealogy.read(pedigreepath);
		
		String[][] count = Table.table(liststr);

		if (standard_id == true) {
			System.out.println("Checking the identifier of ALL INDIVIDUSLDs");
			boolean bool = true;
			// �ж������Ƿ��׼��ʽ-----15���ַ� ������exit(1)
			for (int i = 0; i < liststr.size() / 15; i++) {
				if (liststr.get(i * 15).toString().length() != 15) {
					System.out.println("The format of these individuals are error:"	+ i);
					bool = false;
				}
			}
			if (bool != true)
				System.exit(1);
			System.out.println("Conformed the identifier of ALL INDIVIDUSLDs");
			for(int i=0; i<liststr.size()/15; i++){
				liststr.set(i*15, i);
			}
		}
		System.out.println("Total number of individuals:" + count.length);
		// #�ϲ���������������
		ArrayList pedx = new ArrayList();
		for (int i = 0; i < liststr.size() / 15; i++) {
			pedx.add(liststr.get(i * 15));
			pedx.add(liststr.get(i * 15 + 1));
			pedx.add(liststr.get(i * 15 + 8));
		}
		ArrayList ped = new ArrayList();
		if (standard_id == true) {
		/*
		* pedx<-cbind(pedx,0); # ȡpedx��һ�е�8�����һ���� ��String���� ����������������������������
		* pedx[,4]<-substr(pedx[,1],8,nchar(pedx[,1]))
		* #order()�����ķ���ֵ��ʵ�������Сֵ����Сֵ���ٴ�Сֵ...�δ�ֵ�����ֵ���ڵ�λ�á�
		* index.born<-order(as.numeric(pedx[,4])) pedx<-pedx[index.born,]
		* ped<-pedx colnames(ped)=c("ID","Sir","Dam","Birthday")
		*/
		} else {
			ArrayList pedx1 = new ArrayList();
			ArrayList pedx2 = new ArrayList();
			System.out.println("Making needed files");
			for (int i = 0; i < pedx.size() / 3; i++) {
				if (pedx.get(i * 3 + 1).equals("0")
						&& pedx.get(i * 3 + 2).equals("0")) {
					pedx1.add(pedx.get(i * 3));
					pedx1.add(pedx.get(i * 3 + 1));
					pedx1.add(pedx.get(i * 3 + 2));
				} else {
					pedx2.add(pedx.get(i * 3));
					pedx2.add(pedx.get(i * 3 + 1));
					pedx2.add(pedx.get(i * 3 + 2));
				}
			}// pedx2<-pedx[!(pedx[,2]=="0" & pedx[,3]=="0"),]
			boolean go = true;
			int pedx1Length = pedx1.size() / 3;
			while (go == true) {
				ArrayList<Integer> index = new ArrayList<Integer>();
				for (int i = 0; i < pedx2.size() / 3; i++) {// Ҫ��Դ�ļ��޸�ĸ���� ��ſ�ǰ

					if ( Integer.valueOf((String)pedx2.get(i * 3 + 1)) <= pedx1Length
							&& (int) Integer.valueOf((String)pedx2.get(i * 3 + 2)) <= pedx1Length) {
						index.add(i);// pedx2[,2:3]
										// ���ַ�������pedx1ȫ���ַ���ʱ����pedx2����add��index
					} else {
						label1: for (int j = 0; j < pedx1.size() / 3; j++) {
							for (int k = 0; k < pedx1.size() / 3; k++) {
								if ( pedx2.get(i * 3 + 1).equals(pedx1.get(j * 3))	//��ʱԭ���� Ӧȫ��Ϊ��Ųſɱȶԣ� ��Ϊ�������������  �������������жϺ�  ��������Ϊint��ʽ
										&& pedx2.get(i * 3 + 2).equals(pedx1.get(k * 3))) {
									index.add(i);// pedx2[,2:3]
													// ���ַ�������pedx1ȫ���ַ���ʱ����pedx2����add��index
									break label1;
								}
							}
						}
					}
				}	
				for (int i = 0; i < index.size(); i++) {// ����
					pedx1.add(pedx2.get(index.get(i)*3));
					pedx1.add(pedx2.get(index.get(i)*3 + 1));
					pedx1.add(pedx2.get(index.get(i)*3 + 2));
				}
				for (int i = index.size()-1; i >= 0; i--) {// ɾ��
					pedx2.remove(index.get(i) * 3 + 2);
					pedx2.remove(index.get(i) * 3 + 1);
					pedx2.remove(index.get(i) * 3);
				}
				if (pedx2.size() == 0)
					go = false;
			}
			ped = pedx1;
			// colnames(ped)<-c("ID","Sir","Dam")
		}
		// if(file_output==true)
		// write.table(ped,"original pedigree.txt",col.names=T,row.names=F,quote=F,sep="\t");
		String[] order_id = new String[ped.size() / 3];
		for (int i = 0; i < ped.size() / 3; i++){
			order_id[i] = String.valueOf(ped.get(i * 3));
		}
		ArrayList num_ped = new ArrayList();
		for (int i = 0; i < ped.size()/3; i++) {
			
			for (int j = 0; j < order_id.length; j++) {
				if (order_id[j].equals(ped.get(i*3))) {
					num_ped.add(j+1);
					break;
				}
			}
			num_ped.add(ped.get(i*3+1));
			num_ped.add(ped.get(i*3+2));
		}

		// if(file_output==true)
		// write.table(num.ped,"numeric pedigree.txt",col.names=T,row.names=F,quote=F,sep="\t");
		String[] g0_id = new String[liststr.size() / 15];
		for (int i = 0; i < liststr.size() / 15; i++) 
			g0_id[i] = String.valueOf(liststr.get(i * 15));
		int[] g0_index = new int[g0_id.length];
		for (int i = 0; i < g0_id.length; i++) {
			for (int j = 0; j < order_id.length; j++) {
				if (g0_id[i].equals(order_id[j]) ) {
					g0_index[i] = j;
					break;
				}
			}
		}
		System.out.println("Starting calculating A with composed files");
		double[][] A_all = A_cal(num_ped);
		double[][] temp = new double[A_all.length][A_all[0].length];
		double[][] A = new double[A_all.length][A_all[0].length];
				
		for (int i = 0; i < g0_index.length; i++) {
			for(int j = 0; j<A_all.length; j++){			
			temp[j][i] = A_all[j][g0_index[i]];
			}
		}
		for (int i = 0; i < temp.length; i++) {
			System.arraycopy(temp[g0_index[i]], 0, A[i], 0, temp[0].length);		
		}
		// colnames(A)<- g0.id
		// rownames(A)<- g0.id
		return A;
	}

	private static double[][] A_cal(ArrayList ped) {
		int nid = ped.size() / 3;
		double[][] A = new double[nid][nid];
		for (int i = 0; i < nid; i++) {
			for (int j = 0; j < i+1; j++) {
				if (j == i) { // diag. elements
					// ת�ַ��� �ж�
					if ( Integer.valueOf(ped.get(i * 3 + 1).toString()) == 0
								|| Integer.valueOf(ped.get(i * 3 + 2).toString()) == 0) {
							A[i][i] = 1;
							break;
						} else {
							int sirid = Integer.valueOf(ped.get(i * 3 + 1).toString())-1;
							int damid = Integer.valueOf(ped.get(i * 3 + 2).toString())-1;
							A[i][i] = 1 + 0.5 * A[sirid][damid];
						}
				} else { // # off diag.elements
					int id = (int) ped.get(i * 3)-1;
					if (Integer.valueOf(ped.get(id * 3 + 1).toString()) != 0
							&& Integer.valueOf(ped.get(id * 3 + 2).toString()) != 0) {
						int sirid = Integer.valueOf(ped.get(id * 3 + 1).toString())-1;
						int damid = Integer.valueOf(ped.get(id * 3 + 2).toString())-1;
						A[i][j] = A[j][i] = 0.5 * (A[j][sirid] + A[j][damid]);
					} else if (Integer.valueOf(ped.get(id * 3 + 1).toString()) != 0
							&& Integer.valueOf(ped.get(id * 3 + 2).toString()) == 0) {
						int sirid = Integer.valueOf(ped.get(id * 3 + 1).toString())-1;
						A[i][j] = A[j][i] = 0.5 * A[j][sirid];
					} else if (Integer.valueOf(ped.get(id * 3 + 1).toString()) == 0
							&& Integer.valueOf(ped.get(id * 3 + 2).toString()) != 0) {
						int damid = Integer.valueOf(ped.get(id * 3 + 2).toString())-1;
						A[i][j] = A[j][i] = 0.5 * A[j][damid];
					} else {
						A[i][j] = A[j][i] = 0;
					}

				}

			}
			// if(i%%50==0) print(paste("NO. ANIMAL",i,"DONE!!!"))
			if (i == nid)
				System.out.println("ALL" + i + "ANIMALs DOEN!!!");
		}
		return A;
	}
}
