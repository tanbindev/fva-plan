package com.cn.hansontable.utils;

import java.sql.Clob;

public class PUBLIC {
	public  String getValue(Object o) {
		if (o == null)
			return "";
		if (o instanceof String) {
			return (String) o;
		} else if (o instanceof Clob) {
			try {
				Clob c = (Clob) o;
				return c.getSubString(1, (int) c.length());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return o.toString();
	}
	public  Double getDouble(Object o) {
	     if (o == null)
	         return 0.0;
	     if( o.toString().equals(""))
	    	 return 0.0;
	     return Double.valueOf( o.toString());
	}
	public  Integer getInteger(Object o) {
	     if (o == null)
	         return 0;
	     if( o.toString().equals(""))
	    	 return 0;
	     return Integer.valueOf( o.toString());
	}
}
