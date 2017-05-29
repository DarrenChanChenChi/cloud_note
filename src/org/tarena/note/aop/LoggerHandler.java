package org.tarena.note.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tarena.note.util.PropertiesUtil;

//用户日志记录
@Component
@Aspect
public class LoggerHandler extends HttpServlet {
	
	@Autowired
	private HttpServletRequest request;
	private Logger logger = Logger.getLogger(LoggerHandler.class);
	//采用环绕通知
	@Around("within(org.tarena.note.controller..*)")
	public Object logger(ProceedingJoinPoint jp) throws Throwable{
		//前置逻辑
		//获取要执行的目标组件类型
		String className = jp.getTarget().getClass().getName();
		//获取要执行的目标组建方法名
		String methodName = jp.getSignature().getName();
		//根据类名和方法名，给用户提示具体信息
		String key = className+"."+methodName;
		//获取用户ID
		Cookie[] cookies = request.getCookies();
		String userId = null;
		if(cookies != null){
			userId = cookies[0].getValue();
		}
		//解析opt.properties，根据key获取消息
		System.out.println(userId);
		System.out.println(key);
		System.out.println(PropertiesUtil.getValue(key));
		logger.warn(userId+"用户执行了"+PropertiesUtil.getValue(key)+"操作");
		Object obj = jp.proceed();//执行目标方法
		//后置逻辑
		return obj;
	}
}
