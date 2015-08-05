package cn.edu.tju.ina.estuary.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Spring has a reflection util called org.springframework.util.ReflectionUtils, 
 * but it has some problems when using it. So I implements it rather than just using it.
 * @author ZhangFC
 */
public class ReflectUtils {
	public static Object invokeMethod(Method method, Object target,
			Object... args) {
		Object obj = null;
		try {
			obj = method.invoke(target, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Method findMethod(Class<?> clazz, String name) {
		Method [] ms = clazz.getMethods();
		for (Method m : ms) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

}
