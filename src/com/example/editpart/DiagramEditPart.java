package com.example.editpart;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.ide.ResourceUtil;

import com.example.helper.ConstantResourceFactory;
import com.example.helper.CustomConstraintFactory;
import com.example.helper.ModelStringConstant;
import com.example.model.DiagramModel;
import com.example.model.NodeModel;
import com.example.policy.CustomDiagramEditPolicy;
import com.example.policy.CustomDiagramLayoutEditPolicy;
import com.example.ui.jiaohu.MyClient;

public class DiagramEditPart extends RootEditPart {
	//***********************************����ͼ�β���*****************************************************

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		
		/*DiagramFigure f = new DiagramFigure();
		return f;*/
		
		Figure figure = new FreeformLayer();
		figure.setForegroundColor(ConstantResourceFactory.getDiagramForeColor());
		figure.setBackgroundColor(ConstantResourceFactory.getDiagramBackColor());
	    figure.setLayoutManager(new FreeformLayout());
	    return figure;
        
	}
	/*
	protected DiagramFigure getDiagramFigure(){
		return (DiagramFigure)getFigure();
	}*/
	//***********************************����ͼ�β���*****************************************************
	
	
	
	//***********************************EditPolicy����*****************************************************
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		//������Ӳ�������Ӳ����Ǹ�ģ�ʹ�����layoutPolicy��ɾ����������ģ�ʹ�����componentPolicy��
		installEditPolicy(EditPolicy.LAYOUT_ROLE,new CustomDiagramLayoutEditPolicy());
		//installEditPolicy(EditPolicy.LAYOUT_ROLE,new CustomXYLayoutEditPolicy());
		//����������ǣ�
		installEditPolicy(EditPolicy.CONTAINER_ROLE,new CustomDiagramEditPolicy());
	}
	//***********************************EditPolicy����*****************************************************
	
	
	
	//***********************************property change*****************************************************
    public void propertyChange(PropertyChangeEvent event){	
		//�����diagram����ӻ������ģ��
		if(event.getPropertyName().equals(ModelStringConstant.P_DIAGRAM_CHILDREN_ADD)){
			refreshChildren();
//			
//	    	MyClient myclient = new MyClient();
//			myclient.send("not saved");
			
		}
		else if(event.getPropertyName().equals(ModelStringConstant.P_DIAGRAM_CHILDREN_DELETE)){
			refreshChildren();
            
//	    	MyClient myclient = new MyClient();
//			myclient.send("not saved");
		}
	}
    /*
    protected void refreshContainers(int sel){		
		CustomConstraintFactory.refreshContainers(sel,getDiagramModel().getContainers());
	}	
	*/
	protected DiagramModel getDiagramModel(){
		return (DiagramModel)getModel();
	}
	//***********************************property change*****************************************************
	
	
	
	//*****************************************��Ϊ��ģ��*****************************************************
	protected List<Object> getModelChildren() {
        return ((DiagramModel) this.getModel()).getChildren();
    }
	//*****************************************��Ϊ��ģ��*****************************************************
	
}
