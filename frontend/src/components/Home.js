import React, { useState, useEffect, useContext } from 'react'
import Publications from './Publications'
import InputPublicacion from './InputPublicacion'
import useGetPublications from '../api/publicaciones/useGetPublications'
import userContext from '../context/userContext'
import Loader from './PreLoader'
import M from 'materialize-css'
const Home = () => {
  const { execute: getPublicationsExecute } = useGetPublications()
  const [publicaciones, setPublicaciones] = useState(null)
  const [loading, setLoading] = useState(false)
  const { userData } = useContext(userContext)
  const [totalPages, setTotalPages] = useState([])
  const [pagActual, setPagActual] = useState(1)
  const handleIncrementPage = () => {
    if (pagActual < totalPages.length) {
      setPagActual(pagActual + 1)
    }
  }
  const handleDecrementPage = () => {
    if (pagActual > 1) {
      setPagActual(pagActual - 1)
    }
  }
  useEffect(() => {
    handleLoadPublications()
  }, [pagActual])

  // Reacargar publicaciones
  const handleLoadPublications = () => {
    setPublicaciones(null)
    setLoading(true)
    getPublicationsExecute(pagActual)
      .then((res) => {
        setTotalPages(
          Array.apply(null, { length: res.data.totalPages }).map(
            Number.call,
            Number
          )
        )
        console.log(totalPages)
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
        {!loading && userData.roles[0].authority !== 'ROLE_SUPERUSER'
          ? (
          <InputPublicacion reloadPublications={handleLoadPublications} />
            )
          : null}
      </div>
      <div>
        <Publications
          totalPages={totalPages}
          publicaciones={publicaciones}
          reloadPublications={handleLoadPublications}
        />
      </div>
      <div>
        <ul className="pagination">
          <li>
            <a href="#!" onClick={handleDecrementPage}>
              <i className="material-icons">chevron_left</i>
            </a>
          </li>
          {totalPages.length !== 0
            ? totalPages.map((t) => (
                <li
                key={t} onClick={() => setPagActual(t + 1)}
                className={pagActual === (t + 1) ? 'active' : ''}>
                  <a href="#!">{t + 1}</a>
                </li>
            ))
            : null}

          <li>
            <a href="#!" onClick={handleIncrementPage}>
              <i className="material-icons">chevron_right</i>
            </a>
          </li>
        </ul>
      </div>
    </div>
  )
}
export default Home
