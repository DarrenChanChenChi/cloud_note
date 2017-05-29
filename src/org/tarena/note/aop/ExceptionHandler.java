package org.tarena.note.aop;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//案例：记录异常日志
//切面：抛出异常写入日志文件
//切入点：所有Controller
//通知：异常通知（切入点方法发生异常后调用切面处理）
@Component
@Aspect
public class ExceptionHandler {
	
	private Logger logger = Logger.getLogger(ExceptionHandler.class);
	//ex参数作用：接收目标组件抛出的异常对象
	@AfterThrowing(throwing="ex",
			pointcut="within(org.tarena.note.controller..*)")
	public void handler(Exception ex){
		//将异常信息写入文件
		logger.error("==============异常信息==============");
		logger.error("异常类型："+ex);
		StackTraceElement[] els = ex.getStackTrace();
		logger.error(els[0]);
	}
	
}
