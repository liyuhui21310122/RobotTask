package com.example.factory;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.IFigure;
import com.example.figure.root.CircleMotionFigure;
import com.example.figure.root.ConditionFigure;
import com.example.figure.root.ContainerFigure;
import com.example.figure.root.EndFigure;
import com.example.figure.root.InputFigure;
import com.example.figure.root.LinearMotionFigure;
import com.example.figure.root.LoopFigure;
import com.example.figure.root.NodeFigure;
import com.example.figure.root.OutputFigure;
import com.example.figure.root.RotateFigure;
import com.example.figure.root.ShiftFigure;
import com.example.figure.root.StartFigure;
import com.example.figure.root.WaitFigure;
import com.example.helper.ModelStringConstant;
import com.example.model.NodeModel;


public class CustomFigureFactory {
	public static final IFigure createFigure(Object model){
		Assert.isNotNull(model);
		Assert.isTrue(model instanceof NodeModel);		
		
		Object id = ((NodeModel)model).getModelID();
		
		if(id.equals(ModelStringConstant.ID_LOOP_MODEL)){
			return new LoopFigure();
			//return new StartFigure();
		}
		else if(id.equals(ModelStringConstant.ID_START_MODEL)){
			return new StartFigure();
		}
		else if(id.equals(ModelStringConstant.ID_END_MODEL)){
			return new EndFigure();
			//return new NodeFigure();
		}
		else if(id.equals(ModelStringConstant.ID_INPUT_MODEL)){
			return new InputFigure();
		}
		else if(id.equals(ModelStringConstant.ID_OUTPUT_MODEL)){
			return new OutputFigure();
		}
		else if(id.equals(ModelStringConstant.ID_CONDITION_MODEL)){
			return new ConditionFigure();
		}
		else if(id.equals(ModelStringConstant.ID_CIRCLEMOTION_MODEL)){
			return new CircleMotionFigure();
		}
		else if(id.equals(ModelStringConstant.ID_SHIFT_MODEL)){
			return new ShiftFigure();
		}
		else if(id.equals(ModelStringConstant.ID_ROTATE_MODEL)){
			return new RotateFigure();
		}
		else if(id.equals(ModelStringConstant.ID_WAIT_MODEL)){
			return new WaitFigure();
		}
		else 
			return new LinearMotionFigure();
		
		
	}
}
