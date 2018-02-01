package com.cn.hansontable.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hansontable.domain.DSSC08;
import com.cn.hansontable.service.DSSC08Service;


@Controller
public class LNODataContriller {
	@Autowired
	private DSSC08Service SC08Service;
	
	@RequestMapping(value="/lnoInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> lnoInfo(HttpServletRequest request) throws Exception
	{
		  String pno = request.getParameter("pno");
		  List<DSSC08> datas = this.SC08Service.findLno(pno);
		  Map<String,Object> mapObj = new HashMap<String,Object>();
		  mapObj.put("datas", datas);
		  return mapObj;
	   }
}
