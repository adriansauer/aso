import { useState } from 'react'
import api from '../api'

const useGetUsers = () => {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const execute = (page = 0, size = 5) => {
    if (size < 1) size = 5
    setError(null)
    setLoading(true)
    api
      .get('api/users')
      .then((response) => {
        setData(response.data.content)
        setError(null)
        setLoading(false)
      })
      .catch((_error) => {
        setData(null)
        setError('No se puede obtener la lista de usuarios')
        setLoading(false)
      })
  }

  return { data, loading, error, execute }
}

export default useGetUsers
