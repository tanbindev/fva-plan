var ssLNO = "1001";
$("#SLNO").text(ssLNO);
var clipboardCache = '';	//cut的資料
var sheetclip = new SheetClip();  //獲取剪切板資料相關
var CutRows="";   //cut的資料行數
var storage = window.sessionStorage;  //本地存儲
var pno;
$("#tabs").tabs();

$.ajax({
        type: "post",
        url: "Fpnos",
        async: false,
        dataType: "text",
        data: "json",
        success: function(Result) {
                var pnos = eval(Result);
                pno = pnos[0].pno;
                $(function() {
                    //自杀
                    // $("#J_TbData").remove();
                    for( var i = 0; i < pnos.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr></tr>");
                        if (pnos[i].status === '1') {
                            var sta = '生效';
                        } else {
                            sta = '已结案';
                        }
                        //往行里面追加 td单元格
                        $trTemp.append("<td>"+ pnos[i].pno +"</td>");
                        $trTemp.append("<td>"+ pnos[i].ex_PNO +"</td>");
                        $trTemp.append("<td>"+ sta +"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#J_TbData");
                    }
                    if (pnos[0].status === '1') {
                        $("#text-pno").text( pnos[0].pno).append('<span  class="label label-success" > 生效</span>') ;
                    } else {
                        $("#text-pno").text( pnos[0].pno).append('<span  class="label label-danger" > 已结案</span>') ;
                    }
                 });
        }
});

