package com.my.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Scope("prototype")
@Controller
@RequestMapping("/products")
public class MyFirstRest {
	 @RequestMapping(value="/list/{proId}",method=RequestMethod.GET)
	    public String getProductsList(@PathVariable String proId,HttpServletRequest request,HttpServletResponse response) throws Exception {
	   
	        request.setAttribute("name","helloworld");
	        request.setAttribute("id", proId);
	        return "products/list";
	         
	    }
	 @RequestMapping(value="/info",method=RequestMethod.GET)
	    public String getProductsInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
	   
	        request.setAttribute("introduce", "this is a nice producor");
	        //request.setAttribute("id", "helloWord");
	         
	        return "products/info";
	         
	    }
	 @RequestMapping(value="/test",method=RequestMethod.GET)
	    public String getProductsTest() throws Exception {
	   
	        //request.setAttribute("id", "helloWord");
	         
	        return "products/info";
	         
	    }
}
