package com.cn.hansontable.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hansontable.dao.CommonDSPMMapper;
import com.cn.hansontable.domain.CXDATE;
import com.cn.hansontable.domain.DSPM13;
import com.cn.hansontable.domain.DSPM141;
import com.cn.hansontable.domain.DSPM321;
import com.cn.hansontable.domain.DSSC17;
import com.cn.hansontable.domain.PM121;
import com.cn.hansontable.domain.PM20OC;
import com.cn.hansontable.domain.PM321ANDPM20;
import com.cn.hansontable.service.CommonDSPMService;
import com.cn.hansontable.utils.PUBLIC;
/**
                            _ooOoo_
                           o8888888o
                           88" . "88
                           (| -_- |)
                            O\ = /O
                        ____/`---'\____
                      .   ' \\| |// `.
                       / \\||| : |||// \
                     / _||||| -:- |||||- \
                       | | \\\ - /// | |
                     | \_| ''\---/'' | |
                      \ .-\__ `-` ___/-. /
                   ___`. .' /--.--\ `. . __
                ."" '< `.___\_<|>_/___.' >'"".
               | | : `- \`.;`\ _ /`;.`/ - ` : | |
                 \ \ `-. \_ __\ /__ _/ .-` / /
         ======`-.____`-.___\_____/___.-`____.-'======
                            `=---='

         .............................................
                  佛祖保佑             永无BUG
          佛曰:
                  写字楼里写字间，写字间里程序员；
                  程序人员写程序，又拿程序换酒钱。
                  酒醒只在网上坐，酒醉还来网下眠；
                  酒醉酒醒日复日，网上网下年复年。
                  但愿老死电脑间，不愿鞠躬老板前；
                  奔驰宝马贵者趣，公交自行程序员。
                  别人笑我忒疯癫，我笑自己命太贱；
                  不见满街漂亮妹，哪个归得程序员？
*/
@Service("CommonDSPMService") 
public class CommonDSPMServiceImpl implements CommonDSPMService{

	@Resource
	private CommonDSPMMapper mapper;
	PUBLIC compub = new PUBLIC();

	@Override
	public int geSC17Days(String LNO, String WKDATE1, String WKDATE2) {
		int days = 0;
		List<Integer> sc17 = mapper.getSC17Days(LNO, WKDATE1, WKDATE2);
		for(int hours : sc17) {
			  if (hours > 0) {
				  days++;
			  }
			}
		return days;
	}

	@Override
	public void RefreshNewSHNO(String PNO, String SHNO, String LNO,
			String CXDATE) {
		String SHOEID= mapper.getSHOE_ID(SHNO);
		if (SHOEID.equals("")) {
			return;
		}
		mapper.deDSPM141(PNO, LNO, SHOEID);
		List<CXDATE> list = mapper.findCXDATE(PNO, SHOEID, LNO, CXDATE);
		CXDATE cxdate = new CXDATE();
		String CXBDATE= "",CXEDATE= "";
		for(int i=0;i<list.size();i++) {
			if(CXBDATE.equals("")){
				cxdate= list.get(i);
        		CXBDATE=cxdate.getCX_BDATE();
        		CXEDATE=cxdate.getCX_EDATE();
        	}else{
        		if(CXEDATE.equals(cxdate.getCX_BDATE())){
	        		CXEDATE=cxdate.getCX_EDATE();
	        	}else{
	        		CXBDATE=cxdate.getCX_BDATE();
	        		CXEDATE=cxdate.getCX_EDATE();
	        	}
        	}
		}
		int LPCT= 0,PER= 0;
		PER = mapper.getPER(LNO, SHOEID)+geSC17Days(LNO,CXBDATE,CXEDATE);
		String clum = "D"+PER;
		if(PER>30){
        	LPCT= 100;
        }else if(PER<=0){
        	LPCT= 0;
        }else{
        	if(LPCT==-1){
	        	LPCT= 100;
	        }else{
	        	LPCT= mapper.getLPCT(clum,SHOEID);
	        }
        }
		mapper.inDSPM141(PNO, SHOEID, LNO, LPCT, PER, CXEDATE);
	}

