package com.cn.hansontable.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.service.CommonDSPMService;
import com.cn.hansontable.utils.PUBLIC;


@Controller
public class COMMONController {
	
	@Autowired
	private CommonDSPMService CommonService;
		
	  @RequestMapping(value = "/refresh", method = RequestMethod.POST)
	   @ResponseBody
	   public String refresh(HttpServletRequest request) throws Exception
	   {
		  String info="";
		  Map<String, String[]> map=request.getParameterMap();
		  List<DSPM321> updateDSPM321=new ArrayList<DSPM321>();
		  PUBLIC pub = new PUBLIC();
		  int rows=map.size()/5;
		  DSPM321 dspm321;
		  for(int i=0;i<rows;i++) {
			  String planseq=map.get("changeDatas["+i+"][PLAN_SEQ]")[0];
			  dspm321=new DSPM321(
					   map.get("changeDatas["+i+"][PNO]")[0],
					   map.get("changeDatas["+i+"][OD_NO]")[0],
					   map.get("changeDatas["+i+"][OD_LOT]")[0],
					   map.get("changeDatas["+i+"][LNO]")[0],
					   pub.getDouble(planseq)
			   );  
			   updateDSPM321.add(dspm321);
		   }
		  this.CommonService.saveData(updateDSPM321);
		  String pno = request.getParameter("PNO");
		  String slno = request.getParameter("SLNO");
		  String elno = request.getParameter("ELNO");
		  if(slno.equals(elno)){
			  this.CommonService.deDSPM141A(pno, slno);
			  info =info + this.CommonService.RefreshLNO(pno, slno);
		  }else{
			  this.CommonService.deDSPM141A(pno, slno);
			  this.CommonService.deDSPM141A(pno, elno);
			  info =info + this.CommonService.RefreshLNO(pno, slno);
			  info =info + this.CommonService.RefreshLNO(pno, elno);
		  }
		   return info;
	   }
}
