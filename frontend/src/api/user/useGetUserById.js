import api from '../api'

const useGetUserById = () => {
  const execute = (id) => {
    return api.get(`api/users/${id}`)
  }

  return { execute }
}

export default useGetUserById
