package com.example.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;


public class OrphanChildCommand extends Command {
	private NodeModel nodeModel;
	private DiagramModel diagramModel;
    private List sourceConnections = new ArrayList();
    private List targetConnections = new ArrayList();
    
	public void setDiagram(DiagramModel diagramModel) {
        this.diagramModel = diagramModel;
    }

    public void setNode(NodeModel nodeModel) {
        this.nodeModel = nodeModel;
    }
    
    public void execute() {
    	//删除模型的连线
    	sourceConnections.addAll(nodeModel.getIncomingConnections());
    	targetConnections.addAll(nodeModel.getOutgoingConnections());
    	
    	for(int i=0;i<sourceConnections.size();i++){
    		ConnectionModel connection = (ConnectionModel) sourceConnections.get(i);
    		connection.removeSource();
    		connection.removeTarget();
    	}
    	
    	for(int i=0;i<targetConnections.size();i++){
    		ConnectionModel connection = (ConnectionModel) targetConnections.get(i);
    		connection.removeSource();
    		connection.removeTarget();
    	}
    	
    	//删除模型
    	if(nodeModel instanceof ContainerModel){
    		diagramModel.removeContainer(nodeModel);
    		
    	}
    	diagramModel.removeNonContainer(nodeModel);
    	diagramModel.removeChild(nodeModel);
    }
    
    public void undo() {
    	if(nodeModel instanceof ContainerModel){
    		diagramModel.addContainer(nodeModel);
    	}
    	diagramModel.addNonContainer(nodeModel);
    	diagramModel.addChild(nodeModel);
    	
    	//undo the connection
    	for(int i=0;i<sourceConnections.size();i++){
    		ConnectionModel connection = (ConnectionModel) sourceConnections.get(i);
    		connection.addSource();
    		connection.addTarget();
    	}
    	
    	for(int i=0;i<targetConnections.size();i++){
    		ConnectionModel connection = (ConnectionModel) targetConnections.get(i);
    		connection.addSource();
    		connection.addTarget();
    	}
    	
    	sourceConnections.clear();
    	targetConnections.clear();
    }
    
    public void redo(){
    	execute();
    }
}
