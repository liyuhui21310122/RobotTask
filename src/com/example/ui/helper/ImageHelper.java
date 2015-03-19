package com.example.ui.helper;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.example.application.Application;




/**
 * class to get image from image registry
 * 
 * @author user
 * 
 */

public final class ImageHelper {

	public static ImageDescriptor getSharedImageDescriptor(String key) {
		return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(key);
	}

	public static ImageDescriptor getImageDescriptor(String ID,
			ImageRegistry registry, String key) {

		ImageDescriptor imageDescriptor = registry.getDescriptor(key);
		if (imageDescriptor != null)
			return imageDescriptor;

		final String path = "/icons/ui/" + key + ".gif";

		imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
				Application.PLUGIN_ID, path);

		if (imageDescriptor == null)
			imageDescriptor = ImageDescriptor.getMissingImageDescriptor();

		registry.put(key, imageDescriptor);
		return imageDescriptor;
	}
}
