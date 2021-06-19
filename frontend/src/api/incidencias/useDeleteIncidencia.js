import api from '../api'

const useDeleteIncidencia = () => {
  const execute = (id) => {
    return api.delete(`api/incidence-code/${id}`)
  }

  return { execute }
}

export default useDeleteIncidencia
