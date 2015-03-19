/*
 * Created on 2005-1-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.example.command;

import org.eclipse.gef.commands.Command;

import com.example.model.ConnectionModel;
import com.example.model.NodeModel;

/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReconnectSourceCommand extends Command {
    private ConnectionModel connection;

    private NodeModel newSource;

    private NodeModel oldSource;

    private NodeModel target;

    //setters
    public void setConnection(ConnectionModel connection) {
        this.connection = connection;
        this.target=this.connection.getTarget();
        this.oldSource=this.connection.getSource();
    }

    public void setSource(NodeModel source) {
        this.newSource = source;
    }

    public void execute() {
        oldSource.removeOutput(connection);
        newSource.addOutput(connection);
        connection.setSource(newSource);
    }

    public String getLabel() {
        return "Reconnect Source";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        newSource.removeOutput(connection);
        oldSource.addOutput(connection);
        connection.setSource(oldSource);
    }
}