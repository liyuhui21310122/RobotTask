package com.example.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import com.example.helper.CustomConstraintFactory;
import com.example.model.ContainerModel;
import com.example.model.NodeModel;
//ÔÚcontainerÖÐÌí¼Ónode
public class ContainerCreateCommand extends Command {
	private NodeModel model;
	private Point location;
	private ContainerModel container;
	
	public boolean canExecute(){	
		return container.canAddChild(model);		
	}
	
	public void execute(){
		if (this.location != null) {
            this.model.setLocation(this.location);
        }
		CustomConstraintFactory.newConfinedConstraint(container,model);
		/*
		if(model instanceof ContainerElementModel){			
			CustomConstraintFactory.configureContainerModel(
					(ContainerElementModel)model,
					container.getComponent(),
					container.getPorts().size());
		}
		*/
		container.addChild(model);
	}
	
	public void setContainerModel(Object container){
		this.container = (ContainerModel)container;
	}
	
	public void setNodeModel(Object model){
		this.model = (NodeModel)model; 
	}
	
	public void setLocation(Point location){
		this.location = location;
	}

	
	public void undo(){	
		if(container.canDeleteChild(model)){
			container.removeChild(model);
		}
	}

}
