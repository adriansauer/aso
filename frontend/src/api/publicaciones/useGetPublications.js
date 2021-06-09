import api from '../api'

const useGetPublications = () => {
  const execute = (pag) => {
    return api.get(`api/publications?page=${pag - 1}&size=5&sort=created_at,desc`)
  }

  return { execute }
}

export default useGetPublications
