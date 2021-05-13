import { useState } from 'react'
import api from '../api'

const useDeletePublication = () => {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const execute = id => {
    setError(null)
    setLoading(true)
    api.delete(`/publications/${id}`)
      .then(response => {
        setData(response.data)
        setError(null)
        setLoading(false)
      })
      .catch(_error => {
        setData(null)
        setError('Ha ocurrido un error al intentar eliminar el usuario')
        setLoading(false)
      })
  }

  return { data, loading, error, execute }
}

export default useDeletePublication
