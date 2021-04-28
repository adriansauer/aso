import api from '../api'

const useGetBrigadas = () => {
  const execute = () => {
    return api.get('api/brigades')
  }

  return { execute }
}

export default useGetBrigadas
