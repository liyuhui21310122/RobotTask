package com.example.ui.helper;

import org.eclipse.ui.IPageLayout;

/**
 * class to get viewer id
 * 
 * @author user
 * 
 */
public interface RobotTaskView {
	
	//view ID
	public final static String VIEW_EOUTLINE = "org.eclipse.ui.views.ContentOutline";
	public final static String VIEW_EDITOR = "robottaskViewer.editor";
	//public final static String VIEW_CORELANG = "TTCNViewer.corelangviewer";
	//public final static String VIEW_TODOITEM = "TTCNViewer.todoitemviewer";
	//public final static String VIEW_DEFINE = "TTCNViewer.moduledefineviewer";
	public final static String VIEW_PROJECT = "org.eclipse.ui.views.ResourceNavigator";
	public final static String VIEW_DPROPERTY = "org.eclipse.ui.views.PropertySheet";
	public final static String VIEW_PROBLEM = IPageLayout.ID_PROBLEM_VIEW;

	
	//wizard page ID
	public final static String NAME_NEWWIZARDPAGEA = "newwizardpage";
	public final static String NAME_TESTCASEPAGEA = "testcasepagea";
	public final static String NAME_ALTSTEPPAGEA = "altsteppagea";
	public final static String NAME_FUNTIONPAGE = "funcitonpage";
	public final static String NAME_PROJECTPAGE = "newprojectpage";
	public final static String NAME_GFTMODULE = "newgftmodule";
	public final static String NANE_DEFINEPAGE = "newdefinepage";

}
