import api from '../api'

const useGetBrigadaById = () => {
  const execute = (id) => {
    return api.get(`api/brigades/${id}`)
  }

  return { execute }
}

export default useGetBrigadaById
