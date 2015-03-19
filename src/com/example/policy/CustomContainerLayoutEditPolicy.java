package com.example.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;


import com.example.command.ChangeConstraintCommand;
import com.example.command.ContainerChangeConstraintCommand;
import com.example.command.ContainerCreateCommand;
import com.example.command.ContainerMoveNodeCommand;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;




public class CustomContainerLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		// TODO Auto-generated method stub
		
		ContainerChangeConstraintCommand command = new ContainerChangeConstraintCommand();
		command.setContainer(getHost().getModel());
		command.setNodeModel(child.getModel());
		command.setLocation(((Rectangle)constraint).getTopLeft());
		//command.setSize(((Rectangle)constraint).getSize());
		command.setConstraint(constraint);
		return command;
		
		
//        ContainerMoveNodeCommand cmd = new ContainerMoveNodeCommand();
//        
//        cmd.setContainerModel(getHost().getModel());
//        cmd.setNodeModel((NodeModel) child.getModel());
//        cmd.setLocation(((Rectangle)constraint).getTopLeft());
//		return cmd;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
        ContainerCreateCommand command = new ContainerCreateCommand();
		/*
        ContainerModel cm = (ContainerModel) getHost().getModel();
		NodeModel nm = (NodeModel)request.getNewObject();
		Point p = ((Rectangle)getConstraintFor(request)).getTopLeft();
		*/
		command.setContainerModel(getHost().getModel());
		command.setNodeModel(request.getNewObject());
		command.setLocation(((Rectangle)getConstraintFor(request)).getTopLeft());
		
		return command;
	}
    /*
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child){
		return new CustomResizableEditPolicy();
		
	}
	*/
}
