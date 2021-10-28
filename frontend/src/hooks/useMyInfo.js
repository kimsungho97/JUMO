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
    console.log(res)
    return res;
}
