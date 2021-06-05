import api from '../api'

const useGetPublicationsByUserId = () => {
  const execute = (pag, id) => {
    return api.get(`api/publications/byuser/${id}?size=10&sort=desc&page=${pag - 1}`)
  }

  return { execute }
}

export default useGetPublicationsByUserId
