import axios  from "axios"

export async function fetchHolding(id) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "GET",
        url: `${url}/api/myinvestment`,
        headers: {
            "Content-Type": "application/json"
        }
    })).data.data;
    return res;
}


export async function fetchHistory(id) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "GET",
        url: `${url}/api/history`,
        headers: {
            "Content-Type": "application/json"
        }
    })).data.data;
    return res;
}

export async function fetchReset() {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "POST",
        url: `${url}/api/resetuser`,
        headers: {
            "Content-Type": "application/json"
        }
    })).status;
    return res;
}