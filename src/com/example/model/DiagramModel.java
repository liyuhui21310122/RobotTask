package com.example.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.example.helper.ConstantResourceFactory;
import com.example.helper.ModelStringConstant;
import com.example.model.node.ConditionModel;
import com.example.model.node.EndModel;
import com.example.model.node.StartModel;




public class DiagramModel extends AbstractModel {
	private String name;
	private boolean finish = true;
	private boolean checkValid = true;
    private String valid;
	private String task;
	private Document diagramDoc;
	private Element rootElement,diagramElement;
	
	private List<Object> children = new ArrayList<Object>();
	private List<Object> nonContainers = new ArrayList<Object>();	
	private List<Object> containers = new ArrayList<Object>();
	private List<Object> starts = new ArrayList<Object>();
	private List<Object> ends = new ArrayList<Object>();
	
	
	//***************************************初始化部分*****************************************
    public DiagramModel(){
    	super();
    	name = "";
    	task = "false";
    	valid = "true";
    	initModel();
    }
    
    public void initModel(){
		setName("Diagram");		
		setDiagramDoc(DocumentHelper.createDocument());		
		setRootElement(getDiagramDoc().addElement(ModelType.ELE_ROOT));
		setDiagramElement(getRootElement().addElement(ModelType.ELE_DIAGRAM));	
		//setDiagramExElement(getRootElement().addElement(ModelType.ELE_DIAGRAMEX));
	}
    //***************************************初始化部分******************************************
    
    
    
    //***************************************子模型部分*****************************************
    //返回子模型
    public List<Object> getChildren() {
		return children;		
	}
    
    //添加子模型
    public void addChild(Object child) {
    	
    	if(child instanceof NonContainerModel){
			children.add(child);
			addNonContainer(child);
			addXmlChild(((NodeModel) child).getXMLModel());
			if(child instanceof StartModel){
				starts.add(child);
			}
			else if(child instanceof EndModel){
				ends.add(child);
			}
		}
    	else if(child instanceof ContainerModel){
    		children.add(child);
    		addContainer(child);
    		addXmlChild(((NodeModel)child).getXMLModel());
    	}
    	
    	//xmlModel的顺序
    	//addXmlChild(((NodeModel)child).getXMLModel());
    	
    	firePropertyChange(ModelStringConstant.P_DIAGRAM_CHILDREN_ADD,null,child);	
    }
    
    public void addNonContainer(Object nonContainer) {
		nonContainers.add(nonContainer);
	}
    
    public void addContainer(Object container){
		containers.add(container);	
	}
   
    public void addXmlChild(Element child){
		List<Element> children = getDiagramElement().elements();
		children.add(child);	
	}
    
    //删除子模型
    public void removeChild(Object child){
		children.remove(child);
		if(child instanceof NonContainerModel){
			removeNonContainer(child);
			removeXmlChild(((NodeModel) child).getXMLModel());
			if(child instanceof StartModel){
				starts.remove(child);
			}
			else if(child instanceof EndModel){
				ends.remove(child);
			}
		}
    	else if(child instanceof ContainerModel){
    		removeContainer(child);
    		removeXmlChild(((NodeModel) child).getXMLModel());
    	}
	
		firePropertyChange(ModelStringConstant.P_DIAGRAM_CHILDREN_DELETE,null,child);	
	}
    
    public void removeNonContainer(Object noneContainer) {
		nonContainers.remove(noneContainer);
	}
    
    public void removeContainer(Object container){
		containers.remove(container);	
	}
    
    public void removeXmlChild(Element child){
		getDiagramElement().remove(child);
	}
    //***************************************子模型部分*****************************************
    
    
    
    //***************************************属性编辑部分****************************************
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		return new IPropertyDescriptor[0];
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
	//***************************************属性编辑部分****************************************
	
	
	
