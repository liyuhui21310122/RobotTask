package com.example.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import com.example.model.ConnectionModel;
import com.example.model.ContainerModel;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;
import com.example.model.node.CircleMotionModel;
import com.example.model.node.ConditionModel;
import com.example.model.node.EndModel;
import com.example.model.node.InputModel;
import com.example.model.node.LinearMotionModel;
import com.example.model.node.LoopModel;
import com.example.model.node.OutputModel;
import com.example.model.node.RotateModel;
import com.example.model.node.ShiftModel;
import com.example.model.node.StartModel;
import com.example.model.node.WaitModel;


public class CustomModelFactory {
	public static final NodeModel getNodeModel(String modelName){	
		Assert.isNotNull(modelName);		

		NodeModel model = null;
		if(modelName.equals(ModelStringConstant.LABEL_LINEARMOTION_MODEL)){
			model = new LinearMotionModel();			
		}
		else if(modelName.equals(ModelStringConstant.LABEL_LOOP_MODEL)){
			model = new LoopModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_START_MODEL)){
			model = new StartModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_END_MODEL)){
			model = new EndModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_CONDITION_MODEL)){
			model = new ConditionModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_CIRCLEMOTION_MODEL)){
			model = new CircleMotionModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_SHIFT_MODEL)){
			model = new ShiftModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_ROTATE_MODEL)){
			model = new RotateModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_OUTPUT_MODEL)){
			model = new OutputModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_INPUT_MODEL)){
			model = new InputModel();
		}
		else if(modelName.equals(ModelStringConstant.LABEL_WAIT_MODEL)){
			model = new WaitModel();
		}
		return model;
	}
	
	//如果连线是在diagram上********************************************************************************
	public static final  ArrayList<ConnectionModel>  getConnectionModel(DiagramModel diagram,NodeModel node){	
		
		Assert.isNotNull(node);			
		
		NodeModel snode,source = null;
		String[] a = node.getFromID(node.getFromID());
		 List<ConnectionModel> c = new  ArrayList<ConnectionModel> ();
		//ConnectionModel connection = null;
		//寻找源点
		for(int i=0;i<diagram.getChildren().size();i++){
			snode = (NodeModel) diagram.getChildren().get(i);
			if (snode.equals(node)){
			}
			
			else {
				//
				for(int j=0;j<a.length;j++){
					
					if((snode.getID()).equals(a[j])){
						source = snode;  
						ConnectionModel con= new ConnectionModel(source,node);
						c.add(con);
						break;
			        }
				
				}	
				//
			}
		}
		return (ArrayList<ConnectionModel>) c;
		//return new ConnectionModel(source,node);
	}
	//如果连线是在diagram上********************************************************************************
	
	
	
	//container上的连线
	public static final ConnectionModel getConnectionModel(ContainerModel container,
			String sourceID, String targetID){	
		
		Assert.isNotNull(sourceID);		
		Assert.isNotNull(targetID);	
		NodeModel node;
		ConnectionModel connection = null;
		for(int i=0;i<container.getChildren().size();i++){
			node = (NodeModel) container.getChildren().get(i);
			if((node.getID()).equals(sourceID)){
				node.addOutput(connection);
			}
			else if((node.getID()).equals(targetID)){
				node.addInput(connection);
			}
			else{
				//doNothing
			}
		}
		return connection;
	}
	
}

