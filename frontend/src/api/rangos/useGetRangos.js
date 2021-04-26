import api from '../api'

const UseGetRangos = () => {
  const execute = () => {
    return api.get('api/ranges')
  }

  return { execute }
}

export default UseGetRangos
