import api from '../api'

const useGetCity = () => {
  const execute = () => {
    return api.get('api/cities')
  }

  return { execute }
}

export default useGetCity
