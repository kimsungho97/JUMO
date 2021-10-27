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
