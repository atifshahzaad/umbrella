import axios from 'axios'
import * as storeService from './storeService'

const instance = axios.create({
    baseURL: 'http://localhost:9098/',
    headers: {
        'Content-type': 'application/json',
    }
});

instance.interceptors.request.use(config => {
    if (!config.url.includes('/auth')) {
        const token = storeService.get('access_token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
    }
    return config;
});

export default instance;
