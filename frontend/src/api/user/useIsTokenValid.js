import api from '../api'

const useIsTokenValid = () => {
  const execute = (token) => {
    return api
      .get('/istokenvalid', { headers: { 'x-auth-token': token } })
  }

  return { execute }
}

export default useIsTokenValid
