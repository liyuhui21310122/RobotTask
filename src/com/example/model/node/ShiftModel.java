package com.example.model.node;

import java.util.List;

import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.example.helper.ConstantResourceFactory;
import com.example.helper.ModelStringConstant;
import com.example.helper.CustomErrorInfo;
import com.example.model.ModelType;
import com.example.model.NonContainerModel;
import com.example.ui.jiaohu.ServerHelper;
import com.example.ui.jiaohu.messageHelper;



public class ShiftModel extends NonContainerModel {
	private String index;//选择的轴
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_INDEX= "index";
	//******************************************结构体部分********************************
	public ShiftModel() {
		super(ConstantResourceFactory.LABEL_SHIFT_MODEL,
				ConstantResourceFactory.ID_SHIFT_MODEL,
				ConstantResourceFactory.SIZE_SHIFT_ELEMENT);
		// TODO Auto-generated constructor stub
	    setName("轴平移");
	    //initModel(); 
	}
	
	public void initModel(){
		super.initModel();
		setIndex(messageHelper.getIndexs()[0]);
	}
	//******************************************结构体部分********************************
	



	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
    		
    		new TextPropertyDescriptor(SELECT_NAME, "名字"),
    		new ComboBoxPropertyDescriptor(SELECT_INDEX, "轴的序号",messageHelper.getIndexr()),
           
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
			
			for(i=0; i<messageHelper.getIndexs().length;i++){
				if(this.getIndex().equals(messageHelper.getIndexs()[i])){
					return new Integer(i);
				}
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
	    	if(id.equals(SELECT_NAME)){
			    setName((String)value);
			}
	    	
			else if(id.equals(SELECT_INDEX)){
				int i;
				for(i=0; i<messageHelper.getIndexs().length;i++){
					if(((Integer) value).equals( new Integer(i))){
						this.setIndex(messageHelper.getIndexs()[i]);
					}
				}
		    }
	}
	//******************************************属性编辑部分********************************
	 
	 
	 
	//******************************************XML部分******************************** 
	public void doSaveXML(){
		   super.doSaveXML();
		   this.addXMLElementAttribute(ModelType.ATR_INDEX, getIndex());	 	
	
		   
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
	
	public String getIndex() {
		// TODO Auto-generated method stub
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
		firePropertyChange(ConstantResourceFactory.P_INDEX,null,index);
	}
	//******************************************get/set部分******************************** 

	
	

}

