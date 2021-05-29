import api from '../api'

const useGetPublications = () => {
  const execute = () => {
    return api.get('api/publications?size=10')
  }

  return { execute }
}

export default useGetPublications
