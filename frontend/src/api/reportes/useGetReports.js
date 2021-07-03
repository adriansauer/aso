import api from '../api'

const useGetReportes = () => {
  const execute = (data) => {
    return api.post('/api/reports/year-user', data)
  }

  return { execute }
}

export default useGetReportes
