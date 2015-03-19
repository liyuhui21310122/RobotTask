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
					//System.out.println("client start ..."); //向本机的4800端口发出客户请求
					//BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //由系统标准输入设备构造BufferedReader对象
					PrintWriter writer=new PrintWriter(socket.getOutputStream()); //由Socket对象得到输出流，并构造PrintWriter对象
					//BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream())); //由Socket对象得到输入流，并构造相应的BufferedReader对象

					writer.write(msg); //将从系统标准输入读入的字符串输出到Server端
					writer.flush(); //刷新输出流，使Server马上收到该字符串
					writer.close(); //关闭Socket输出流
		            //in.close(); //关闭Socket输入流
					
		            socket.close(); //关闭Socket
				 }
		         catch(Exception e) {
		        	 System.out.println("can not listen to:"+e);//出错，打印出错信息 }
		        }
			}  
	    } 
	 
	 public void send(String msg){
		 SendThread th = new SendThread(socket,msg);
         th.start();
	 }
	 
}


