package com.example.application;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;

import org.eclipse.swt.graphics.Point;

import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.example.ui.helper.IImageKey;

/*
import ttcn3.gft.editor.ui.helper.IImageKey;
import ttcn3.gft.editor.ui.projectviewer.FileDecorator;
*/
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}
    //**创造菜单条对象**//
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}
    //窗口初始化
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();

		configurer.setInitialSize(new Point(400, 300));
		configurer.setShowCoolBar(true);//工具栏
		configurer.setShowStatusLine(true);//状态栏
		configurer.setTitle("机器人任务");
		configurer.setShowProgressIndicator(true);//进度条
		//configurer.setShowPerspectiveBar(true);//透视图
		
		//FileDecorator.setEnable(true);

		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		try {
			workspace.build(IncrementalProjectBuilder.FULL_BUILD,
					new NullProgressMonitor());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void postWindowOpen() {
		// TODO Auto-generated method stub
		super.postWindowOpen();
		final IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.getWindow().getShell().setMaximized(true);
		configurer.getWindow().getShell().setImage(
				IImageKey.getImage(IImageKey.KEY_WINDOW));

	}

	@Override
	public void postWindowCreate() {
		// TODO Auto-generated method stub
		super.postWindowCreate();

		// remove unwanted menu items
		IContributionItem[] mItems, mSubItems;
		IMenuManager mm = getWindowConfigurer().getActionBarConfigurer()
				.getMenuManager();
		mItems = mm.getItems();
		for (int i = 0; i < mItems.length; i++) {
			if (mItems[i] instanceof MenuManager) {
				mSubItems = ((MenuManager) mItems[i]).getItems();

				for (int j = 0; j < mSubItems.length; j++) {
					if (mItems[i].getId().equals("File"))
						((MenuManager) mItems[i])
								.remove("org.eclipse.ui.openLocalFile");
					else if (mItems[i].getId().equals("help")) {
						((MenuManager) mItems[i]).remove("group.updates");
						((MenuManager) mItems[i])
								.remove("org.eclipse.update.ui.updateMenu");
						((MenuManager) mItems[i])
								.remove("org.eclipse.ui.actions.showKeyAssistHandler");
					}
				}
			}
		}

	}

}
