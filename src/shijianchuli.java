import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;  //对话框所需包
public class shijianchuli {      
	//处理按钮、对话框状态、内容改变
	public static void panduan(int i){
		switch (i){
		case 1:   //导入文件地址成功
			jiemian.jbkstj.setEnabled(true);  //开始统计按钮可用
			jiemian.jbxsjg.setEnabled(false); //显示结果不可用
			jiemian.jbazcx.setEnabled(false); //字符查询不可用
			jiemian.zifukuang.setEditable(false);   //字符框只读
			jiemian.zifukuang.setText("不可使用\n还未统计");  //字符框赋值
			jiemian.jieguolan.setText("\n\n\n\t准备开始统计");    //结果栏赋值
			break;
		case 2:  //导入文件地址失败
			jiemian.jbkstj.setEnabled(false);  //开始统计按钮不可用
			jiemian.jbxsjg.setEnabled(false); //显示结果不可用
			jiemian.jbazcx.setEnabled(false); //按字查询不可用
			jiemian.zifukuang.setEditable(false);   //字符框只读
			jiemian.zifukuang.setText("不可使用\n还未统计");  //字符框赋值
			jiemian.jieguolan.setText("\n\n\t非文本文件！");    //结果栏赋值
			break;
		case 3: //文件统计成功
			jiemian.jbkstj.setEnabled(true);  //开始统计按钮可用
			jiemian.jbxsjg.setEnabled(true); //显示结果可用
			jiemian.jbazcx.setEnabled(true); //按字查询可用
			jiemian.zifukuang.setEditable(true);   //字符框可读写
			jiemian.zifukuang.setText("");  //字符框赋值
			//在方法内输出结果栏
			break;
		case 4: //文件统计失败
			jiemian.jbkstj.setEnabled(true);  //开始统计按钮可用
			jiemian.jbxsjg.setEnabled(false); //显示结果不可用
			jiemian.jbazcx.setEnabled(false); //按字查询不可用
			jiemian.zifukuang.setEditable(false);   //字符框只读
			jiemian.zifukuang.setText("不可使用\n还未统计");  //字符框赋值
			//在方法内输出结果栏
			break;
		}
	}
}
//导入文本按钮事件
class daoruaction implements ActionListener{
	static File file;  //定义文件对象
	public void actionPerformed(ActionEvent e){
		jiemian.jfc.showOpenDialog(null); //打开文件对话框
		try{ 
			if(jiemian.jfc.getSelectedFile().equals(file))return;  //非第一次未打开文件时
			file = jiemian.jfc.getSelectedFile();                  //获取导入的文件
			jiemian.dizhilan.setText(file.getAbsolutePath());      //获取文件路径并显示在地址栏
			String s = jiemian.dizhilan.getText();                 //获取地址栏路径
			s=s.substring(s.length()-4, s.length());               //获取路径最后的文件后缀名
			if(s.equalsIgnoreCase(".txt"))       //判断是否是TXT文件，不区分大小写
				  shijianchuli.panduan(1);   //导入文件地址成功
			else  shijianchuli.panduan(2);   //不是TXT,导入文件地址失败
		}
		catch(Exception e1){
			 //未打开文件时
		}
	}
}
//开始统计按钮事件
class kaishiaction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String s = jiemian.zftj.kaishitongji(jiemian.dizhilan.getText()); //获取统计结果
		if(jiemian.zftj.shifouchenggongtongji()) //判断文件是否统计成功
			  shijianchuli.panduan(3);   //文件统计成功
		else  shijianchuli.panduan(4);   //文件统计失败
		jiemian.jieguolan.setText(s);   //在结果栏显示结果
	}
}
//显示结果按钮事件
class xianshiaction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		jiemian.jieguolan.setText(jiemian.zftj.tongjijieguo());//输出统计结果
		jiemian.jieguolan.setCaretPosition(0);                 //使滚动条置顶
	}
}
//按字查询按钮事件
class chaxunaction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String s = jiemian.zftj.chaxun(jiemian.zifukuang.getText());  //获取字符框内容并查询得到结果
		jiemian.jieguolan.setText(s);             //将结果在结果栏中显示
	}
}
//关于按钮事件
class guanyuaction implements ActionListener{
	public void actionPerformed(ActionEvent e){   
		//弹出对话框，输出关于信息
		JOptionPane.showMessageDialog(null, jiemian.guanyu, "关于", JOptionPane.INFORMATION_MESSAGE);
	}
}