package cn.edu.tju.ina.estuary.doc;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.tju.ina.estuary.doc.annotation.Description;
import cn.edu.tju.ina.estuary.doc.annotation.Param;
import cn.edu.tju.ina.estuary.doc.annotation.Return;
import cn.edu.tju.ina.estuary.doc.annotation.Title;
import cn.edu.tju.ina.estuary.doc.annotation.Transparent;
import cn.edu.tju.ina.estuary.doc.entity.FieldObject;
import cn.edu.tju.ina.estuary.doc.entity.MethodObject;
import cn.edu.tju.ina.estuary.doc.entity.ParamObject;
import cn.edu.tju.ina.estuary.doc.entity.ValueObject;

public class VoHelper {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<ValueObject> voDoc(String packageName) throws ClassNotFoundException {
		List<ValueObject> vos = new ArrayList<ValueObject>();
		List<Class> classes = findAllClasses(packageName);
		for (Class c : classes) {
			ValueObject vo = new ValueObject();
			Annotation a = c.getAnnotation(Transparent.class);
			if (a == null) {
				String name = c.getSimpleName().substring(0, c.getSimpleName().length() - 2);
				vo.setName(name);
				Field [] fields = c.getDeclaredFields();
				for (Field field : fields) {
					FieldObject fo = new FieldObject();
					fo.setName(field.getName());
					String type = field.getType().getSimpleName();
					if (type.endsWith("Vo")) {
						type = type.substring(0, type.length() - 2);
						fo.setInnerType(false);
					}
					fo.setType(type);
					a = field.getAnnotation(Description.class);
					if (a != null) {
						fo.setDesc(((Description)a).value());
					}
					vo.addField(fo);
				}
			} else {
				continue;
			}
			a = c.getAnnotation(Description.class);
			if (a != null) {
				vo.setDesc(((Description)a).value());
				
			}
			Class superc = c.getSuperclass();
			if (superc != null && superc != Object.class && (a = superc.getSuperclass().getAnnotation(Transparent.class)) == null) {
				String baseClass = superc.getSimpleName().substring(0, superc.getSimpleName().length() - 2);
				vo.setBase(baseClass);
			}
			
			vos.add(vo);
		}
		return vos;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<MethodObject> apiDoc(String packageName) throws ClassNotFoundException {
		List<MethodObject> mos = new ArrayList<MethodObject>();
		List<Class> classes = findAllClasses(packageName);
		for (Class c : classes) {
			Annotation a = c.getAnnotation(RequestMapping.class);
			if (a != null) {
				RequestMapping rm = (RequestMapping)a;
				String baseUrl = rm.value()[0];
				Method [] methods = c.getDeclaredMethods();
				for (Method method : methods) {
					MethodObject mo = new MethodObject();
					mo.setName(c.getSimpleName().replace("Controller", "") + method.getName());
					
					a = method.getAnnotation(RequestMapping.class);
					if (a == null) {
						continue;
					}
					rm = (RequestMapping)a;
					if (rm.value().length > 0) {
						mo.setUrl(baseUrl + rm.value()[0]);
					} else {
						mo.setUrl(baseUrl);
					}
					String methodStr = "";
					for (RequestMethod ms : rm.method()) {
						methodStr += ms.name() + ",";
					}
					if (methodStr.endsWith(",")) {
						methodStr = methodStr.substring(0, methodStr.length() - 1);
					}
					if ("".equals(methodStr)) {
						methodStr = "GET,POST,PUT,DELETE";
					}
					mo.setMethod(methodStr);
					
					a = method.getAnnotation(Return.class);
					if (a != null) {
						mo.setJsonRet(((Return)a).value());
					}
					
					a = method.getAnnotation(Param.class);
					if (a != null) {
						Param p = (Param)a;
						String [] names = p.name();
						String [] descs = p.desc();
						List<ParamObject> pos = new ArrayList<ParamObject>();
						for (int i = 0 ; i < names.length ; i++) {
							ParamObject po = new ParamObject();
							po.setName(names[i]);
							po.setDesc(descs[i]);
							pos.add(po);
						}
						mo.setParams(pos.toArray(new ParamObject[pos.size()]));
					}
					
					a = method.getAnnotation(Title.class);
					if (a != null) {
						mo.setTitle(((Title)a).value());
					}
					
					a = method.getAnnotation(Description.class);
					if (a != null) {
						mo.setDesc(((Description)a).value());
					}
					
					mos.add(mo);
				}
			} else {
				continue;
			}
		}
		return mos;
	}
	
	@SuppressWarnings("rawtypes")
	private static List<Class> findAllClasses(String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
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
		if (directory != null && directory.exists()) {
			classes = findAllClassesInFile(packageName, directory);
		} else {
			throw new ClassNotFoundException(packageName
					+ " does not appear to be a valid package b");
		}
		return classes;
	}
	
	/**
	 * @param packageName should be consistent with directory
	 * @param directory
	 * @return all subclasses in the dir and sub dirs
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findAllClassesInFile(String packageName, File directory) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (directory != null && directory.exists()) {
			// Get the list of the files contained in the package
			File [] files = directory.listFiles();
			for (File f : files) {
				String fileName = f.getName();
				if (f.isDirectory()) {
					classes.addAll(findAllClassesInFile(packageName + "." + f.getName(), f));
				} else if (fileName.endsWith(".class")) {
					classes.add(Class.forName(packageName + '.'
							+ fileName.substring(0, fileName.length() - 6)));
				}
			}
		}
		return classes;
	}
}


//@SuppressWarnings({ "rawtypes", "unchecked" })
//public static List<ValueObject> voDoc(String packageName) throws ClassNotFoundException {
//	List<ValueObject> vos = new ArrayList<ValueObject>();
//	List<Class> classes = findAllClasses(packageName);
//	for (Class c : classes) {
//		ValueObject vo = new ValueObject();
//		Annotation a = c.getAnnotation(Export.class);
//		if (a != null) {
//			String name = ((Export)a).value();
//			if ("".equals(name)) {
//				name = c.getSimpleName();
//			}
//			vo.setName(name);
//			Field [] fields = c.getDeclaredFields();
//			for (Field field : fields) {
//				FieldObject fo = new FieldObject();
//				fo.setName(field.getName());
//				fo.setType(field.getType().getSimpleName());
//				a = field.getAnnotation(Description.class);
//				if (a != null) {
//					fo.setDesc(((Description)a).value());
//				}
//				vo.addField(fo);
//			}
//		} else {
//			continue;
//		}
//		a = c.getAnnotation(Description.class);
//		if (a != null) {
//			vo.setDesc(((Description)a).value());
//			
//		}
//		Class superc = c.getSuperclass();
//		if (superc != null && (a = superc.getSuperclass().getAnnotation(Export.class)) != null) {
//			String baseClass = ((Export)a).value();
//			if ("".equals(baseClass)) {
//				baseClass = superc.getSimpleName();
//			}
//			vo.setBase(baseClass);
//		}
//		
//		vos.add(vo);
//	}
//	return vos;
//}
