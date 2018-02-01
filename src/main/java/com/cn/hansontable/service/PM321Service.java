package com.cn.hansontable.service;

import java.util.List;
import java.util.Map;

import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.domain.PNO;

public interface PM321Service {
	List<DSPM321> getPM321APM20(Map<String, Object> map);
	
	List<PNO> findPNO();
}
