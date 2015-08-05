package cn.edu.tju.ina.estuary.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DocsUtils {
	
	@SuppressWarnings("rawtypes")
	public static List<Class> getAllClasses(String packageName) throws ClassNotFoundException {
		ArrayList<Class> classes = new ArrayList<Class>();
		File directory = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null)
				throw new ClassNotFoundException("Can't get class loader.");
			String path = '/' + packageName.replace('.', '/');
			URL resource = classLoader.getResource(path);
			if (resource == null)
				throw new ClassNotFoundException("No resource for " + path);
			directory = new File(resource.getFile());
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(packageName + " (" + directory
					+ ") does not appear to be a valid package a");
		}
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(packageName + '.'
							+ files[i].substring(0, files[i].length() - 6)));
				}
			}
		} else {
			throw new ClassNotFoundException(packageName
					+ " does not appear to be a valid package b");
		}
		return classes;
	}
}
