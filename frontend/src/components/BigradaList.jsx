import React, { useEffect, useState, useContext } from 'react'
import './components.css'
import M from 'materialize-css'
import CreateBrigadaForm from './modals/CreateBrigadaForm'
import useGetBrigadas from '../api/brigada/useGetBrigadas'

import PreLoader from './PreLoader'
import { useHistory } from 'react-router-dom'
import userContext from '../context/userContext'
import Image from './Image'
const BrigadaList = () => {
  const [instance, setInstance] = useState(null)
  const { execute: getBrigadasExecute } = useGetBrigadas()
  const [brigadas, setBrigadas] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [totalPages, setTotalPages] = useState([])
  const [pagActual, setPagActual] = useState(1)
  const history = useHistory()
  const { userData, setSelectData, selectData } = useContext(userContext)

  useEffect(() => {
    handleLoadBrigades()
  }, [pagActual])

  // Reacargar brigadas
  const handleLoadBrigades = () => {
    setBrigadas(null)
    setIsLoading(true)
    getBrigadasExecute(pagActual)
      .then((res) => {
        setTotalPages(
          Array.apply(null, { length: res.data.totalPages }).map(
            Number.call,
            Number
          )
        )
        setBrigadas(res.data.content)
        setIsLoading(false)
      })
      .catch(() => {
        setIsLoading(false)
      })
  }
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
    handleLoadBrigades()

    const elem1 = document.querySelector('.modal')
    const instance = M.Modal.init(elem1, {
      inDuration: 300
    })
    setInstance(instance)
    M.AutoInit()
  }, [])
  // Cerrar el modal de agregar usuario
  const closeModal = () => {
    instance.close()
    handleLoadBrigades()
  }
  const handleGoToBrigadeProfile = (brigadaId) => {
    setSelectData({
      brigadaId,
      userId: selectData.userId
    })
    history.push({
      pathname: '/brigada'
    })
  }
  return (
    <div className="container">
      <CreateBrigadaForm close={closeModal} />
      <PreLoader visible={isLoading}/>
      <div className="row">
        <div className="col m12 s12 center-align">
          <h1>
            Brigadas
            {userData.roles[0].authority === 'ROLE_SUPERUSER'
              ? <button
            className="btn btn-floating btn-medium waves-light tooltipped"
            data-position="top"
            data-tooltip="Agregar nueva brigada"
            onClick={() => instance.open()}
            style={{
              backgroundColor: '#0C0019',
              marginLeft: '5%'
            }}
          >
            <i className="material-icons">add</i>
          </button>
              : null}

          </h1>
        </div>
      </div>
      <div className="row">
        <div className="collection">
          {brigadas !== null
            ? brigadas.map((b) => (
                <div
                  key={b.id}
                  className="btn btn-large collection-item avatar brigada_button"
                  onClick={() => handleGoToBrigadeProfile(b.id)}
                  style={{
                    margin: '4%',
                    color: 'black',
                    paddingTop: 0,
                    paddingLeft: 0,
                    marginTop: '2%',
                    width: '90%',
                    borderRadius: '1%'
                  }}
                >
                  <div className="row">
                    <div className="col s4 m2">
                      <Image imageId={b.imageId}/>
                    </div>
                    <div className="col s8 m6" style={{ textAlign: 'left' }}>
                      <span className="title responsive">
                        {b.name}
                      </span>
                      <br />
                      <span className='responsive'>{b.numberMember} MIEMBROS</span>
                    </div>

                  </div>
                </div>
            ))
            : null}
        </div>
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

export default BrigadaList
