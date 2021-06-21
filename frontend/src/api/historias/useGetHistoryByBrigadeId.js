import api from '../api'

const useGetHistoryByBrigadeId = () => {
  const execute = (brigadeId) => {
    return api.get(`/api/brigade-history/bybrigadeid/${brigadeId}`)
  }

  return { execute }
}

export default useGetHistoryByBrigadeId
