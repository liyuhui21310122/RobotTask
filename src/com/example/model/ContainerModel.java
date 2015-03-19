package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;


import com.example.helper.ConstantResourceFactory;
import com.example.helper.CustomErrorInfo;
import com.example.helper.ModelStringConstant;



public abstract class ContainerModel extends NodeModel implements IContainer  {
	private List<Object> children = new ArrayList<Object>();
	private List<Object> nonContainers = new ArrayList<Object>();	
	private List<Object> containers = new ArrayList<Object>();
	//protected boolean collapsed = false;
	int h =100;
	int w = 100;
	//protected Dimension collapsedDimension = new Dimension(80, 50);
	
	//***********************************结构体**********************************************
	public ContainerModel(String label, String modelID, Dimension size) {
		super(label, modelID, size);
		// TODO Auto-generated constructor stub
	}
	//***********************************结构体**********************************************
	
	
	
	//***********************************子模型部分**********************************************
	public List<Object> getChildren() {
		return children;		
	}
	
	public boolean hasChild(){
		return !(children.isEmpty());
	}
	
	@Override
	public boolean canDeleteChild(Object child) {
		// TODO Auto-generated method stub
		return getChildren().contains(child);
	}

	@Override
	public boolean canAddChild(Object child) {
		// TODO Auto-generated method stub
		/*
		Point childLocation; 
		childLocation = ((NodeModel) child).getLocation();
		if(isInContainer(childLocation)){
			return true;
		}
		return false;
		*/
		return true;
	}
	/*
	//判定是否位于container范围内
	private boolean isInContainer(Point p){
		int x,y,xc,yc,wc,hc;
		x = p.x;//子模型的x
		y = p.y;//子模型的y
		//container的constraint信息
		xc = this.getConstraint().x;
		yc = this.getConstraint().y;
		wc = this.getConstraint().width;
		hc = this.getConstraint().height;
		if((x > xc)&&(y > yc)&&(x < xc + wc)&&(y < yc + hc)){
			return true;
		}
		else {
			return false;
		}
	}
	*/
	//添加子模型
	public void addChild(Object child) {
		children.add(child);
    	if(child instanceof NonContainerModel){
			addNonContainer(child);
			addXmlChild(((NodeModel) child).getXMLModel());
		}
    	else if(child instanceof ContainerModel){
    		addContainer(child);
    		addXmlChild(((NodeModel) child).getXMLModel());
    	}
 
    	firePropertyChange(ModelStringConstant.P_CONTAINER_CHILDREN_ADD,null,child);	
    }
	
	public void addNonContainer(Object nonContainer) {
		nonContainers.add(nonContainer);
	}
	    
	public void addContainer(Object container){
		containers.add(container);	
    }
	  
	public void addXmlChild(Element child){
		List<Element> children = getXMLModel().elements();
		children.add(child);	
	}
	 
	 //删除子模型
	public void removeChild(Object child) {
		children.remove(child);
	    if(child instanceof NonContainerModel){
			removeNonContainer(child);
			removeXmlChild(((NodeModel) child).getXMLModel());
		}
	    else if(child instanceof ContainerModel){
	    	removeContainer(child);
	    	removeXmlChild(((NodeModel) child).getXMLModel());
	    }
	    
	    firePropertyChange(ModelStringConstant.P_CONTAINER_CHILDREN_DELETE,null,child);	
	}
	 
	public void removeNonContainer(Object noneContainer) {
		nonContainers.remove(noneContainer);
	}
	    
	public void removeContainer(Object container){
		containers.remove(container);
	}
	 
	public void removeXmlChild(Element child){
		getXMLModel().remove(child);
	}
    
	public void removeChildWithoutFire(Object child) {
		// TODO Auto-generated method stub
		children.remove(child);
	    if(child instanceof NonContainerModel){
			removeNonContainer(child);
			removeXmlChild(((NodeModel) child).getXMLModel());
		}
	    else if(child instanceof ContainerModel){
	    	removeContainer(child);
	    	removeXmlChild(((NodeModel) child).getXMLModel());
	    } 
	}
    
	//***********************************子模型部分**********************************************
	

	
	//***********************************Constraint部分******************************************
	/*public Dimension getSize() {
        if (!isCollapsed())
            return super.getPreferredDimension();
        else
            return collapsedDimension;
    }
	
	public void setSize(Dimension d) {
        if (this.size.equals(d)) {
            return;
        }
        this.size = d;
        firePropertyChange(ModelStringConstant.P_CONTAINER_SIZE, null, d);
    }
    */
	
