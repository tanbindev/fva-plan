<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Date cell type - Handsontable</title>
    <link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/lib/bootstrap-3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/lib/bootstrap-3.3.7/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/lib/handsontable.full.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/lib/highcharts.css"/>
    <link rel="stylesheet" type="text/css" href="css/lib/loading.css" />
    <link rel="stylesheet" type="text/css" href="css/lib/samples.css"/>
    <link rel="stylesheet" type="text/css" href="css/lib/reveal.css">
    <link rel="stylesheet" type="text/css" href="css/lib/jquery.pagination.css"/>
</head>
<body>
<div class="wrapper">
    <!--背景图片-->
    <div class="web_bg"></div>
    <div class="logo">
        <h1><a href="http://10.16.1.11:8080/ds/app"></a></h1>
    </div>
    <div id="tabs">
        <ul>
            <li><a href="#tabs-1">细排计划</a></li>
            <li><a href="#tabs-2">计划图示</a></li>
        </ul>
        <div id="tabs-1">
            <!-- 用於彈出提示信息的div -->
            <div id="dialog" title="提示！">
                <p></p>
            </div>
            <div class="searchform">
                <!-- 按钮触发模态框 -->
                <button type="button" class="btn btn-primary btn-xs " data-toggle="modal" data-target="#myModal">
                    <span class="glyphicon glyphicon-book"> 大排编号</span>
                </button>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h5 class="modal-title" id="myModalLabel">请选择</h5>
                            </div>
                            <div class="modal-body">
                                <table class="table table-bordered" id="tb">
                                    <thead>
                                    <tr>
                                        <th data-field="pno">当前编号</th>
                                        <th data-field="ex_PNO">前次编号</th>
                                        <th data-field="status">状态</th>
                                    </tr>
                                    </thead>
                                    <tbody  id="J_TbData">
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="oks" data-dismiss="modal">确定</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
                <h5 id="text-pno"></h5>
                <button type="submit" id="search" class="btn btn-primary btn-xs" >
                    <span class="glyphicon glyphicon-search"> 查询</span>
                </button>
                <span id="SLNO"></span>
                <button class="btn btn-primary ok btn-xs" >
                    <span class="glyphicon glyphicon-transfer"> 跨线调整</span>
                </button>
                <button class="btn btn-primary lnoOK btn-xs" data-toggle="modal" data-target="#lnoModal">
                    调整至 <span class="glyphicon glyphicon-share-alt"></span>
                </button>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="lnoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h5 class="modal-title" id="myModalLabel">请选择</h5>
                            </div>
                            <div class="modal-body">
                                <table class="table table-bordered" id="ltb">
                                    <thead>
                                    <tr>
                                        <th data-field="lno">线别代号</th>
                                        <th data-field="lname">线别名称</th>
                                        <th data-field="qty">已排生产数</th>
                                    </tr>
                                    </thead>
                                    <tbody  id="L_TbData">
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="okl" data-dismiss="modal">确定</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
                <span id="text-lno" class="label label-info"></span>
                <button class="btn btn-primary btn-xs" id="dialog-link-2"  >
                    <span class="glyphicon glyphicon-refresh"> 刷新重整</span>
                </button>
					<div id="dialog-2" title='提示!' >
						<p id="executerDiv"></p>
					</div>
            </div>
            <!-- 线别导航DIV -->
            <div id="lnoNav"></div>
            <!-- handsontable資料顯示div -->
            <div id="example">
                <div id="example1" class="hantable1"></div>
                <div id="example2" class="hantable2"></div>
            </div>
        </div>
        <div id="tabs-2">
        	<div id="container" style="width: 1400px; height: 600px; margin: 0 auto"></div>
			<button id="plain" class="btn btn-primary">Plain</button>
			<button id="inverted" class="btn btn-primary">Inverted</button>
			<button id="polar" class="btn btn-primary">Polar</button>
        </div>
    </div>
</div>
<script src="js/lib/jquery.js"></script>
<script src="css/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="js/lib/handsontable.full.min.js"></script>
<script src="js/lib/jquery-ui.js"></script>
<script src="js/lib/highcharts.js"></script>
<script src="js/lib/highcharts-more.js"></script>
<script src="js/lib/exporting.js"></script>
<script src="js/lib/loading.js"></script>
<script src="js/lib/jquery.reveal.js"></script>
<script src="js/lib/sheetclip.js"></script>
<script src="js/all.js"></script>
<script src="js/improve.js"></script>
<!--点线连接-->
<script src="js/spotLine.js"></script>
<script src="js/charts.js"></script>

</body>
</html>
