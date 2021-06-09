import api from '../api'

const useLike = () => {
  const execute = (publicationId) => {
    return api.post('api/likes', {
      publicationId
    })
  }

  return { execute }
}

export default useLike
