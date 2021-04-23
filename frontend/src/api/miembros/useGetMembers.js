import api from '../api'

const useGetMembers = () => {
  const execute = (id) => {
    return api.get(`api/fireman/by/brigade/${id}`)
  }

  return { execute }
}

export default useGetMembers
