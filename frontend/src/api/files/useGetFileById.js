import api from '../api'

const useGetFileById = () => {
  const execute = (id) => {
    return api.get(`api/files/${id}`)
  }

  return { execute }
}

export default useGetFileById
