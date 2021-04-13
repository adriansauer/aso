import api from '../api'

const useGetUsers = () => {
  const execute = () => {
    api.get('api/users')
  }

  return { execute }
}

export default useGetUsers
