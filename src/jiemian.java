import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class jiemian extends JFrame{
	//�豻���������õ����о�̬��
	static zifutongji zftj = new zifutongji(); //�ַ�ͳ����
	static String guanyu =                     //���ڰ�ť������
						"���ƣ������ַ�ͳ��\n" +
						"�����ߣ�tsc\n" +
						"���ڣ�2014�� 11�� ��Ѯ\n"+
						"ע�⣺��ѯ��֧�֡��س�������Linux/UNIX��\n"+
						"��Դ��ɣ�GPL v3";
		//ȫ���������
	static JTextField dizhilan=new JTextField("��δ���ı�");      //��ַ����Ȩ�ޣ�����д  
	static JTextArea zifukuang=new JTextArea("����ʹ��\n��δͳ��"); //�ַ���Ȩ�ޣ� ����д���ɲ����޸�
	static JTextArea jieguolan=new JTextArea("\n\n\t�뵼���ı�");   //�������Ȩ�ޣ� ����д
	static JButton jbkstj=new JButton("��ʼͳ��");   //��ť��Ȩ�ޣ� �Բ���ʾ
	static JButton jbxsjg=new JButton("��ʾ���");   //��ť��Ȩ�ޣ� �Բ���ʾ
	static JButton jbazcx=new JButton("���ֲ�ѯ");   //��ť��Ȩ�ޣ� �Բ���ʾ
	static JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));//�ı�����Ի�����ʾ��ǰ�ļ�·��
	//���캯��
	public jiemian(){
		//�����Ի���
		setTitle("�ַ�ͳ��");
		setBounds(450,45,406,629);  //���ڴ�С
		setLayout(null);            //ȡ�����ֹ�����
		setResizable(false);        //��С���ɱ�
		//�������
		Container mianban=this.getContentPane(); //������
		mianban.setSize(400,600);                //����С
		//�������
		JLabel lujing=new JLabel("·����");                 //·����ǩ
		JLabel zifu=new JLabel("���ѯ�֣�");               //�ַ���ǩ
		JScrollPane zfgdt=new JScrollPane(zifukuang);      //�ַ��������
		JScrollPane jggdt=new JScrollPane(jieguolan);      //�����������     
		JButton jbdrwb=new JButton("�����ı�");   //��ť
		JButton jbgy=new JButton("����");         //��ť
		//�����ӵ����
		mianban.add(lujing);    //·����ǩ
		mianban.add(zifu);      //�ַ���ǩ
		mianban.add(dizhilan);  //��ַ��
		mianban.add(zfgdt);     //�ַ���
		mianban.add(jggdt);     //ͳ�ƽ����
		mianban.add(jbdrwb);//�����ı�
		mianban.add(jbkstj);//��ʼͳ��
		mianban.add(jbxsjg);//��ʾ���
		mianban.add(jbazcx);//���ֲ�ѯ
		mianban.add(jbgy);  //����
		//�������
		lujing.setBounds(15, 15, 40, 25);        //·����ǩ
		zifu.setBounds(290, 355, 65, 25);        //�ַ���ǩ
		dizhilan.setBounds(55, 15, 330, 25);     //��ַ��
		zfgdt.setBounds(290, 380, 94, 55);       //�ַ���
		jggdt.setBounds(15, 55, 260, 530);       //ͳ�ƽ����
		jbdrwb.setBounds(290, 55, 94, 60);  //�����ı�
		jbkstj.setBounds(290, 130, 94, 60); //��ʼͳ��
		jbxsjg.setBounds(290, 205, 94, 60); //��ʾ���
		jbazcx.setBounds(290, 280, 94, 60); //���ֲ�ѯ
		jbgy.setBounds(290, 450, 94, 134);  //����
		//�����ʾ
		dizhilan.setEditable(false);    //��ַ��ֻ��
		zifukuang.setLineWrap(true);    //�ַ����Զ�����
		zifukuang.setEditable(false);   //�ַ���ֻ��
		jieguolan.setLineWrap(true);    //������Զ�����
		jieguolan.setEditable(false);   //�����ֻ��
		jbkstj.setEnabled(false); //��ʼͳ�Ʋ�����
		jbxsjg.setEnabled(false); //��ʾ���������
		jbazcx.setEnabled(false); //���ֲ�ѯ������
		jfc.setFileFilter(new FileNameExtensionFilter("�ı��ļ�", "txt"));  //�ı�����
		//ע������¼�
		jbdrwb.addActionListener(new daoruaction());  //�����ı���ť
		jbkstj.addActionListener(new kaishiaction()); //��ʼͳ�ư�ť
		jbxsjg.addActionListener(new xianshiaction());//��ʾ�����ť
		jbazcx.addActionListener(new chaxunaction()); //��ʼͳ�ư�ť
		jbgy.addActionListener(new guanyuaction()); //���ڰ�ť
	}
	//������
	public static void main(String[] args){
		jiemian jm = new jiemian();                        //���ɽ������
		jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ʹ���̿ɹر�
		jm.setVisible(true);                               //��ʾ����
	}
}