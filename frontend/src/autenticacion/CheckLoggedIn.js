import { useState } from 'react'
import useIsTokenValid from '../api/user/useIsTokenValid'
const CheckLoggedIn = () => {
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)

  const { execute: isTokenValidExecute } = useIsTokenValid()

  const execute = (setIsAutenticate) => {
    setLoading(true)
    const token = localStorage.getItem('token')
    if (token !== 'null' && token !== undefined && token !== null) {
      isTokenValidExecute(token)
        .then((res) => {
          setIsAutenticate(true)
        })
        .catch((err) => {
          setError(err)
          setIsAutenticate(false)
        })
    }
    setLoading(false)
  }

  return {
    execute,
    error,
    loading
  }
}

export default CheckLoggedIn
