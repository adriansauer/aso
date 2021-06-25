import api from '../api'

const useSearchIncident = () => {
  const execute = (text) => {
    return api.get(`/api/incidence-code/description-code/${text}`)
  }

  return { execute }
}

export default useSearchIncident
