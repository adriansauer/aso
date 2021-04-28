import api from '../api'

const useCreateUser = () => {
  const execute = user => {
    const {
      email,
      lastname,
      name,
      password,
      roles,
      usercode
    } = user
    return api.post('api/users', {
      email,
      lastname,
      name,
      password,
      roles,
      usercode
    })
  }

  return { execute }
}

export default useCreateUser
