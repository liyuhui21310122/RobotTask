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
import com.example.model.ContainerModel;
import com.example.model.ModelType;

public class LoopModel extends ContainerModel {
	private String Index,value;
	private String count;
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_COUNT = "count";
	
	//******************************************�ṹ�岿��********************************
	public LoopModel() {
		super(ConstantResourceFactory.LABEL_LOOP_MODEL,
				ConstantResourceFactory.ID_LOOP_MODEL,
				ConstantResourceFactory.SIZE_LOOP_ELEMENT);
		// TODO Auto-generated constructor stub
		setName("ѭ��");
		setCount("1");
		//setIndex("1");
		//setValue("0");
	}
	//******************************************�ṹ�岿��********************************

	
	
	//******************************************���Ա༭����********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
		new TextPropertyDescriptor(SELECT_NAME, "����"),
		new PropertyDescriptor(SELECT_LABEL, "ģ������"),
		//new PropertyDescriptor(SELECT_LABEL, "ģ������"),	
    	
        new TextPropertyDescriptor(SELECT_COUNT, "ִ�д���"),
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
        else if(id.equals(SELECT_COUNT)){
			return  getCount();
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
	    
	    if (id.equals(SELECT_NAME)){
	    	setName((String)value);
        }	
        else if(id.equals(SELECT_COUNT)){
			 setCount((String)value);
		}
	}
	//******************************************���Ա༭����********************************
	
	
	
	
	//******************************************XML����******************************** 
	public void doSaveXML(){
	    super.doSaveXML();
		    	
	    this.addXMLElementAttribute(ModelType.ATR_COUNT, getCount());
		   
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
			
		if(e.attribute(ModelType.ATR_COUNT) != null){
			count = e.attributeValue(ModelType.ATR_COUNT);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_COUNT));
		}
	}
	//******************************************XML����******************************** 
		
	
	/*
	//******************************************���Ʋ���******************************** 
	@Override
	public Object copy() {
		// TODO Auto-generated method stub
		LoopModel model=new LoopModel();
		initCopyModel(model);
		return model;
	}
		
	protected void initCopyModel(LoopModel model) {
		// TODO Auto-generated method stub
		super.initCopyModel(model);
		model.setCount(count);
	}
	//******************************************���Ʋ���******************************** 
	*/	
	   
		
	//******************************************get/set����******************************** 
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
		firePropertyChange(ConstantResourceFactory.P_COUNT,null,count);
	}
	
	
	//******************************************get/set����******************************** 

}