	public void setConstraint(Rectangle r){
		super.setConstraint(r);
		//setConstraintChildren(r);
	}
	
	
    //找到角的位置
    protected int[] getXY(){
    	List<Object> children = getChildren();
		NodeModel m ;
		int childrenX [] = new int [2*(children.size())] ;   
		int childrenY [] = new int [2*(children.size())] ; 
		int XY[] = new int[4];
		for(int i=0;i<children.size();i++){
			m = (NodeModel) children.get(i);
			
				childrenX[i] = m.getConstraint().x;
				childrenY[i] = m.getConstraint().y;
				childrenX[i + children.size()] = m.getConstraint().x + m.getConstraint().width;
				childrenY[i + children.size()] = m.getConstraint().y + m.getConstraint().height;
			
		}
		XY[0] = Min(childrenX);
		XY[1] = Min(childrenY);
		
		XY[2] = Max(childrenX);
		XY[3] = Max(childrenY);
		return XY;
    }
    
    protected int Max(int[] a){
    	int max = a[0];
    
    	for(int i=1;i<a.length;i++){
    		if (max > a[i]){
    		}
    		else {max = a[i];}
    	}
    	return max;
    }
    
    protected int Min(int[] a){
    	int min = a[0];
    
    	for(int i=1;i<a.length;i++){
    		if (min < a[i]){
    		
    		}
    		else min = a[i];
    	}
    	return min;
    }
    
    public void setConstraintChildren(Rectangle r){		
		
//		r.x = this.getLocation().x;
//		r.y = this.getLocation().y;
		List<Object> children = getChildren();
		int size = children.size();
		Rectangle rCopy = r;
		if(size!=1){
			int XY [] = getXY();
			r.height = XY[3] - XY[1];
			r.width = XY[2] - XY[0];
		}else{
			r.y = rCopy.y - 20;
		}
		setConstraint(r);
		//setAbsoluteConstraint(r);
	}	
	
	//***********************************Constraint部分******************************************
    
    
    
    //***********************************xml部分**********************************************
    @Override
	public void doSaveXML() {
		// TODO Auto-generated method stub
		super.doSaveXML();		
		this.addXMLElementAttribute(ModelType.ATR_WIDTH, Integer.toString(this.getConstraint().width));
		this.addXMLElementAttribute(ModelType.ATR_HEIGHT, Integer.toString(this.getConstraint().height));
		NodeModel node,source;
		List<ConnectionModel> inputs = new ArrayList<ConnectionModel>();
		
		for(int i=0;i<getChildren().size();i++){
			//得到某一个node
			node = (NodeModel) getChildren().get(i);
			
			//对于每一个node，都有自己的inputs列表和fromID
			inputs = node.getIncomingConnections();
			String[] sourceID = new String[inputs.size()];
			//刷新formID的值
			if(node.hasInputs()){
			    
				for(int j=0;j<inputs.size();j++){
				   source = (inputs.get(j)).getSource();
			       sourceID[j] = source.ID;
			    }
			    node.fromID = node.getStr(sourceID);
			    
			}
			
			node.doSaveXML();
		}
	}
    public void rebuildFromXml(Element e,List<Object> err) {
    	super.rebuildFromXml(e, err);
		if(e.attribute(ModelType.ATR_HEIGHT) != null){ 
			String height = e.attributeValue(ModelType.ATR_HEIGHT);
	//		getConstraint().height = Integer.parseInt(height);
	//		getPreferredDimension().height = Integer.parseInt(height);
			h = Integer.parseInt(height);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_HEIGHT));
		}
		
		if(e.attribute(ModelType.ATR_WIDTH) != null){ 
			String width = e.attributeValue(ModelType.ATR_WIDTH);
	//		getConstraint().width = Integer.parseInt(width);
	//	    getPreferredDimension().width = Integer.parseInt(width);
			w = Integer.parseInt(width);
		}
		else{
			err.add(new CustomErrorInfo(
					e,
					CustomErrorInfo.NULL,
					ModelType.ATR_WIDTH));
		}
    }
    //***********************************xml部分**********************************************
    
    
    
    //***********************************get/set部分**********************************************
    public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}


	public int getW() {
		return w;
	}


	public void setW(int w) {
		this.w = w;
	}

    public List<Object> getNonContainers(){
		return nonContainers;
	}
	
	public List<Object> getContainers(){
		return containers;
	}
	/*
	public boolean isCollapsed() {
        return collapsed;
    }
    
    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
        firePropertyChange(ConstantResourceFactory.P_CONTAINER_COLLAPSED,null, new Boolean(collapsed));
    }
    */
	//***********************************get/set部分**********************************************
	
	/*
	@Override
	public abstract Object copy();
    */
	@Override
	public abstract IPropertyDescriptor[] getPropertyDescriptors();

	public void fireToRefresh(Object model){
		firePropertyChange(ConstantResourceFactory.P_CONTAINER_REFRESH, null, model);
	}
	
}
