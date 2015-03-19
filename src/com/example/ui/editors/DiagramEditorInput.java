package com.example.ui.editors;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;


public class DiagramEditorInput extends PlatformObject implements
		IPathEditorInput, IFileEditorInput, IPersistableElement {
	private IPath path;

	public DiagramEditorInput(IPath path) {
		this.path = path;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return path.toFile().exists();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		String name = path.toString();
		String title = name.substring(name.lastIndexOf('/') + 1, name.length());

		return title;
	}

	

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return path.makeRelative().toString();
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub

		if (adapter.equals(Path.class)) {
			return path.toOSString();
		} else if (adapter.equals(IFile.class)) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFile file = root.getFileForLocation(path);
			return file;
		}

		return super.getAdapter(adapter);
	}

	@Override
	public IStorage getStorage() throws CoreException {
		// TODO Auto-generated method stub
		return getFile();
	}

	public IPath getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return path.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;

		if (obj instanceof IPathEditorInput) {
			IPathEditorInput other = (IPathEditorInput) obj;
			return path.equals(other.getPath());
		}

		return false;
	}

	@Override
	public IFile getFile() {
		// TODO Auto-generated method stub
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFileForLocation(path);
		return file;
	}

	@Override
	public String getFactoryId() {
		// TODO Auto-generated method stub
		return EditorInputFactory.getFactoryId();
	}

	@Override
	public void saveState(IMemento memento) {
		// TODO Auto-generated method stub
		EditorInputFactory.saveState(memento, this);
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub

		return this;
	}
	
}


