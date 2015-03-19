package com.example.ui.jiaohu;

import com.example.model.node.StartModel;

public class messageHelper {
	public static String[]aa = getMessage().split("\\:");
	public static String[] indexshift = new String[aa.length-1];
	public static String[] indexrotate = new String[aa.length-1];
	public static String number;
	public static String shift="";
	public static String rotate="";
	
	
	public static String getMessage() {
		// TODO Auto-generated method stub
		//return ServerHelper.message;
		return StartModel.message;
	}
	
	public static String getNumber(){
		number = aa[0];
		return number;	
	}
	
	
	//存放的是轴序号的数组
	public static String[] getIndexs(){
		int i;
		int j=0;
		String[] bb = aa;
		for(i=0;i<aa.length;i++){
 			if(aa[i].equals("s")){
			    indexshift[j]=String.valueOf(i); 
			    j++;
			}
		}
		String[] cc = indexshift;
		int k=0;
		for(i=0;i<indexshift.length;i++){
			if(indexshift[i]!=null){
			       k++;
			}
		}
		String[] indexs = new String[k]; 
		
		for(i=0;i<k;i++){
			indexs[i]=indexshift[i];
		}
		String[] dd = indexs;
		return indexs;	
	}
	
	//存放的是轴序号的字符串
	public static String getShift(){
		 shift = getStr(getIndexs());
	     return shift;
	}
	
	
	public static String getStr(String[]args){ 
		String str=""; 
		for(int i=0;i<args.length;i++){	 
			str+=(String)args[i]; 
			if(i==(args.length-1)){
				
			}
			else{
			str = str+" ";
			}
		}
		return str; 
	}
	
	
	//存放的是轴序号的数组
	public static String[] getIndexr(){
		int i;
		int j=0;
		for(i=0;i<aa.length;i++){
			if(aa[i].equals("r")){
			    indexrotate[j]=String.valueOf(i);
			    j++;
			}
		}
		
		int k=0;
		for(i=0;i<indexrotate.length;i++){
			if(indexrotate[i]!=null){
			       k++;
			}
		}
		String[] indexr = new String[k]; 
		
		for(i=0;i<k;i++){
			indexr[i]=indexrotate[i];
		}
		return indexr;	
	}
	//存放的是轴序号的字符串
	public static String getRotate(){
	     rotate = getStr(getIndexr());
	     return rotate;
	}	
}
