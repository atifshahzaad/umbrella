import http from './http'

const login = async (username, password) => {
    const respose = await http.post('oua/api/v1/auth/login', {
        username,
        password
    })

    return respose.data;
}

export {
    login
}