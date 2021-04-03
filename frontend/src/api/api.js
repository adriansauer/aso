import axios from 'axios'

const defaultOptions = {
  baseURL: '',
  headers: {
    'Content-Type': 'application/json'
  }
}
const api = axios.create(defaultOptions)
export default api
