package com.example.helper;

import org.dom4j.Element;

import com.example.model.ModelType;


public class CustomErrorInfo implements ErrorInfoConstant{
	
	private String errorLevel;
	private String errorType;
	private String errorPos;
	private String errorName;
	private String errorInfo;
	
	public CustomErrorInfo(){
		errorLevel = UNKNOWN;
		errorType = UNKNOWN;
		errorPos = UNKNOWN;
		errorName = UNKNOWN;
		errorInfo = UNKNOWN;
	}
	
	public CustomErrorInfo(Element e,int code,String info){
		errorLevel = LEVEL_ERROR;
		errorType = TYPE_XML;
		
		if(e.attribute(ModelType.ATR_NAME) != null){
			errorPos = e.attributeValue(ModelType.ATR_NAME);
		}
		else{
			errorPos = UNKNOWN;
		}
		errorPos += "/";
		
		if(e.attribute(ModelType.ATR_ID) != null){
			errorPos += e.attributeValue(ModelType.ATR_ID);
		}
		else{
			errorPos += UNKNOWN;
		}
		
		if(code == INVALID){
			errorName = "invalid element";
		}
		else if(code == NULL){
			errorName = "missed element";
		}
		
		errorInfo = info;
	}
	
	public String getError(){
		String err = errorType +" "+ errorLevel + ":" 
			+ errorName + "\n	at " + errorPos
			+ "(" + errorInfo + ")";
		
		return err;
	}
	
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	public String getErrorType() {
		return errorType;
	}
	
	public void setErrorPos(String errorPos) {
		this.errorPos = errorPos;
	}
	
	public String getErrorPos() {
		return errorPos;
	}
	
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	
	public String getErrorName() {
		return errorName;
	}
	
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorLevel(String errorLevel) {
		this.errorLevel = errorLevel;
	}

	public String getErrorLevel() {
		return errorLevel;
	}
	

}
