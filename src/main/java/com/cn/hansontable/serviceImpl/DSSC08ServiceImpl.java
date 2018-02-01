package com.cn.hansontable.serviceImpl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.hansontable.domain.DSSC08;
import com.cn.hansontable.service.DSSC08Service;

@Service("DSSC08Service") 
public class DSSC08ServiceImpl implements DSSC08Service {
	@Resource
	private com.cn.hansontable.dao.DSSC08Mapper DSSC08Mapper;

	/**
	 * 初始化资料
	 * @param pno
	 * @return
	 */
    public List<DSSC08> findLno(String pno) {
    	List<DSSC08> dssc08 = DSSC08Mapper.findLno(pno);
    	if (dssc08!=null) {
    		return dssc08;
    	}
    	return null;
    }
}
