require([ "jquery", "ajax", "jsonAjax","commons","highcharts"], function($) {
	
	var highcharts = require(['highcharts']);
	
	var json='{"人才渠道":"50","直接渠道":"30","间接渠道":"20"}'
	var objJson=JSON.parse(json);
	var chartData=[];
	for(var obj in objJson){
		chartData.push([obj, parseFloat(objJson[obj])])
	}
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
        },
        title: {
            text: ''
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        colors: [
             '#058DC7',
             '#50B432',
             '#ED561B'
        ],
        legend: {   
        	layout:'vertical',
            align: 'right', 
        },    
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                },
                showInLegend: true,
                events:{
    				click:function(e){
    					console.info(e.point);		
    				}
                }
            }
        },
        exporting:{
        	filename:'answer',
        },
        series: [{
            type: 'pie',
            name: '占比',
            data: chartData
        }]
    });
	
});