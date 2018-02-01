package com.cn.hansontable.domain;

public class DSPM321 extends DSPM321Key {
	private String LNO;

    private double PLAN_SEQ;

    private double OD_LQTY;
    
    private String SH_ARITCLENO;
    
    private String SH_NO;
    
    private String OD_ACDAT;
    
    private String ETD;
    
    private String CX_BDATE;
    
    private String CX_EDATE;
    
    private double DIFF_ETD;
    
    private String MATE_DATE;
    
    private String ZC_EDATE;
    
    private String ZC_BDATE;
    
    private String M_MDATE;
    
    private String ZD_EDATE;
    
    private String OS_EDATE;
    
    private String SE_EDATE;
    
    private String CZ_EDATE;
    
    private String PL_STATUS;
    
    private double DIFF_OSDATE;
    
    private String PRO_WKSEQ;
    
    private String ZD_BDATE;
    
    private String SE_BDATE;
    
    private String CFM_DATE1;
    
    private String CFM_DATE2;
    
    private String CX_EHOUR;
    
    private double DIFF_HOURS;
    
    private String CZ2_BDATE;
    
    private String CZ2_EDATE;
    
    private double ZC_HOURS;
    
    private double DIFF_ZCDATE;
    
    private double WK_HOURS;
    
    private double CAP_HOUR;
    
    private String ISLOCK;
    
    private String NEW_SHNO;
    
    private String TL_EXP;
    
    private String DEST_NAME;
    
    private String P_MDATE;
    
    private String SH_LAST;
    
    private String SH_MODEL;

    public DSPM321() {
    	super();
    }
	
	public DSPM321(String pno, String odNo, String odLot, String LNO, double PLAN_SEQ) {
		super(pno, odNo, odLot);
		this.LNO = LNO;
		this.PLAN_SEQ = PLAN_SEQ;
	}

	public DSPM321(String pno, String odNo, String odLot, String LNO, double PLAN_SEQ, double OD_LQTY, String SH_ARITCLENO, String SH_NO, 
			String OD_ACDAT, String ETD, String CX_BDATE, String CX_EDATE, double DIFF_ETD, String MATE_DATE, double DIFF_ZCDATE, double WK_HOURS, double CAP_HOUR) {
		super(pno, odNo, odLot);
		this.LNO = LNO;
		this.PLAN_SEQ = PLAN_SEQ;
		this.OD_LQTY = OD_LQTY;
		this.SH_ARITCLENO = SH_ARITCLENO;
		this.SH_NO = SH_NO;
		this.OD_ACDAT = OD_ACDAT;
		this.ETD = ETD;
		this.CX_BDATE = CX_BDATE;
		this.CX_EDATE = CX_EDATE;
		this.DIFF_ETD = DIFF_ETD;
		this.MATE_DATE = MATE_DATE;
		this.DIFF_ZCDATE = DIFF_ZCDATE;
		this.WK_HOURS = WK_HOURS;
		this.CAP_HOUR = CAP_HOUR;
	}

	public DSPM321(String pno, String odNo, String odLot,String lNO, double pLAN_SEQ, String cX_BDATE,
			String cX_EDATE, double dIFF_ETD, String mATE_DATE,
			String zC_EDATE, String zC_BDATE, String m_MDATE, String zD_EDATE,
			String oS_EDATE, String sE_EDATE, String cZ_EDATE,
			String pL_STATUS, double dIFF_OSDATE, String pRO_WKSEQ,
			String zD_BDATE, String sE_BDATE, String cFM_DATE1,
			String cFM_DATE2, String cX_EHOUR, double dIFF_HOURS,
			String cZ2_BDATE, double zC_HOURS, double dIFF_ZCDATE,
			double wK_HOURS, double cAP_HOUR,String cZ2_EDATE) {
		super(pno, odNo, odLot);
		LNO = lNO;
		PLAN_SEQ = pLAN_SEQ;
		CX_BDATE = cX_BDATE;
		CX_EDATE = cX_EDATE;
		DIFF_ETD = dIFF_ETD;
		MATE_DATE = mATE_DATE;
		ZC_EDATE = zC_EDATE;
		ZC_BDATE = zC_BDATE;
		M_MDATE = m_MDATE;
		ZD_EDATE = zD_EDATE;
		OS_EDATE = oS_EDATE;
		SE_EDATE = sE_EDATE;
		CZ_EDATE = cZ_EDATE;
		PL_STATUS = pL_STATUS;
		DIFF_OSDATE = dIFF_OSDATE;
		PRO_WKSEQ = pRO_WKSEQ;
		ZD_BDATE = zD_BDATE;
		SE_BDATE = sE_BDATE;
		CFM_DATE1 = cFM_DATE1;
		CFM_DATE2 = cFM_DATE2;
		CX_EHOUR = cX_EHOUR;
		DIFF_HOURS = dIFF_HOURS;
		CZ2_BDATE = cZ2_BDATE;
		ZC_HOURS = zC_HOURS;
		DIFF_ZCDATE = dIFF_ZCDATE;
		WK_HOURS = wK_HOURS;
		CAP_HOUR = cAP_HOUR;
		CZ2_EDATE = cZ2_EDATE;
	}

