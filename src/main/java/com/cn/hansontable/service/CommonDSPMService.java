package com.cn.hansontable.service;

import java.util.List;

import com.cn.hansontable.domain.DSPM321;

public interface CommonDSPMService {
	
	/**
	 * 获取工作天数
	 * @param LNO
	 * @param WKDATE1
	 * @param WKDATE2
	 * @return
	 */
	int geSC17Days(String LNO,String WKDATE1,String WKDATE2);
	
	/**
	 * 刷新新型体
	 * @param PNO
	 * @param SHNO
	 * @param LNO
	 * @param CXDATE
	 */
	void RefreshNewSHNO(String PNO,String SHNO,String LNO,String CXDATE);
	
	/**
	 * 线别重整
	 * @param PNO
	 * @param LNO
	 */
	String RefreshLNO(String PNO,String LNO);
	
	/**
	 * 获取产线搭配生管时产能
	 * @param PNO
	 * @param LNO
	 * @param SHNO
	 * @return
	 */
	String[] getPM13(String PNO,String LNO,String SHNO);
	
	String getOGAC(String CXEDATE,String RGAC);
	
	double[] getPMHOURS(String LNO,double ODLQTY,double PMOUTPUT,double PMOUTPUT1,String WKDATE,double HOURS);
	
	double[] getSHNOPM14(String SHNO,String LNO,double QTY,String PROCID,double PM13OUT,String BDATE,double BHOURS);
	
	void UpdateDSPM141(String PNO,String SHNO,String LNO,String PROCID,String PTYPE,String CXBDATE,String CXEDATE);
	
	String[] getCXEDateBySC17(String LNO,String WKDATE,double HOURS);
	
	String[] getPM321DATE_FVA(String PNO,String LNO,String ODNO,String CXBDATE,String CXEDATE);
	
	double getSC17HOURS(String LNO,String WKDATE1,String WKDATE2,double FHOURS);
	
	String ADDDateSC17(String LNO,String WKDATE,double DAYS);
	
	void deDSPM141A(String PNO,String LNO);
	
	int updateDSPM321(List<DSPM321> updateDSPM321);
	
	String saveData(List<DSPM321> updateDSPM321);
}
