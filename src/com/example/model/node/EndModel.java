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



public class EndModel extends NonContainerModel {
	
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";

	//******************************************�ṹ�岿��********************************
	public EndModel() {
		super(ConstantResourceFactory.LABEL_END_MODEL,
				ConstantResourceFactory.ID_END_MODEL,
				ConstantResourceFactory.SIZE_END_ELEMENT);
		// TODO Auto-generated constructor stub
	    setName("��������");
	    //initModel(); 
	}
	//******************************************�ṹ�岿��********************************
	
	
	
	//******************************************���Ա༭����********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "ģ������"),
    		
    		new TextPropertyDescriptor(SELECT_NAME, "����"),
            
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
	//******************************************���Ա༭����********************************
	 
	 
	 
	//******************************************XML����******************************** 
	public void doSaveXML(){
		   super.doSaveXML();
		    	
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
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