	@Override
	public String RefreshLNO(String PNO, String LNO) {
		String strMSG="";
		List<DSPM321> updateDSPM321=new ArrayList<DSPM321>();
		DSPM321 dspm321;
		mapper.dePM321(PNO, LNO);
		double PLAN_SEQ= 0;
		double WKHOURS= 0;//累計耗時
		String TMPCXEDATE="",BCXEDATE="";
		String[] ARRLNODATE=new String[2];
		String BDATE="";
	    String PROCID= mapper.GETPNO("PROCID",PNO);    	
	    List<String> ListSHNO=new ArrayList<String>();  
	    List<PM321ANDPM20> list = mapper.refreshInfo(PNO, LNO);
	    PM321ANDPM20 info = new PM321ANDPM20();
	    for(int i=0;i<list.size();i++) {
	    	if(i==0){
					WKHOURS=0;
					BDATE=mapper.GETPNO("BDATE",PNO);
					BCXEDATE=BDATE;
					PLAN_SEQ=PLAN_SEQ+1;	
					ARRLNODATE[0]=BCXEDATE;//成型日期進度
					ARRLNODATE[1]= Double.toString(WKHOURS);//最後一天佔用時數						
			}else{
				PLAN_SEQ=PLAN_SEQ+1;						
			}
	    	info = list.get(i);
	    	String OD_NO = info.getOD_NO();
    		String OD_LOT = info.getOD_LOT();
    		String SHNO = info.getSH_MODEL();
    		double HOURS = info.getWK_HOURS();
    		double OD_LQTY = info.getOD_LQTY();	 
    		
    		//成型上線日
			String CXBDATE="";
			if(TMPCXEDATE.equals("")){
				if(!BCXEDATE.equals("")){
					CXBDATE=BCXEDATE;							    		
				}
			}else{
				CXBDATE=TMPCXEDATE;
			}
			
			boolean NEWSHNO=false;
			String[] arrPM13=getPM13(PNO,LNO,SHNO);										    	    			
			double PM13OUT= compub.getDouble(arrPM13[0]); 
			double ZCPMOUT= compub.getDouble(arrPM13[3]);//針車時產能
			double ZCHOURS=0;//針車耗時
			if(ZCPMOUT>0){
				ZCHOURS=OD_LQTY/ZCPMOUT;
			}
			
			String SHLEVEL=arrPM13[1];					
			if(!mapper.C06().equals("") || SHLEVEL.equals("") || SHLEVEL.equals("1")){//舊型體
				double [] ARRHOURS= getPMHOURS(LNO,OD_LQTY,PM13OUT,compub.getDouble(arrPM13[4]),ARRLNODATE[0],compub.getDouble(ARRLNODATE[1]));
				HOURS=ARRHOURS[0];
				PM13OUT=ARRHOURS[1];
			}else{//新型體 需考慮型體成長曲線	
				String SHOEID= mapper.getSHOE_ID(SHNO);//按系列成長
				if(ListSHNO.indexOf(SHOEID)<0){
    				RefreshNewSHNO(PNO,SHNO,LNO,CXBDATE);
    				ListSHNO.add(SHOEID);
    			}	   
				double LPCT= mapper.getSHNOLPCT(SHNO,LNO,PROCID);
				if(LPCT>=100){//已經成長為舊型體, 直接按舊型體產線搭配計算							
					if(PM13OUT>0){
						double [] ARRHOURS= getPMHOURS(LNO,OD_LQTY,PM13OUT,compub.getDouble(arrPM13[4]),ARRLNODATE[0],compub.getDouble(ARRLNODATE[1]));
	    				HOURS=ARRHOURS[0];
	    				PM13OUT=ARRHOURS[1];
    				}else if(PM13OUT<=0){
    					if(!strMSG.equals("")){
    	    				strMSG=strMSG+"\n";
    	    			}
    					strMSG=strMSG+"指令號"+"/"+"線別"+"/"+"型體"+":"+OD_NO+"/"+LNO+"/"+SHNO+"未建立產線搭配產能!";
    					continue;
					}
				}else{						
					double[] arrPM14= getSHNOPM14(SHNO,LNO,OD_LQTY,"59",PM13OUT,ARRLNODATE[0],compub.getDouble(ARRLNODATE[1]));
	    			HOURS=RoundDecimal(arrPM14[0]);
					if(HOURS<=0){					
						strMSG=OD_NO+":"+"型體"+":"+SHNO+"未建立型體成長曲線或建立有誤!";
					}else{
						PM13OUT=(int)Math.ceil(OD_LQTY/HOURS);
					}
					NEWSHNO=true;
				}    	    			
			}
			
			WKHOURS=compub.getDouble(ARRLNODATE[1])+HOURS;
    		String[] ARRCXEDT= getCXEDateBySC17(LNO,CXBDATE,WKHOURS);
			String CXEDATE=ARRCXEDT[0];
			String CXEHOUR=ARRCXEDT[1];
    		TMPCXEDATE=CXEDATE;
    		if(NEWSHNO){//為新型體且同型體指令才需重計
    			UpdateDSPM141( PNO, SHNO, LNO, "59","2",CXBDATE,CXEDATE);
    		}
    		
    		String[] ARRDATE= getPM321DATE_FVA(PNO,LNO,OD_NO, CXBDATE, CXEDATE);
	    	String ZCBDATE=ARRDATE[0]; //針車上線日	
			String ZCEDATE=ARRDATE[1]; //針車完成日
			String ZDBDATE=ARRDATE[2]; //組底上線日
			String ZDEDATE=ARRDATE[3]; //組底完成日
			String SEBDATE=ARRDATE[4]; //二次加工上線日
			String SEEDATE=ARRDATE[5]; //二次加工完成日
			String MMDATE=ARRDATE[6];  //最晚面材到廠日M_MDATE
			String MATEDATE=ARRDATE[7];//資材日MATE_DATE
			String OSEDATE=ARRDATE[8]; //底材交期OS_EDATE
			String CZEDATE=ARRDATE[9]; //裁準完成日（CZ_EDATE)
			String CZ2BDATE=ARRDATE[11]; //加工裁斷開斬/完成日期=加工上線日 - 6/3
			String CZ2EDATE=ARRDATE[12]; //加工裁斷開斬/完成日期=加工上線日 - 6/3
			
    	    String ETD= info.getETD();
    		//出貨差異
    		String DIFFETD= Integer.toString(geSC17Days(LNO,ETD,CXEDATE));//出貨差異	    	
    		//資材差異	    	
    		String ZCDATE=MMDATE;
	    	if(!MATEDATE.equals("")){
	    		ZCDATE=MATEDATE;
	    	}
    		int DIFFZCDATE=mapper.CompareDate(CXBDATE, ZCDATE);
    		//排程異常
	    	String PLSTATUS="";
	    	String PROWKDAY= getWKSEQ(CXBDATE);
	    	if(compub.getDouble(DIFFETD)<compub.getDouble(mapper.B01())){//出貨差異小於B02
	    		PLSTATUS=PLSTATUS+"A";
	    	}	    	
	    	if(compub.getInteger(DIFFZCDATE)<compub.getInteger(mapper.B07())){//最晚面材到廠日M_MDATE >預計面材到廠日  需考慮色卡日P_MDATE
	    		PLSTATUS=PLSTATUS+"B";
	    	}
	    	double DIFFOSDATE=0;
	    	if(DIFFOSDATE<0){
	    		PLSTATUS=PLSTATUS+"C";
	    	}
	    	String CFMDATE1="",CFMDATE2="";
	    	String RGAC= info.getRGAC();
	    	String OGAC= info.getOGAC();
	    	if(RGAC==null){
	    		RGAC= "";
	    	}
	    	if(OGAC==null){
	    		OGAC= "";
	    	}
	    	if(OGAC.equals("")){
	    		CFMDATE1=getOGAC(CXEDATE,RGAC);//不加模OGAC CFM_DATE1
	    		CFMDATE2="";
	    	}else{
	    		CFMDATE1=OGAC;//不加模OGAC CFM_DATE1
	    		CFMDATE2=OGAC;
	    	}
	   	
		   	dspm321=new DSPM321(PNO,OD_NO,OD_LOT,LNO,PLAN_SEQ,CXBDATE,CXEDATE,
		   			compub.getDouble(DIFFETD),MATEDATE,ZCEDATE,ZCBDATE,MMDATE,ZDEDATE,OSEDATE,SEEDATE,
		   			CZEDATE,PLSTATUS,DIFFOSDATE,PROWKDAY,ZDBDATE,SEBDATE,CFMDATE1,CFMDATE2,
		   			CXEHOUR,getSC17HOURS(LNO,ETD,CXEDATE,compub.getDouble(CXEHOUR)),CZ2BDATE,
		   			ZCHOURS,DIFFZCDATE,HOURS,PM13OUT,CZ2EDATE);
		   	updateDSPM321.add(dspm321);
	    }
	    mapper.updatePM321(updateDSPM321);
		return strMSG+" "+LNO+"重整完成!";
	}

