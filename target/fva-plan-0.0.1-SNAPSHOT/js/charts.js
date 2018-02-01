$(document).ready(function() { 
   var pno = $("#text-pno").text().split(" ")[0];
   var chart = Highcharts.chart('container', {

	    title: {
	        text: '产线负荷图'
	    },

	    subtitle: {
	        text: '已排计划数'
	    },

	    xAxis: {
	        categories: getCarData(pno)
	    },

	   yAxis: {
		   min: 0,
		   title: {
			   text: 'ODLQTY'
		   }
	   	},
	    
	    series: [{
	    	name: 'ODLQTY',
	        type: 'column',
	        colorByPoint: true,
	        data: getQtyData(pno),
	        showInLegend: false
	    }]

	});


	$('#plain').click(function () {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: false
	        },
	        subtitle: {
	            text: 'Plain'
	        }
	    });
	});

	$('#inverted').click(function () {
	    chart.update({
	        chart: {
	            inverted: true,
	            polar: false
	        },
	        subtitle: {
	            text: 'Inverted'
	        }
	    });
	});

	$('#polar').click(function () {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: true
	        },
	        subtitle: {
	            text: 'Polar'
	        }
	    });
	});

   
   function getCarData(pno) {
		var convertData = [];
		$.ajax({
			url : 'lnoInfo?pno='+pno,
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(rdata) {
				if (rdata !== "") {
					$.each(rdata.datas, function(index, node) {
						convertData.push(
							node.lno+'-'+node.lname
						);
					});
				}
			}
		});
		return convertData;
	}
   
   function getQtyData(pno) {
		var qtyData = [];
		$.ajax({
			url : 'lnoInfo?pno='+pno,
			type : 'post',
			async : false,
			dataType : 'json',
			success : function(rdata) {
				if (rdata != "") {
					$.each(rdata.datas, function(index, node) {
						qtyData.push(
								node.qty
							);
					});
				}
			}
		});
		return qtyData;
	}
});