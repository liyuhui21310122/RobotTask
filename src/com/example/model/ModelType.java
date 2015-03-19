package com.example.model;

import com.example.model.AbstractModel;
import com.example.model.ConnectionModel;
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


public class ModelType {
    //´íÎó
	public final static String HEAD_ERROR="ERROR_HEAD";
    
	
	
	//XML head
	public final static String ELE_ROOT="Root";
	public final static String ELE_DIAGRAM="Diagram";
    //NonContainer model XML head
	public final static String HEAD_LINEARMOTION="LinearMotion";
	public final static String HEAD_CIRCLEMOTION = "CircleMotion";
	public final static String HEAD_CONDITION= "Condition";
	public final static String HEAD_LOOP = "Loop";
    public final static String HEAD_END= "End";
    public final static String HEAD_START = "Start";
    public final static String HEAD_OUTPUT= "output";
    public final static String HEAD_INPUT = "Input";
    //container model XML head
    public final static String HEAD_ROTATE = "Rotate";
    public final static String HEAD_SHIFT = "Shift";
   
   
    public final static String HEAD_WAIT = "Wait";
   
    //connection model XML head
    public final static String HEAD_CONNECTION = "Connection";
    
    
    
    //node model XML attribute
    public final static String ATR_ID = "ID";
    public final static String ATR_MODELNAME = "ModelName";
    public static final String ATR_LOCATION_X = "LocationX";
    public static final String ATR_LOCATION_Y = "LocationY";
    public final static String ATR_NAME = "Name";
    public static final String ATR_FROMID = "FromID";
    //nonContainer model XML attribute
    public static final String ATR_VELOCITY = "Velocity";
    public static final String ATR_COORTYPE = "CoorType";
    public static final String ATR_DISPLACEMENT = "Displacement";
    public static final String ATR_VALUE = "Value";
	//container model XML attribute
	public final static String ATR_COUNT="Count";
	//connection model XML attribute
	public final static String ATR_SOURCE="Source";
	public final static String ATR_TARGET="Target";
	public static final String ATR_TARGETNAME = "TargetName";
	public final static String ATR_SOURCENAME="SourceName";
 
	public static final String ATR_FINISH = "Finish";

	public static final String ATR_SOURCEID = "SourceID";
	public static final String ATR_TARGETID = "TargetID";



	public static final String ATR_CX = "CX";
	public static final String ATR_CY = "CY";
	public static final String ATR_CZ = "CZ";
	public static final String ATR_DA = "DA";
	public static final String ATR_DB = "DB";
	public static final String ATR_DC = "DC";
	public static final String ATR_R = "R";
	public static final String ATR_RAD = "Rad";



	public static final String ATR_VALID = "valid";



	public static final String ATR_MESSAGE = "message";



	public static final String ATR_NUMBER = "number";
	public static final String ATR_SHIFT = "shift";
	public static final String ATR_ROTATE = "rotate";

	public static final String ATR_INDEX = "index";

	public static final String ATR_TIME = "time";



	public static final String ATR_WIDTH = "width";



	public static final String ATR_HEIGHT = "height";

	

	public static String getModelXMLHead(Object model){
		if(model instanceof LoopModel)
			return HEAD_LOOP;
		else if(model instanceof LinearMotionModel)
			return HEAD_LINEARMOTION;
		else if(model instanceof ConnectionModel)
			return HEAD_CONNECTION;
		else if(model instanceof ConditionModel)
			return HEAD_CONDITION;
		else if(model instanceof StartModel)
			return HEAD_START;
		else if(model instanceof EndModel)
			return HEAD_END;
		else if(model instanceof CircleMotionModel)
			return HEAD_CIRCLEMOTION;
		else if(model instanceof RotateModel)
			return HEAD_ROTATE;
		else if(model instanceof ShiftModel)
			return HEAD_SHIFT;
		else if(model instanceof WaitModel)
			return HEAD_WAIT;
		else if(model instanceof OutputModel)
			return HEAD_OUTPUT;
		else if(model instanceof InputModel)
			return HEAD_INPUT;
		return HEAD_ERROR;	
	}
	
	

	



	

    /*
	public static String getModelXMLHead(ConnectionModel model){
		return HEAD_CONNECTION;	
	}
	
	public static String getModelXMLHead(NodeModel model){
		if(model instanceof NonContainerModel)
			return getModelXMLHead(model);
		else if(model instanceof ContainerModel)
			return getModelXMLHead(model); 
		return HEAD_ERROR;	
	}
    
    public static String getModelXMLHead(NonContainerModel model){
    	if(model instanceof LinearMotionModel)
    		return HEAD_LINEARMOTION;
        return HEAD_ERROR;
    }
    
    public static String getModelXMLHead(ContainerModel model){
        if(model instanceof LoopModel)
        	return HEAD_LOOP;
        return HEAD_ERROR;
    }
    */
	
	
}
