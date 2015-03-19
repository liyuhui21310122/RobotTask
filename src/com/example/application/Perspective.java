package com.example.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.example.ui.helper.GEFXMLView;


/*
public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		final String editorArea=layout.getEditorArea();
		
		IFolderLayout leftTopFolder=layout.createFolder("leftTopOutline", IPageLayout.LEFT, 0.35f, editorArea);
		//leftTopFolder.addView(GEFXMLView.VIEW_PROJECT);
		leftTopFolder.addView("org.eclipse.ui.views.ResourceNavigator");
		leftTopFolder.addView(GEFXMLView.VIEW_EOUTLINE);
		
		
		
		IFolderLayout midBottomFolder=layout.createFolder("BottomProperty", IPageLayout.BOTTOM, 0.35f,editorArea );
		midBottomFolder.addView(GEFXMLView.VIEW_DPROPERTY);
		//midBottomFolder.addView(GEFXMLView.VIEW_CORELANG);
		//midBottomFolder.addView(GEFXMLView.VIEW_TODOITEM);
		midBottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		
		
		//layout.addFastView(GEFXMLView.VIEW_DEFINE);
		
		layout.setEditorAreaVisible(true);
		}
}
*/


import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		final String navigator = "org.eclipse.ui.views.ResourceNavigator";
		final String properties = "org.eclipse.ui.views.PropertySheet";
		final String outline = "org.eclipse.ui.views.ContentOutline";
		final String problem = "org.eclipse.ui.views.ProblemView";

		final String editor = layout.getEditorArea();

		layout.addView(navigator, IPageLayout.LEFT, 0.6f, editor);
		IFolderLayout folder1 = layout.createFolder("left-bottom", IPageLayout.BOTTOM, (float) 0.5, navigator);
		folder1.addView(properties);
		folder1.addView(outline);
		folder1.addView(problem);
		layout.setEditorAreaVisible(true);
		
		
	}

}
