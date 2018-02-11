package com.johnfnash.study.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.johnfnash.study.model.Result;
import com.johnfnash.study.model.User;

@RestController
public class UserController {
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Result addUser(@Valid @RequestBody User user, BindingResult errors) {
		/*if(errors.hasFieldErrors()) {
			StringBuilder sb = new StringBuilder();
			List<ObjectError> errorList = errors.getAllErrors();
			for (ObjectError err : errorList) {
				sb.append(err.getDefaultMessage());
			}
			
			throw new ParameterException(sb.toString());
		}*/
		return Result.getSuccessResult(null);
	}
}
