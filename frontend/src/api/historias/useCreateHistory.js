import api from '../api'

const useCreateHistory = () => {
  const execute = (brigadaId, history) => {
    return api.put(`/api/brigade-history/${brigadaId}`, history)
  }

  return { execute }
}

export default useCreateHistory
