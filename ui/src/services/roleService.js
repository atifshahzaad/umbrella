import http from './http';


const create = () => {

}

const getAll = async() => {
    const { data } = await http.get('oua/api/v1/role');
    return data;
}

const deleteRole = async(id) => {
    const { status } = await http.delete(`oua/api/v1/role/${id}`);
    return status;
}


export {
    create,
    getAll,
    deleteRole
}