	@Override
	public String[] getPM13(String PNO, String LNO, String SHNO) {
		String[] arrPM13=new String[5]; 
		List<DSPM13> list;
		list = mapper.getPM13(PNO, SHNO, LNO);
		if(!list.isEmpty()){
			arrPM13[0]=list.get(0).getPM_OUTPUT();//成型生管時產能
        	arrPM13[1]=list.get(0).getSH_LEVEL();//新舊型體
        	arrPM13[2]=list.get(0).getTR_OUTPUT();//成型現場時產能
        	arrPM13[3]=list.get(0).getPM_UOUTPUT();//生管時產能(半成品)
        	arrPM13[4]=list.get(0).getPM_OUTPUT1();//生管時產能(半成品並行時)
		}else {
			List<PM121> pm121 = mapper.getPM121(SHNO);
			if(!pm121.isEmpty()) {
				arrPM13[0]=pm121.get(0).getSH_PMOUT();//成型生管時產能
	        	arrPM13[1]="";
	        	arrPM13[2]=pm121.get(0).getSH_PMOUT();//成型生管時產能
	        	arrPM13[3]=pm121.get(0).getZC_PMOUT();//生管時產能(半成品)
	        	arrPM13[4]="";
	        }else{
	        	arrPM13[0]="";
	        	arrPM13[1]="";
	        	arrPM13[2]="";
	        	arrPM13[3]="";
	        	arrPM13[4]="";
	        }
		}
		return arrPM13;
	}

