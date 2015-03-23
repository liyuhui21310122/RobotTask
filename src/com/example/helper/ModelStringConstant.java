package com.example.helper;

import org.eclipse.draw2d.geometry.Dimension;

public interface ModelStringConstant {
    /*property change*/
	public static final String P_NODE_MODELNAME = "p_node_modelName";
	public static final String P_NODE_TEXT = "p_node_text";
	public static final String P_NODE_CONSTRAINT = "p_node_constraint";
	public static final String P_NODE_SIZE = "p_node_size";
	public static final String P_NODE_LOCATION = "p_node_location";
	public static final String P_NODE_OUTPUTS = "p_node_outputs";
	public static final String P_NODE_INPUTS = "p_node_inputs";
	
	public static final String P_CONTAINER_CHILDREN_ADD = "p_constraint_children_add";
	public static final String P_CONTAINER_NONCONTAINER_ADD = "p_container_nonContainer_add";
	public static final String P_CONTAINER_CONTAINER_ADD = "p_container_container_add";
	public static final String P_CONTAINER_CONNECTION_ADD = "p_container_connection_add";
	public static final String P_CONTAINER_REFRESH = "p_container_refresh";
	public static final String P_CONTAINER_COLLAPSED = "p_container_refresh";
	
	public static final String P_CONTAINER_CHILDREN_DELETE = "p_container_children_delete";
	public static final String P_CONTAINER_NONCONTAINER_DELETE= "p_container_nonContainer_delete";
	public static final String P_CONTAINER_CONTAINER_DELETE = "p_container_container_delete";
	public static final String P_CONTAINER_CONNECTION_DELETE = "p_container_connection_delete";
	
	public static final String P_CONTAINER_CONSTRAINT = "p_container_constraint";
	public static final String P_CONTAINER_SIZE = "p_container_size";
	
	public static final String P_DIAGRAM_CHILDREN_ADD = "p_diagram_children_add";
	public static final String P_DIAGRAM_NONCONTAINER_ADD = "p_diagram_nonContainer_add";
	public static final String P_DIAGRAM_CONTAINER_ADD = "p_diagram_container_add";
	public static final String P_DIAGRAM_CONNECTION_ADD = "p_diagram_connection_add";
	public static final String P_DIAGRAM_REFRESH = "p_diagram_refresh";
	
	public static final String P_DIAGRAM_NONCONTAINER_DELETE = "p_diagram_nonContainer_delete";
	public static final String P_DIAGRAM_CONTAINER_DELETE = "p_diagram_container_delete";
	public static final String P_DIAGRAM_CHILDREN_DELETE = "p_diagram_children_delete";
	public static final String P_DIAGRAM_CONNECTION_DELETE = "p_diagram_connection_delete";
	
	
	public static final String P_COORTYPE = "p_coorType";
	public static final String P_DISPLACEMENT = "p_displacement";
	public static final String P_VELOCITY = "p_velocity";
	public static final String P_COUNT = "count";
	public static final String P_VALUE = "value";
	
	public static final String P_CX = "p_cx";
	public static final String P_CY = "p_cy";
	public static final String P_CZ = "p_cz";
	public static final String P_DA = "p_da";
	public static final String P_DB = "p_db";
	public static final String P_DC = "p_dc";
	public static final String P_R = "p_r";
	public static final String P_RAD = "p_rad";
	
	public static final String P_INDEXVALUE = "indexValue";
	
	/*label ID*/
	public static final String LABEL_LINEARMOTION_MODEL = "直线运动";
	public static final String ID_LINEARMOTION_MODEL = "id_linearMotion_model";
	
	public static final String LABEL_LOOP_MODEL = "设定次数循环";
	public static final String ID_LOOP_MODEL ="id_loop_model";

	public static final String LABEL_START_MODEL = "初始化";
	public static final String ID_START_MODEL ="id_start_model";

	public static final String LABEL_END_MODEL = "结束";
	public static final String ID_END_MODEL ="id_end_model";
	
	public static final String LABEL_CIRCLEMOTION_MODEL = "圆周运动";
	public static final String ID_CIRCLEMOTION_MODEL ="id_circleMotion_model";
	
	
	public static final String LABEL_CONDITION_MODEL = "条件判断";
	public static final String ID_CONDITION_MODEL ="id_condition_model";
	
	public static final String LABEL_ROTATE_MODEL = "轴旋转运动";
	public static final String ID_ROTATE_MODEL ="id_rotate_model";
	
	public static final String LABEL_WAIT_MODEL = "等待";
	public static final String ID_WAIT_MODEL ="id_wait_model";
	
	public static final String LABEL_OUTPUT_MODEL = "输出信号";
	public static final String ID_OUTPUT_MODEL ="id_output_model";
	
	public static final String LABEL_INPUT_MODEL = "等待触发信号";
	public static final String ID_INPUT_MODEL ="id_input_model";
	
	public static final String LABEL_SHIFT_MODEL = "轴平移运动";
	public static final String ID_SHIFT_MODEL ="id_shift_model";
	
	public static final String LABEL_WHILE_MODEL = "循环";
	public static final String ID_WHILE_MODEL ="id_while_model";
	
	
	public static final String P_MESSAGE = "message";
	public static final String P_NUMBER = "number";
	public static final String P_SHIFT = "shift";
	public static final String P_ROTATE ="rotate";
	public static final String P_BEND_POINT = "bendpoint";
	public static final String P_INDEX = "index";
	public static final String P_CONDITION = "condition";
	public static final String P_TIME = "time";
}
