import api from '../api'

const useGetImages = () => {
  const execute = (id) => {
    return api.get(`api/images/files/${id}`)
  }

  return { execute }
}

export default useGetImages
