import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;  //�Ի��������
public class shijianchuli {      
	//����ť���Ի���״̬�����ݸı�
	public static void panduan(int i){
		switch (i){
		case 1:   //�����ļ���ַ�ɹ�
			jiemian.jbkstj.setEnabled(true);  //��ʼͳ�ư�ť����
			jiemian.jbxsjg.setEnabled(false); //��ʾ���������
			jiemian.jbazcx.setEnabled(false); //�ַ���ѯ������
			jiemian.zifukuang.setEditable(false);   //�ַ���ֻ��
			jiemian.zifukuang.setText("����ʹ��\n��δͳ��");  //�ַ���ֵ
			jiemian.jieguolan.setText("\n\n\n\t׼����ʼͳ��");    //�������ֵ
			break;
		case 2:  //�����ļ���ַʧ��
			jiemian.jbkstj.setEnabled(false);  //��ʼͳ�ư�ť������
			jiemian.jbxsjg.setEnabled(false); //��ʾ���������
			jiemian.jbazcx.setEnabled(false); //���ֲ�ѯ������
			jiemian.zifukuang.setEditable(false);   //�ַ���ֻ��
			jiemian.zifukuang.setText("����ʹ��\n��δͳ��");  //�ַ���ֵ
			jiemian.jieguolan.setText("\n\n\t���ı��ļ���");    //�������ֵ
			break;
		case 3: //�ļ�ͳ�Ƴɹ�
			jiemian.jbkstj.setEnabled(true);  //��ʼͳ�ư�ť����
			jiemian.jbxsjg.setEnabled(true); //��ʾ�������
			jiemian.jbazcx.setEnabled(true); //���ֲ�ѯ����
			jiemian.zifukuang.setEditable(true);   //�ַ���ɶ�д
			jiemian.zifukuang.setText("");  //�ַ���ֵ
			//�ڷ�������������
			break;
		case 4: //�ļ�ͳ��ʧ��
			jiemian.jbkstj.setEnabled(true);  //��ʼͳ�ư�ť����
			jiemian.jbxsjg.setEnabled(false); //��ʾ���������
			jiemian.jbazcx.setEnabled(false); //���ֲ�ѯ������
			jiemian.zifukuang.setEditable(false);   //�ַ���ֻ��
			jiemian.zifukuang.setText("����ʹ��\n��δͳ��");  //�ַ���ֵ
			//�ڷ�������������
			break;
		}
	}
}
//�����ı���ť�¼�
class daoruaction implements ActionListener{
	static File file;  //�����ļ�����
	public void actionPerformed(ActionEvent e){
		jiemian.jfc.showOpenDialog(null); //���ļ��Ի���
		try{ 
			if(jiemian.jfc.getSelectedFile().equals(file))return;  //�ǵ�һ��δ���ļ�ʱ
			file = jiemian.jfc.getSelectedFile();                  //��ȡ������ļ�
			jiemian.dizhilan.setText(file.getAbsolutePath());      //��ȡ�ļ�·������ʾ�ڵ�ַ��
			String s = jiemian.dizhilan.getText();                 //��ȡ��ַ��·��
			s=s.substring(s.length()-4, s.length());               //��ȡ·�������ļ���׺��
			if(s.equalsIgnoreCase(".txt"))       //�ж��Ƿ���TXT�ļ��������ִ�Сд
				  shijianchuli.panduan(1);   //�����ļ���ַ�ɹ�
			else  shijianchuli.panduan(2);   //����TXT,�����ļ���ַʧ��
		}
		catch(Exception e1){
			 //δ���ļ�ʱ
		}
	}
}
//��ʼͳ�ư�ť�¼�
class kaishiaction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String s = jiemian.zftj.kaishitongji(jiemian.dizhilan.getText()); //��ȡͳ�ƽ��
		if(jiemian.zftj.shifouchenggongtongji()) //�ж��ļ��Ƿ�ͳ�Ƴɹ�
			  shijianchuli.panduan(3);   //�ļ�ͳ�Ƴɹ�
		else  shijianchuli.panduan(4);   //�ļ�ͳ��ʧ��
		jiemian.jieguolan.setText(s);   //�ڽ������ʾ���
	}
}
//��ʾ�����ť�¼�
class xianshiaction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		jiemian.jieguolan.setText(jiemian.zftj.tongjijieguo());//���ͳ�ƽ��
		jiemian.jieguolan.setCaretPosition(0);                 //ʹ�������ö�
	}
}
//���ֲ�ѯ��ť�¼�
class chaxunaction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String s = jiemian.zftj.chaxun(jiemian.zifukuang.getText());  //��ȡ�ַ������ݲ���ѯ�õ����
		jiemian.jieguolan.setText(s);             //������ڽ��������ʾ
	}
}
//���ڰ�ť�¼�
class guanyuaction implements ActionListener{
	public void actionPerformed(ActionEvent e){   
		//�����Ի������������Ϣ
		JOptionPane.showMessageDialog(null, jiemian.guanyu, "����", JOptionPane.INFORMATION_MESSAGE);
	}
}