import React, { useEffect, useState } from 'react';
import HighStock from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";


export const HighChart = React.memo(function ({ configs, name }) {
    const [key, setKey] = useState(0);
    useEffect(() => {
        async function rerender(){
            setTimeout(() => {
                setKey(name);
            }, 0);
        }
        
        rerender();
    }, [name]);
    return (
        <HighchartsReact
                key={key}
               highcharts={HighStock}
               constructorType={"stockChart"}
                options={configs}
            />
        
    )
},(prevProps,nextProps)=>prevProps.name===nextProps.name);