	/**
	 * @return the lNO
	 */
	public String getLNO() {
		return LNO;
	}
	/**
	 * @param lNO the lNO to set
	 */
	public void setLNO(String lNO) {
		LNO = lNO;
	}
	/**
	 * @return the pLAN_SEQ
	 */
	public double getPLAN_SEQ() {
		return PLAN_SEQ;
	}
	/**
	 * @param pLAN_SEQ the pLAN_SEQ to set
	 */
	public void setPLAN_SEQ(double pLAN_SEQ) {
		PLAN_SEQ = pLAN_SEQ;
	}
	/**
	 * @return the oD_LQTY
	 */
	public double getOD_LQTY() {
		return OD_LQTY;
	}
	/**
	 * @param oD_LQTY the oD_LQTY to set
	 */
	public void setOD_LQTY(double oD_LQTY) {
		OD_LQTY = oD_LQTY;
	}
	/**
	 * @return the sH_ARITCLENO
	 */
	public String getSH_ARITCLENO() {
		return SH_ARITCLENO;
	}
	/**
	 * @param sH_ARITCLENO the sH_ARITCLENO to set
	 */
	public void setSH_ARITCLENO(String sH_ARITCLENO) {
		SH_ARITCLENO = sH_ARITCLENO;
	}
	/**
	 * @return the sH_NO
	 */
	public String getSH_NO() {
		return SH_NO;
	}
	/**
	 * @param sH_NO the sH_NO to set
	 */
	public void setSH_NO(String sH_NO) {
		SH_NO = sH_NO;
	}
	/**
	 * @return the oD_ACDAT
	 */
	public String getOD_ACDAT() {
		return OD_ACDAT;
	}
	/**
	 * @param oD_ACDAT the oD_ACDAT to set
	 */
	public void setOD_ACDAT(String oD_ACDAT) {
		OD_ACDAT = oD_ACDAT;
	}
	/**
	 * @return the eTD
	 */
	public String getETD() {
		return ETD;
	}
	/**
	 * @param eTD the eTD to set
	 */
	public void setETD(String eTD) {
		ETD = eTD;
	}
	/**
	 * @return the cX_BDATE
	 */
	public String getCX_BDATE() {
		return CX_BDATE;
	}
	/**
	 * @param cX_BDATE the cX_BDATE to set
	 */
	public void setCX_BDATE(String cX_BDATE) {
		CX_BDATE = cX_BDATE;
	}
	/**
	 * @return the cX_EDATE
	 */
	public String getCX_EDATE() {
		return CX_EDATE;
	}
	/**
	 * @param cX_EDATE the cX_EDATE to set
	 */
	public void setCX_EDATE(String cX_EDATE) {
		CX_EDATE = cX_EDATE;
	}
	/**
	 * @return the dIFF_ETD
	 */
	public double getDIFF_ETD() {
		return DIFF_ETD;
	}
	/**
	 * @param dIFF_ETD the dIFF_ETD to set
	 */
	public void setDIFF_ETD(double dIFF_ETD) {
		DIFF_ETD = dIFF_ETD;
	}
	/**
	 * @return the mATE_DATE
	 */
	public String getMATE_DATE() {
		return MATE_DATE;
	}
	/**
	 * @param mATE_DATE the mATE_DATE to set
	 */
	public void setMATE_DATE(String mATE_DATE) {
		MATE_DATE = mATE_DATE;
	}
	/**
	 * @return the zC_EDATE
	 */
	public String getZC_EDATE() {
		return ZC_EDATE;
	}
	/**
	 * @param zC_EDATE the zC_EDATE to set
	 */
	public void setZC_EDATE(String zC_EDATE) {
		ZC_EDATE = zC_EDATE;
	}
	/**
	 * @return the zC_BDATE
	 */
	public String getZC_BDATE() {
		return ZC_BDATE;
	}
	/**
	 * @param zC_BDATE the zC_BDATE to set
	 */
	public void setZC_BDATE(String zC_BDATE) {
		ZC_BDATE = zC_BDATE;
	}
	/**
	 * @return the m_MDATE
	 */
	public String getM_MDATE() {
		return M_MDATE;
	}
	/**
	 * @param m_MDATE the m_MDATE to set
	 */
	public void setM_MDATE(String m_MDATE) {
		M_MDATE = m_MDATE;
	}
	/**
	 * @return the zD_EDATE
	 */
	public String getZD_EDATE() {
		return ZD_EDATE;
	}
	/**
	 * @param zD_EDATE the zD_EDATE to set
	 */
	public void setZD_EDATE(String zD_EDATE) {
		ZD_EDATE = zD_EDATE;
	}
	/**
	 * @return the oS_EDATE
	 */
	public String getOS_EDATE() {
		return OS_EDATE;
	}
	/**
	 * @param oS_EDATE the oS_EDATE to set
	 */
	public void setOS_EDATE(String oS_EDATE) {
		OS_EDATE = oS_EDATE;
	}
	/**
	 * @return the sE_EDATE
	 */
	public String getSE_EDATE() {
		return SE_EDATE;
	}
	/**
	 * @param sE_EDATE the sE_EDATE to set
	 */
	public void setSE_EDATE(String sE_EDATE) {
		SE_EDATE = sE_EDATE;
	}
	/**
	 * @return the cZ_EDATE
	 */
	public String getCZ_EDATE() {
		return CZ_EDATE;
	}
	/**
	 * @param cZ_EDATE the cZ_EDATE to set
	 */
	public void setCZ_EDATE(String cZ_EDATE) {
		CZ_EDATE = cZ_EDATE;
	}
	/**
	 * @return the pL_STATUS
	 */
	public String getPL_STATUS() {
		return PL_STATUS;
	}
	/**
	 * @param pL_STATUS the pL_STATUS to set
	 */
	public void setPL_STATUS(String pL_STATUS) {
		PL_STATUS = pL_STATUS;
	}
	/**
	 * @return the dIFF_OSDATE
	 */
	public double getDIFF_OSDATE() {
		return DIFF_OSDATE;
	}
	/**
	 * @param dIFF_OSDATE the dIFF_OSDATE to set
	 */
	public void setDIFF_OSDATE(double dIFF_OSDATE) {
		DIFF_OSDATE = dIFF_OSDATE;
	}
	/**
	 * @return the pRO_WKSEQ
	 */
	public String getPRO_WKSEQ() {
		return PRO_WKSEQ;
	}
	/**
	 * @param pRO_WKSEQ the pRO_WKSEQ to set
	 */
	public void setPRO_WKSEQ(String pRO_WKSEQ) {
		PRO_WKSEQ = pRO_WKSEQ;
	}
	/**
	 * @return the zD_BDATE
	 */
	public String getZD_BDATE() {
		return ZD_BDATE;
	}
	/**
	 * @param zD_BDATE the zD_BDATE to set
	 */
	public void setZD_BDATE(String zD_BDATE) {
		ZD_BDATE = zD_BDATE;
	}
	/**
	 * @return the sE_BDATE
	 */
	public String getSE_BDATE() {
		return SE_BDATE;
	}
	/**
	 * @param sE_BDATE the sE_BDATE to set
	 */
	public void setSE_BDATE(String sE_BDATE) {
		SE_BDATE = sE_BDATE;
	}
	/**
	 * @return the cFM_DATE1
	 */
	public String getCFM_DATE1() {
		return CFM_DATE1;
	}
	/**
	 * @param cFM_DATE1 the cFM_DATE1 to set
	 */
	public void setCFM_DATE1(String cFM_DATE1) {
		CFM_DATE1 = cFM_DATE1;
	}
	/**
	 * @return the cFM_DATE2
	 */
	public String getCFM_DATE2() {
		return CFM_DATE2;
	}
	/**
	 * @param cFM_DATE2 the cFM_DATE2 to set
	 */
	public void setCFM_DATE2(String cFM_DATE2) {
		CFM_DATE2 = cFM_DATE2;
	}
	/**
	 * @return the cX_EHOUR
	 */
	public String getCX_EHOUR() {
		return CX_EHOUR;
	}
	/**
	 * @param cX_EHOUR the cX_EHOUR to set
	 */
	public void setCX_EHOUR(String cX_EHOUR) {
		CX_EHOUR = cX_EHOUR;
	}
	/**
	 * @return the dIFF_HOURS
	 */
	public double getDIFF_HOURS() {
		return DIFF_HOURS;
	}
	/**
	 * @param dIFF_HOURS the dIFF_HOURS to set
	 */
	public void setDIFF_HOURS(double dIFF_HOURS) {
		DIFF_HOURS = dIFF_HOURS;
	}
	/**
	 * @return the cZ2_BDATE
	 */
	public String getCZ2_BDATE() {
		return CZ2_BDATE;
	}
	/**
	 * @param cZ2_BDATE the cZ2_BDATE to set
	 */
	public void setCZ2_BDATE(String cZ2_BDATE) {
		CZ2_BDATE = cZ2_BDATE;
	}
	/**
	 * @return the zC_HOURS
	 */
	public double getZC_HOURS() {
		return ZC_HOURS;
	}
	/**
	 * @param zC_HOURS the zC_HOURS to set
	 */
	public void setZC_HOURS(double zC_HOURS) {
		ZC_HOURS = zC_HOURS;
	}
	/**
	 * @return the dIFF_ZCDATE
	 */
	public double getDIFF_ZCDATE() {
		return DIFF_ZCDATE;
	}
	/**
	 * @param dIFF_ZCDATE the dIFF_ZCDATE to set
	 */
	public void setDIFF_ZCDATE(double dIFF_ZCDATE) {
		DIFF_ZCDATE = dIFF_ZCDATE;
	}
	/**
	 * @return the wK_HOURS
	 */
	public double getWK_HOURS() {
		return WK_HOURS;
	}
	/**
	 * @param wK_HOURS the wK_HOURS to set
	 */
	public void setWK_HOURS(double wK_HOURS) {
		WK_HOURS = wK_HOURS;
	}
	/**
	 * @return the cAP_HOUR
	 */
	public double getCAP_HOUR() {
		return CAP_HOUR;
	}
	/**
	 * @param cAP_HOUR the cAP_HOUR to set
	 */
	public void setCAP_HOUR(double cAP_HOUR) {
		CAP_HOUR = cAP_HOUR;
	}
	
	
	/**
	 * @return the iSLOCK
	 */
	public String getISLOCK() {
		return ISLOCK;
	}

