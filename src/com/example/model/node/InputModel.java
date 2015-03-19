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



public class InputModel extends NonContainerModel {
	
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_INDEX = "index";
	public static final String SELECT_VALUE = "value";
	public static String index,value;
	//******************************************结构体部分********************************
	public InputModel() {
		super(ConstantResourceFactory.LABEL_INPUT_MODEL,
				ConstantResourceFactory.ID_INPUT_MODEL,
				ConstantResourceFactory.SIZE_INPUT_ELEMENT);
		// TODO Auto-generated constructor stub
	    setName("等待触发信号");
	    setIndex("0");
	    setValue("0");
	    //initModel(); 
	}
	//******************************************结构体部分********************************
	
	
	
	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
    		new TextPropertyDescriptor(SELECT_NAME, "名字"),
    		new ComboBoxPropertyDescriptor(SELECT_INDEX, 
    				"输入信号", 
    			    new String[] { "0", "1","2" ,"3","4","5","6","7"}),
    			    
    		new ComboBoxPropertyDescriptor(SELECT_VALUE, 
    	    		"输入信号的值", 
    	    		new String[] { "0", "1"}),
    			    
    		
    		
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
    	
        else if(id.equals(SELECT_INDEX)){
            int i;
            
			for(i=0; i<8;i++){
				if(this.getIndex().equals(String.valueOf(i))){
					return new Integer(i);
				}
			}
		}
    	
        else if(id.equals(SELECT_VALUE)){
			if(this.getValue().equals("0")){
				return new Integer(0);
			}
			else {
				return new Integer(1);
			}
		}
    	
    	
        return null;
     }
	
	 private String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	private String getIndex() {
		// TODO Auto-generated method stub
		return index;
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
	    	
	    	else if(id.equals(SELECT_VALUE)){
				if(((Integer) value).equals( new Integer(0))){
				this.setValue("0");
			    }
				else
				{
					this.setValue("1");
				}
		    }
	    	
	    	
	    	else if(id.equals(SELECT_INDEX)){
				int i;
				for(i=0; i<8;i++){
					if(((Integer) value).equals( new Integer(i))){
						this.setIndex(String.valueOf(i));
					}
				}
		    }
	}
	 
	 
	private void setValue(String value) {
		// TODO Auto-generated method stub
		this.value= value;
		firePropertyChange(ConstantResourceFactory.P_VALUE,null,value);
	}

	private void setIndex(String index) {
		// TODO Auto-generated method stub
		this.index = index;
		firePropertyChange(ConstantResourceFactory.P_INDEX,null,index);
	}

	//******************************************属性编辑部分********************************
	 
	 
	 
	//******************************************XML部分******************************** 
	public void doSaveXML(){
		   super.doSaveXML();
		   this.addXMLElementAttribute(ModelType.ATR_INDEX, (String) getIndex());
		   this.addXMLElementAttribute(ModelType.ATR_VALUE, (String) getValue());  	
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
		
		if(e.attribute(ModelType.ATR_INDEX) != null){
			 index = e.attributeValue(ModelType.ATR_INDEX);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_INDEX));
		}
		
		if(e.attribute(ModelType.ATR_VALUE) != null){
			value = e.attributeValue(ModelType.ATR_VALUE);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_VALUE));
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
	
	//******************************************get/set部分******************************** 
	

}
