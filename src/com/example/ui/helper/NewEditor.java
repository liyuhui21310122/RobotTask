package com.example.ui.helper;



import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.example.ui.editors.DiagramEditor;
import com.example.ui.editors.DiagramEditorInput;




/**
 * class to open diagram  editor
 * 
 * @author user
 * 
 */
public class NewEditor {
	public static final int OPENFILE_GEF= 1;
	//public static final int OPENFILE_TTCN = 0;

	

	private String path = null;
	private int type;
	private IFile file = null;

	public NewEditor(IFile file) {
		this.file = file;

	}

	public NewEditor(String path, int type) {
		super();
		this.path = path;
		this.type = type;
	}

	// open editor according file根据文件打开编辑器
	public IEditorPart openFileEditor() {
		if (file == null)
			return null;

		// get workbenchwindow
		String fName = file.getName();
		IWorkbenchWindow win = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		// open diagram editor
		if (fName.endsWith(".robotTask")) {
			try {
				DiagramEditorInput input = new DiagramEditorInput(file
						.getLocation());
				return win.getActivePage().openEditor(input, DiagramEditor.ID);

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        /*
		// open definitive editor
		else if (fName.endsWith(".ttcn3")) {
			try {
				file.setPersistentProperty(IDE.EDITOR_KEY, ModuleTextEditor.ID);
				return IDE.openEditor(win.getActivePage(), file);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		return null;
	}

	// open editor according path根据路径打开编辑器
	public void openEditor() {

		if (path == null) {
			return;
		}

		File file = new File(path);
		if (file.exists()) {

		} else {
			return;
		}

		if (type == NewEditor.OPENFILE_GEF) {
			openDiagram();
		}
        /*
		else if (type == NewEditor.OPENFILE_TTCN) {
			openModuleEditor();
		}
        */
	}

	// open diagram editor
	private void openDiagram() {
		// TODO Auto-generated method stub
		IWorkbenchWindow win = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		IEditorInput diagramInput = new DiagramEditorInput(new Path(path));

		try {

			IDE.openEditor(win.getActivePage(), diagramInput, DiagramEditor.ID);

		} catch (PartInitException e) {
			e.printStackTrace();
		}

	}
     /*
	// open module editor
	private void openModuleEditor() {

		IWorkbenchWindow win = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		IPath filePath = new Path(path);
		IFile iFile = root.getFileForLocation(filePath);

		// try open with FileEditorInput firstly
		if (iFile != null && iFile.exists()) {
			try {
				iFile
						.setPersistentProperty(IDE.EDITOR_KEY,
								ModuleTextEditor.ID);
				IDE.openEditor(win.getActivePage(), iFile);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		// can not open with FileEditorInput then open with
		// ModuleTextEditorInput
		ModuleTextEditorInput input = new ModuleTextEditorInput(filePath);

		try {
			win.getActivePage().openEditor(input, ModuleTextEditor.ID, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
    */
}
