import React, { useEffect, useState } from 'react';
import HighStock from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";


export default function HighChart({ configs }) {
    const [key, setKey] = useState(0);
    useEffect(() => {
        async function rerender(){
            setTimeout(() => {
                setKey(key + 1);
            }, 0);
        }
        
        rerender();
    }, [configs]);
    return (
        <HighchartsReact
                key={key}
               highcharts={HighStock}
               constructorType={"stockChart"}
                options={configs}
            />
        
    )
}
