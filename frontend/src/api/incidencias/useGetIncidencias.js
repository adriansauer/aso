import api from '../api'

const useGetIncidencia = () => {
  const execute = (pag) => {
    return api.get(`api/incidence-code?size=6&page=${pag - 1}&sort=id,desc`)
  }

  return { execute }
}

export default useGetIncidencia
