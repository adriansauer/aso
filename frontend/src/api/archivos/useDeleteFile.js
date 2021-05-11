import { useState } from 'react'
import api from '../api'

const useDeleteFile = () => {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const execute = id => {
    setError(null)
    setLoading(true)
    api.delete(`/files/${id}`)
      .then(response => {
        setData(response.data)
        setError(null)
        setLoading(false)
      })
      .catch(_error => {
        setData(null)
        setError('Ha ocurrido un error al intentar eliminar')
        setLoading(false)
      })
  }

  return { data, loading, error, execute }
}

export default useDeleteFile
