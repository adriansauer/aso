import api from '../api'

const useCreateCity = () => {
  const execute = (city) => {
    const { name } = city
    return api.post('api/cities', {
      name
    })
  }

  return { execute }
}

export default useCreateCity
