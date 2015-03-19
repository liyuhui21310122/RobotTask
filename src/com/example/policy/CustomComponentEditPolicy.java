package com.example.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.example.command.DeleteNodeCommand;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

public class CustomComponentEditPolicy extends ComponentEditPolicy {
	
	protected Command createDeleteCommand(GroupRequest request){
		DeleteNodeCommand command = new DeleteNodeCommand();
		
		command.setDiagram((DiagramModel) getHost().getParent().getModel());
		command.setNode((NodeModel) getHost().getModel());		
		
		return command;
	}

}
