import axios from 'axios'
import BASE_URL from './baseURL'
const defaultOptions = {
  baseURL: BASE_URL,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  }
}
const api = axios.create(defaultOptions)
api.interceptors.request.use(function (config) {
  const token = localStorage.getItem('token')
  config.headers.Authorization = token ? `Bearer ${token.replace(/['"]+/g, '')}` : ''
  return config
})
export default api
