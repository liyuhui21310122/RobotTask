package com.example.policy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;

public class CustomDirectEditPolicy implements EditPolicy {

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void eraseSourceFeedback(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eraseTargetFeedback(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Command getCommand(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditPart getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditPart getTargetEditPart(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHost(EditPart editpart) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showSourceFeedback(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTargetFeedback(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean understandsRequest(Request request) {
		// TODO Auto-generated method stub
		return false;
	}

}
