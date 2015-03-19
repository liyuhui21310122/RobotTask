package com.example.command;

import java.util.List;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;

public class MoveNodeCommand extends Command {
	private DiagramModel diagram;
	private NodeModel model;		
	private Point oldLocation, newLocation;
	//private int oldIndex;

	public void execute(){
	
		oldLocation = model.getLocation();		
		diagram.removeChild(model);
		//ÐÂ¼ÓµÄ
		//diagram.removeXmlChild(model.getXMLModel());
		
		model.setLocation(newLocation);
		diagram.addChild(model);
	
		//diagram.fireToRefresh(model);
	}
	
	public void setLocation(Object location){
		this.newLocation = (Point)location;
	}
	
	public void setNodeModel(Object model){
		this.model = (NodeModel)model;		
	}
	
	public void setDiagram(Object diagram){
		this.diagram = (DiagramModel)diagram;
	}
	
	public void undo(){		
		diagram.removeChild(model);
		model.setLocation(oldLocation);
		diagram.addChild(model);
	}

}
