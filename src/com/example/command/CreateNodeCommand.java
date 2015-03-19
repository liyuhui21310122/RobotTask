package com.example.command;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;

import com.example.helper.CustomConstraintFactory;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;
import com.example.model.node.StartModel;


public class CreateNodeCommand extends Command{
    protected  NodeModel node;
    protected  Point location;
    protected  DiagramModel diagram;
	
	//List<Object> containers = diagram.getContainers();
	
	public void execute(){	
		if (this.location != null) {
            this.node.setLocation(this.location);
        }
		if(node instanceof StartModel){
	    	   if(diagram.getStarts().size()==0){
	    		   CustomConstraintFactory.newConfinedConstraint(node);
	    			/*if (this.location != null) {
	    	            this.node.setLocation(this.location);
	    	        }
	    	        */
	    			diagram.addChild(node);
	    	   }
	    	   else{
	    		   MessageDialog.openInformation(null, "error", "只能有一个start模块");
	    	   }
	    }
		else{
			if(diagram.getStarts().size()==0){
				 MessageDialog.openInformation(null, "error", "第一个添加的必须为start模块");
			}
			else{
				CustomConstraintFactory.newConfinedConstraint(node);
    			/*if (this.location != null) {
    	            this.node.setLocation(this.location);
    	        }
    	        */
    			diagram.addChild(node);
			}
			
		}
		/*
		CustomConstraintFactory.newConfinedConstraint(node);
		diagram.addChild(node);*/
	}
	
	public void setDiagramModel(DiagramModel diagram){
		this.diagram = diagram;
	}
	
	public void setNodeModel(NodeModel node){
		this.node = node; 
	}
	
	public void setLocation(Point location){
		this.location = location;
	}
	
	public void undo(){
		diagram.removeChild(node);
	}
	
}
