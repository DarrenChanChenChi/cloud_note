package org.tarena.note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//封装打桩信息，将来当切面组件使用
@Component//扫描到Spring容器
@Aspect//指定为切面
public class DebugLoggerBean {
	
	//用前置通知切入到所有Controller方法前调用
	@Before("within(org.tarena.note.controller..*)")
	public void debugController(){
		//System.out.println("进入Controller方法处理");
	}
	
	@Before("within(org.tarena.note.service..*)")
	public void debugService(){
		//System.out.println("进入Service方法处理");
	}
	
}



