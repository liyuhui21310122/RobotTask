package com.example.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

public class ContainerChangeConstraintCommand extends Command {
	private ContainerModel container;
	private NodeModel model;		
	private Point oldLocation, newLocation;
	private Rectangle oldCons,newCons;
	private Dimension oldSize,newSize;
	//private int oldIndex;

	public void execute(){
	
		oldLocation = model.getLocation();		
		oldCons = model.getConstraint();
//		oldSize = model.getPreferredDimension();
		container.removeChild(model);
		//�¼ӵ�
		//diagram.removeXmlChild(model.getXMLModel());
		model.setLocation(newLocation);
//		model.setSize(newSize);
		model.setConstraint(newCons);
		container.addChild(model);
	
		//diagram.fireToRefresh(model);
	}
	
	public void setLocation(Object location){
		this.newLocation = (Point)location;
	}
	
	
	public void setNodeModel(Object model){
		this.model = (NodeModel)model;		
	}
	

	public void setConstraint(Object cons) {
		this.newCons = (Rectangle) cons;
	}

	public void setSize(Object size) {
	     this.newSize = (Dimension) size;
    }
	
//	public void setDiagram(Object diagram){
//		this.diagram = (DiagramModel)diagram;
//	}
	
	public void setContainer(Object container){
	    this.container = (ContainerModel) container;
    }
	
	public void undo(){		
		container.removeChild(model);
		model.setLocation(oldLocation);
		model.setConstraint(oldCons);
//		model.setSize(oldSize);
		container.addChild(model);
	}

}
