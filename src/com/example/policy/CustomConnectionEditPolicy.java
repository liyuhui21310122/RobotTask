package com.example.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.example.command.DeleteConnectionCommand;
import com.example.model.ConnectionModel;
import com.example.model.DiagramModel;

public class CustomConnectionEditPolicy extends ComponentEditPolicy{

    protected Command createDeleteCommand(GroupRequest deleteRequest) {
        ConnectionModel conn=(ConnectionModel)getHost().getModel();
        DeleteConnectionCommand cmd=new DeleteConnectionCommand();
        cmd.setConnection(conn);
        cmd.setSource(conn.getSource());
        cmd.setTarget(conn.getTarget());
        return cmd;
    }
}

