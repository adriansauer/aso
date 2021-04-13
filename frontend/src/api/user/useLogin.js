import api from '../api'

const useLogin = () => {
  const execute = (user) => {
    const { password, usercode } = user
    return api
      .post('/login', JSON.stringify({
        password,
        usercode
      }))
  }

  return { execute }
}

export default useLogin
