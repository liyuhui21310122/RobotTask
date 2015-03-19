package com.example.model.node;

import java.util.List;

import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.helper.ConstantResourceFactory;
import com.example.helper.ModelStringConstant;
import com.example.helper.CustomErrorInfo;
import com.example.model.ModelType;
import com.example.model.NonContainerModel;
import com.example.ui.editors.DiagramEditor;
import com.example.ui.jiaohu.ServerHelper;
import com.example.ui.jiaohu.messageHelper;
import com.example.ui.wizards.WizardHandle;



public class StartModel extends NonContainerModel {
	
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_NUMBER = "number";
	public static final String SELECT_SHIFT = "shift";
	public static final String SELECT_ROTATE = "rotate";
	
	public static String message;
	public String number, shift, rotate;
	//******************************************结构体部分********************************
	public StartModel() {
		super(ConstantResourceFactory.LABEL_START_MODEL,
 				ConstantResourceFactory.ID_START_MODEL,
				ConstantResourceFactory.SIZE_START_ELEMENT);
		// TODO Auto-generated constructor stub
	    setName("初始化");
        initModel(); 
	}
	
	public void initModel(){
		super.initModel();
		//setMessage(ServerHelper.message);
		//setNumber(ServerHelper.number);
		setMessage(ServerHelper.message);
		
		setNumber(messageHelper.getNumber());
		setShift(messageHelper.getShift());
		setRotate(messageHelper.getRotate());
	}
	
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
		firePropertyChange(ModelStringConstant.P_MESSAGE,null,null);
	}
	
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	
	public String getNumber() {
		// TODO Auto-generated method stub
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
		firePropertyChange(ModelStringConstant.P_NUMBER,null,null);
	}
	
	public String getShift() {
		// TODO Auto-generated method stub
		return shift;
	}
	
	public void setShift(String shift) {
		this.shift = shift;
		firePropertyChange(ModelStringConstant.P_SHIFT,null,null);
	}
	
	public String getRotate() {
		// TODO Auto-generated method stub
		return rotate;
	}
	
	public void setRotate(String rotate) {
		this.rotate = rotate;
		firePropertyChange(ModelStringConstant.P_ROTATE,null,null);
	}
	
	//******************************************结构体部分********************************
	
	
	
	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
    		new TextPropertyDescriptor(SELECT_NAME, "名字"),
    		new PropertyDescriptor(SELECT_NUMBER, "轴数"),
    		new PropertyDescriptor(SELECT_SHIFT, "平移轴"),
    		new PropertyDescriptor(SELECT_ROTATE, "旋转轴")
    };
    
	public IPropertyDescriptor[] getPropertyDescriptors() {
        return descriptors;
    }
	
	public Object getPropertyValue(Object id) {
    	super.getPropertyValue(id);
    	if (id.equals(SELECT_LABEL)){
            return getModelName();
        }
        else if (id.equals(SELECT_NAME)){
            return getName();
        }
        else if (id.equals(SELECT_NUMBER)){
            return getNumber();
        }
        else if (id.equals(SELECT_SHIFT)){
            return getShift();
        }
        else if (id.equals(SELECT_ROTATE)){
            return getRotate();
        }
        return null;
     }
	
	 public boolean isPropertySet(Object id) {
	        return true;
	 }

	 public void resetPropertyValue(Object id) {
	 }

	 public void setPropertyValue(Object id, Object value) {
	    	super.setPropertyValue(id, value);
	    	if(id.equals(SELECT_NAME)){
			    setName((String)value);
			}
	}
	//******************************************属性编辑部分********************************
	 
	 
	 
	//******************************************XML部分******************************** 
	 
	public void doSaveXML(){
		   super.doSaveXML();
		   this.addXMLElementAttribute(ModelType.ATR_MESSAGE, getMessage());
		   this.addXMLElementAttribute(ModelType.ATR_NUMBER, getNumber()); 
		   this.addXMLElementAttribute(ModelType.ATR_SHIFT, getShift());
		   this.addXMLElementAttribute(ModelType.ATR_ROTATE, getRotate()); 
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
		if(e.attribute(ModelType.ATR_MESSAGE) != null){
			message = e.attributeValue(ModelType.ATR_MESSAGE);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_MESSAGE));
		}
		
		if(e.attribute(ModelType.ATR_NUMBER) != null){
			number = e.attributeValue(ModelType.ATR_NUMBER);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_NUMBER));
		}
		
		if(e.attribute(ModelType.ATR_SHIFT) != null){
			shift = e.attributeValue(ModelType.ATR_SHIFT);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_SHIFT));
		}
		
		if(e.attribute(ModelType.ATR_ROTATE) != null){
			rotate = e.attributeValue(ModelType.ATR_ROTATE);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_ROTATE));
		}
	}
	//******************************************XML部分******************************** 
		
	
	
	/*
	//******************************************复制部分******************************** 
	@Override
	public Object copy() {
		// TODO Auto-generated method stub
		LinearMotionModel model=new LinearMotionModel();
		initCopyModel(model);
		return model;
	}
		
	protected void initCopyModel(LinearMotionModel model) {
		// TODO Auto-generated method stub
		super.initCopyModel(model);
		model.setCoorType(coorType);
		model.setDisplacement(displacement);
		model.setVelocity(velocity);
	}
	//******************************************复制部分******************************** 
	*/	
	   
		
	//******************************************get/set部分******************************** 
	/*
	public String getNumber(){
		aa = message.split("\\:");
		number = aa[0];
		return number;	
	}
	*/
	/*
	public String getType(){
		
	}*/
	//******************************************get/set部分******************************** 
	

}
