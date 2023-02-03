

const put = (key, value) => {
    localStorage.setItem(key, value)
}

const get = (key) => {
    return localStorage.getItem(key)
}

export {
    put,
    get
}