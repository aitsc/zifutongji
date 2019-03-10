import java.io.*;
public class zifutongji {   		 //字符统计封装类
	private int[][] jieguo = new int[0x10000][2];
	private int[][] cishu;		//排序后的结果
	private char[] wenben;      //文本中的内容
	private int zongshu;		//文件中出现字符的种类数目
	private int zifugeshu;		//文件共多少字符
	private int biaozhi;        //判断统计的状态
	//构造方法
	zifutongji(){
		chushihua();
	}
	//每次统计时的初始化
	private void chushihua(){
		int i;
		for(i=0;i<0x10000;i++){
			jieguo[i][0]=i;   		 //字符编码
			jieguo[i][1]=0;     	 //出现次数
		}
		zongshu=0;      
		zifugeshu=0;
		biaozhi=0;
	}
	//可能的错误标志的判断
	private String cuowu(){
		if(biaozhi==0){
			return "操作失败!\n还未统计文件！";
		}
		if(biaozhi==-1){
			return "操作失败!\n文件为空！";
		}
		if(biaozhi==-2){
			return "操作失败!\n文件不是Unicode编码！";
		}
		if(biaozhi==-3){
			return "操作失败!\n文件路径有误！";
		}
		return "未知错误！";
	}
	//判断文件是否成功统计
	public boolean shifouchenggongtongji(){
		if(biaozhi==1){
			return true;
		}
		else{
			return false;
		}
	}
	//开始统计方法
	public String kaishitongji(String filename){  
		long start,end,length;
		start=System.currentTimeMillis();
		chushihua();//统计前初始化,防止二次统计数据重叠
		try{
			FileInputStream fis = new FileInputStream(filename);
			File f = new File(filename);  //创建可以读取文件长度的类
			if(f.length()<2){
				fis.close();			//关闭文件
				biaozhi=-2;
				return cuowu(); 		//文件不是Unicode编码
			}
			zifugeshu=(int)(f.length()/2-1);
			byte[] buff = new byte[(int)f.length()];  //建立一个存储文件的数组
			fis.read(buff);   			//读取文件
			length=f.length();    //获取文件长度
			fis.close();			//关闭文件
			if((buff[0]&0xff)!=0xff&&(buff[1]&0xff)!=0xfe){
				biaozhi=-2;
				return cuowu(); 			//文件不是Unicode编码
			}
			if(length==2){
				biaozhi=-1;
				return cuowu(); 			//文件为空
			}
			int i,j;   //临时变量
			//文本内容转换并统计各个字符出现次数
			wenben = null;     //防止内存溢出
			wenben = new char[(int)length/2-1];  
			for(i=2;i<length;i+=2){
				jieguo[wenben[i/2-1]=(char)(buff[i+1]<<8|(buff[i]&0xff))][1]++;
			}
			//统计有多少种字符出现过
			for(i=0,zongshu=0;i<0x10000;i++){  
				if(jieguo[i][1]!=0){
					zongshu++;  //出现一次加一
				}
			}
			//出现过的字符重新装入
			cishu = new int[zongshu][2];
			for(i=0,j=0;i<0x10000;i++){  
				if(jieguo[i][1]!=0){
					cishu[j][0]=jieguo[i][0];   //编码装入
					cishu[j][1]=jieguo[i][1];   //次数装入
					j++;
				}
			}
			//排序
			int a1,a2;
			for(i=0;i<zongshu-1;i++){   
				a1=cishu[i][1]; 		//出现次数
				for(j=i+1;j<zongshu;j++){
					if(cishu[j][1]>a1){  		//cishu[i]和cishu[j]交换
						a2=cishu[i][0]; 		 //字符编码
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
			return cuowu(); 		 //文件路径有误
		}
		biaozhi=1;  //文本统计成功标志
		end=System.currentTimeMillis();      //获取结束计算时间
		long shijian=end-start;              //得到总计时间
		String s="文件统计成功！\n";
		s+="共耗时"+String.valueOf(shijian/1000.0)+"秒\n";
		s+="总计"+String.valueOf(zifugeshu)+"个字符，"+String.valueOf(zongshu)+"种字符\n";
		s+="文件大小：";
		//计算文件大小
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
		return s; 			 //文件成功读取
	}
	//输出统计结果
	public String tongjijieguo(){
		if(biaozhi!=1){
			return cuowu();
		}
		String linshi,s="字符总个数：";
		s+=String.valueOf(zifugeshu)+"\n字符种类数量："+String.valueOf(zongshu)+"\n\n字符 - 频率 - 出现次数\n";
		char[] zong = new char[zongshu*30]; //不用String，提高效率
		int i,k=0;
		float f; //出现频率
		for(i=0;i<zongshu;i++){             //生成统计结果
			f=(float)(cishu[i][1]*1.0/zifugeshu)*100; //频率
			linshi=(char)cishu[i][0]+" - "+String.valueOf(f)+"% - "+String.valueOf(cishu[i][1])+"\n";
			System.arraycopy(linshi.toCharArray(), 0, zong, k, linshi.length());//拷贝
			k+=linshi.length();   //结果数组个数增加
		}
		char[] z = new char[k];   //临时变量
		System.arraycopy(zong, 0, z, 0, k); //去掉多余的字符
		s+=String.valueOf(z);
		return s; //返回
	}
	//按字查询
	public String chaxun(String zfs){
		if(biaozhi!=1){    //未统计成功
			return cuowu();
		}
		if(zfs.length()==0){
			return "请写入需查询的字符或字符串！";
		}
		//头部分
		String s="字符总个数："+String.valueOf(zifugeshu)+"\n字符种类数量："+String.valueOf(zongshu);
		if(zfs.length()==1){         //是字符时
			char zf = zfs.charAt(0); //变为char类型
			s+="\n\n字符：‘"+zf+"’\n频率：";
			float f=(float)(jieguo[zf][1]*1.0/zifugeshu)*100;
			s+=String.valueOf(f)+"%\n次数："+jieguo[zf][1];
			if(jieguo[zf][1]==0){
				s="字符‘"+zf+"’未出现在所统计文本中！";
			}
		}
		else{                        //是字符串时
			char[] zfc = zfs.toCharArray();
			int i,j,k=wenben.length-zfc.length;//超过这个位置不可能再相等
			int num=0;//统计的个数
			for(i=0;i<=k;i++){   //开始统计
				if(wenben[i]==zfc[0]){   //若第一个字符相等
					for(j=zfc.length-1;j>=1;j--)
						if(wenben[i+j]!=zfc[j]) break; //从后向前对比比较快
					if(j==0) num++;  //出现一次
				}
			}
			s+="\n\n字符串：“"+String.valueOf(zfc)+"”\n出现次数："+String.valueOf(num);
			if(num==0){
				s="字符串“"+String.valueOf(zfc)+"”未出现在所统计文本中！";
			}
		}
		return s;
	}
}