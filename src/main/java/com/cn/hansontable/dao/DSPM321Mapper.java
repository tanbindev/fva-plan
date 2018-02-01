package com.cn.hansontable.dao;

import java.util.List;
import java.util.Map;

import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.domain.PNO;

public interface DSPM321Mapper {
	
	/**
	 * 获取排程资料
	 * @param map
	 * @return
	 */
    List<DSPM321> selectByPrimaryKey(Map<String, Object> map); 
    
    /**
     * 获取大排程编号
     * @return
     */
    List<PNO> findPNO();
}