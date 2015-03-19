package com.example.policy;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import com.example.command.OrphanChildCommand;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;


public class CustomDiagramEditPolicy  extends ContainerEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
    
	protected Command getOrphanChildrenCommand(GroupRequest request) {
		List<?> parts = request.getEditParts();
		CompoundCommand result = new CompoundCommand();
		
		for (int i = 0; i < parts.size(); i++) {
			OrphanChildCommand command = new OrphanChildCommand();
			command.setNode((NodeModel) ((EditPart)parts.get(i)).getModel());
			command.setDiagram((DiagramModel) getHost().getModel());
			
			result.add(command);
		}
		return result.unwrap();
	}

}
