import api from '../api'

const useGetCityWithoutPage = () => {
  const execute = (pag) => {
    return api.get(`api/cities?size=99&page=${pag - 1}&sort=id,desc`)
  }

  return { execute }
}

export default useGetCityWithoutPage