	@Override
	public String getOGAC(String CXEDATE, String RGAC) {
		String OGAC="";
		if(mapper.CompareDate(CXEDATE, RGAC)<=0){
			if(!RGAC.equals("") && getWKDAY(RGAC)==6){//如果是週六
				OGAC=mapper.ADDDate(RGAC, -compub.getInteger(mapper.A16_1()));
			}else{
				OGAC=RGAC;
			}
		}else{
			OGAC=mapper.ADDDate(CXEDATE, -compub.getInteger(mapper.A16_2()));
		}
		return OGAC;
	}
	
	public double RoundDecimal(double qty) {
		return qty;
	}
	
	public  int getWKDAY(String strdate){
		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy/MM/dd" ); 
		Date date=null;
        try {
        	date=sdf.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0){
			dayofweek = 7;
		}
		return dayofweek;
	}
	
	public  String getWKSEQ(String strdate){
		if(strdate.equals("")){
			return "";
		}
		String E03= mapper.E03();//成型周每週起始日(週一/週日)(1/7)
		int E03WK=compub.getInteger(E03);
        if(E03WK==7){
        	E03WK=0;
        }
        SimpleDateFormat sdf =new SimpleDateFormat( "yyyy/MM/dd" ); 
        Date date=null;
        try {
        	date=sdf.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0){
			dayofweek = 7;
		}
		cal.add(Calendar.DATE, -dayofweek+E03WK);		
		return sdf.format(cal.getTime());
	}

