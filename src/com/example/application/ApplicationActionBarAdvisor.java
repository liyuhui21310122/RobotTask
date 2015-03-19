package com.example.application;


import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import com.example.ui.actions.NewDiagramAction;
import com.example.ui.actions.NewFolderAction;
import com.example.ui.actions.NewProjectAction;
import com.example.ui.actions.OpenFileAction;
import com.example.ui.actions.ViewAction;


/*
import ttcn3.gft.editor.action.WorkbenchFilterAction;
import ttcn3.gft.editor.action.WorkbenchPrintAction;
import ttcn3.gft.editor.preference.PreferenceAction;
import ttcn3.gft.editor.ui.actions.ExportAction;
import ttcn3.gft.editor.ui.actions.ExportGFTAction;
import ttcn3.gft.editor.ui.actions.ImportAction;
import ttcn3.gft.editor.ui.actions.NewDiagramAction;
import ttcn3.gft.editor.ui.actions.NewGFTModuleAction;
import ttcn3.gft.editor.ui.actions.NewModuleAction;
import ttcn3.gft.editor.ui.actions.NewProjectAction;
import ttcn3.gft.editor.ui.actions.OpenFileAction;
import ttcn3.gft.editor.ui.actions.ViewAction;
import ttcn3.gft.editor.ui.actions.WorkbenchCompileAction;
import ttcn3.gft.editor.ui.actions.WorkbenchGenerateAction;
*/
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	//file actions
	private NewDiagramAction newFileAction;
	private NewProjectAction newProjectAction;
	private NewFolderAction newFolderAction;
	
	private OpenFileAction openFileAction;
	private IWorkbenchAction closeFileAction;
	private IWorkbenchAction closeAllAction;
	
    private IWorkbenchAction saveAction;
    private IWorkbenchAction saveasAction;
    private IWorkbenchAction saveAllAction;
    
    private IWorkbenchAction exitAction;
    
    //edit actions
    private IWorkbenchAction undoAction;
    private IWorkbenchAction redoAction;
    //private IWorkbenchAction cutAction;
    //private IWorkbenchAction copyAction;
    //private IWorkbenchAction pasteAction;
    private IWorkbenchAction deleteAction;
    private IWorkbenchAction selectAllAction;
    
    //view actions
    private ViewAction propertyAction;
    private ViewAction filesAction;
    private ViewAction outlineAction;
    //private ViewAction corelangAction;
    //private ViewAction tdItemAction;
    private ViewAction problemAction;
    
    //about action
    private IWorkbenchAction aboutAction; 
    

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();
	    IActionSetDescriptor[] actionSets = reg.getActionSets();
	    
	    String[] actionSetId =new String[]
	                            { "org.eclipse.ui.edit.text.actionSet.navigation",
	    		                  "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo",
	    		                  "org.eclipse.ui.edit.text.actionSet.annotationNavigation"};
        
	  
	    for (int i = 0; i <actionSets.length; i++)
	    {
	        if ((!actionSets[i].getId().equals(actionSetId[0]))&&
	        		(!actionSets[i].getId().equals(actionSetId[1]))&&
	        		(!actionSets[i].getId().equals(actionSetId[2])))
	            continue;
	            IExtension ext = actionSets[i].getConfigurationElement()
	                                   .getDeclaringExtension();
	            reg.removeExtension(ext, new Object[] { actionSets[i] });
	    }	        
    }
    
    

	protected void makeActions(IWorkbenchWindow window) {
    	//create file actions
    	newFileAction=new NewDiagramAction(window);
    	register(newFileAction);
    	newProjectAction=new NewProjectAction(window);
    	register(newProjectAction);
    	newFolderAction=new NewFolderAction(window);
    	register(newFolderAction);
    	openFileAction=new OpenFileAction(window);
    	register(openFileAction);
    	
    	closeFileAction=ActionFactory.CLOSE.create(window);
    	register(closeFileAction);
    	closeAllAction=ActionFactory.CLOSE_ALL.create(window);
    	register(closeAllAction);	
   
    	saveAction=ActionFactory.SAVE.create(window);
    	register(saveAction);
    	saveasAction=ActionFactory.SAVE_AS.create(window);
    	register(saveasAction);
    	saveAllAction=ActionFactory.SAVE_ALL.create(window);
    	register(saveAllAction);
    	
    	exitAction=ActionFactory.QUIT.create(window);
    	register(exitAction);
    	
    	//create edit actions
    	undoAction=ActionFactory.UNDO.create(window);
    	register(undoAction);
    	redoAction=ActionFactory.REDO.create(window);
    	register(redoAction);
    	//cutAction=ActionFactory.CUT.create(window);
    	//register(cutAction);
    	//copyAction= ActionFactory.COPY.create(window);
    	//register(copyAction);
    	//pasteAction=ActionFactory.PASTE.create(window);
    	//register(pasteAction);
    	deleteAction=ActionFactory.DELETE.create(window);
    	register(deleteAction);
    	selectAllAction=ActionFactory.SELECT_ALL.create(window);
    	register(selectAllAction);
    	
    	//create view actions
    	propertyAction=new ViewAction(window,ViewAction.VPROPERTY);
    	register(propertyAction);
    	outlineAction=new ViewAction(window,ViewAction.VEOUTLINE);
    	register(outlineAction);
    	filesAction=new ViewAction(window,ViewAction.VFOUTLINE);
    	register(filesAction);
    	problemAction = new ViewAction(window,ViewAction.VPROBLEM);
    	register(problemAction);
    	
    	//create help actions
    	aboutAction = ActionFactory.ABOUT.create(window);
    	register(aboutAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	//new menu
    	MenuManager newMenu=new MenuManager("&新建","新建");
    	newMenu.add(newFileAction);
    	newMenu.add(newFolderAction);
    	newMenu.add(newProjectAction);
    	
    	//file menu
    	MenuManager fileMenu=new MenuManager("&文件","文件");
    	fileMenu.add(newMenu);
    	fileMenu.add(openFileAction);
    	fileMenu.add(new Separator());
    	
    	fileMenu.add(closeFileAction);
    	fileMenu.add(closeAllAction);
    	fileMenu.add(new Separator());
    	
    	fileMenu.add(saveAction);
    	fileMenu.add(saveasAction);
    	fileMenu.add(saveAllAction);
    	fileMenu.add(new Separator());
    	
    	fileMenu.add(exitAction);
    	
    	//edit menu
    	MenuManager editMenu=new MenuManager("&编辑",IWorkbenchActionConstants.M_EDIT);
    	editMenu.add(undoAction);
    	editMenu.add(redoAction);
    	editMenu.add(new Separator());
    	
    	//editMenu.add(cutAction);
    	//editMenu.add(copyAction);
    	//editMenu.add(pasteAction);
    	//editMenu.add(new Separator());
  
    	editMenu.add(deleteAction);
    	editMenu.add(selectAllAction);
    	
    
    	//view menu
    	MenuManager viewMenu=new MenuManager("&显示视图","视图"); 
    	viewMenu.add(filesAction);
    	viewMenu.add(propertyAction);
    	viewMenu.add(outlineAction);
    	viewMenu.add(problemAction);
    	 
    	MenuManager windowMenu=new MenuManager("&窗口",IWorkbenchActionConstants.M_WINDOW);
    	windowMenu.add(viewMenu);
    	//windowMenu.add(preferenceAction);
    	
    	MenuManager helpMenu=new MenuManager("&帮助",IWorkbenchActionConstants.M_HELP);
    	helpMenu.add(aboutAction);
    	
    	menuBar.add(fileMenu);
    	menuBar.add(editMenu);
    	menuBar.add(windowMenu);
    	menuBar.add(helpMenu);
    }

    //工具栏
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		IToolBarManager toolbar=new ToolBarManager(SWT.FLAT|SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolbar,"打开文件"));
		toolbar.add(newFileAction);
		toolbar.add(newFolderAction);
		toolbar.add(newProjectAction);
		toolbar.add(openFileAction);
		
	}   
}
