import api from '../api'

const useSetPassword = () => {
  const execute = (password, id) => {
    return api.put(`api/users/pass/${id}`, password)
  }

  return { execute }
}

export default useSetPassword
