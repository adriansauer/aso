import api from '../api'

const useGetRoles = () => {
  const execute = () => {
    return api.get('api/roles')
  }

  return { execute }
}

export default useGetRoles
