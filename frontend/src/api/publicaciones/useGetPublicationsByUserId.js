import api from '../api'

const useGetPublicationsByUserId = () => {
  const execute = (pag, id) => {
    return api.get(`api/publications/byuser/${id}?page=${pag - 1}&size=5&sort=created_at,desc`)
  }

  return { execute }
}

export default useGetPublicationsByUserId
