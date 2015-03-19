package com.example.application;

import java.net.URL;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.model.WorkbenchAdapterBuilder;
import org.osgi.framework.Bundle;




public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
    //
	private static final String PERSPECTIVE_ID = "robottaskViewer.perspective";
	

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        //×¢²áworkbench±äÁ¿
    	WorkbenchAdapterBuilder.registerAdapters();
    	
    	return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	@Override
	public void postStartup() {
		// TODO Auto-generated method stub
		super.postStartup();
			
	}

	@Override
	public void preStartup() {
		// TODO Auto-generated method stub
		super.preStartup();
		//WorkbenchAdapterBuilder.registerAdapters();
	}
	
	@Override
	 public IAdaptable getDefaultPageInput() {
	    return ResourcesPlugin.getWorkspace().getRoot();
	 }

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		// TODO Auto-generated method stub
		super.initialize(configurer);
		//ÉùÃ÷workbenchÍ¼Æ¬
		declareDefaultImages((IWorkbenchConfigurer) configurer);
		configurer.setSaveAndRestore(true);
	   // PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP, true);
	
	}
	
	private  void declareDefaultImages(IWorkbenchConfigurer configurer) {
		final String iconsPath = "icons/full/obj16/";
		Bundle ide = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);
		
		/* images for Project Explorer */
		declareWorkbenchImage(configurer, ide, IDE.SharedImages.IMG_OBJ_PROJECT, iconsPath + "prj_obj.gif");
		declareWorkbenchImage(configurer, ide, IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, iconsPath + "cprj_obj.gif");
		/* images for the problems browser */
		declareWorkbenchImage(configurer, ide, IDEInternalWorkbenchImages.IMG_OBJS_ERROR_PATH, iconsPath + "error_tsk.gif");
		declareWorkbenchImage(configurer, ide, IDEInternalWorkbenchImages.IMG_OBJS_WARNING_PATH, iconsPath + "warn_tsk.gif");
		declareWorkbenchImage(configurer, ide, IDEInternalWorkbenchImages.IMG_OBJS_INFO_PATH, iconsPath + "info_tsk.gif");
	}


	private  void declareWorkbenchImage(IWorkbenchConfigurer configurer, Bundle bundle, String symbolicName, String path) {
	    URL url = bundle.getEntry(path);
	    ImageDescriptor desc = ImageDescriptor.createFromURL(url);
	    configurer.declareImage(symbolicName, desc, true);
	}
	
	
}
