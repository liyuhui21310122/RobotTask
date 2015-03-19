/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.example.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.example.command.CreateConnectionCommand;
import com.example.command.ReconnectSourceCommand;
import com.example.model.ConnectionModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
        command.setTarget((NodeModel) getHost().getModel());
        //command.setDiagramModel((DiagramModel) getHost().getModel());
        return command;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        CreateConnectionCommand command = new CreateConnectionCommand();
        command.setSource((NodeModel) getHost().getModel());
        request.setStartCommand(command);
        return command;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((ConnectionModel)request.getConnectionEditPart().getModel());
		cmd.setSource((NodeModel)getHost().getModel());
		return cmd;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }
}