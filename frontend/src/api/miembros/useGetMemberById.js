import api from '../api'

const useGetMemberById = () => {
  const execute = (id) => {
    return api.get(`api/fireman/${id}`)
  }

  return { execute }
}

export default useGetMemberById
