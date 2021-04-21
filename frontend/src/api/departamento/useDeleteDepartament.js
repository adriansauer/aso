import api from '../api'

const useDeleteDepartament = () => {
  const execute = (id) => {
    return api.delete(`api/departaments/${id}`)
  }

  return { execute }
}

export default useDeleteDepartament
