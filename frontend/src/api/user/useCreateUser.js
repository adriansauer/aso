import { useState } from 'react'
import api from '../api'

const useCreateUser = () => {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const execute = user => {
    const {
      createdAt,
      email,
      lastname,
      name,
      password,
      roles,
      updatedAt,
      usercode
    } = user
    setError(null)
    setLoading(true)
    api.post('/users', {
      createdAt,
      email,
      lastname,
      name,
      password,
      roles,
      updatedAt,
      usercode
    })
      .then(response => {
        setData(response.data)
        setError(null)
        setLoading(false)
      })
      .catch(_error => {
        setData(null)
        setError('Ha ocurrido un error al intentar registrar el usuario')
        setLoading(false)
      })
  }

  return { data, loading, error, execute }
}

export default useCreateUser
