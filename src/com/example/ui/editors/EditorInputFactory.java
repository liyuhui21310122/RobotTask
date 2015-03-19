package com.example.ui.editors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import java.io.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.part.FileEditorInput;

public class EditorInputFactory implements IElementFactory {

	private final static String ID = "Gef.editor.editorInputFactory";
	private final static String TAG_PATH = "path";
	private final static String GEF = "robotTask";

	@Override
	public IAdaptable createElement(IMemento memento) {
		// TODO Auto-generated method stub
		String path = memento.getString(TAG_PATH);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IPath filePath = new Path(path);
		IFile file = root.getFileForLocation(filePath);

		// external file restore
		if (file == null) {
			File osFile = new File(path);
			if (osFile == null || !osFile.exists()) {
				return null;
			}
			String name = osFile.getName();
			if (name.endsWith(".robotTask")) {
				return new DiagramEditorInput(filePath);
			} 
		    else {
				return null;
			}
		}

		// internal file restore
		String extension = file.getFileExtension();
		if (GEF.equals(extension)) {
			return new DiagramEditorInput(filePath);
		} else {
			return new FileEditorInput(file);//???
		}
	}

	public static String getFactoryId() {
		return ID;
	}

	public static void saveState(IMemento memento, IPathEditorInput input) {
		String path = input.getPath().toOSString();
		memento.putString(TAG_PATH, path);
	}
}

