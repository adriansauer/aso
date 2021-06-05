import api from '../api'

const useUpdatePublication = () => {
  const execute = (id, body, destination) => {
    return api.put(`api/publications/${id}`, { body, destination })
  }
  return { execute }
}

export default useUpdatePublication
