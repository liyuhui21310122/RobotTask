package com.example.ui.jiaohu;

import java.io.BufferedReader; 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.Socket; 
import java.net.UnknownHostException;
import java.util.Calendar;
import java.io.*;


public class MyClient { 
	 private Socket socket;
	 public MyClient() {
		 try {
			socket=new Socket("127.0.0.1",6667);
			//socket=new Socket("10.15.199.175",6667);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 class SendThread extends Thread  
		{  

			Socket socket = null;  
			String msg ;
			public SendThread(Socket sk, String _msg)  
			{  
				this.socket = sk;  
				this.msg = _msg;
			}  
			public void run()  
			{  
				try{
					//System.out.println("client start ..."); //�򱾻���4800�˿ڷ����ͻ�����
					//BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //��ϵͳ��׼�����豸����BufferedReader����
					PrintWriter writer=new PrintWriter(socket.getOutputStream()); //��Socket����õ��������������PrintWriter����
					//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream())); //��Socket����õ�����������������Ӧ��BufferedReader����

					writer.write(msg); //����ϵͳ��׼���������ַ��������Server��
					writer.flush(); //ˢ���������ʹServer�����յ����ַ���
					writer.close(); //�ر�Socket�����
		            //in.close(); //�ر�Socket������
					
		            socket.close(); //�ر�Socket
				 }
		         catch(Exception e) {
		        	 System.out.println("can not listen to:"+e);//������ӡ������Ϣ }
		        }
			}  
	    } 
	 
	 public void send(String msg){
		 SendThread th = new SendThread(socket,msg);
         th.start();
	 }
	 
}


