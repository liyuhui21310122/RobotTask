package com.example.helper;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;
import com.example.model.NonContainerModel;


public class CustomConstraintFactory {

	//*******************************刷新container 12.26*********************************
	//根据子模型而更改自己的constraint
	public static void refreshContainerChildren(ContainerModel container) {
		// TODO Auto-generated method stub
		Assert.isNotNull(container);
		
		//Rectangle r = new Rectangle();
		Rectangle r = container.getConstraint();
//		container.setConstraintChildren(r);
		container.setConstraint(r);

	}
	//********************************************刷新container*********************************
	
	
	//***************************ContainerCreateCommand调用，在container中添加node********************
	public static void newConfinedConstraint(ContainerModel container,NodeModel model){
		try{
		Rectangle constraint = new Rectangle();
		constraint.setSize(model.getPreferredDimension());
		constraint.setLocation(model.getLocation());
		model.setConstraint(constraint);//添加node
		
		//改变父container的constraint,不需要吧，add的时候自然会调用？
		//refreshContainerChildren(NODE,container);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//***************************ContainerCreateCommand调用，在container中添加node*********************
	
	
	//***************************createNodeCommand调用，在diagram中添加node*********************
	public static void newConfinedConstraint(NodeModel model){
		if(model instanceof NonContainerModel){
			try{
				Rectangle constraint = new Rectangle();
				constraint.setSize(model.getPreferredDimension());
				constraint.setLocation(model.getLocation());
				model.setConstraint(constraint);
				
				}catch(Exception e){
					e.printStackTrace();
				}
		}
		else{
			try{
			Rectangle constraint = new Rectangle();
	//		constraint.setSize(model.getPreferredDimension());
			Dimension size = new Dimension(((ContainerModel) model).getW(),((ContainerModel) model).getH());
			constraint.setSize(size);
			constraint.setLocation(model.getLocation());
	//		constraint = model.getConstraint();
			model.setConstraint(constraint);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//***************************createNodeCommand调用，在diagram中添加node*********************
	
	
	//********************************************定制diagram*********************************
	public static void configureDiagram(DiagramModel diagram){
		Assert.isNotNull(diagram);
		
		List<Object> containers = diagram.getContainers();
		ContainerModel container;
		for(int i=0;i<containers.size();i++){
			container = (ContainerModel) containers.get(i);
			newConfinedConstraint(container);
		    configureContainer(container);
		}
		
		List<Object> nonContainers = diagram.getNonContainers();
		NonContainerModel nonContainer;
		for(int i=0;i<nonContainers.size();i++){
			nonContainer = (NonContainerModel) nonContainers.get(i);
			newConfinedConstraint(nonContainer);
			
		}
		/*
		List<Object> connections = diagram.getConnections();
		ConnectionModel con;
		for(int i=0;i<connections.size();i++){
			con = (ConnectionModel) connections.get(i);
			
		}
		*/
		//实现连线
		NodeModel node;
		List<ConnectionModel> con;
		for(int i=0;i<diagram.getChildren().size();i++){
			node = (NodeModel) diagram.getChildren().get(i);
			if(node.getFromID().equals("no input")){
				//如果没有输入线，则跳过
			}
			else{
			con = CustomModelFactory.getConnectionModel(diagram,node);
			}
		}
	}
	
	
	
	//********************************************定制container*********************************
	private static void configureContainer(ContainerModel container) {
		// TODO Auto-generated method stub
		Assert.isNotNull(container);
		
		List<Object> containers = container.getContainers();
		ContainerModel cc;
		for(int i=0;i<containers.size();i++){
			cc = (ContainerModel) containers.get(i);
			newConfinedConstraint(container,cc);
		    configureContainer(cc);
		}
		
		List<Object> nonContainers = container.getNonContainers();
		NonContainerModel nonC;
		for(int i=0;i<nonContainers.size();i++){
			nonC = (NonContainerModel) nonContainers.get(i);
			newConfinedConstraint(container,nonC);
		}
		/*
		List<Object> connections = container.getConnections();
		ConnectionModel con;
		for(int i=0;i<connections.size();i++){
			con = (ConnectionModel) connections.get(i);
			
		}
		*/
	}
	//********************************************定制container*********************************
  
}
