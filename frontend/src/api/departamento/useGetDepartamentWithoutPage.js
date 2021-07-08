import api from '../api'

const UseGetDepartamentWithoutPage = () => {
  const execute = (pag) => {
    return api.get(`api/departaments?size=99&page=${pag - 1}`)
  }

  return { execute }
}

export default UseGetDepartamentWithoutPage