$(document).ready(function() {
    function getCarData(pno, lno) {
                var convertData = [];
                $.ajax({
                        url: "selectPM321?pno=" + pno + "&lno=" + lno,
                        type: "post",
                        async: false,
                        dataType: "json",
                        success: function(rdata) {
                                if (rdata !== "") {
                                        $.each(rdata.datas,
                                        function(index, node) {
                                                convertData.push({
                                                        PLAN_SEQ: node.plan_SEQ,
                                                        OD_NO: node.odNo,
                                                        SH_MODEL: node.sh_MODEL,
                                                        SH_ARITCLENO: node.sh_ARITCLENO,
                                                        SH_NO: node.sh_NO,
                                                        ETD: node.etd,
                                                        OD_ACDAT: node.od_ACDAT,
                                                        OD_LQTY: node.od_LQTY,
                                                        CX_BDATE: node.cx_BDATE,
                                                        CX_EDATE: node.cx_EDATE,
                                                        DIFF_ETD: node.diff_ETD,
                                                        MATE_DATE: node.mate_DATE,
                                                        DIFF_ZCDATE: node.diff_ZCDATE,
                                                        CAP_HOUR: node.cap_HOUR,
                                                        WK_HOURS: node.wk_HOURS,
                                                        M_MDATE: node.m_MDATE,
                                                        P_MDATE: node.p_MDATE,
                                                        OS_EDATE: node.os_EDATE,
                                                        DIFF_OSDATE: node.diff_OSDATE,
                                                        DEST_NAME: node.dest_NAME,
                                                        SH_LAST: node.sh_LAST,
                                                        ISLOCK: node.islock,
                                                        NEW_SHNO: node.new_SHNO
                                                });
                                        });
                                }
                        }
                });
                return convertData;
        }
    function getLnoData(pno) {
                var convertData = [];
                $.ajax({
                        url: "lnoInfo?pno=" + pno,
                        type: "post",
                        async: false,
                        dataType: "json",
                        success: function(rdata) {
                                if (rdata !== "") {
                                        $.each(rdata.datas,
                                        function(index, node) {
                                                convertData.push({
                                                        id: node.lno,
                                                        name: node.lname,
                                                        qtys: node.qty
                                                });
                                        });
                                }
                        }
                });
                return convertData;
        }
    $("#text-lno").text("1001线");
    var container = document.getElementById("example1");
    var container2 = document.getElementById("example2");
    var hot;
    var hot2;
    var settings = {
            data: getCarData(pno,'1001'),
            rowHeaders: true,
            colHeaders: ['顺序号', '指令号', '客户型体', '型号',
						'欠数', '接单日期', 'ETD', '成型上线日', '成型完成日', '出货差异','资材最晚入厂日','成型最早可上线日',
						'资材日CFM', '资材差异','大底需求日','大底差异','目的地','楦头', '小时产能', '耗工时','鞋型','是否锁定','新型体' ],
            autoWrapCol: true,
            autoWrapRow: true,
            //columnSorting : true,//[column],
			//sortIndicator : true,
            manualColumnResize: true,
            manualRowResize: true,
            outsideClickDeselects: true,
            fixedColumnsLeft: 0,
            fixedRowsTop: 0,
            currentRowClassName: "currentRow",
            currentColClassName: "currentCol",
            manualColumnFreeze: true,
            autoColumnSize: false,
            manualColumnMove: true,
            manualRowMove: true,
            dropdownMenu: true,
            filters: true,
            colWidths : [ 40, 100, 200, 110, 50, 90, 90, 90, 90,
                60, 90, 90, 90, 60, 90, 60 , 50, 90, 60, 50,
                120, 60 ,40],
            columns: [
            {
                    data: "PLAN_SEQ",
                    
                    type: "text"
            },
            {
                    data: "OD_NO",
                    
                    type: "text"
            },
            {
                    data: "SH_MODEL",
                    
                    type: "text"
            },
            {
                    data: "SH_NO",
                    
                    type: "text"
            },
            {
                    data: "OD_LQTY",
                    
                    type: "numeric",
                    format: "0.00"
            },
            {
                    data: "OD_ACDAT",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "ETD",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "CX_BDATE",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "CX_EDATE",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "DIFF_ETD",
                    
                    type: "numeric",
                    format: "0.00"
            },
            {
                    data: "M_MDATE",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "P_MDATE",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "MATE_DATE",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "DIFF_ZCDATE",
                    
                    type: "numeric",
                    format: "0.00"
            },
            {
                    data: "OS_EDATE",
                    
                    type: "date",
                    dateFormat: "YYYY/MM/DD"
            },
            {
                    data: "DIFF_OSDATE",
                    
                    type: "numeric",
                    format: "0.00"
            },
            {
                    data: "DEST_NAME",
                    
                    type: "text"
            },
            {
                    data: "SH_LAST",
                    
                    type: "text"
            },
            {
                    data: "CAP_HOUR",
                    
                    type: "numeric",
                    format: "0.00"
            },
            {
                    data: "WK_HOURS",
                    
                    type: "text"
            },
            {
                    data: "SH_ARITCLENO",
                    
                    type: "text"
            },
            {
                    data: "ISLOCK",
                    
                    type: "text"
            },
            {
                    data: "NEW_SHNO",
                    
                    type: "text"
            }]
    };
    hot = new Handsontable(container,settings);
    hot2 = new Handsontable(container2,settings);
    hot.updateSettings({
        contextMenu: {
            callback: function (key, options) {
                if (key === 'cut') {
                    setTimeout(function () {
                        var startRow=hot.getSelected()[0];
                        var endRow=hot.getSelected()[2];
                        CutRows=endRow-startRow+1;
                        /*for (var i = endRow; i >= startRow; i--) {
                         hot.alter('remove_row', i);
                         }*/
                        hot.alter('remove_row',startRow,CutRows);
                        hot.deselectCell();   //取消當前選中
                    }, 100);
                }
            },
            items: {
                "cut":{name:'剪切'},
                "paste":{
                    key: 'paste',
                    name: '插入粘貼行',
                    disabled: function() {
                        return clipboardCache.length === 0;
                    },
                    callback: function() {
                        var index=hot.getSelected()[0]; //當前行號
                        hot.alter('insert_row', index+1,CutRows);
                        hot.selectCell(index+1,0);  //自動選中所要粘貼的位置
                        var plugin = this.getPlugin('copyPaste');
                        this.listen();
                        plugin.paste(clipboardCache);
                    }
                }
            }
        },
        afterCut: function(changes) {
            clipboardCache = sheetclip.stringify(changes);
        },
        afterPaste: function(changes) {
            clipboardCache = "";
            CutRows="";
        },
    });
    hot2.updateSettings({
        contextMenu: {
            callback: function (key, options) {
                if (key === 'cut') {
                    setTimeout(function () {
                        var startRow=hot2.getSelected()[0];
                        var endRow=hot2.getSelected()[2];
                        CutRows=endRow-startRow+1;
                        /*for (var i = endRow; i >= startRow; i--) {
                         hot.alter('remove_row', i);
                         }*/
                        hot2.alter('remove_row',startRow,CutRows);
                        hot2.deselectCell();   //取消當前選中
                    }, 100);
                }
            },
            items: {
                "cut":{name:'剪切'},
                "paste":{
                    key: 'paste',
                    name: '插入粘貼行',
                    disabled: function() {
                        return clipboardCache.length === 0;
                    },
                    callback: function() {
                        var index=hot2.getSelected()[0]; //當前行號
                        hot2.alter('insert_row', index+1,CutRows);
                        hot2.selectCell(index+1,0);  //自動選中所要粘貼的位置
                        var plugin = this.getPlugin('copyPaste');
                        this.listen();
                        plugin.paste(clipboardCache);
                    }
                }
            }
        },
        afterCut: function(changes) {
            clipboardCache = sheetclip.stringify(changes);
        },
        afterPaste: function(changes) {
            clipboardCache = "";
            CutRows="";
        },
    });
    $("#search").click(function() {
                var fpno = $("#text-pno").text().split(" ")[0];
                var executerDiv = document.getElementById("lnoNav");
                executerDiv.innerHTML = "";
                var ul = document.createElement("ul");
                ul.id = "lno";
                tableDatas = getLnoData(fpno);
                for (var i = 0; i < tableDatas.length; i++) {
                    var lnoinfo = tableDatas[i];
                    var li = document.createElement("li");
                    li.innerHTML = lnoinfo.id + "-" + lnoinfo.name;
                    ul.appendChild(li);
            }
                executerDiv.appendChild(ul);
                $("#lno li").siblings('li').removeClass('selecteds');
                $("#lno li:first-child").addClass("selecteds");
                var tO = $("#text-lno").text();
                ssLNO = tO.substr(0,tO.length-1);
                hot.loadData(getCarData(fpno,'1001'));
                hot2.loadData(getCarData(fpno,ssLNO));
        });
    $("#executerDiv").text("是否确定?");
    $("#dialog-2").dialog({
                autoOpen: false,
                width: 500,
                buttons: [{
                        text: "确定",
                        click: function() {
                                var RPNO = $("#text-pno").text().split(" ")[0];
                                var sLNO = $("#SLNO").text();
                                var NO = $("#text-lno").text();
                                var eLNO = NO.substr(0,NO.length-1);
                                var changeDatas = [];
                                var rows = hot.countRows();
                                var rows2 = hot2.countRows();
                                var t = 1;
                                var k = 1;
                                for (var i = 0; i < rows; i++) {
                                	var rowdata = hot.getDataAtRow(i);
                                    if (rowdata !== null) {
                                    	var OD_NO = rowdata[1].split("-")[0]+"-"+rowdata[1].split("-")[1];
                                    	var OD_LOT = rowdata[1].split("-")[2];
                                    	var SEQ = rowdata[0];
                                    	var data = {
                                    			PNO: RPNO,
                                    			LNO: sLNO,
                                    			PLAN_SEQ: k,
                                    			OD_NO: OD_NO,
                                    			OD_LOT: OD_LOT
                                                };
                                        if(k!==SEQ) {
                                        	changeDatas.push(data);
                                        }
                                   }
                                        k++;
                                }
                            for (var i = 0; i < rows2; i++) {
                                var rowdata = hot2.getDataAtRow(i);
                                if (rowdata !== null) {
                                    var OD_NO2 = rowdata[1].split("-")[0]+"-"+rowdata[1].split("-")[1];
                                    var OD_LOT2 = rowdata[1].split("-")[2];
                                    var SEQ2 = rowdata[0];
                                    var data2 = {
                                        PNO: RPNO,
                                        LNO: eLNO,
                                        PLAN_SEQ: t,
                                        OD_NO: OD_NO2,
                                        OD_LOT: OD_LOT2
                                    };
                                    if(t!==SEQ2) {
                                        changeDatas.push(data2);
                                    }
                                }
                                t++;
                            }
                                $.myloading({
                                        title: "Loading..."
                                });
                                $.ajax({
                                        url: "refresh",
                                        type: "post",
                                        dataType: "json",
                                        data: {
                                            "changeDatas": changeDatas,
                                			"PNO": RPNO,
                                			"SLNO": sLNO,
                                            "ELNO": eLNO,
                                    },
                                        success: function(rdata) {
                                                if (rdata !== "") {
                                                        hot.loadData(getCarData(RPNO,sLNO));
                                                        hot2.loadData(getCarData(RPNO,eLNO));
                                                        $.myloading("hide");
                                                        $("#dialog P").text(rdata);
                                                        $("#dialog").dialog("open");
                                                }
                                        }
                                });
                                $(this).dialog("close");
                        }
                },
                {
                        text: "取消",
                        click: function() {
                                $(this).dialog("close");
                        }
                }]
        });
    $("#dialog-link-2").click(function(event) {
                $("#dialog-2").dialog("open");
                event.preventDefault();
        });
    
    $( "#dialog" ).dialog({   //提示對話框
	      autoOpen: false,
	      height:150,
	      modal: true,       //對話框以外的頁面不可操作
	      autoOpen: false,    //默認關閉
	      resizable: false,   //不可改變尺寸
	      buttons: {
	          "確定": function() {
	            $( this ).dialog( "close" );
	          }
	        }
	    });

    var executerDiv = document.getElementById("lnoNav");
    executerDiv.innerHTML = "";
    var ul = document.createElement("ul");
    ul.id = "lno";
    tableDatas = getLnoData(pno);
    for (var i = 0; i < tableDatas.length; i++) {
                var lnoinfo = tableDatas[i];
                var li = document.createElement("li");
                li.innerHTML = lnoinfo.id + "-" + lnoinfo.name;
                ul.appendChild(li);
        }
    executerDiv.appendChild(ul);
    $("#lno li:first-child").addClass("selecteds");
    $("#lnoNav").on("click","#lno li",function(){   //核心代码
        	$(this).siblings('li').removeClass('selecteds');  // 删除其他兄弟元素的样式
            $(this).addClass('selecteds');                   // 添加当前元素的样式
            var fpno = $("#text-pno").text().split(" ")[0];
        	ssLNO = $(this).text().split("-")[0];
        	$("#SLNO").text(ssLNO);
        	hot.loadData(getCarData(fpno,ssLNO));
         });

        //以jquery为例实现点击某行获得某个td的数据
    $( "#tb tr" ).click( function() {//给每行绑定了一个点击事件
        $(this).siblings('tr').removeClass('selecteds');  // 删除其他兄弟元素的样式
        $(this).addClass('selecteds');                   // 添加当前元素的样式
        var td = $( this ).find( "td" );//this指向了当前点击的行，通过find我们获得了该行所有的td对象
        var pnot = td.eq( 0 ).html();
        var pno_status = td.eq( 2 ).html();//通过eq可以得到具体的某个td对象，从而得到相应的数据
        $("#oks").click(function () {
            if (pno_status === '生效') {
                $("#text-pno").text( pnot).append('<span  class="label label-success" > 生效</span>');
            } else {
                    $("#text-pno").text( pnot).append('<span  class="label label-danger" > 已结案</span>');
            }
        });
        });
    
    $(".lnoOK").click(function () {
        var html = "";
        var epno = $("#text-pno").text().split(" ")[0];
        tableData = getLnoData(epno);
        for( var i = 0; i < tableData.length; i++ ) {
            html += "<tr>";
            html +=     "<td>" + tableData[i].id + "</td>"
            html +=     "<td>" + tableData[i].name + "</td>"
            html +=     "<td>" + tableData[i].qtys + "</td>"
            html += "</tr>";
        }
        $("#L_TbData").html(html);
        $( "#ltb tr" ).click( function() {//给每行绑定了一个点击事件
            $(this).siblings('tr').removeClass('selecteds');  // 删除其他兄弟元素的样式
            $(this).addClass('selecteds');                   // 添加当前元素的样式
            var td = $( this ).find( "td" );//this指向了当前点击的行，通过find我们获得了该行所有的td对象
            var lno = td.eq( 0 ).html();
            $("#okl").click(function () {
                $("#text-lno").text(lno+'线');
                hot2.loadData(getCarData(epno,lno));
            });
        });
    });
});
