import java.io.*;
public class zifutongji {   		 //�ַ�ͳ�Ʒ�װ��
	private int[][] jieguo = new int[0x10000][2];
	private int[][] cishu;		//�����Ľ��
	private char[] wenben;      //�ı��е�����
	private int zongshu;		//�ļ��г����ַ���������Ŀ
	private int zifugeshu;		//�ļ��������ַ�
	private int biaozhi;        //�ж�ͳ�Ƶ�״̬
	//���췽��
	zifutongji(){
		chushihua();
	}
	//ÿ��ͳ��ʱ�ĳ�ʼ��
	private void chushihua(){
		int i;
		for(i=0;i<0x10000;i++){
			jieguo[i][0]=i;   		 //�ַ�����
			jieguo[i][1]=0;     	 //���ִ���
		}
		zongshu=0;      
		zifugeshu=0;
		biaozhi=0;
	}
	//���ܵĴ����־���ж�
	private String cuowu(){
		if(biaozhi==0){
			return "����ʧ��!\n��δͳ���ļ���";
		}
		if(biaozhi==-1){
			return "����ʧ��!\n�ļ�Ϊ�գ�";
		}
		if(biaozhi==-2){
			return "����ʧ��!\n�ļ�����Unicode���룡";
		}
		if(biaozhi==-3){
			return "����ʧ��!\n�ļ�·������";
		}
		return "δ֪����";
	}
	//�ж��ļ��Ƿ�ɹ�ͳ��
	public boolean shifouchenggongtongji(){
		if(biaozhi==1){
			return true;
		}
		else{
			return false;
		}
	}
	//��ʼͳ�Ʒ���
	public String kaishitongji(String filename){  
		long start,end,length;
		start=System.currentTimeMillis();
		chushihua();//ͳ��ǰ��ʼ��,��ֹ����ͳ�������ص�
		try{
			FileInputStream fis = new FileInputStream(filename);
			File f = new File(filename);  //�������Զ�ȡ�ļ����ȵ���
			if(f.length()<2){
				fis.close();			//�ر��ļ�
				biaozhi=-2;
				return cuowu(); 		//�ļ�����Unicode����
			}
			zifugeshu=(int)(f.length()/2-1);
			byte[] buff = new byte[(int)f.length()];  //����һ���洢�ļ�������
			fis.read(buff);   			//��ȡ�ļ�
			length=f.length();    //��ȡ�ļ�����
			fis.close();			//�ر��ļ�
			if((buff[0]&0xff)!=0xff&&(buff[1]&0xff)!=0xfe){
				biaozhi=-2;
				return cuowu(); 			//�ļ�����Unicode����
			}
			if(length==2){
				biaozhi=-1;
				return cuowu(); 			//�ļ�Ϊ��
			}
			int i,j;   //��ʱ����
			//�ı�����ת����ͳ�Ƹ����ַ����ִ���
			wenben = null;     //��ֹ�ڴ����
			wenben = new char[(int)length/2-1];  
			for(i=2;i<length;i+=2){
				jieguo[wenben[i/2-1]=(char)(buff[i+1]<<8|(buff[i]&0xff))][1]++;
			}
			//ͳ���ж������ַ����ֹ�
			for(i=0,zongshu=0;i<0x10000;i++){  
				if(jieguo[i][1]!=0){
					zongshu++;  //����һ�μ�һ
				}
			}
			//���ֹ����ַ�����װ��
			cishu = new int[zongshu][2];
			for(i=0,j=0;i<0x10000;i++){  
				if(jieguo[i][1]!=0){
					cishu[j][0]=jieguo[i][0];   //����װ��
					cishu[j][1]=jieguo[i][1];   //����װ��
					j++;
				}
			}
			//����
			int a1,a2;
			for(i=0;i<zongshu-1;i++){   
				a1=cishu[i][1]; 		//���ִ���
				for(j=i+1;j<zongshu;j++){
					if(cishu[j][1]>a1){  		//cishu[i]��cishu[j]����
						a2=cishu[i][0]; 		 //�ַ�����
						cishu[i][0]=cishu[j][0];
						cishu[i][1]=cishu[j][1];
						cishu[j][0]=a2;
						cishu[j][1]=a1;
						a1=cishu[i][1];
					}
				}
			}
		}
		catch(Exception e){
			biaozhi=-3;
			return cuowu(); 		 //�ļ�·������
		}
		biaozhi=1;  //�ı�ͳ�Ƴɹ���־
		end=System.currentTimeMillis();      //��ȡ��������ʱ��
		long shijian=end-start;              //�õ��ܼ�ʱ��
		String s="�ļ�ͳ�Ƴɹ���\n";
		s+="����ʱ"+String.valueOf(shijian/1000.0)+"��\n";
		s+="�ܼ�"+String.valueOf(zifugeshu)+"���ַ���"+String.valueOf(zongshu)+"���ַ�\n";
		s+="�ļ���С��";
		//�����ļ���С
		int i=1024;
		if(length<i){           
			s+=String.valueOf(length)+"B";  
		}
		else if(length<(i*=1024)){
			s+=String.valueOf((float)length/1024)+"KB";
		}
		else if(length<(i*=1024)){
			s+=String.valueOf((float)length/1024/1024)+"MB";
		}
		else{
			s+=String.valueOf((float)length/1024/1024/1024)+"GB";
		}
		return s; 			 //�ļ��ɹ���ȡ
	}
	//���ͳ�ƽ��
	public String tongjijieguo(){
		if(biaozhi!=1){
			return cuowu();
		}
		String linshi,s="�ַ��ܸ�����";
		s+=String.valueOf(zifugeshu)+"\n�ַ�����������"+String.valueOf(zongshu)+"\n\n�ַ� - Ƶ�� - ���ִ���\n";
		char[] zong = new char[zongshu*30]; //����String�����Ч��
		int i,k=0;
		float f; //����Ƶ��
		for(i=0;i<zongshu;i++){             //����ͳ�ƽ��
			f=(float)(cishu[i][1]*1.0/zifugeshu)*100; //Ƶ��
			linshi=(char)cishu[i][0]+" - "+String.valueOf(f)+"% - "+String.valueOf(cishu[i][1])+"\n";
			System.arraycopy(linshi.toCharArray(), 0, zong, k, linshi.length());//����
			k+=linshi.length();   //��������������
		}
		char[] z = new char[k];   //��ʱ����
		System.arraycopy(zong, 0, z, 0, k); //ȥ��������ַ�
		s+=String.valueOf(z);
		return s; //����
	}
	//���ֲ�ѯ
	public String chaxun(String zfs){
		if(biaozhi!=1){    //δͳ�Ƴɹ�
			return cuowu();
		}
		if(zfs.length()==0){
			return "��д�����ѯ���ַ����ַ�����";
		}
		//ͷ����
		String s="�ַ��ܸ�����"+String.valueOf(zifugeshu)+"\n�ַ�����������"+String.valueOf(zongshu);
		if(zfs.length()==1){         //���ַ�ʱ
			char zf = zfs.charAt(0); //��Ϊchar����
			s+="\n\n�ַ�����"+zf+"��\nƵ�ʣ�";
			float f=(float)(jieguo[zf][1]*1.0/zifugeshu)*100;
			s+=String.valueOf(f)+"%\n������"+jieguo[zf][1];
			if(jieguo[zf][1]==0){
				s="�ַ���"+zf+"��δ��������ͳ���ı��У�";
			}
		}
		else{                        //���ַ���ʱ
			char[] zfc = zfs.toCharArray();
			int i,j,k=wenben.length-zfc.length;//�������λ�ò����������
			int num=0;//ͳ�Ƶĸ���
			for(i=0;i<=k;i++){   //��ʼͳ��
				if(wenben[i]==zfc[0]){   //����һ���ַ����
					for(j=zfc.length-1;j>=1;j--)
						if(wenben[i+j]!=zfc[j]) break; //�Ӻ���ǰ�ԱȱȽϿ�
					if(j==0) num++;  //����һ��
				}
			}
			s+="\n\n�ַ�������"+String.valueOf(zfc)+"��\n���ִ�����"+String.valueOf(num);
			if(num==0){
				s="�ַ�����"+String.valueOf(zfc)+"��δ��������ͳ���ı��У�";
			}
		}
		return s;
	}
}