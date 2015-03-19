package com.example.command;

import java.util.List;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.example.model.ContainerModel;
import com.example.model.NodeModel;
//container中移动子模型
public class ContainerMoveNodeCommand extends Command {
	private NodeModel model;
	private Point newLocation,oldLocation;
	private ContainerModel container;
	//private int oldIndex;
	
	public boolean canExecute(){	
		return container.canAddChild(model);
	}
	
	public void execute(){
		oldLocation = model.getLocation();		
		container.removeChild(model);
		model.setLocation(newLocation);
		container.addChild(model);
	}
	
	public void setContainerModel(Object container){
		this.container = (ContainerModel)container;
	}
	
	public void setNodeModel(Object model){
		this.model = (NodeModel)model; 
	}
	
	public void setLocation(Object location){
		this.newLocation = (Point)location;
	}
	
	public void undo(){		
		container.removeChild(model);
		model.setLocation(oldLocation);
		container.addChild(model);
	}
}
