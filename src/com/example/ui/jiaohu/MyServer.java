package com.example.ui.jiaohu;


import java.io.BufferedReader;
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.Calendar;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.lang.Thread;

import org.eclipse.core.runtime.CoreException;

import com.example.ui.wizards.WizardHandle;
 
public class MyServer extends Thread { 
    private ServerSocket serverSocket; //  
    private ExecutorService servicePool; // �̳߳� 
    
    public MyServer(int port) { 
        try { 
            this.serverSocket = new ServerSocket(port); 
            //this.servicePool = Executors.newFixedThreadPool(2); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
    
    
    public void run() { 
             try{
            	//System.out.println(i + "-");
                Socket socket = this.serverSocket.accept(); // ���ܵ�һ�����ӣ����ҷ���һ���ͻ��˵�Socket����ʵ�� 
                ServerThread th = new ServerThread(socket);
                th.start();
                //System.out.println(i + "=");
                //this.servicePool.execute(new Handler(socket)); 
                //System.out.println(i + "+");
                //System.out.println("User " + i + " is connecting to the Server"); 
               // i++; 
                //this.serverSocket.close();
            } catch (IOException e) { 
                e.printStackTrace(); 
                //this.servicePool.shutdown(); 
            } 
            
        } 
    } 
 
	class ServerThread extends Thread  
	{  

		Socket socket = null;  
		public ServerThread(Socket sk)  
		{  
			this.socket = sk;  
		}  
		public void run()  
		{  
			System.out.println("�ͻ�:" + socket.getInetAddress() + "��ʱ��:" + Calendar.getInstance().getTime().toString() + "���ʹ�!"); 
            try {
                //PrintWriter streamWriter = new PrintWriter(socket.getOutputStream());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                ServerHelper.message = streamReader.readLine();//����ͻ��˼��������Ϣ���ͻ�ͣ�����ﲻ����ִ��.
                System.out.println(ServerHelper.message);
                
             
                streamReader.close();
                System.out.println("done");
                socket.close();
                
            }
            /*catch(FileNotFoundException e) {
                e.printStackTrace();
            }*/
            catch(IOException e) {
               e.printStackTrace();
            }
		}  
    }  
    class Handler implements Runnable { 
        private Socket socket; 
 
        public Handler(Socket socket) { 
            this.socket = socket; 
        } 
 
        @Override 
        public void run() { 
            /*try { 
                // һ�������������ڻ�ȡ�ͻ��˴��������� 
                DataInputStream dis = new DataInputStream( 
                        this.socket.getInputStream()); 
                // ���ڲ���������׼����Ӧ������ 
                DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream()); 
                String str; 
                System.out.println("run"); 
                while (null != (str = dis.readUTF())) { 
                    System.out.println(str); 
                	ServerHelper.message = str;
                    if ("exit".equals(str)) { 
                        System.out.println("�ͻ��˷����ж�����"); 
                        dos.writeUTF("�������Ѿ��رձ�������."); 
                        dos.flush(); 
//                      dos.writeUTF("exit"); //  
//                      dos.flush(); 
                         
                        dos.close(); 
                        dis.close(); 
                        break; 
                    } 
                } 
 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
            */
        	System.out.println("�ͻ�:" + socket.getInetAddress() + "��ʱ��:" + Calendar.getInstance().getTime().toString() + "���ʹ�!"); 
            try {
                PrintWriter streamWriter = new PrintWriter(socket.getOutputStream());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                String read = streamReader.readLine();//����ͻ��˼��������Ϣ���ͻ�ͣ�����ﲻ����ִ��.
                System.out.println(read);
                
                //String tmp = new String(read.getBytes("UTF-8"),"UTF-8");
                
                //sun.misc.BASE64Decoder base64 = new sun.misc.BASE64Decoder();
                //read = new String(base64.decodeBuffer(read));
                //System.out.println("client said:" + tmp);
                String msg = "login_ok";
                streamWriter.write(msg);
                streamWriter.flush();
                streamWriter.close();
                streamReader.close();
                //socket.close();
                
            }
            /*catch(FileNotFoundException e) {
                e.printStackTrace();
            }*/
            catch(IOException e) {
               e.printStackTrace();
            }
        }
    } 


