import api from '../api'

const useIsTokenValid = () => {
  const execute = (token) => {
    return api
      .get('/istokenvalid', { headers: { Authorization: `Bearer ${token}` } })
  }

  return { execute }
}

export default useIsTokenValid