	/**
	 * @param iSLOCK the iSLOCK to set
	 */
	public void setISLOCK(String iSLOCK) {
		ISLOCK = iSLOCK;
	}

	/**
	 * @return the nEW_SHNO
	 */
	public String getNEW_SHNO() {
		return NEW_SHNO;
	}

	/**
	 * @param nEW_SHNO the nEW_SHNO to set
	 */
	public void setNEW_SHNO(String nEW_SHNO) {
		NEW_SHNO = nEW_SHNO;
	}

	/**
	 * @return the tL_EXP
	 */
	public String getTL_EXP() {
		return TL_EXP;
	}

	/**
	 * @param tL_EXP the tL_EXP to set
	 */
	public void setTL_EXP(String tL_EXP) {
		TL_EXP = tL_EXP;
	}

	/**
	 * @return the dEST_NAME
	 */
	public String getDEST_NAME() {
		return DEST_NAME;
	}

	/**
	 * @param dEST_NAME the dEST_NAME to set
	 */
	public void setDEST_NAME(String dEST_NAME) {
		DEST_NAME = dEST_NAME;
	}

	/**
	 * @return the p_MDATE
	 */
	public String getP_MDATE() {
		return P_MDATE;
	}

	/**
	 * @param p_MDATE the p_MDATE to set
	 */
	public void setP_MDATE(String p_MDATE) {
		P_MDATE = p_MDATE;
	}

