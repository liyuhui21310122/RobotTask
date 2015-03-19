package com.example.command;

import org.eclipse.gef.commands.Command;
import com.example.model.ConnectionModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

public class DeleteConnectionCommand extends Command {
	//protected  DiagramModel diagram;
    NodeModel source;
    NodeModel target;
    ConnectionModel connection;

    //Setters
    public void setConnection(ConnectionModel connection) {
        this.connection = connection;
    }

    public void setSource(NodeModel source) {
        this.source = source;
    }

    public void setTarget(NodeModel target) {
        this.target = target;
    }
    /*
    public void setDiagramModel(DiagramModel diagram){
		this.diagram = diagram;
	}
    */
    public void execute() {
        source.removeOutput(connection);
        target.removeInput(connection);
        connection.setSource(null);
        connection.setTarget(null);
    	
       
    }

    public String getLabel() {
        return "Delete Connection";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        connection.setSource(source);
        connection.setTarget(target);
        source.addOutput(connection);
        target.addInput(connection);
    	
       
    }
}