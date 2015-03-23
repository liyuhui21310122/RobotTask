package com.example.model.node;

import java.util.List;

import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.helper.ConstantResourceFactory;
import com.example.helper.CustomErrorInfo;
import com.example.model.ContainerModel;
import com.example.model.ModelType;
import com.example.ui.jiaohu.messageHelper;

public class WhileModel extends ContainerModel {
    private String index,value,condition;
    public static final String SELECT_INDEX= "index";
    public static final String SELECT_VALUE= "value";
    public static final String SELECT_CONDITION= "condition";
  //******************************************结构体部分********************************
	public WhileModel() {
		super(ConstantResourceFactory.LABEL_WHILE_MODEL,
				ConstantResourceFactory.ID_WHILE_MODEL,
				ConstantResourceFactory.SIZE_WHILE_ELEMENT);
		// TODO Auto-generated constructor stub
		setName("条件循环");
		setModelName("条件循环");
		setIndex("0");
		setValue("0");
        setCondition("00");
		//condition = "00";
	}
	//******************************************结构体部分********************************
	
	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
			new TextPropertyDescriptor(SELECT_NAME, "名字"),
			new PropertyDescriptor(SELECT_CONDITION, "循环条件"),
			new ComboBoxPropertyDescriptor(SELECT_INDEX, "输入信号序号", 
					new String[] { "0", "1","2" ,"3","4","5","6","7"}),
			new ComboBoxPropertyDescriptor(SELECT_VALUE, "输入信号的值", 
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
        else if(id.equals(SELECT_CONDITION)){
			return  getCondition();
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
    	else if(id.equals(SELECT_INDEX)){
			int i;
			for(i=0; i<8;i++){
				if(((Integer) value).equals( new Integer(i))){
					this.setIndex(String.valueOf(i));
				}
			}
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
    
	}
	//******************************************属性编辑部分********************************
	
	//******************************************XML部分******************************** 
	public void doSaveXML(){
	    super.doSaveXML();
		    	
	    this.addXMLElementAttribute(ModelType.ATR_CONDITION, getCondition());
	    this.addXMLElementAttribute(ModelType.ATR_INDEX, getIndex());
	    this.addXMLElementAttribute(ModelType.ATR_VALUE, getValue());     
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
			
		if(e.attribute(ModelType.ATR_CONDITION) != null){
			 condition = e.attributeValue(ModelType.ATR_CONDITION);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_CONDITION));
		}
		
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
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
		firePropertyChange(ConstantResourceFactory.P_INDEX,null,index);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		firePropertyChange(ConstantResourceFactory.P_VALUE,null,value);
	}

	public String getCondition() {
		String index = getIndex();
		String value = getValue();
		condition = index+value;
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
		firePropertyChange(ConstantResourceFactory.P_CONDITION,null,condition);
	}

	
}
