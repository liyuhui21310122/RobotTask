package com.example.ui.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;


/**
 * helper class to handle operation associated project
 * 
 * @author user
 * 
 */

public class ProjectHelper {
	public static IFolder getGEFFolder(IResource resource) {
		if (resource == null)
			return null;

		if (resource instanceof IProject) {
			return null;
		} else if (resource instanceof IFolder) {
			IContainer container = (IContainer) resource;
			while (true) {
				if (container.getParent() == null)
					return null;
				if (container.getParent() instanceof IProject)
					return (IFolder) container;
				container = container.getParent();
			}
		}

		else if (resource instanceof IFile) {
			IContainer container = (IContainer) resource.getParent();
			if (container instanceof IProject)
				return null;
			while (true) {
				if (container.getParent() == null)
					return null;
				if (container.getParent() instanceof IProject)
					return (IFolder) container;
				container = container.getParent();
			}

		}
		return null;
	}
}