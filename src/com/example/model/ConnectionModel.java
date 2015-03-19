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

import com.example.helper.CustomErrorInfo;
import com.example.helper.ModelStringConstant;




public class ConnectionModel extends AbstractModel {
    
	private NodeModel source;
    private NodeModel target;
    private String sourceID, targetID;
    protected String xmlHead;
	protected Element xmlModel;
	private String sourceName;
	private String targetName;	
	
	
	private List<Point> bendpoints = new ArrayList<Point>();
	
    //*************************************结构体部分 *************************************
    public ConnectionModel(NodeModel source, NodeModel target) {
        super();
        this.source = source;
        this.target = target;
        source.addOutput(this);
        target.addInput(this);
        //target.setID(source.getIndex());
        //initModel();
    }
    //*************************************结构体部分 *************************************
    
    
    
    //*************************************连接node部分 *************************************
    public void setSource(NodeModel source) {
        this.source = source;
    }

    public void setTarget(NodeModel target) {
        this.target = target;
    }

    public NodeModel getTarget() {
        return this.target;
    }

    public NodeModel getSource() {
        return this.source;
    }
    
    public String getTargetName() {
    	return this.target.getModelName();
    }

    public String getSourceName() {
        return this.source.getModelName();
    }
    
    
    //*************************************连接node部分 *************************************
    
    
    
    //***********************************属性编辑部分***********************************
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		//return new IPropertyDescriptor[0];
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
	}
	//***********************************属性编辑部分***********************************
	
	
	
	
	//***********************************xml部分***********************************
	
	@Override
	public void doSaveXML() {
		// TODO Auto-generated method stub
		this.addXMLElementAttribute(ModelType.ATR_TARGETID, targetID);
		this.addXMLElementAttribute(ModelType.ATR_SOURCEID, sourceID);
		this.addXMLElementAttribute(ModelType.ATR_TARGETNAME, getTargetName());
		this.addXMLElementAttribute(ModelType.ATR_TARGETNAME, getSourceName());
	}

	@Override
	public void initModel(){
		//setModelName("connection");
		setXMLHead(ModelType.getModelXMLHead(this));
		setXMLModel(DocumentHelper.createElement(getXMLHead()));
	}
    
	@Override
	public void rebuildFromXml(Element e,List<Object> err) {
		// TODO Auto-generated method stub
		if(e.attribute(ModelType.ATR_TARGETID) != null){
			targetID = e.attributeValue(ModelType.ATR_TARGETID);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_TARGETID));
		}
		
		if(e.attribute(ModelType.ATR_SOURCEID) != null){
			sourceID = e.attributeValue(ModelType.ATR_SOURCEID);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_SOURCE));
		}
		
		if(e.attribute(ModelType.ATR_TARGETNAME) != null){
			targetName = e.attributeValue(ModelType.ATR_TARGETNAME);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_TARGETNAME));
		}
		
		if(e.attribute(ModelType.ATR_SOURCENAME) != null){
			sourceName = e.attributeValue(ModelType.ATR_SOURCENAME);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_SOURCENAME));
		}
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
	
	public String getSourceID() {
		return source.getID();
	}

	public String getTargetID() {
		return target.getID();
	}
    
	//***********************************xml部分***********************************



	public void removeSource() {
		// TODO Auto-generated method stub
		source.removeOutput(this);
	}



	public void removeTarget() {
		// TODO Auto-generated method stub
		target.removeInput(this);
	}



	public void addSource() {
		// TODO Auto-generated method stub
		if(!source.getOutgoingConnections().contains(this)){
			source.addOutput(this);
		}
	}



	public void addTarget() {
		// TODO Auto-generated method stub
		if(!target.getIncomingConnections().contains(this)){
			target.addInput(this);
		}
	}
	
	//*********************************bendpoints*****************************************************
	public void addBendPoint(int index,Point point){
		bendpoints.add(index, point);
		firePropertyChange(ModelStringConstant.P_BEND_POINT,null,null);
	}
	
	public void removeBendPoint(int index){
		bendpoints.remove(index);
		firePropertyChange(ModelStringConstant.P_BEND_POINT,null,null);
	}
	
	public void replaceBenpoint(int index,Point point){
		bendpoints.set(index, point);
		firePropertyChange(ModelStringConstant.P_BEND_POINT,null,null);
	}
	
	public List getBendpoints(){
		return bendpoints;
	}
}
