import api from '../api'

const UseGetDepartament = () => {
  const execute = (pag) => {
    return api.get(`api/departaments?size=6&page=${pag - 1}`)
  }

  return { execute }
}

export default UseGetDepartament
