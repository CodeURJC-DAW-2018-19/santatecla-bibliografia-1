package com.santatecla.G1.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorControler implements ErrorController{
	
	@RequestMapping("/errorNotFound")
	public String handleError(Model model, HttpServletRequest request) {
	      Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	      Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
	      Error error = new Error(statusCode,exception);
	      if (error!=null) {
				model.addAttribute("error", error);
			}
	      return "errorPage";
	  }

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
}
