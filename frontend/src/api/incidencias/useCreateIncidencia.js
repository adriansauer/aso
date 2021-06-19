import api from '../api'

const useCreateIncidencia = () => {
  const execute = (incidencia) => {
    return api.post('api/incidence-code', incidencia)
  }

  return { execute }
}

export default useCreateIncidencia
