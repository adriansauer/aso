import api from '../api'

const useCreateFile = () => {
  const execute = (f) => {
    const {
      file,
      name
    } = f
    return api.post('api/files/files', {
      file,
      name
    })
  }

  return { execute }
}

export default useCreateFile
