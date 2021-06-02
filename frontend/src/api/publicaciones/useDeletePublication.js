import api from '../api'

const useDeletePublication = () => {
  const execute = (id) => {
    return api.delete(`api/publications/${id}`)
  }
  return { execute }
}

export default useDeletePublication
