import React, { useEffect, useState } from 'react'
import perfil from '../images/default.jpg'
import useGetMembers from '../api/miembros/useGetMembers'
import PreLoader from './PreLoader'
import { useLocation, useHistory } from 'react-router-dom'
import M from 'materialize-css'
import './components.css'
const UsuarioList = () => {
  const location = useLocation()
  const history = useHistory()
  const [members, setMembers] = useState(null)
  const [isLoading, setIsLoading] = useState(false)
  const { execute: getMembersExecute } = useGetMembers()
  const [brigadaId, setBrigadaId] = useState(null)
  const [totalPages, setTotalPages] = useState([])
  const [pagActual, setPagActual] = useState(1)
  useEffect(() => {
    handleLoadMembers()
  }, [pagActual])
  // Reacargar miembros
  const handleLoadMembers = () => {
    if (brigadaId !== null) {
      setMembers(null)
      setIsLoading(true)
      console.log(brigadaId)
      getMembersExecute(brigadaId, pagActual)
        .then((res) => {
          setTotalPages(
            Array.apply(null, { length: res.data.totalPages }).map(
              Number.call,
              Number
            )
          )
          setMembers(res.data.content)
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
    if (location.brigada === undefined) {
      history.goBack()
    }
  }, [])
  useEffect(() => {
    if (location.brigada !== undefined) {
      setBrigadaId(location.brigada.id)
    }
  }, [location.brigada])
  useEffect(() => {
    if (brigadaId !== null) {
      handleLoadMembers()
    }
  }, [brigadaId])
  return (
    <div className="container" style={{ marginTop: '4%' }}>
      <PreLoader visible={isLoading} />
      <div className="row">
        <h3>
          Miembros de{' '}
          {location.brigada === undefined ? null : location.brigada.name}
        </h3>
      </div>
      {members !== null
        ? <div className="row">
          <div className="collection">
            {/** Item collection representa cada uno de los usuarios */}
            {members.map((member) => (
              <div
                key={member.id}
                onClick={() => {
                  history.push({
                    pathname: '/perfil',
                    member: member,
                    brigada: location.brigada
                  })
                }}
                className="btn btn-large collection-item avatar brigada_button"
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
                {/** Foto de perfil */}
                <div className="row">
                  <div className="col s4 m2">
                    <img
                      src={perfil}
                      alt=""
                      className="circle"
                      style={{ height: 75, width: 75, marginBottom: '5%' }}
                    />
                  </div>
                  <div className="col s8 m6" style={{ textAlign: 'left' }}>
                    <span className="title responsive">
                      {member.name} {member.lastname}
                    </span>
                    <br />
                    <span>{member.usercode}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
        : null}
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

export default UsuarioList