	//***************************************xml部分*********************************************
	public void doSaveXML() {
		// TODO Auto-generated method stub		
		//this.addDiagramAttribute(ModelType.ATR_NAME, name);
		//getDiagramHead().doSaveXML();
		NodeModel node,source;
		List<ConnectionModel> inputs = new ArrayList<ConnectionModel>();
		
		//修改fromID
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
		
		//判定任务的合法性
		finish = true;
		checkValid = true;
		if((starts.size())==1&&(ends.size()==1)){
			for(int i=0;i<getChildren().size();i++){
				//得到某一个node
				node = (NodeModel) getChildren().get(i);
				List<ConnectionModel> outputs = new ArrayList<ConnectionModel>();
				outputs = node.getOutgoingConnections();
				if((outputs.size()==0)){
					setValid(true);
				}
				else if((outputs.size()==1)){
					NodeModel n0 = outputs.get(0).getTarget();
					if(n0 instanceof ConditionModel){
						setValid(false);
					}
					else setValid(true);
				}
				else if(outputs.size()==2){
					NodeModel n1 = outputs.get(0).getTarget();
					NodeModel n2 = outputs.get(1).getTarget();
					if((n1 instanceof ConditionModel)&&(n2 instanceof ConditionModel)){
						if(((ConditionModel) n1).getValue()!= ((ConditionModel) n2).getValue()){
							setValid(true);
						}
						else setValid(false);
					}
					else setValid(false);
				}
				else setValid(false);
			}
			
		}
		else setValid(false);
			
		

		
		//前序遍历
		for(int i=0;i<getChildren().size();i++){
			node = (NodeModel) getChildren().get(i);
			//如果为任务的最开始
			if(!node.hasInputs()&&(node instanceof StartModel)){
				preOrder(node);
			}	
		}
		
		//添加diagram属性
		this.addDiagramAttribute(ModelType.ATR_NAME, name);
		this.addDiagramAttribute(ModelType.ATR_FINISH,getFinish());
		this.addDiagramAttribute(ModelType.ATR_VALID,getValid());
	}
	
	//递归调用
	public void preOrder(NodeModel node) {
		// TODO Auto-generated method stub
		//node.doSaveXML();
		if((getDiagramElement().elements()).contains(node.getXMLModel())){
			removeXmlChild(((NodeModel) node).getXMLModel());
		}
		addXmlChild(((NodeModel) node).getXMLModel());
		
		List<ConnectionModel> outputs = new ArrayList<ConnectionModel>();
		outputs = node.getOutgoingConnections();
		
		for(int i=0;i<outputs.size();i++){
		   
			NodeModel n = outputs.get(i).getTarget();
			//判定是否为条件模型,若是，则一定从value=0的开始
			if((n instanceof ConditionModel)){
				if(((ConditionModel) n).getValue().equals("0")&&(i==1)){
					n = outputs.get(i-1).getTarget();
				}
				else if(((ConditionModel) n).getValue().equals("1")&&(i==0)){
					n = outputs.get(i+1).getTarget();
				}
			}
			//如果是任务的最后
			if(!n.hasOutputs()){
				if((getDiagramElement().elements()).contains(n.getXMLModel())){
					removeXmlChild(((NodeModel) n).getXMLModel());
				}
				addXmlChild(((NodeModel) n).getXMLModel());
				if(n instanceof EndModel){
					boolean f = true;
					setFinish(f);
				}
				else{
					boolean f = false;
					setFinish(f);
				}
				
			}
			else{
				preOrder(n);
			}	
		}
	}
	
	@Override
	public void rebuildFromXml(Element e, List<Object> err) {
		// TODO Auto-generated method stub	
	}
	//***************************************xml部分*********************************************
	
	
	
	//***************************************get和set部分*********************************************
	public List<Object> getNonContainers(){
		return nonContainers;
	}
	
	public List<Object> getContainers(){
		return containers;
	}
	
	public List<Object> getStarts(){
		return starts;
	}
	
	public List<Object> getEnds(){
		return ends;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDiagramElement(Element diagramElement) {
		this.diagramElement = diagramElement;
	}

	public Element getDiagramElement() {
		return diagramElement;
	}
	
	public void setDiagramDoc(Document diagramDoc) {
		this.diagramDoc = diagramDoc;
	}

	public Document getDiagramDoc() {
		return diagramDoc;
	}

	public void setRootElement(Element rootElement) {
		this.rootElement = rootElement;
	}

	public Element getRootElement() {
		return rootElement;
	}
    
	protected void addDiagramAttribute(String name,String value){
		if(getDiagramElement()==null){
			return;
		}
		getDiagramElement().addAttribute(name, value);
	}
	
	protected void removeDiagramAttribute(String name){
		if(getDiagramElement()==null){
			return;
		}
		Attribute atr = getDiagramElement().attribute(name);
		if(atr != null){
			getDiagramElement().remove(atr);
		}
	}
	//***************************************get和set部分*********************************************	
	public void fireToRefresh(Object model){
		firePropertyChange(ConstantResourceFactory.P_DIAGRAM_REFRESH,null,model);
	}
	
	public void setFinish(boolean f){
		finish = f&&finish;
	}
	
	public String getFinish(){
		if(finish == false){
			task = "false";
		}
		else task = "true";
		return task;
	}
	
	public void setValid(boolean f){
		checkValid = f&&checkValid;
	}
	
	public String getValid(){
		if(checkValid == false){
			valid = "false";
		}
		else valid = "true";
		return valid;
	}
	
	
}