
import http from './http'
import URL from './url'

const createProject = async(data) => {

    const response = await http.post(URL.PMS.CREATE_PROJECT, data)
    return response;

}

export {
    createProject
}