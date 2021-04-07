package com.dfz.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.dfz.dto.AjaxResult;

/**
 * Global Exception Handler class
 * 
 * catch and handle all exceptions. return error message in json or ModelAndView to request client.
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req){
		//log the error message of the exception
		logger.error("find exception-->{}", e.getMessage());
		
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        
        if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
        	
        	//return json
            return AjaxResult.fail(e.getMessage());
            
        } else {
        	
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("code", 500);
            modelAndView.addObject("msg", e.getMessage());
            modelAndView.setViewName("error/error");
            
            //return ModelAndView
            return modelAndView;
            
        }
    }
	

}
