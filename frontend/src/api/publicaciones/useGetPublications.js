import api from '../api'

const useGetPublications = () => {
  const execute = () => {
    return api.get('api/publication')
  }

  return { execute }
}

export default useGetPublications
