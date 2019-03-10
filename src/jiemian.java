import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class jiemian extends JFrame{
	//需被监听器调用的所有静态类
	static zifutongji zftj = new zifutongji(); //字符统计类
	static String guanyu =                     //关于按钮的内容
						"名称：快速字符统计\n" +
						"制作者：tsc\n" +
						"日期：2014年 11月 下旬\n"+
						"注意：查询不支持“回车”（非Linux/UNIX）\n"+
						"开源许可：GPL v3";
		//全局组件定义
	static JTextField dizhilan=new JTextField("还未打开文本");      //地址栏（权限）读、写  
	static JTextArea zifukuang=new JTextArea("不可使用\n还未统计"); //字符框（权限） 读、写、可不可修改
	static JTextArea jieguolan=new JTextArea("\n\n\t请导入文本");   //结果栏（权限） 读、写
	static JButton jbkstj=new JButton("开始统计");   //按钮（权限） 显不显示
	static JButton jbxsjg=new JButton("显示结果");   //按钮（权限） 显不显示
	static JButton jbazcx=new JButton("按字查询");   //按钮（权限） 显不显示
	static JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));//文本导入对话框，显示当前文件路径
	//构造函数
	public jiemian(){
		//调整对话框
		setTitle("字符统计");
		setBounds(450,45,406,629);  //窗口大小
		setLayout(null);            //取消布局管理器
		setResizable(false);        //大小不可变
		//建立面板
		Container mianban=this.getContentPane(); //面板对象
		mianban.setSize(400,600);                //面板大小
		//生成组件
		JLabel lujing=new JLabel("路径：");                 //路径标签
		JLabel zifu=new JLabel("需查询字：");               //字符标签
		JScrollPane zfgdt=new JScrollPane(zifukuang);      //字符框滚动条
		JScrollPane jggdt=new JScrollPane(jieguolan);      //结果栏滚动条     
		JButton jbdrwb=new JButton("导入文本");   //按钮
		JButton jbgy=new JButton("关于");         //按钮
		//组件添加到面板
		mianban.add(lujing);    //路径标签
		mianban.add(zifu);      //字符标签
		mianban.add(dizhilan);  //地址栏
		mianban.add(zfgdt);     //字符框
		mianban.add(jggdt);     //统计结果栏
		mianban.add(jbdrwb);//导入文本
		mianban.add(jbkstj);//开始统计
		mianban.add(jbxsjg);//显示结果
		mianban.add(jbazcx);//按字查询
		mianban.add(jbgy);  //关于
		//组件布局
		lujing.setBounds(15, 15, 40, 25);        //路径标签
		zifu.setBounds(290, 355, 65, 25);        //字符标签
		dizhilan.setBounds(55, 15, 330, 25);     //地址栏
		zfgdt.setBounds(290, 380, 94, 55);       //字符框
		jggdt.setBounds(15, 55, 260, 530);       //统计结果栏
		jbdrwb.setBounds(290, 55, 94, 60);  //导入文本
		jbkstj.setBounds(290, 130, 94, 60); //开始统计
		jbxsjg.setBounds(290, 205, 94, 60); //显示结果
		jbazcx.setBounds(290, 280, 94, 60); //按字查询
		jbgy.setBounds(290, 450, 94, 134);  //关于
		//组件显示
		dizhilan.setEditable(false);    //地址栏只读
		zifukuang.setLineWrap(true);    //字符框自动换行
		zifukuang.setEditable(false);   //字符框只读
		jieguolan.setLineWrap(true);    //结果栏自动换行
		jieguolan.setEditable(false);   //结果栏只读
		jbkstj.setEnabled(false); //开始统计不可用
		jbxsjg.setEnabled(false); //显示结果不可用
		jbazcx.setEnabled(false); //按字查询不可用
		jfc.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));  //文本过滤
		//注册监听事件
		jbdrwb.addActionListener(new daoruaction());  //导入文本按钮
		jbkstj.addActionListener(new kaishiaction()); //开始统计按钮
		jbxsjg.addActionListener(new xianshiaction());//显示结果按钮
		jbazcx.addActionListener(new chaxunaction()); //开始统计按钮
		jbgy.addActionListener(new guanyuaction()); //关于按钮
	}
	//主函数
	public static void main(String[] args){
		jiemian jm = new jiemian();                        //生成界面对象
		jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //使进程可关闭
		jm.setVisible(true);                               //显示窗口
	}
}