	@Override
	public double[] getPMHOURS(String LNO, double ODLQTY, double PMOUTPUT,
			double PMOUTPUT1, String WKDATE, double HOURS) {
		double PMHOURS=0,OUTPUT=0;
    	double NUMHOUR=0,PMQTY=0;
    	List<DSSC17> list = mapper.getPMHOURS(LNO, WKDATE);
    	DSSC17 sc17 = new DSSC17();
    	    for(int i=0;i<list.size();i++) {   
    	    	sc17 = list.get(i);
    	    	double WKHOURS= compub.getDouble(sc17.getWK_T())+compub.getDouble(sc17.getWK_ADDT());
    	    	if(WKHOURS>0){
    	    		String STRDT= sc17.getWK_DATE();
    	    		if(STRDT.equals(WKDATE)){
						NUMHOUR=(WKHOURS-HOURS);    	    						
					}else{
						NUMHOUR=WKHOURS;    	  
					}
    	    			PMQTY=NUMHOUR*PMOUTPUT;
    					if(ODLQTY-PMQTY>0){
							PMHOURS=PMHOURS+NUMHOUR;
							ODLQTY=ODLQTY-PMQTY;
						}else{
							PMHOURS=PMHOURS+ODLQTY/PMOUTPUT;
							ODLQTY=0;
						}
    					if(OUTPUT<=0){
    						OUTPUT=PMOUTPUT;
    					}
    	    		if(ODLQTY<=0){
    	    			break;
    	    		}
    	    	}    	    	
    	    }
        return new double[]{PMHOURS,OUTPUT};
	}

	@Override
	public double[] getSHNOPM14(String SHNO, String LNO, double QTY,
			String PROCID, double PM13OUT, String BDATE, double BHOURS) {
		double[] arrPM14=new double[2]; 
		arrPM14[0]=0; arrPM14[1]=0;
		String SHOEID=mapper.getSHOE_ID(SHNO);
	        double LHOURS=0,LPCT=0;
	        String CXDATE="";
	        List<DSPM141> pm141s = mapper.getPM141(SHNO, LNO, PROCID);
	        if(!pm141s.isEmpty()){	
	        	LHOURS= pm141s.get(0).getLHOURS();
	        	LPCT= pm141s.get(0).getL_PCT();
	        	CXDATE= pm141s.get(0).getCXDATE();
	        }
	        if(LPCT>=100){
	        	arrPM14[0]=QTY/PM13OUT;
	        	arrPM14[1]=100;
	        	return arrPM14;	    
	        }
	        int lcpt = mapper.getLPCT("D1", SHOEID);
	        if(lcpt!=-1){		        	
	        	int DAYS=(int)Math.floor(LHOURS)+1;//計算當前位於第幾天
	        	if(CXDATE.equals(BDATE)){//同一天接著成長
	        		DAYS=(int)Math.floor(LHOURS);//計算當前位於第幾天
	        	}
	        	List<DSSC17> list = mapper.getPMHOURS(LNO, BDATE);
		        int s =DAYS;
		        DSSC17 sc17 = new DSSC17();
		        for(int i=0;i<list.size();i++) { 
		        	sc17 = list.get(i);
		        	double HOURS= compub.getDouble(sc17.getWK_T())+compub.getDouble(sc17.getWK_ADDT());
		        	if(HOURS>0){
		        		double LHOUR=0;
		        		if(i==0){
		        			LHOUR=HOURS-BHOURS;//當天剩餘時數
		        		}else{
		        			LHOUR=HOURS;
		        		}
		        		double PQTY=0;
		        		if(QTY>0){//還有欠數
		        			double CPER=PM13OUT;
		        			if(i<=30 && mapper.getLPCT("D"+s, SHOEID)>0){//有成長
			        			CPER=PM13OUT*(mapper.getLPCT("D"+s, SHOEID)/100);
			        			arrPM14[1]=mapper.getLPCT("D"+s, SHOEID);			
		        			}else{
		        				arrPM14[1]=100;			
		        			}
		        			PQTY=LHOUR*CPER;	
		        			if(PQTY>=QTY){
		        				arrPM14[0]=arrPM14[0]+QTY/CPER;	
		        				QTY=0;			        						        				
		        			}else{
		        				QTY=QTY-PQTY;
		        				arrPM14[0]=arrPM14[0]+LHOUR;
		        			}			        				        		
		        		}else{
		        			break;
		        		}
		        		i++;
		        	}
		        }
	        }
		return arrPM14;	 
	}

