import api from '../api'

const useCreatePublication = () => {
  const execute = (publication) => {
    const {
      body,
      destination,
      userId
    } = publication
    return api.post('api/publications', {
      body,
      destination,
      userId
    })
  }

  return { execute }
}

export default useCreatePublication
