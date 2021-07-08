import publicApi from '../publicApi'

const useGetPublicsReportes = () => {
  const execute = (data) => {
    return publicApi.post('/api/public/reports/all/year', data)
  }

  return { execute }
}

export default useGetPublicsReportes
