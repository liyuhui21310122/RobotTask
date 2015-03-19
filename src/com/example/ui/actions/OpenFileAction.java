package com.example.ui.actions;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.example.ui.helper.IImageKey;
import com.example.ui.helper.NewEditor;


/**
 * open file action
 * 
 * @author user
 * 
 */
public class OpenFileAction extends Action implements ISelectionListener,
		IWorkbenchAction {
	private IWorkbenchWindow window;

	private final static String ID = "robottaskviewer.openfileaction";

	public OpenFileAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("打开文件");

		setImageDescriptor(IImageKey.getImageDescriptor(IImageKey.KEY_OPEN));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		dialog.setText("打开文件");
		dialog.setFilterPath(ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toOSString());
		dialog.setFilterExtensions(new String[] { "*.robotTask"});
		String path = dialog.open();

		if (path == null)
			return;

		if (path.endsWith(".robotTask")) {
			NewEditor diagramEditor = new NewEditor(path,
					NewEditor.OPENFILE_GEF);
			diagramEditor.openEditor();
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
