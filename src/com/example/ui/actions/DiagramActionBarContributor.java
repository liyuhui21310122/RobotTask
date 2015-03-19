/*
 * Created on 2005-1-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.example.ui.actions;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;


import com.example.ui.editors.DiagramEditor;
import com.example.ui.helper.IImageKey;


/**
 * @author zhanghao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DiagramActionBarContributor extends ActionBarContributor {


    protected void buildActions() {
    	RetargetAction retargetAction = new RetargetAction(ActionFactory.SAVE
				.getId(), "保存");
		retargetAction.setImageDescriptor(IImageKey
				.getImageDescriptor(IImageKey.KEY_COPY));
		addRetargetAction(retargetAction);
		
    	
        /*
        retargetAction = new RetargetAction(ActionFactory.CUT.getId(), "CUT");
		retargetAction.setImageDescriptor(IImageKey
				.getImageDescriptor(IImageKey.KEY_CUT));
		addRetargetAction(retargetAction);
		
        retargetAction = new RetargetAction(ActionFactory.COPY.getId(), "COPY");
		retargetAction.setImageDescriptor(IImageKey
				.getImageDescriptor(IImageKey.KEY_COPY));
		addRetargetAction(retargetAction);

		retargetAction = new RetargetAction(ActionFactory.PASTE.getId(),
				"PASTE");
		retargetAction.setImageDescriptor(IImageKey
				.getImageDescriptor(IImageKey.KEY_PASTE));
		*/	
		addRetargetAction(new DeleteRetargetAction());
		retargetAction.setImageDescriptor(IImageKey
				.getImageDescriptor(IImageKey.KEY_DELETE));
		addRetargetAction(retargetAction);
		
		addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        /*
		retargetAction = new RetargetAction(ActionFactory.SELECT_ALL.getId(), "SELECT ALL");
		addRetargetAction(retargetAction);
		*/
        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());

        //对齐方式  
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));  
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));  
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));  
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));  
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));  
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));  
        addRetargetAction(new MatchWidthRetargetAction());  
        addRetargetAction(new MatchHeightRetargetAction());  
    }
    protected void declareGlobalActionKeys() {
        // TODO Auto-generated method stub

    }
    public void contributeToToolBar(IToolBarManager toolBarManager) {
		//toolBarManager.add(getAction(ActionFactory.SAVE.getId()));
		//toolBarManager.add(new Separator());
		
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
        toolBarManager.add(getAction(ActionFactory.REDO.getId()));
        toolBarManager.add(new Separator());
        /*
		toolBarManager.add(getAction(ActionFactory.CUT.getId()));
		toolBarManager.add(getAction(ActionFactory.COPY.getId()));
		toolBarManager.add(getAction(ActionFactory.PASTE.getId()));
		toolBarManager.add(new Separator());
		*/
        toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
        //toolBarManager.add(getAction(ActionFactory.SELECT_ALL.getId()));
        toolBarManager.add(new Separator());  
        
        //水平方向对齐按钮  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_LEFT));  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_CENTER));  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_RIGHT));  
        toolBarManager.add(new Separator());  
        
        //垂直方向对齐按钮  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_TOP));  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_MIDDLE));  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_BOTTOM));  
        toolBarManager.add(new Separator());
        
        //匹配宽度  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.MATCH_HEIGHT));  
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.MATCH_WIDTH));  
        toolBarManager.add(new Separator());
        
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
        toolBarManager.add(new ZoomComboContributionItem(getPage()));
        
        
    }
    
    @Override
	public void setActiveEditor(IEditorPart editor) {
		// TODO Auto-generated method stub
		super.setActiveEditor(editor);

		if (editor instanceof DiagramEditor) {
			getActionBars().getStatusLineManager().setMessage(
					((DiagramEditor) editor).getPartName());
		}
	}
}
