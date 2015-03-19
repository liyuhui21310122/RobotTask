/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.example.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.example.model.ConnectionModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateConnectionCommand extends Command {
	protected  DiagramModel diagram;
    protected ConnectionModel connection;
    protected NodeModel source;
    protected NodeModel target;
    
    public void setDiagramModel(DiagramModel diagram){
		this.diagram = diagram;
	}
    
    public void setSource(NodeModel source) {
        this.source = source;
    }

    public void setConnection(ConnectionModel connection) {
        this.connection = connection;
    }

    public void setTarget(NodeModel target) {
        this.target = target;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
        connection = new ConnectionModel(source, target);
        //diagram.addChild(connection);
    }

    public String getLabel() {
        return "Create Connection";
    }

    public void redo() {
        this.source.addOutput(this.connection);
        this.target.addInput(this.connection);
        //diagram.addChild(connection);
    }

    public void undo() {
        this.source.removeOutput(this.connection);
        this.target.removeInput(this.connection);
        //diagram.removeChild(connection);
    }

    public boolean canExecute() {
        if (source.equals(target))
            return false;
        // Check for existence of connection already
        List connections = this.source.getOutgoingConnections();
        for (int i = 0; i < connections.size(); i++) {
            if (((ConnectionModel) connections.get(i)).getTarget().equals(target))
                return false;
        }
        return true;
    }
}