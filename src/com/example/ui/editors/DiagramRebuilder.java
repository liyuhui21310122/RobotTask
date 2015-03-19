package com.example.ui.editors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.dom4j.Element;
import org.eclipse.core.runtime.Assert;

import com.example.helper.CustomConstraintFactory;
import com.example.helper.CustomErrorInfo;
import com.example.helper.CustomModelFactory;
import com.example.helper.ModelStringConstant;
import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.ModelType;
import com.example.model.NodeModel;


public class DiagramRebuilder {
	/*
	private static HashMap<String,Object> connectionMap = new HashMap<String, Object>();
	private static HashMap<String,Object> containerMap = new HashMap<String, Object>();
	private static List<Element> masterModel = new ArrayList<Element>();
	*/
	//�ָ�diagram
	public static final DiagramModel rebuildFromRoot(Element root,List<Object> err){
		Assert.isNotNull(root);
		//�õ�diagramModel
		Element dE = getDiagram(root);
		Assert.isNotNull(dE);
		DiagramModel dModel = new DiagramModel();
		
		//�õ�diagram�µ������ӽڵ�
		List<?> elementsE = dE.elements();		
		
		//�ֱ�rebuild
		Element e;
		NodeModel node;
		ConnectionModel con;
		
		for(int i=0;i<elementsE.size();i++){
			e = (Element)elementsE.get(i);	
			
			//����modelName������Ӧ��model
			node = getNodeModel(e);
			if(node != null){
				node.rebuildFromXml(e, err);
				dModel.addChild(node);
				if(node instanceof ContainerModel){
					//Element cE = getContainer(dE);
				rebuildContainer(e,(ContainerModel) node,err);
				}
			}
			else{
				err.add(new CustomErrorInfo(
						e,
						CustomErrorInfo.NULL,
						"Unknown Model"));
			}
		}
		/*	
		//�⵽�׷���configureDiagram�ﻹ����������������configure���Ѿ�����model��Ȼ�󻭳���
		//rebuild connection
		for(int i=0;i<elementsE.size();i++){
			e = (Element)elementsE.get(i);	
			//�õ�node��fromID
			//String formID = e.attributeValue(ModelType.ATR_FROMID);
			con = getConnectionModel(dModel,e);
		}	
		*/	
        CustomConstraintFactory.configureDiagram(dModel);
		return dModel;
		
	}
	
	//�ָ�����
	protected static final void rebuildContainer(
			Element cE,
			ContainerModel container,//����rebuild��container
			List<Object> err){
		
		Assert.isNotNull(container);
		//�õ�container�������ӽڵ�
		List<?> elementsE = cE.elements();
		
		Element e;
		NodeModel cNode;
		ConnectionModel cConnection;
		
		for(int i=0;i<elementsE.size();i++){
			e = (Element)elementsE.get(i);	
			
			//�����connection
			if(e.getName().equals(ModelType.HEAD_CONNECTION)){
				//do nothing
		    }
		   
			//�����node
			else {
				//����modelName������Ӧ��model
				cNode = getNodeModel(e);
				if(cNode != null){
					cNode.rebuildFromXml(e, err);
					container.addChild(cNode);
					if(cNode instanceof ContainerModel){
					//Element cE = getContainer(dE);
					rebuildContainer(e,(ContainerModel) cNode,err);
					}
				}
				else{
					err.add(new CustomErrorInfo(
							e,
							CustomErrorInfo.NULL,
							"Unknown Model"));
				}
			}
		}	
		//�⵽�׷���configureContainer�ﻹ���������
		//rebuild connection
		for(int i=0;i<elementsE.size();i++){
			e = (Element)elementsE.get(i);	
			
			//�����connection
			if(e.getName().equals(ModelType.HEAD_CONNECTION)){
				cConnection = getConnectionModel(container,e);
				container.addChild(cConnection);
		    }
		}	
	}
	
	protected static final Element getDiagram(Element root){
		return root.element(ModelType.ELE_DIAGRAM);
	}
	
	//�ָ���diagram�������е�node
	protected static final NodeModel getNodeModel(Element e){	
		Assert.isNotNull(e);		
		
		//����modelName������Ӧ���͵�node
		NodeModel model = CustomModelFactory.getNodeModel(e.attributeValue(ModelType.ATR_MODELNAME));		
		//modelMap.put(e.attributeValue(ModelType.ATR_ID), model);
		return model;
		
	}
	/*
	//�ָ���diagram�е�����
	protected static final ConnectionModel getConnectionModel(DiagramModel diagram,Element e){	
		Assert.isNotNull(e);		
		
		ConnectionModel model = CustomModelFactory.getConnectionModel
		                      (diagram,
		                       e.attributeValue(ModelType.ATR_FROMID),
		                       e.attributeValue(ModelType.ATR_ID));		
		//connectionMap.put(e.attributeValue(ModelType.ATR_ID), model);
		return model;
	}
	*/
	//�ָ��������е�����
	protected static final ConnectionModel getConnectionModel(ContainerModel container,Element e){	
		Assert.isNotNull(e);		
		
		ConnectionModel model = CustomModelFactory.getConnectionModel
		                      (container,
		                       e.attributeValue(ModelType.ATR_SOURCEID),
		                       e.attributeValue(ModelType.ATR_TARGETID));		
		//connectionMap.put(e.attributeValue(ModelType.ATR_ID), model);
		return model;
	}
}
