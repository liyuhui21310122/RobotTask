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
import com.example.ui.jiaohu.ServerHelper;
import com.example.ui.jiaohu.messageHelper;



public class LinearMotionModel extends NonContainerModel {
	private String coorType;
	private String displacement;
	private String velocity;
	
	//private String index;//选择的轴
	
	
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_VELOCITY = "velocity";
	public static final String SELECT_COORTYPE = "cootType";
	public static final String SELECT_DISPLACEMENT = "displacement";
	
	//public static final String SELECT_INDEX= "index";
	//******************************************结构体部分********************************
	public LinearMotionModel() {
		super(ConstantResourceFactory.LABEL_LINEARMOTION_MODEL,
				ConstantResourceFactory.ID_LINEARMOTION_MODEL,
				ConstantResourceFactory.SIZE_LINEARMOTION_ELEMENT);
		// TODO Auto-generated constructor stub
		setCoorType("base");
		setDisplacement("0.0");
	    setVelocity("0.0");
	    setName("直线运动");
	    //initModel(); 
	}
	
	public void initModel(){
		super.initModel();
		//setIndex(messageHelper.getIndexs()[0]);
	}
	//******************************************结构体部分********************************
	
	
	
	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
    		
    		new TextPropertyDescriptor(SELECT_NAME, "名字"),
    		//new ComboBoxPropertyDescriptor(SELECT_INDEX, "轴的序号",messageHelper.getIndexs()),
            
			new TextPropertyDescriptor(SELECT_VELOCITY, "速度(m/s)"),
    		new TextPropertyDescriptor(SELECT_DISPLACEMENT, "行程(mm)"),
    		
    		new ComboBoxPropertyDescriptor(SELECT_COORTYPE, 
    				"坐标系类型", new String[] { "base", "project","tool" ,"earth"})
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
        else if(id.equals(SELECT_VELOCITY)){
			return  getVelocity();
		}
		else if(id.equals(SELECT_COORTYPE)){
			if(this.getCoorType().equals("base")){
				return new Integer(0);
			}
			else if(this.getCoorType().equals("project")){
				return new Integer(1);
			}
			else if(this.getCoorType().equals("tool")){
				return new Integer(2);
			}
			return new Integer(3);
		}
		else if(id.equals(SELECT_DISPLACEMENT)){
			return getDisplacement();
		}
		/*else if(id.equals(SELECT_INDEX)){
			int i;
			
			for(i=0; i<messageHelper.getIndexs().length;i++){
				if(this.getIndex().equals(messageHelper.getIndexs()[i])){
					return new Integer(i);
				}
			}
		}*/
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
	    	else if(id.equals(SELECT_VELOCITY)){
			    setVelocity((String)value);
			}
			else if(id.equals(SELECT_COORTYPE)){
				if(((Integer) value).equals( new Integer(0))){
				this.setCoorType("base");
			    }
				else if(((Integer) value).equals( new Integer(1))){
					this.setCoorType("project");
				}
				else if(((Integer) value).equals( new Integer(2))){
					this.setCoorType("tool");
				}
				this.setCoorType("earth");
		    }
			else if(id.equals(SELECT_DISPLACEMENT)){
				setDisplacement((String)value);
			}
			/*else if(id.equals(SELECT_INDEX)){
				int i;
				for(i=0; i<messageHelper.getIndexs().length;i++){
					if(((Integer) value).equals( new Integer(i))){
						this.setIndex(messageHelper.getIndexs()[i]);
					}
				}
		    }*/
	}
	//******************************************属性编辑部分********************************
	 
	 
	 
	//******************************************XML部分******************************** 
	public void doSaveXML(){
		   super.doSaveXML();
		   //this.addXMLElementAttribute(ModelType.ATR_INDEX, getIndex());	
		   this.addXMLElementAttribute(ModelType.ATR_COORTYPE, getCoorType());
		   this.addXMLElementAttribute(ModelType.ATR_VELOCITY, getVelocity());	
		   this.addXMLElementAttribute(ModelType.ATR_DISPLACEMENT, getDisplacement());
	} 
		
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
			// TODO Auto-generated method stub
		super.rebuildFromXml(e,err);
			
		if(e.attribute(ModelType.ATR_COORTYPE) != null){
				coorType = e.attributeValue(ModelType.ATR_COORTYPE);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_COORTYPE));
		}
			
		if(e.attribute(ModelType.ATR_VELOCITY) != null){
			velocity = e.attributeValue(ModelType.ATR_VELOCITY);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_VELOCITY));
		}
			
		if(e.attribute(ModelType.ATR_DISPLACEMENT) != null){
			displacement = e.attributeValue(ModelType.ATR_DISPLACEMENT);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_DISPLACEMENT));
		}
		
		/*if(e.attribute(ModelType.ATR_INDEX) != null){
			index = e.attributeValue(ModelType.ATR_INDEX);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_INDEX));
		}*/
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
	public String getVelocity() {
		return velocity;
	}
	public void setVelocity(String velocity) {
		this.velocity = velocity;
		firePropertyChange(ConstantResourceFactory.P_VELOCITY,null,velocity);
	}

	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
		firePropertyChange(ConstantResourceFactory.P_DISPLACEMENT,null,displacement);
	}
		
	public String getCoorType() {
		return coorType;
	}
	public void setCoorType(String coorType) {
		this.coorType = coorType;
		firePropertyChange(ConstantResourceFactory.P_COORTYPE,null,coorType);
	}
	/*
	public String getIndex() {
		// TODO Auto-generated method stub
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
		firePropertyChange(ConstantResourceFactory.P_INDEX,null,index);
	}*/
	//******************************************get/set部分******************************** 
	

}
