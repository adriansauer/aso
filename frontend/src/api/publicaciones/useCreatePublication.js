import api from '../api'

const useCreatePublication = () => {
  const execute = (publication) => {
    return api.post('api/publications', publication)
  }

  return { execute }
}

export default useCreatePublication
