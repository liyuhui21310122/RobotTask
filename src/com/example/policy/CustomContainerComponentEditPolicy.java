package com.example.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.example.command.ContainerDeleteCommand;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

public class CustomContainerComponentEditPolicy extends ComponentEditPolicy {
	 protected Command createDeleteCommand(GroupRequest deleteRequest) {
	        ContainerDeleteCommand deleteCommand=new ContainerDeleteCommand();
	        deleteCommand.setContainer((ContainerModel) getHost().getParent().getModel());
	        deleteCommand.setNode((NodeModel)getHost().getModel());
	        return deleteCommand;
	    }


}
