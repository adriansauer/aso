import api from '../api'

const useUpdatePublication = () => {
  const execute = (publication) => {
    const {
      id,
      body,
      destination
    } = publication
    return api.put('api/publications/' + id, {
      body,
      destination
    })
  }

  return { execute }
}

export default useUpdatePublication
