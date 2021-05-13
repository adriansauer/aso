import api from '../api'

const useUpdateFile = () => {
  const execute = (f) => {
    const {
      id,
      file
    } = f
    return api.put('api/files/files/' + id, {
      file
    })
  }

  return { execute }
}

export default useUpdateFile
