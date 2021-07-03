import api from '../api'

const useUpdatePublication = () => {
  const execute = (id, incidenceCodeId, body, destination) => {
    return api.put(`api/publications/${id}`, { body, destination, incidenceCodeId })
  }
  return { execute }
}

export default useUpdatePublication
