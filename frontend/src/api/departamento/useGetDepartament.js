import api from '../api'

const UseGetDepartament = () => {
  const execute = () => {
    return api.get('api/departaments')
  }

  return { execute }
}

export default UseGetDepartament
