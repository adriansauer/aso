import { useState } from 'react'
import api from '../api'

const useGetUserById = () => {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const execute = (id) => {
    setError(null)
    setLoading(true)
    api
      .get(`/users/${id}`)
      .then((response) => {
        setData(response.data.content)
        setError(null)
        setLoading(false)
      })
      .catch((_error) => {
        setData(null)
        setError('No se pudo encontrar el usuario')
        setLoading(false)
      })
  }

  return { data, loading, error, execute }
}

export default useGetUserById
