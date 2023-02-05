import http from './http';

const getMyDetail = async () => {
    const { status, data } = await http.get('oua/api/v1/user/my/detail');
    return { status, data };
}

const invite = async (data) => {
    const { status } = await http.post('oua/api/v1/company/user', data);
    return status;
}

const update = async (data) => {
    const { status } = await http.patch(`ouh/api/v1/user`, data);
    return status;
}

const searchUser = async (key) => {

    const { data } = await http.get(`oua/api/v1/user/search/${key}`);
    return data;

}

export {
    getMyDetail,
    invite,
    update,
    searchUser
}