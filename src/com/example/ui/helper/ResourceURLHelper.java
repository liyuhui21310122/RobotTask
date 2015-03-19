package com.example.ui.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


/**
 * class to locate project resource
 * 
 * @author user
 * 
 */
public class ResourceURLHelper {

	// change between fil and ifile
	public static IFile getFiletoIFile(File file) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath location = Path.fromOSString(file.getAbsolutePath());
		IFile ifile = workspace.getRoot().getFileForLocation(location);
		return ifile;
	}

	public static File getIFiletoFile(IFile ifile) {
		return ifile.getLocation().toFile();
	}

	public static String readFile(IFile file) {
		Assert.isNotNull(file);
		String contents = null;

		try {

			FileInputStream fileinput = new FileInputStream(file.getLocation()
					.toOSString());
			
			int x = fileinput.available();
			byte b[] = new byte[x];
			fileinput.read(b);
			contents = new String(b);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contents;
	}

	public static void writeFile(IFile file, String content)
			throws IOException, CoreException {
		if (file == null || !file.exists())
			return;

		FileOutputStream fot = new FileOutputStream(
				file.getLocation().toFile(), false);
		fot.write(content.getBytes());
		fot.close();

		// refresh
		file.refreshLocal(IResource.DEPTH_ZERO, null);
	}
	
	public static InputStream getNullInputStream(){
		String contents ="";
		return new ByteArrayInputStream(contents.getBytes());
	}

	// get control contentinput
	public static InputStream getControlContentInput(String modelName) {
		org.dom4j.Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("Root");

		Element diagram = root.addElement("Diagram");
		/*
		Element n ;	
		n = diagram.addElement("Node");
		n.addAttribute(NodeModel.ATR_MODELNAME, "Node");
		*/	
		return new ByteArrayInputStream(doc.asXML().getBytes());
	}	
}
