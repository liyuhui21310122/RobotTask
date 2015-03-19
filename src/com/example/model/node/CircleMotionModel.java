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



public class CircleMotionModel extends NonContainerModel {
	private String R;
	private String velocity;
	private String cx,cy,cz,da,db,dc;
	private String rad,coorType;
	//private String index;//选择的轴
	public static final String SELECT_LABEL = "label";
	public static final String SELECT_NAME = "name";
	public static final String SELECT_VELOCITY = "velocity";
	public static final String SELECT_COORTYPE = "cootType";
	
	public static final String SELECT_CENTERX = "centerX";
	public static final String SELECT_CENTERY = "centerY";
	public static final String SELECT_CENTERZ = "centerZ";
	
	public static final String SELECT_DIRECTIONA = "centerA";
	public static final String SELECT_DIRECTIONB= "centerB";
	public static final String SELECT_DIRECTIONC = "centerC";
	public static final String SELECT_R = "radius";
	public static final String SELECT_RAD = "rad";
	//public static final String SELECT_INDEX= "index";
	//******************************************结构体部分********************************
	public CircleMotionModel() {
		super(ConstantResourceFactory.LABEL_CIRCLEMOTION_MODEL,
				ConstantResourceFactory.ID_CIRCLEMOTION_MODEL,
				ConstantResourceFactory.SIZE_CIRCLEMOTION_ELEMENT);
		// TODO Auto-generated constructor stub
		setCoorType("base");
	    setVelocity("0.0");
	    setName("圆周运动");
	    setCX("0.0");
	    setCY("0.0");
	    setCZ("0.0");
	    setDA("0.0");
	    setDB("0.0");
	    setDC("0.0");
	    setR("0.0");
	    setRad("0.0");
	    //initModel(); 
	}
	
	public void initModel(){
		super.initModel();
		//setIndex(messageHelper.getIndexr()[0]);
	}
	//******************************************结构体部分********************************
	



	//******************************************属性编辑部分********************************
	public IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(SELECT_LABEL, "模型名字"),
    		