	@Override
	public void UpdateDSPM141(String PNO, String SHNO, String LNO,
			String PROCID, String PTYPE, String CXBDATE, String CXEDATE) {
			String SHOEID= mapper.getSHOE_ID(SHNO);
			List<DSPM141> pm141s = mapper.getPM141(SHNO, LNO, PROCID);
	        int TDAY=0;double L_PCT=0;
	        String CXDATE="";
	        DSPM141 dspm141 = pm141s.get(0);
	        if(dspm141!=null){	
	        	TDAY= (int) dspm141.getLHOURS();	     
	        	L_PCT= dspm141.getL_PCT();	
	        	CXDATE= dspm141.getCXDATE();	
	        }
	        if(L_PCT>=100){
	        	return;
	        }	
	        int Days= geSC17Days(LNO,CXBDATE,CXEDATE);
	        if(!CXDATE.equals("") && CXDATE.equals(CXBDATE)){
	        	Days=Days-1;//同一天，需接著成長
	        }
	        TDAY= TDAY+Days;
	        int lpct= mapper.getLPCT("D"+TDAY, SHOEID);
	        double LPCT= 0;
	        if(lpct!= -1){		        	
	        	if(TDAY>30){
	        		LPCT=100;
	        	}else if(TDAY<=0){
	        		LPCT=0;
	        	}else{
	        		if(Integer.toString(lpct).equals("")){
		        		LPCT=100;
		        	}else{
		        		LPCT= lpct;
		        	}
	        	}	        	
	        }
	        int num= mapper.updateDSPM141(PNO, SHOEID, LNO, LPCT, Days, CXEDATE);
	        if(num<=0){
	           	mapper.inDSPM141(PNO, SHOEID, LNO, LPCT, Days, CXEDATE);
	        }    	    								
	}

	@Override
	public String[] getCXEDateBySC17(String LNO, String WKDATE, double HOURS) {
		String STRDT="";
		double CXEHOURS=0;
    	double NUMHOUR=0;
    	List<DSSC17> list = mapper.getPMHOURS(LNO, WKDATE);
    	DSSC17 sc17 = new DSSC17();
    	for(int i=0;i<list.size();i++) { 
    		sc17 = list.get(i);
    	    double WKHOURS= compub.getDouble(sc17.getWK_T())+compub.getDouble(sc17.getWK_ADDT());
    	    if(WKHOURS>0){
    	    	STRDT= sc17.getWK_DATE();
    	    	NUMHOUR=NUMHOUR+WKHOURS;
        	    if(NUMHOUR>=HOURS){
        	    	CXEHOURS=WKHOURS-(NUMHOUR-HOURS);
        	    	break;
        	    }
    	    }    	    	
    	}
        String[] ARRRESULT=new String[]{STRDT,Double.toString(CXEHOURS)};
        return ARRRESULT;
	}

