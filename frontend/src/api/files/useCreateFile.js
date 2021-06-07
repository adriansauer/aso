import api from '../api'

const useCreateFile = () => {
  const execute = (f) => {
    const {
      file,
      name,
      publicationId
    } = f
    return api.post('api/files/', {
      file,
      name,
      publicationId
    })
  }

  return { execute }
}

export default useCreateFile
