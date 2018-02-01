package com.cn.hansontable.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cn.hansontable.domain.CXDATE;
import com.cn.hansontable.domain.DSPM13;
import com.cn.hansontable.domain.DSPM141;
import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.domain.DSSC17;
import com.cn.hansontable.domain.PM121;
import com.cn.hansontable.domain.PM20OC;
import com.cn.hansontable.domain.PM321ANDPM20;

public interface CommonDSPMMapper {
	
    /**
     * 更新DSPM321资料
     * @param updateDSPM321
     * @return
     */
    int updateDSPM321(List<DSPM321> updateDSPM321);
    
	/**
	 * 获取型体系列号
	 * @param SHNO
	 * @return
	 */
	String getSHOE_ID(@Param("SHNO")String SHNO);
	
	/**
	 * 获取某日期段该线别上班天数
	 * @param LNO
	 * @param WKDATE1
	 * @param WKDATE2
	 * @return
	 */
	List<Integer> getSC17Days(@Param("LNO")String LNO,@Param("WKDATE1")String WKDATE1,@Param("WKDATE2")String WKDATE2);
	
	/**
	 * 清空新型体成长曲线达成
	 * @param PNO
	 * @param LNO
	 * @param SHOEID
	 */
	void deDSPM141(@Param("PNO")String PNO,@Param("LNO")String LNO,@Param("SHOEID")String SHOEID);
	
	void deDSPM141A(@Param("PNO")String PNO,@Param("LNO")String LNO);

	/**
	 * 新增资料至DSPM141
	 * @param PNO
	 * @param SHOEID
	 * @param LNO
	 * @param LPCT
	 * @param DAYS
	 * @param CXEDATE
	 */
	void inDSPM141(@Param("PNO")String PNO, @Param("SHOEID")String SHOEID, @Param("LNO")String LNO, @Param("LPCT")double LPCT, @Param("DAYS")double DAYS, @Param("CXEDATE")String CXEDATE);
	
	/**
	 * 更新DSPM14资料
	 * @param PNO
	 * @param SHOEID
	 * @param LNO
	 * @param LPCT
	 * @param DAYS
	 * @param CXEDATE
	 * @return
	 */
	int updateDSPM141(@Param("PNO")String PNO, @Param("LNO")String LNO, @Param("SHOEID")String SHOEID, @Param("LPCT")double LPCT, @Param("DAYS")double DAYS, @Param("CXEDATE")String CXEDATE);
	
	/**
	 * 获取成型上线日
	 * @param PNO
	 * @param SHOEID
	 * @param LNO
	 * @param CXDATE
	 * @return
	 */
	List<CXDATE> findCXDATE(@Param("PNO")String PNO, @Param("LNO")String LNO, @Param("SHOEID")String SHOEID, @Param("CXDATE")String CXDATE);
	
	/**
	 * 获取新型体成长天数
	 * @param LNO
	 * @param SHOEID
	 * @return
	 */
	int getPER(@Param("SHOEID")String SHOEID, @Param("LNO")String LNO);
	
	/**
	 * 获取成长产能
	 * @return
	 */
	int getLPCT(@Param("SHOEID")String SHOEID, @Param("field")String PER);
	
	/**
	 * 清空DSPM321
	 * @param PNO
	 * @param LNO
	 */
	void dePM321(@Param("PNO")String PNO,@Param("LNO")String LNO);
	
	/**
	 * 刷新DSPM321
	 * @param map
	 */
	int updatePM321(List<DSPM321> updateDSPM321); 
	
	/**
	 * 查询编号信息
	 * @param FIELD
	 * @param PNO
	 * @return
	 */
	String GETPNO(@Param("field")String FIELD,@Param("PNO")String PNO);
	
	/**
	 * 线别刷新信息
	 * @param PNO
	 * @param LNO
	 * @return
	 */
	List<PM321ANDPM20> refreshInfo(@Param("PNO")String PNO,@Param("LNO")String LNO);
	
	/**
	 * 产线搭配
	 * @param PNO
	 * @param SHNO
	 * @param LNO
	 * @return
	 */
	List<DSPM13> getPM13(@Param("PNO")String PNO,@Param("SHNO")String SHNO,@Param("LNO")String LNO);
	
	List<PM121> getPM121(@Param("SHNO")String SHNO);
	
	/**
	 * 计算两日期相差天数
	 * @param DT1
	 * @param DT2
	 * @return
	 */
	int CompareDate(@Param("DT1")String DT1,@Param("DT2")String DT2);
	
	/**
	 * 細排是否考慮學習曲線
	 * @return
	 */
	String C06();
	
	/**
	 * 出貨前N天完成成型
	 * @return
	 */
	String B01();
	
	/**
	 * 資材最晚入廠日至針車上線日的間隔天數
	 * @return
	 */
	String B07();
	
	/**
	 * 報OGAC
	 * @return
	 */
	String A16_1();
	
	/**
	 * 報OGAC
	 * @return
	 */
	String A16_2();
	
	String E03();
	
	String B02();
	
	String B03();
	
	String B05();
	
	String B12();
	
	String B12_2();
	
	String B14();
	
	String B30();
	
	String ADDDate(@Param("DT")String DT,@Param("DAYS")int DAYS);
	
	double getSHNOLPCT(@Param("SHNO")String SHNO,@Param("LNO")String LNO,@Param("PROCID")String PROCID);
	
	List<DSSC17> getPMHOURS(@Param("LNO")String LNO,@Param("WKDATE")String WKDATE);
	
	List<DSSC17> getPMHOURS2(@Param("LNO")String LNO,@Param("WKDATE")String WKDATE);
	
	List<DSPM141> getPM141(@Param("SHNO")String SHNO,@Param("LNO")String LNO,@Param("PROCID")String PROCID);
	
	List<PM20OC> getPM20OC(@Param("PNO")String PNO,@Param("OD_NO")String OD_NO);
	
	String getWorkDays(@Param("OD_NO")String OD_NO);
	
	String getPM07MAXDATE(@Param("OD_NO")String OD_NO);
	
	String getPM08MAXDATE(@Param("OD_NO")String OD_NO);
}
