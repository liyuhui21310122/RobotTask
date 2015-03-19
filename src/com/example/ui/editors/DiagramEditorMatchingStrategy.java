package com.example.ui.editors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PartInitException;

/**
 * match editor input when open file
 * @author user
 *
 */

public class DiagramEditorMatchingStrategy implements IEditorMatchingStrategy {

	@Override
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		// TODO Auto-generated method stub

		if (editorRef == null || input == null)
			return false;

		try {
			IPersistableElement element = editorRef.getEditorInput()
					.getPersistable();
			if ((element instanceof IPathEditorInput)
					&& (input instanceof IPathEditorInput)) {
				IPath path = ((IPathEditorInput) element).getPath();
				return path.equals(((IPathEditorInput) input).getPath());
			}

		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
