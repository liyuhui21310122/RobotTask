package com.example.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import com.example.command.CreateBendpointCommand;

public class CustomBendpointEditPolicy extends BendpointEditPolicy {

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		// TODO Auto-generated method stub
		Point point = request.getLocation();
		getConnection().translateToRelative(point);
		
		CreateBendpointCommand command = new CreateBendpointCommand();
		
		command.setLocation(point);
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
