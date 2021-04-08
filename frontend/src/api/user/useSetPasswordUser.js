import { useState } from 'react'
import api from '../api'

const useSetPasswordUser = () => {
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const execute = (id, pass, user) => {
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
    api.put(`/users/${pass}/${id}`, {
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
        setError('Ha ocurrido un error al intentar editar la contrasña del usuario')
        setLoading(false)
      })
  }

  return { data, loading, error, execute }
}

export default useSetPasswordUser
