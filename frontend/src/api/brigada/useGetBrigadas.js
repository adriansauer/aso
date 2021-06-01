import api from '../api'

const useGetBrigadas = () => {
  const execute = (pag) => {
    return api.get(`api/brigades?size=10&page=${pag - 1}`)
  }

  return { execute }
}

export default useGetBrigadas
