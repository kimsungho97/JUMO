export const config = (data) => {  
    // split the data set into ohlc and volume
    let ohlc = [],
        volume = [],
        dataLength = data.length,
        // set the allowed units for data grouping
        groupingUnits = [[
            'week',                         // unit name
            [1]                             // allowed multiples
        ], [
            'month',
            [1, 2, 3, 4, 6]
        ]]

        

    for (let i=0; i < dataLength; i += 1) {
        ohlc.push([
            Date.UTC(data[i][0], data[i][1] - 1, data[i][2], 9), // the date
            data[i][3], // open
            data[i][4], // high
            data[i][5], // low
            data[i][6] // close
        ]);

        volume.push([
            Date.UTC(data[i][0], data[i][1] - 1, data[i][2], 9), // the date
            data[i][7] // the volume
        ]);
    }
    return {
        rangeSelector: {
            selected: 1
        },

        title: {
            text: ''
        },

        yAxis: [
            {
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
        colors: ['#2f7ed8', '#0d233a', '#8bbc21', '#910000', '#1aadce',
        '#492970', '#blue', '#77a1e5', '#c42525', '#a6c96a'],
        series: [{
            type: 'candlestick',
            name: '',
            data: ohlc,
            downColor: 'blue',
            upColor: 'red',
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
            }
        }
        ]
    }
}