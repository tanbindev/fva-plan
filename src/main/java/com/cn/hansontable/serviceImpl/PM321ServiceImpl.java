package com.cn.hansontable.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cn.hansontable.dao.DSPM321Mapper;
import org.springframework.stereotype.Service;

import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.domain.PNO;
import com.cn.hansontable.service.PM321Service;

@Service("PM321Service") 
public class PM321ServiceImpl implements PM321Service {
	@Resource
	private DSPM321Mapper DSPMPM321Mapper;

	/**
	 * 初始化资料
	 * @param map
	 * @return
	 */
    public List<DSPM321> getPM321APM20(Map<String, Object> map) {
    	List<DSPM321> pm321 = DSPMPM321Mapper.selectByPrimaryKey(map);
    	if (pm321!=null) {
    		return pm321;
    	}
    	return null;
    }

	/**
	 * 查询大排编号
	 * @return
	 */
	public List<PNO> findPNO() {
    	return DSPMPM321Mapper.findPNO();
    }
}
