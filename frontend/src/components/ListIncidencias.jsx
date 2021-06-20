import React, { useEffect, useState } from 'react'
import useGetIncidencia from '../api/incidencias/useGetIncidencias'
import useDeleteIncidencia from '../api/incidencias/useDeleteIncidencia'
import IncidenciaForm from './modals/IncidenciaForm'

import PreLoader from './PreLoader'
import M from 'materialize-css'
const ListIncidencias = () => {
  const [incidencias, setIncidencias] = useState(null)
  const { execute: getIncidenciaExecute } = useGetIncidencia()
  const { execute: deleteIncidenciaExecute } = useDeleteIncidencia()
  const [incidenciaModal, setIncidenciaModal] = useState(null)
  const [isLoading, setIsLoading] = useState(false)
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
    handleLoad()
  }, [pagActual])

  // Reacargar ciudades
  const handleLoad = () => {
    setIncidencias(null)
    setIsLoading(true)
    getIncidenciaExecute(pagActual)
      .then((res) => {
        setTotalPages(
          Array.apply(null, { length: res.data.totalPages }).map(
            Number.call,
            Number
          )
        )
        setIncidencias(res.data.content)
        setIsLoading(false)
      })
      .catch((err) => {
        setIsLoading(false)
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
      })
  }

  useEffect(() => {
    const elem1 = document.getElementById('modal_incidence_form')
    const incidenceModal = M.Modal.init(elem1, {
      inDuration: 300
    })
    setIncidenciaModal(incidenceModal)
    M.AutoInit()
  }, [])
  // Cerrar el modal de agregar usuario
  const closeModal = () => {
    incidenciaModal.close()
    handleLoad()
  }
  useEffect(() => {
    handleLoad()
  }, [])

  const deleteIncidencia = (id) => {
    setIsLoading(true)
    deleteIncidenciaExecute(id)
      .then((res) => {
        handleLoad()
        setIsLoading(false)
      })
      .catch((err) => {
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
        setIsLoading(false)
      })
  }
  return (
    <div className="container" style={{ marginTop: '4%' }}>
        <br/>
      <IncidenciaForm close={closeModal} />
      <PreLoader visible={isLoading} />
      <div className="row">
        <blockquote style={{ borderColor: '#0C0019' }}>
          Códigos de Incidentes
          <button
            className="btn-floating waves-effect"
            style={{ marginLeft: '20px', backgroundColor: '#0C0019' }}
            onClick={() => incidenciaModal.open()}
          >
            <i className="material-icons">add</i>
          </button>
        </blockquote>
        <table>
          <thead>
            <tr>
              <th>Código</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {incidencias
              ? incidencias.map((incidencia) => {
                return (
                    <tr key={incidencia.id}>
                      <td>{incidencia.code}</td>
                      <td>{incidencia.description}</td>
                      <td>
                        <button
                          className="btn-floating btn-small"
                          style={{ backgroundColor: '#0C0019' }}
                          onClick={() => deleteIncidencia(incidencia.id)}
                        >
                          <i className="material-icons">delete</i>
                        </button>
                      </td>
                    </tr>
                )
              })
              : null}
          </tbody>
        </table>
      </div>
      <div className="row" align="center">
        <ul className="pagination">
          <li>
            <a href="#!" onClick={handleDecrementPage}>
              <i className="material-icons">chevron_left</i>
            </a>
          </li>
          {totalPages.length !== 0
            ? totalPages.map((t) => (
                <li
                  key={t}
                  onClick={() => setPagActual(t + 1)}
                  className={pagActual === t + 1 ? 'active' : ''}
                >
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

export default ListIncidencias