	/**
	 * @return the sH_LAST
	 */
	public String getSH_LAST() {
		return SH_LAST;
	}

	/**
	 * @param sH_LAST the sH_LAST to set
	 */
	public void setSH_LAST(String sH_LAST) {
		SH_LAST = sH_LAST;
	}

	/**
	 * @return the sH_MODEL
	 */
	public String getSH_MODEL() {
		return SH_MODEL;
	}

	/**
	 * @param sH_MODEL the sH_MODEL to set
	 */
	public void setSH_MODEL(String sH_MODEL) {
		SH_MODEL = sH_MODEL;
	}
	
	/**
	 * @return the cZ2_EDATE
	 */
	public String getCZ2_EDATE() {
		return CZ2_EDATE;
	}

	/**
	 * @param cZ2_EDATE the cZ2_EDATE to set
	 */
	public void setCZ2_EDATE(String cZ2_EDATE) {
		CZ2_EDATE = cZ2_EDATE;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DSPM321 [PLAN_SEQ=" + PLAN_SEQ + ", OD_LQTY=" + OD_LQTY
				+ ", SH_ARITCLENO=" + SH_ARITCLENO + ", SH_NO=" + SH_NO
				+ ", OD_ACDAT=" + OD_ACDAT + ", ETD=" + ETD + ", CX_BDATE="
				+ CX_BDATE + ", CX_EDATE=" + CX_EDATE + ", DIFF_ETD="
				+ DIFF_ETD + ", MATE_DATE=" + MATE_DATE + ", DIFF_ZCDATE="
				+ DIFF_ZCDATE + ", WK_HOURS=" + WK_HOURS + ", CAP_HOUR="
				+ CAP_HOUR + "]";
	}
    
    
}