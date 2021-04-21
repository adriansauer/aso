import api from '../api'

const useGetBrigadas = () => {
  const execute = () => {
    api.get('api/brigades')
  }

  return { execute }
}

export default useGetBrigadas
