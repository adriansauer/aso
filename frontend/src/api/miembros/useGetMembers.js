import api from '../api'

const useGetMembers = () => {
  const execute = (id, pag) => {
    return api.get(`api/fireman/by/brigade/${id}?size=10&page=${pag - 1}`)
  }

  return { execute }
}

export default useGetMembers
