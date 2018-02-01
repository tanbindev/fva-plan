package com.cn.hansontable.domain;

public class DSPM321Key {
    private String pno;

    private String odNo;

    private String odLot;

    public DSPM321Key() {
    	
    }
    
    public DSPM321Key(String pno, String odNo, String odLot) {
        this.pno = pno;
        this.odNo = odNo;
        this.odLot = odLot;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno == null ? null : pno.trim();
    }

    public String getOdNo() {
        return odNo;
    }

    public void setOdNo(String odNo) {
        this.odNo = odNo == null ? null : odNo.trim();
    }

    public String getOdLot() {
        return odLot;
    }

    public void setOdLot(String odLot) {
        this.odLot = odLot == null ? null : odLot.trim();
    }
}