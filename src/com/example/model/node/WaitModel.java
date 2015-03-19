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



public class WaitModel extends NonContainerModel {
	
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_TIME = "time";
    public String time;
	//******************************************�ṹ�岿��********************************
	public WaitModel() {
		super(ConstantResourceFactory.LABEL_WAIT_MODEL,
				ConstantResourceFactory.ID_WAIT_MODEL,
				ConstantResourceFactory.SIZE_WAIT_ELEMENT);
		// TODO Auto-generated constructor stub
	    setName("�ȴ�");
	    setTime("0");
	    //initModel(); 
	}
	//******************************************�ṹ�岿��********************************
	
	
	
	//******************************************���Ա༭����********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "ģ������"),
    		
    		new TextPropertyDescriptor(SELECT_NAME, "����"),
    		new TextPropertyDescriptor(SELECT_TIME, "�ȴ�ʱ��"),
            
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
        else if (id.equals(SELECT_TIME)){
            return getTime();
        }
        return null;
     }
	
	 private String getTime() {
		// TODO Auto-generated method stub
		return time;
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
	    	if(id.equals(SELECT_TIME)){
			    setTime((String)value);
			}
	}
	//******************************************���Ա༭����********************************
	 
	 
	 
	private void setTime(String time) {
		// TODO Auto-generated method stub
		this.time = time;
		firePropertyChange(ConstantResourceFactory.P_TIME,null,time);
	}

	//******************************************XML����******************************** 
	public void doSaveXML(){
		   super.doSaveXML();
		   this.addXMLElementAttribute(ModelType.ATR_TIME, (String) getTime()); 	
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
		if(e.attribute(ModelType.ATR_TIME) != null){
			 time = e.attributeValue(ModelType.ATR_TIME);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_TIME));
		}
	}
	//******************************************XML����******************************** 
		
	
	
	/*
	//******************************************���Ʋ���******************************** 
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
	//******************************************���Ʋ���******************************** 
	*/	
	   
		
	//******************************************get/set����******************************** 
	
	//******************************************get/set����******************************** 
	

}