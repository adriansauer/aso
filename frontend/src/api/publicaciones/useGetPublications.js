import api from '../api'

const useGetPublications = () => {
  const execute = () => {
    return api.get('api/publications')
  }

  return { execute }
}

export default useGetPublications
