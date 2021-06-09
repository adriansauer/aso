import api from '../api'

const useDeleteLike = () => {
  const execute = (publicationId) => {
    return api.delete(`api/likes/${publicationId}`)
  }

  return { execute }
}

export default useDeleteLike
