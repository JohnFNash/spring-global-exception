package com.johnfnash.study.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.validation.Valid;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.johnfnash.study.exception.ParameterException;
import com.johnfnash.study.model.Result;

@Aspect
//@Component
public class ValidAspect {
	
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")  
	public Object processErrors(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		if(!Result.class.equals(method.getReturnType())) {
			return pjp.proceed();
		}
		
		Object[] args = pjp.getArgs();
		Annotation[][] annotations = method.getParameterAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			if(!hasValidAnnotation(annotations[i])){  
                continue;  
            }
			
			if(! ( i<annotations.length-1 && args[i+1] instanceof BindingResult )  ) {
				//验证对象后面没有跟bindingResult,事实上如果没有应该到不了这一步  
                continue;  
			}
			
			BindingResult bindingResult = (BindingResult) args[i+1];
			if(bindingResult.hasErrors()) {
				throw new ParameterException(parseError(bindingResult));
				//return Result.getFailResult(parseError(bindingResult));
			}
		}
		
		return pjp.proceed();
	}
	
	private boolean hasValidAnnotation(Annotation[] annotations) {
		if(annotations == null){  
            return false;  
        }  
        for(Annotation annotation : annotations){  
            if(annotation instanceof Valid){  
                return true;  
            }  
        }  
        return false;
	}
	
	private String parseError(BindingResult result) {
		StringBuilder sBuilder = null;
		if(null != result && result.hasErrors()) {
			sBuilder = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				sBuilder.append(error.getDefaultMessage()).append('\n');
			}
		}
		return sBuilder==null ? null : sBuilder.toString();
	}
	
}
