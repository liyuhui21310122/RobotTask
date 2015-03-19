package com.example.command;

import org.eclipse.gef.commands.Command;

import com.example.helper.CustomConstraintFactory;
import com.example.model.ContainerModel;
import com.example.model.NodeModel;

public class ContainerDeleteCommand extends Command{
	private ContainerModel container;
	private NodeModel node;
	
	public void setContainer(ContainerModel container){
		this.container = container;
	}
	
	public void setNode(NodeModel node){
		this.node = node;
	}
	
	public void execute(){
		CustomConstraintFactory.newConfinedConstraint(container,node);
		container.removeChild(node);		
	}
	
	public boolean canExecute(){
		return container.canDeleteChild(node);
	}
	
	public void undo(){	
		if(container.canAddChild(node)){
			container.addChild(node);
		}	
	}
} 
