import axios  from "axios"

export async function fetchTrade(type, stockName, stockCode, amount) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "POST",
        url: `${url}/api/trade`,
        headers: {
            "Content-Type": "application/json"
        },
        data: {
            "type": type,
            "stockName": stockName,
            "stockCode": stockCode,
            "amount": amount
        }
    })).data
    return res;
}


export async function fetchCurPrice(stockCode) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "GET",
        url: `${url}/api/stockprice?stockId=${stockCode}`,
        headers: {
            "Content-Type": "application/json"
        }
    })).data.price
    return res;
}