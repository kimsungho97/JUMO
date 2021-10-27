import axios from "axios";

export async function fetchChartList() {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "GET",
        url: `${url}/api/chartlist`,
        headers: {
            "Content-Type": "application/json"
        }
    })).data;
    
    return res.chartlist
}

export async function fetchChartData(stockName) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "GET",
        url: `${url}/api/chart?stockname=${stockName}`,
        headers: {
            "Content-Type": "application/json"
        }
    })).data;
    
    return res.data
}
