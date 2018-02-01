package com.cn.hansontable.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hansontable.domain.DSSC08;


public interface DSSC08Mapper {
	/**
	 * 获取线别及已排数量
	 * @param PNO
	 * @return
	 */
	List<DSSC08> findLno(@Param("PNO") String PNO);
}
