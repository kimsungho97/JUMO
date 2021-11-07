import axios  from "axios"

export async function fetchLogin(id, password) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "POST",
        url: `${url}/api/login`,
        headers: {
            "Content-Type": "application/json"
        },
        data: {
            id,
            password
        }
    })).data;
    return res;
}

export async function fetchLogout(id) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "POST",
        url: `${url}/api/logout`,
        headers: {
            "Content-Type": "application/json"
        },
        data: {
            id
        }
    })).data;
    return res;
}


export async function fetchSignup(id,password) {
    const url = process.env.REACT_APP_BE_HOST;
    const res = (await axios({
        method: "POST",
        url: `${url}/api/signup`,
        headers: {
            "Content-Type": "application/json"
        },
        data: {
            id,
            password
        }
    })).data;
    return res;
}