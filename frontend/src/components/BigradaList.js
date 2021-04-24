import React, { useEffect, useState } from 'react'
import perfil from '../images/default.jpg'
import './components.css'
import M from 'materialize-css'
import CreateBrigadaForm from './modals/CreateBrigadaForm'
import useGetBrigadas from '../api/brigada/useGetBrigadas'
import PreLoader from './PreLoader'
import { useHistory } from 'react-router-dom'

const BrigadaList = () => {
  const [instance, setInstance] = useState(null)
  const { execute: getBrigadasExecute } = useGetBrigadas()
  const [brigadas, setBrigadas] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const history = useHistory()

  useEffect(() => {
    fetchBrigadas()

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
    fetchBrigadas()
  }

  const fetchBrigadas = () => {
    setIsLoading(true)
    getBrigadasExecute()
      .then((res) => {
        setBrigadas(res.data.content)
        setIsLoading(false)
      })
      .catch((err) => {
        M.toast({ html: err.response.data.description })
        setIsLoading(false)
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
            <button
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
                  onClick={() => {
                    history.push({
                      pathname: '/brigada',
                      brigada: b
                    })
                  }}
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
                    <div className="col s2">
                      <img
                        src={perfil}
                        alt=""
                        className="circle"
                        style={{ height: 80, width: 80, marginBottom: '5%' }}
                      />
                    </div>
                    <div className="col s6" style={{ textAlign: 'left' }}>
                      <span style={{ fontSize: 24 }} className="title">
                        {b.name}
                      </span>
                      <br />
                      <span>{b.numberMember} MIEMBROS</span>
                    </div>
                    <div className="col s3">
                      <img
                        src={perfil}
                        alt=""
                        className="circle secondary-content"
                        style={{
                          height: 50,
                          width: 50,
                          marginBottom: '5%',
                          left: 'auto'
                        }}
                      />
                      <img
                        src={perfil}
                        alt=""
                        className="circle secondary-content"
                        style={{
                          height: 50,
                          width: 50,
                          marginBottom: '5%',
                          left: 'auto',
                          marginRight: '30px'
                        }}
                      />
                      <img
                        src={perfil}
                        alt=""
                        className="circle secondary-content"
                        style={{
                          height: 50,
                          width: 50,
                          marginBottom: '5%',
                          left: 'auto',
                          marginRight: '60px'
                        }}
                      />
                    </div>
                  </div>
                </div>
            ))
            : null}
        </div>
      </div>
    </div>
  )
}

export default BrigadaList
