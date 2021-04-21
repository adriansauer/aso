import api from '../api'

const useCreateDepartament = () => {
  const execute = (depto) => {
    const { name } = depto
    return api.post('api/departaments', {
      name
    })
  }

  return { execute }
}

export default useCreateDepartament
