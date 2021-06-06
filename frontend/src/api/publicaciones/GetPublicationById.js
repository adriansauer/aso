import api from '../api'

const useGetPublicationsById = () => {
  const execute = (id) => {
    return api.get(`api/publications/${id}`)
  }

  return { execute }
}

export default useGetPublicationsById
