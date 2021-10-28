import React, { useEffect, useState } from 'react';
import HighStock from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";
import { fetchChartData } from '../../hooks/useChart';

const config = (data) => {  
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
            }
        }
        ]
    }
}
export default function HighChart({ stockName }) {
    const [configs, setConfigs] = useState([]);

    useEffect(() => {
        async function loadData() {
            const result = await fetchChartData(stockName);
            setConfigs(config(result));
        }
        loadData();
    }, []);

    return (
        <>
        <HighchartsReact
            highcharts={HighStock}
            constructorType={"stockChart"}
                options={configs} />
        </>
    )
}
