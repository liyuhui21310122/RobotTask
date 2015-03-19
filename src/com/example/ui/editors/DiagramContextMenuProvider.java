package com.example.ui.editors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.actions.ActionFactory;

public class DiagramContextMenuProvider extends ContextMenuProvider {
	
	private ActionRegistry actionRegistry;
	/*private IMenuListener listener =
		new IMenuListener(){

		@Override
		public void menuAboutToShow(IMenuManager manager) {
			Control canvas = getViewer().getControl();
			Point cursor_location = canvas.getDisplay().getCursorLocation();
			Point relative_canvas = canvas.toControl(cursor_location);
			setItemLocation(manager,relative_canvas);			
		}

		private	void setItemLocation(IMenuManager manager, Point relative_canvas) {
			IContributionItem[] items = manager.getItems();
			for (int i =0; i < items.length; i++)
			{
		    	IContributionItem item = items[i];		    	
		    	if (item instanceof ActionContributionItem)
		    	{
		        	IAction host_action = ((ActionContributionItem)item).getAction();
		        	if (host_action instanceof ILocationWare)
		            	((ILocationWare)host_action)
		            		.setLocation(relative_canvas.x, relative_canvas.y);
		        	if(host_action instanceof IOnShowAction)
		        		((IOnShowAction)host_action).refreshOnShow();

		        }
		        else if (item instanceof IMenuManager)
		        {
		            setItemLocation((IMenuManager)item, relative_canvas);
		        }    	
		    	
		    }
		}
		        
	};
    */

	public DiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		// TODO Auto-generated constructor stub
		actionRegistry = registry;
		//addMenuListener(listener);
	}
	 
	/*
	 public void dispose(){
	    removeMenuListener(listener);
	    super.dispose();
	}
	*/
	@Override
	public void buildContextMenu(IMenuManager menu) {
		// TODO Auto-generated method stub
		
		// Add standard action groups to the menu
        GEFActionConstants.addStandardActionGroups(menu);
        // Add actions to the menu
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO,getAction(ActionFactory.UNDO.getId()));
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, getAction(ActionFactory.REDO.getId()));
        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, getAction(ActionFactory.DELETE.getId()));
        /*
        menu.appendToGroup(GEFActionConstants.GROUP_SAVE, getAction(ActionFactory.SAVE.getId()));
        menu.appendToGroup(GEFActionConstants.GROUP_SAVE, getAction(ActionFactory.SAVE_AS.getId()));
        menu.appendToGroup(GEFActionConstants.GROUP_SAVE, getAction(ActionFactory.SAVE_ALL.getId()));
        
        menu.appendToGroup(GEFActionConstants.GROUP_EDIT, getAction(ActionFactory.SELECT_ALL.getId()));
        */
        
        /*
        menu.appendToGroup(GEFActionConstants.GROUP_REST,getAction(IConstants.ACTION_MARK_PRIORITY_HIGH)); 
        menu.appendToGroup(GEFActionConstants.GROUP_REST,getAction(IConstants.ACTION_MARK_PRIORITY_MEDIUM)); 
        menu.appendToGroup(GEFActionConstants.GROUP_REST,getAction(IConstants.ACTION_MARK_PRIORITY_LOW));		
		*/
	}
	
	private IAction getAction(String actionId) {
        return actionRegistry.getAction(actionId);
    }
}
