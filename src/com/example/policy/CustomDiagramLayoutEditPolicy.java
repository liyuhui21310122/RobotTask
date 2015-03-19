package com.example.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.example.command.ChangeConstraintCommand;
import com.example.command.MoveNodeCommand;
import com.example.command.CreateNodeCommand;
import com.example.editpart.ContainerPart;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;


public class CustomDiagramLayoutEditPolicy  extends XYLayoutEditPolicy {
    //
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
//		if(child instanceof ContainerPart){
			ChangeConstraintCommand command = new ChangeConstraintCommand();
			command.setDiagram(getHost().getModel());
			command.setNodeModel(child.getModel());
			command.setLocation(((Rectangle)constraint).getTopLeft());
			//command.setSize(((Rectangle)constraint).getSize());
			command.setConstraint(constraint);
			return command;
//		}else{
//		// TODO Auto-generated method stub
//		MoveNodeCommand command = new MoveNodeCommand();
//		
//		command.setDiagram(getHost().getModel());
//		command.setNodeModel(child.getModel());
//		command.setLocation(((Rectangle)constraint).getTopLeft());
//		
//		return command;
//		}
	}
    
	//µ÷ÓÃcreate command
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
        CreateNodeCommand command = new CreateNodeCommand();		
		
		command.setDiagramModel((DiagramModel) getHost().getModel());		
		command.setNodeModel((NodeModel) request.getNewObject());
		command.setLocation(((Rectangle)getConstraintFor(request)).getTopLeft());
		
		return command;
	}

	
}
