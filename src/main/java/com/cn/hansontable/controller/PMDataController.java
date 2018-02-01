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

import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.domain.PNO;
import com.cn.hansontable.service.PM321Service;

@Controller
public class PMDataController {

	   @Autowired
	   private PM321Service pm321Service;

	   @RequestMapping("/")
	   public String index() throws Exception
	   {
	       return "index";
	   }

	   @RequestMapping(value="/selectPM321",method=RequestMethod.POST)
	   @ResponseBody
	   public Map<String,Object> selectPM321(HttpServletRequest request) throws Exception
	   {
		   	String pno = request.getParameter("pno");
		   	String lno = request.getParameter("lno");
		   	Map<String, Object> pm321 =new HashMap<String, Object>();
		   	pm321.put("PNO", pno);
		   	pm321.put("LNO", lno);
		   	List<DSPM321> datas = this.pm321Service.getPM321APM20(pm321);
			Map<String,Object> mapObj = new HashMap<String,Object>();
			mapObj.put("datas", datas);
			return mapObj;
	   }
	   
	   @RequestMapping(value="/Fpnos",method=RequestMethod.POST)
	   @ResponseBody
	   public List<PNO> Lpnos() throws Exception
	   {
		   List<PNO> pnoInfo  = this.pm321Service.findPNO();
		   return pnoInfo;
	   }
}
