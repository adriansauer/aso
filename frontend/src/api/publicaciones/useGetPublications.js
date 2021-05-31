import api from '../api'

const useGetPublications = () => {
  const execute = (pag) => {
    return api.get(`api/publications?page=${pag - 1}`)
  }

  return { execute }
}

export default useGetPublications
