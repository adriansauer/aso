import React, { useState, useEffect } from 'react'
import Publications from './Publications'
import useGetPublicationsByUserId from '../api/publicaciones/useGetPublicationsByUserId'
import Loader from './PreLoader'
import PropTypes from 'prop-types'

const BrigadaPublications = (props) => {
  const { execute: getPublicationsExecute } = useGetPublicationsByUserId()
  const [publicaciones, setPublicaciones] = useState(null)
  const [loading, setLoading] = useState(false)
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
    getPublicationsExecute(pagActual, props.userId)
      .then((res) => {
        setTotalPages(
          Array.apply(null, { length: res.data.totalPages }).map(
            Number.call,
            Number
          )
        )
        setPublicaciones(res.data.content)
        setLoading(false)
      })
      .catch(() => {
        setLoading(false)
      })
  }
  return (
    <div style={{ width: '100%' }}>
      <Loader visible={loading} />
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
BrigadaPublications.propTypes = {
  userId: PropTypes.number
}
export default BrigadaPublications