	@Override
	public String[] getPM321DATE_FVA(String PNO, String LNO, String ODNO,
			String CXBDATE, String CXEDATE) {
		String[] ARRDATE= new String[13];
		String ZCBDATE= ADDDateSC17(LNO, CXBDATE, -compub.getDouble(mapper.B02()));//針車上線日		
		String ZCEDATE= ADDDateSC17(LNO, CXEDATE, -compub.getDouble(mapper.B02()));//針車完成日
		ARRDATE[0]= ZCBDATE;  
		ARRDATE[1]= ZCEDATE;
		ARRDATE[2]= ADDDateSC17(LNO, CXBDATE, -compub.getDouble(mapper.B03()));//組底上線日
		ARRDATE[3]= ADDDateSC17(LNO, CXEDATE, -compub.getDouble(mapper.B03()));//組底完成日       
		String MMAXDATE="",DMAXDATE="";
		List<PM20OC> pm20ocs = mapper.getPM20OC(PNO, ODNO);
		PM20OC pm20 = pm20ocs.get(0);
	    String ODACDAT="";
	    if(pm20!=null){	
	        if(pm20.getOD_ACDAT()!=null){
	        	ODACDAT= pm20.getOD_ACDAT();
	        	String OD_FROM= pm20.getOD_FROM();
	        	if(OD_FROM.equals("2") || ((OD_FROM.equals("1")) && ODNO.endsWith("AA"))){
	        		ARRDATE[6]=ODACDAT;
	        	}else if(ODNO.startsWith("FZS")){//開發訂單 不考慮資材日 fva
	        		ARRDATE[6]=ODACDAT;
	        	}else if(ODNO.startsWith("FZ-") && ODNO.endsWith("M")){//短交期訂單 FVA
	        		ARRDATE[6]= mapper.ADDDate(ODACDAT,compub.getInteger(mapper.B12_2()));
	        	}else{
	        		ARRDATE[6]= mapper.ADDDate(ODACDAT,compub.getInteger(mapper.B12()));
	        	}
	        }	        	
	    }
	    double WORKDAYS=0;
	    ARRDATE[4]="";//二次加工上線日        二次加工上線日=針車上線日-B04
        ARRDATE[5]="";//二次加工完成日= 針車完成日– [參數B04]   (不含節假日，以工作日計)
    	ARRDATE[11]="";//加工裁斷開斬/完成日期=加工上線日 - 6/3
		ARRDATE[12]="";//加工裁斷開斬/完成日期=加工上線日 - 6/3
		String workdays = mapper.getWorkDays(ODNO);
	    if(workdays!=null){	
	        WORKDAYS= compub.getDouble(workdays);	        	
	        ARRDATE[4]= ADDDateSC17(LNO, ZCBDATE, -WORKDAYS);//二次加工上線日        二次加工上線日=針車上線日-B04
	    	ARRDATE[5]= ADDDateSC17(LNO, ZCEDATE, -WORKDAYS);//二次加工完成日= 針車完成日– [參數B04]   (不含節假日，以工作日計)
	    	ARRDATE[11]= ADDDateSC17(LNO, ARRDATE[4], -compub.getDouble(mapper.B30()));//加工裁斷開斬/完成日期=加工上線日 - 6/3
			ARRDATE[12]= ADDDateSC17(LNO, ARRDATE[5], -compub.getDouble(mapper.B30()));//加工裁斷開斬/完成日期=加工上線日 - 6/3
	    }
		String mmaxdate = mapper.getPM08MAXDATE(ODNO);
	    if(mmaxdate!=null){	
	        MMAXDATE= mmaxdate;	        		
	    }
	    String dmaxdate = mapper.getPM07MAXDATE(ODNO);
	    if(dmaxdate!=null){	
	        DMAXDATE= dmaxdate;	        		
	    }
		ARRDATE[7]= MMAXDATE;
		ARRDATE[8]= ADDDateSC17(LNO, CXEDATE, -compub.getDouble(mapper.B14()));//底材交期OS_EDATE（底化工完成日）成型完成日 – [參數B14]     (不含節假日，以工作日計)
		ARRDATE[9]= ADDDateSC17(LNO, ZCEDATE, -compub.getDouble(mapper.B05()));//CZ_EDATE 裁準完成日 裁準完成日（CZ_EDATE）=針車完成日–[參數B05]       (不含節假日，以工作日計)
		ARRDATE[10]= DMAXDATE;
		return ARRDATE;
	}

