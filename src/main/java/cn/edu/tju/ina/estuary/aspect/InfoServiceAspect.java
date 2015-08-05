package cn.edu.tju.ina.estuary.aspect;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cn.edu.tju.ina.estuary.domain.info.Info;
import cn.edu.tju.ina.estuary.util.IdAssistant;

@Component
@Aspect
public class InfoServiceAspect {

	@Autowired
	private WebApplicationContext wac;
	
	@Before("execution(* cn.edu.tju.ina.estuary.service.impl.InfoServiceImpl.*(..))")
	public void wireInfoDao(JoinPoint joinPoint) {
		Object [] args = joinPoint.getArgs();
		Object service = joinPoint.getTarget();
		Field dao = null;
		try {
			dao = service.getClass().getDeclaredField("infoDao");
			if (!dao.isAccessible()) {
				dao.setAccessible(true);
			}
		} catch (NoSuchFieldException | SecurityException e) {
			return;
		}
		if (args.length == 0) {
			String daoName = IdAssistant.getInstance().getInfoTypeName(0).toLowerCase() + "Dao";
			setBean(dao, service, daoName);
		} else if (args[0] instanceof Info) {
			int type = ((Info)args[0]).getType();
			String daoName = IdAssistant.getInstance().getInfoTypeName(type).toLowerCase() + "Dao";
			setBean(dao, service, daoName);
		}
		else if (args[0] instanceof Integer) {
			int type = (int)args[0];
			String daoName = IdAssistant.getInstance().getInfoTypeName(type).toLowerCase() + "Dao";
			setBean(dao, service, daoName);
		}
	}
	
	public boolean setBean(Field dao, Object service, String beanName){
		try {
			dao.set(service, wac.getBean(beanName));
			return true;
		} catch (BeansException | IllegalArgumentException
				| IllegalAccessException e) {
			return false;
		}
	}

}
