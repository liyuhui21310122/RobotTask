package com.example.model.node;

import java.util.List;

import org.dom4j.Element;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.helper.ConstantResourceFactory;
import com.example.helper.CustomErrorInfo;
import com.example.model.ModelType;
import com.example.model.NonContainerModel;

public class ConditionModel extends NonContainerModel {
	private String index,value;
	public static final String SELECT_VALUE = "value";
	public static final String SELECT_INDEX = "index";
	//******************************************结构体部分********************************
	public ConditionModel() {
		super(ConstantResourceFactory.LABEL_CONDITION_MODEL,
				ConstantResourceFactory.ID_CONDITION_MODEL,
				ConstantResourceFactory.SIZE_CONDITION_ELEMENT);
		// TODO Auto-generated constructor stub
		setIndex("0");
		setValue("0");
	    setName("条件判断");
	    //initModel(); 
	}
	//******************************************结构体部分********************************
	
	
	
	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
    		
    		new TextPropertyDescriptor(SELECT_NAME, "名字"),
    		new ComboBoxPropertyDescriptor(SELECT_INDEX, 
    				"输出信号", 
    			    new String[] { "0", "1","2" ,"3","4","5","6","7"}),
    		new ComboBoxPropertyDescriptor(SELECT_VALUE, 
    				"条件类型", 
    			    new String[] { "0", "1"})
    };
	
	@Override
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
			return new Integer(1);	
		}
        return null;
     }
	
	private String getIndex() {
		// TODO Auto-generated method stub
		return index;
	}
	
	private void setIndex(String index) {
		// TODO Auto-generated method stub
		this.index = index;
		firePropertyChange(ConstantResourceFactory.P_INDEX,null,index);
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
				this.setValue("1");
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
	//******************************************属性编辑部分********************************
	 
	 
	//******************************************XML部分********************************
	 public void doSaveXML(){
		super.doSaveXML();
		this.addXMLElementAttribute(ModelType.ATR_INDEX, (String) getIndex());    	
		this.addXMLElementAttribute(ModelType.ATR_VALUE, getValue());
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
	
	//******************************************get/set部分******************************** 
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
		firePropertyChange(ConstantResourceFactory.P_VALUE,null,value);
		
	}
	//******************************************get/set部分******************************** 
}
