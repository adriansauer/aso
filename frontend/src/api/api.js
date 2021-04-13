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
export default api
