package com.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.example.helper.ModelStringConstant;
import com.example.helper.CustomErrorInfo;
import com.example.model.node.EndModel;





public abstract class NodeModel extends AbstractModel
             implements IDirectEditModel,IConstraint{
	protected String label, name;
	protected String modelID;	
	protected Dimension size;
	protected Rectangle constraint;
	
	protected String ID;
	protected String fromID;
	protected String modelName;	
	protected String xmlHead;
	protected Element xmlModel;	
	
	public static final String SELECT_LABEL = "select_label";
	public static final String SELECT_NAME = "select_name";
	
	protected List<ConnectionModel> outputs = new ArrayList<ConnectionModel>(10);
    protected List<ConnectionModel> inputs = new ArrayList<ConnectionModel>(10);
    
	protected Point location = new Point(0, 0);
//	int h =100;
//	int w = 100;
	//**************************************结构体******************************************
	public NodeModel(String label, String modelID, Dimension size){
		this.label = label;
		this.modelID = modelID;
		this.size = size;	
		name = "";
		fromID = "no input";
		constraint = new Rectangle();
		initModel();
	}
	//**************************************结构体******************************************
	
	
	/*
	//***************************************操作允许***************************************
    public abstract Object copy();
	
	public boolean canCopy(){
		return true;
	}
	
	public boolean canDelete(){
		return true;
	}
	
	public boolean canMove(){
		return true;
	}	
	
	protected void initCopyModel(NodeModel model) {
		// TODO Auto-generated method stub
		model.setName(name);
	}
	//***************************************操作允许***************************************
	*/
	
	
	//**********************************属性编辑部分*********************************************
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public abstract IPropertyDescriptor[] getPropertyDescriptors();

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if(id.equals(SELECT_NAME)){
			return getName();
		}
		else if(id.equals(SELECT_LABEL)){
			return getLabel();
		}		
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub	
		if(id.equals(SELECT_NAME)){
			setName((String)value);
		}
	}	
	//**********************************属性编辑部分*********************************************

	
	
	//**********************************xml部分*********************************************
	@Override
	public void doSaveXML() {
		// TODO Auto-generated method stub
		this.addXMLElementAttribute(ModelType.ATR_MODELNAME, modelName);
		this.addXMLElementAttribute(ModelType.ATR_ID, ID);
		this.addXMLElementAttribute(ModelType.ATR_NAME, name);
		this.addXMLElementAttribute(ModelType.ATR_LOCATION_X, Integer.toString(this.getLocation().x));
		this.addXMLElementAttribute(ModelType.ATR_LOCATION_Y, Integer.toString(this.getLocation().y));
		this.addXMLElementAttribute(ModelType.ATR_FROMID, fromID);
//		this.addXMLElementAttribute(ModelType.ATR_WIDTH, Integer.toString(this.getConstraint().width));
//		this.addXMLElementAttribute(ModelType.ATR_HEIGHT, Integer.toString(this.getConstraint().height));

	}
	
	public void addXMLElementAttribute(String name,String value){		
		if(xmlModel==null){
			return;
		}
		xmlModel.addAttribute(name, value);
	} 
	
	public void removeXMLElementAttribute(String name){
		if(xmlModel==null){
			return;
		}
		
		Attribute atr = xmlModel.attribute(name);
		if( atr != null){
			xmlModel.remove(atr);
		}
		
	}
	@Override
	public void initModel(){
		setID(ModelIDFactory.randomString(10));
		setModelName(getLabel());
		setXMLHead(ModelType.getModelXMLHead(this));
		setXMLModel(DocumentHelper.createElement(getXMLHead()));
	}

	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
		// TODO Auto-generated method stub
		if(e.attribute(ModelType.ATR_MODELNAME) != null){
			modelName = e.attributeValue(ModelType.ATR_MODELNAME);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_MODELNAME));
		}
		
		if(e.attribute(ModelType.ATR_FROMID) != null){
			fromID = e.attributeValue(ModelType.ATR_FROMID);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_FROMID));
		}
	    
		if(e.attribute(ModelType.ATR_NAME) != null){
			name = e.attributeValue(ModelType.ATR_NAME);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_NAME));
		}
		
		if(e.attribute(ModelType.ATR_ID) != null){
			ID = e.attributeValue(ModelType.ATR_ID);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_ID));
		}
		
		if(e.attribute(ModelType.ATR_LOCATION_X) != null){ 
			String x = e.attributeValue(ModelType.ATR_LOCATION_X);
			getLocation().x = Integer.parseInt(x);
//			getConstraint().x = Integer.parseInt(x);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_LOCATION_X));
		}
		
		if(e.attribute(ModelType.ATR_LOCATION_Y) != null){
			String y = e.attributeValue(ModelType.ATR_LOCATION_Y);
			getLocation().y = Integer.parseInt(y);
//			getConstraint().y = Integer.parseInt(y);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_LOCATION_Y));
		}
		

	}
	//**********************************xml部分*********************************************
	
	
	
	//***************************************get/set部分***************************************
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public String getFromID() {
		return fromID;
	}
	
	public String[] getFromID(String f){
		
		String[] aa = f.split("\\|");
		return aa;
		
	}
	
	public String getStr(String[]args){ 
		String str=""; 
		for(int i=0;i<args.length;i++){	 
			str+=(String)args[i]; 
			if(i==(args.length-1)){
				
			}
			else{
			str = str+"|";
			}
		}
		
		return str; 
	}
	
	public void setFromID(String iD) {
		fromID = iD;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
		firePropertyChange(ModelStringConstant.P_NODE_MODELNAME,null,null);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		firePropertyChange(ModelStringConstant.P_NODE_TEXT,null,null);
	}
	
	public String getLabel() {
		return label;
	}

	public String getModelID() {
		return modelID;
	}
	
	public String getXMLHead() {
		return xmlHead;
	}

	public void setXMLHead(String xmlHead) {
		this.xmlHead = xmlHead;
	}	
	
	public Element getXMLModel() {
		return xmlModel;
	}
	
	public void setXMLModel(Element xmlModel) {
		this.xmlModel = xmlModel;
	}
	
	
	//***************************************get/set部分***************************************
	
	
	

	//**************************************constraint部分**************************************
	@Override
	public Dimension getPreferredDimension() {
		// TODO Auto-generated method stub
		return size;
	}
	
	public void setSize(Dimension d) {
        if (this.size.equals(d)) {
            return;
        }
        this.size = d;
        firePropertyChange(ModelStringConstant.P_NODE_SIZE, null, d);
    }
	
	public void setLocation(Point p) {
		if (this.location.equals(p)) {
			return;
		}
		this.location = p;
		firePropertyChange(ModelStringConstant.P_NODE_LOCATION, null,p);
	}

	public Point getLocation() {
		return location;
	}
	
	public Rectangle getConstraint() {
		return constraint;
	}
	
	public void setConstraint(Rectangle constraint) {		
		this.constraint = constraint;
		firePropertyChange(ModelStringConstant.P_NODE_CONSTRAINT,null,constraint);
	}
	//**************************************constraint部分**************************************
	
	
	
	//**********************************直接编辑部分*************************************************
	@Override
	public String getEditText(Object sel) {
		// TODO Auto-generated method stub
		if(sel.equals(SELECT_NAME)){
			return getName();
		}
		return null;
	}

	@Override
	public void setEditText(Object sel, String text) {
		// TODO Auto-generated method stub
		if(sel.equals(SELECT_NAME)){
			setName(text);
		}
	}

	@Override
	public boolean canEdit() {
		// TODO Auto-generated method stub
		return true;
	}
	//**********************************直接编辑部分*************************************************
	
	
	
	//**********************************连线部分*************************************************
	public void addInput(ConnectionModel connection) {
        this.inputs.add(connection);
        firePropertyChange(ModelStringConstant.P_NODE_INPUTS, null,connection);
    }

	public void addOutput(ConnectionModel connection) {
        this.outputs.add(connection);
        firePropertyChange(ModelStringConstant.P_NODE_OUTPUTS, null,connection);
    }

    public List<ConnectionModel> getIncomingConnections() {
        return this.inputs;
    }

    public List<ConnectionModel> getOutgoingConnections() {
        return this.outputs;
    }

    public void removeInput(ConnectionModel connection) {
        this.inputs.remove(connection);
        firePropertyChange(ModelStringConstant.P_NODE_INPUTS, null,connection);
    }

    public void removeOutput(ConnectionModel connection) {
        this.outputs.remove(connection);
        firePropertyChange(ModelStringConstant.P_NODE_OUTPUTS, null,connection);
    }
    
    public boolean hasOutputs(){
		return !(outputs.isEmpty());
	}
    
	public boolean hasInputs(){
		return !(inputs.isEmpty());
	}
	//**********************************连线部分*************************************************




	
}