    		new TextPropertyDescriptor(SELECT_NAME, "名字"),
    		//new ComboBoxPropertyDescriptor(SELECT_INDEX, "轴的序号",messageHelper.getIndexr()),
            new TextPropertyDescriptor(SELECT_R, "半径 R"),
            new TextPropertyDescriptor(SELECT_RAD, "弧度(rad)"),
            new TextPropertyDescriptor(SELECT_VELOCITY, "角速度(rad/s)"),
            new TextPropertyDescriptor(SELECT_CENTERX,"圆心坐标   x"),
    		new TextPropertyDescriptor(SELECT_CENTERY,"圆心坐标   y"),
    		new TextPropertyDescriptor(SELECT_CENTERZ,"圆心坐标   z"),
    		new TextPropertyDescriptor(SELECT_DIRECTIONA,"轴向   a"),
     		new TextPropertyDescriptor(SELECT_DIRECTIONB,"轴向   b"),
     		new TextPropertyDescriptor(SELECT_DIRECTIONC,"轴向   c"),
     		
     		
    		new ComboBoxPropertyDescriptor(SELECT_COORTYPE, 
    				"坐标系类型", 
    			    new String[] { "base", "project","tool" ,"earth"})
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
		else if(id.equals(SELECT_CENTERX)){
			return getCX();
		}
		else if(id.equals(SELECT_CENTERY)){
			return getCY();
		}
		else if(id.equals(SELECT_CENTERZ)){
			return getCZ();
		}
		else if(id.equals(SELECT_DIRECTIONA)){
			return getDA();
		}
		else if(id.equals(SELECT_DIRECTIONB)){
			return getDB();
		}
		else if(id.equals(SELECT_DIRECTIONC)){
			return getDC();
		}
		else if(id.equals(SELECT_R)){
			return getR();
		}
		else if(id.equals(SELECT_RAD)){
			return getRad();
		}
		/*else if(id.equals(SELECT_INDEX)){
			int i;
			
			for(i=0; i<messageHelper.getIndexr().length;i++){
				if(this.getIndex().equals(messageHelper.getIndexr()[i])){
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
			else if(id.equals(SELECT_CENTERX)){
				setCX((String)value);
			}
			else if(id.equals(SELECT_CENTERY)){
				setCY((String) value);
			}
			else if(id.equals(SELECT_CENTERZ)){
				setCZ((String) value);
			}
			else if(id.equals(SELECT_DIRECTIONA)){
				setDA((String)value);
			}
			else if(id.equals(SELECT_DIRECTIONB)){
				setDB((String) value);
			}
			else if(id.equals(SELECT_DIRECTIONC)){
				setDC((String) value);
			}
			else if(id.equals(SELECT_R)){
				setR((String) value);
			}
			else if(id.equals(SELECT_RAD)){
				setRad((String) value);
			}
			/*else if(id.equals(SELECT_INDEX)){
				int i;
				for(i=0; i<messageHelper.getIndexr().length;i++){
					if(((Integer) value).equals( new Integer(i))){
						this.setIndex(messageHelper.getIndexr()[i]);
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
		   this.addXMLElementAttribute(ModelType.ATR_CX, getCX());
		   this.addXMLElementAttribute(ModelType.ATR_CY, getCY());
		   this.addXMLElementAttribute(ModelType.ATR_CZ, getCZ());
		   this.addXMLElementAttribute(ModelType.ATR_DA, getDA());
		   this.addXMLElementAttribute(ModelType.ATR_DB, getDB());
		   this.addXMLElementAttribute(ModelType.ATR_DC, getDC());
		   this.addXMLElementAttribute(ModelType.ATR_R, getR());
		   this.addXMLElementAttribute(ModelType.ATR_RAD, getRad());
		   
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
			
		if(e.attribute(ModelType.ATR_CX) != null){
			cx = e.attributeValue(ModelType.ATR_CX);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_CX));
		}
		
		if(e.attribute(ModelType.ATR_CY) != null){
			cy = e.attributeValue(ModelType.ATR_CY);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_CY));
		}
		
		if(e.attribute(ModelType.ATR_CZ) != null){
			cz = e.attributeValue(ModelType.ATR_CZ);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_CZ));
		}
		
		if(e.attribute(ModelType.ATR_DA) != null){
			da = e.attributeValue(ModelType.ATR_DA);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_DA));
		}
		
		if(e.attribute(ModelType.ATR_DB) != null){
			db = e.attributeValue(ModelType.ATR_DB);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_DB));
		}
		
		if(e.attribute(ModelType.ATR_DC) != null){
			dc = e.attributeValue(ModelType.ATR_DC);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_DC));
		}
		
		if(e.attribute(ModelType.ATR_R) != null){
			R = e.attributeValue(ModelType.ATR_R);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_R));
		}
		
		if(e.attribute(ModelType.ATR_RAD) != null){
			rad = e.attributeValue(ModelType.ATR_RAD);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_RAD));
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
		
	public String getCoorType() {
		return coorType;
	}
	public void setCoorType(String coorType) {
		this.coorType = coorType;
		firePropertyChange(ConstantResourceFactory.P_COORTYPE,null,coorType);
	}
	
	public String getCX() {
		// TODO Auto-generated method stub
		return cx;
	}
	public void setCX(String cx) {
		// TODO Auto-generated method stub
		this.cx = cx;
		firePropertyChange(ConstantResourceFactory.P_CX,null,cx);
	}
	
	public String getCY() {
		// TODO Auto-generated method stub
		return cy;
	}
	public void setCY(String cy) {
		// TODO Auto-generated method stub
		this.cy = cy;
		firePropertyChange(ConstantResourceFactory.P_CY,null,cy);
	}
	
	public String getCZ() {
		// TODO Auto-generated method stub
		return cz;
	}
	public void setCZ(String cz) {
		// TODO Auto-generated method stub
		this.cz = cz;
		firePropertyChange(ConstantResourceFactory.P_CZ,null,cz);
	}
	
	public String getDA() {
		// TODO Auto-generated method stub
		return da;
	}
	public void setDA(String da) {
		// TODO Auto-generated method stub
		this.da = da;
		firePropertyChange(ConstantResourceFactory.P_DA,null,da);
	}
	
	public String getDB() {
		// TODO Auto-generated method stub
		return db;
	}
	public void setDB(String db) {
		// TODO Auto-generated method stub
		this.db = db;
		firePropertyChange(ConstantResourceFactory.P_DB,null,db);
	}
	
	public String getDC() {
		// TODO Auto-generated method stub
		return dc;
	}
	public void setDC(String dc) {
		// TODO Auto-generated method stub
		this.dc = dc;
		firePropertyChange(ConstantResourceFactory.P_DC,null,dc);
	}
	
	public String getR() {
		// TODO Auto-generated method stub
		return R;
	}
	
	public void setR(String R) {
		// TODO Auto-generated method stub
		this.R = R;
		firePropertyChange(ConstantResourceFactory.P_R,null,R);
	}
	
	
	public String getRad() {
		// TODO Auto-generated method stub
		return rad;
	}
	public void setRad(String rad) {
		// TODO Auto-generated method stub
		this.rad = rad;
		firePropertyChange(ConstantResourceFactory.P_RAD,null,rad);
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
