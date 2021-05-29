import React, { useState, useEffect } from 'react'
import Publications from './Publications'
import InputPublicacion from './InputPublicacion'
import useGetPublications from '../api/publicaciones/useGetPublications'
import Loader from './PreLoader'
import M from 'materialize-css'
const Home = () => {
  const { execute: getPublicationsExecute } = useGetPublications()
  const [publicaciones, setPublicaciones] = useState(null)
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    handleLoadPublications()
  }, [])
  // Reacargar publicaciones
  const handleLoadPublications = () => {
    setPublicaciones(null)
    setLoading(true)
    getPublicationsExecute()
      .then((res) => {
        setPublicaciones(res.data.content)
        setLoading(false)
      })
      .catch((err) => {
        setLoading(false)
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
      })
  }
  return (
    <div style={{ width: '100%' }}>
      <Loader visible={loading} />
      <div>
        {!loading
          ? (
          <InputPublicacion reloadPublications={handleLoadPublications} />
            )
          : null}
      </div>
      <div>
        <Publications
          publicaciones={publicaciones}
          reloadPublications={handleLoadPublications}
        />
      </div>
    </div>
  )
}
export default Home
