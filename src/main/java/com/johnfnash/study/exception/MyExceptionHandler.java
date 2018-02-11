package com.johnfnash.study.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/** �� rest Ӧ�ã���Ҫ������ͼ */
public class MyExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LogManager.getLogger(MyExceptionHandler.class);
			
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		// ���쳣��Ϣд����־�ļ�
		logger.error("internal error", ex);
		
		// ���ݲ�ͬ�Ĵ���ת��ͬ��ҳ��
		ModelAndView modelAndView = null;
		if(ex instanceof BusinessException) {
			modelAndView = new ModelAndView("error-business", model);
		} else if(ex instanceof ParameterException) {
			modelAndView = new ModelAndView("error-parameter", model);
		} else {
			modelAndView = new ModelAndView("error", model);
		}
		
		return modelAndView;
	}

}
