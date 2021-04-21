import api from '../api'

const useDeleteCity = () => {
  const execute = (id) => {
    return api.delete(`api/cities/${id}`)
  }

  return { execute }
}

export default useDeleteCity