	@Override
	public double getSC17HOURS(String LNO, String WKDATE1, String WKDATE2,
			double FHOURS) {
		if(WKDATE1.equals("") || WKDATE2.equals("")){
			return 0;
		}
		int num=0; double HOURS=0;
		String WKDATE="",MWKDATE="";
		if(mapper.CompareDate(WKDATE1,WKDATE2)>0){
			WKDATE=WKDATE2;
			MWKDATE=WKDATE1;
			num=1;
		}else{
			WKDATE=WKDATE1;
			MWKDATE=WKDATE2;
			num=-1;
		}		
		List<DSSC17> list = mapper.getPMHOURS(LNO, WKDATE);
    	DSSC17 sc17 = new DSSC17();
    	for(int i=0;i<list.size();i++) {    	    	
    		sc17 = list.get(i);
    	    double WKHOURS= compub.getDouble(sc17.getWK_T())+compub.getDouble(sc17.getWK_ADDT());
    	    String STRDT= sc17.getWK_DATE();    	    	
	    	if(WKHOURS>0){
	    		if(STRDT.equals(WKDATE)){
	    			HOURS=HOURS+(WKHOURS-FHOURS);
	    	    }else{
	    	    	HOURS=HOURS+WKHOURS;
	    	    }
	    	}
    	    if(STRDT.equals(MWKDATE)){
    	    	break;
    	    }else if(mapper.CompareDate(STRDT,MWKDATE)>0){
    	    	HOURS=HOURS-WKHOURS;
    	    	break;
    	    }
    	}
    	 
        return num*HOURS;
	}

	@Override
	public String ADDDateSC17(String LNO, String WKDATE, double DAYS) {
		String STRDT="";
		List<DSSC17> list = mapper.getPMHOURS(LNO, WKDATE);
    	DSSC17 sc17 = new DSSC17();
        if(DAYS<0){
        	list = mapper.getPMHOURS2(LNO, WKDATE);
        }
    	int count=0;    	  
    	for(int i=0;i<list.size();i++) { 
    		sc17 = list.get(i);
    	    if(compub.getDouble(sc17.getWK_T())>0){
    	    STRDT= sc17.getWK_DATE();
        	count=count+1;
        	if(count>Math.abs(DAYS)){
        	    	break;
        	    }
    	    }    	    	
    	}
        return STRDT;
	}

	@Override
	public void deDSPM141A(String PNO, String LNO) {
		mapper.deDSPM141A(PNO, LNO);
	}
	
	/**
	 * 更新前端资料至DSPM321
	 * @param updateDSPM321
	 * @return
	 */
	public int updateDSPM321(List<DSPM321> updateDSPM321) {
		return mapper.updateDSPM321(updateDSPM321);
	}
	/**
	 * 保存资料
	 */
	@Transactional             //事務註解
	public String saveData(List<DSPM321> updateDSPM321) {
		int update321 = updateDSPM321(updateDSPM321);
		if(update321== 0) {
			return "fail";
		}
		return "success";
	}
}
