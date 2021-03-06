import api from '../api'

const useGetCity = () => {
  const execute = (pag) => {
    return api.get(`api/cities?size=6&page=${pag - 1}&sort=id,desc`)
  }

  return { execute }
}

export default useGetCity
