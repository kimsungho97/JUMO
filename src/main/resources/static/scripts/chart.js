
    Highcharts.setOptions({
      global:{
        useUTC: false
        }
      });



    // split the data set into ohlc and volume
    var ohlc = [],
        volume = [],
        dataLength = data.length,
        // set the allowed units for data grouping
        groupingUnits = [[
            'week',                         // unit name
            [1]                             // allowed multiples
        ], [
            'month',
            [1, 2, 3, 4, 6]
        ]],

        i = 0;

    for (i; i < dataLength; i += 1) {
        ohlc.push([
            Date.UTC(data[i][0],data[i][1]-1,data[i][2],9), // the date
            data[i][3], // open
            data[i][4], // high
            data[i][5], // low
            data[i][6] // close
        ]);

        volume.push([
            Date.UTC(data[i][0],data[i][1]-1,data[i][2],9), // the date
            data[i][7] // the volume
        ]);
    }


    // create the chart
    Highcharts.stockChart('container', {

        rangeSelector: {
            selected: 1
        },

        title: {
            text: '[[${stock_name}]]'
        },

        yAxis: [{
            labels: {
                align: 'right',
                x: -3
            },
            title: {
                text: 'OHLC'
            },
            height: '60%',
            lineWidth: 2,
            resize: {
                enabled: true
            }
        }, {
            labels: {
                align: 'right',
                x: -3
            },
            title: {
                text: 'Volume'
            },
            top: '65%',
            height: '35%',
            offset: 0,
            lineWidth: 2
        }],

        tooltip: {
            split: true
        },

        series: [{
            type: 'candlestick',
            name: '',
            data: ohlc,
            dataGrouping: {
                units: groupingUnits
            }
        }, {
            type: 'column',
            name: 'Volume',
            data: volume,
            yAxis: 1,
            dataGrouping: {
                units: groupingUnits
            }
        },
        {
                    name: '',
                    data: ohlc,
                    tooltip: {
                        valueDecimals: 2
                    }}
        ]
    });

    const searchEl = document.querySelector('.highcharts-credits');
    searchEl.style.display = 'none';
    document.querySelector(".highcharts-title").style.display='none';

    //차크 캔들 색깔 변경
    function candleColorChange(){
        const candleDown=document.querySelectorAll(".highcharts-point-up");
        const candleUp=document.querySelectorAll(".highcharts-point-down");

        candleDown.forEach((value)=>{
            value.style.fill="#ff3333";
        })

        candleUp.forEach((value)=>{
            value.style.fill="#0000ff";
        })
    }

    window.addEventListener("click",candleColorChange);

    candleColorChange();

    const recommendInfo = document.querySelectorAll(".recommend-result");
    const highChartBackground = document.querySelector(".highcharts-background");
    highChartBackground.style.boxShadow = "1px 1px 8px #d5d6d6";

recommendInfo.forEach((value) => { 
    if(value.innerHTML==="BUY"){
        value.style.backgroundColor="#FC9FA0";
    }
    else{
        value.style.backgroundColor="#A299FF";
    }
})    