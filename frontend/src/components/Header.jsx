import React, { useEffect, useState, useContext } from 'react'
import M from 'materialize-css/dist/js/materialize.min.js'
import perfil from '../images/default.jpg'
import './components.css'
import userContext from '../context/userContext'
import { Link, useHistory } from 'react-router-dom'
import useGetBrigadaById from '../api/brigada/useGetBrigadaById'
import useGetMemberById from '../api/miembros/useGetMemberById'
const Header = (props) => {
  const [instance, setInstance] = useState(null)
  const [brigada, setBrigada] = useState(null)
  const {
    isAutenticate,
    setIsAutenticate,
    userData,
    setSelectData,
    selectData
  } = useContext(userContext)

  const { execute: getBrigadaByIdExecute } = useGetBrigadaById()
  const { execute: getMemberByIdExecute } = useGetMemberById()
  const history = useHistory()
  const [profile, setProfile] = useState(null)
  const handleSelectMyProfile = () => {
    if (userData.roles[0].authority === 'ROLE_BRIGADE') {
      setSelectData({
        userId: selectData.userId,
        brigadaId: userData.perfilId
      })
    } else if (userData.roles[0].authority === 'ROLE_USER') {
      setSelectData({
        userId: userData.perfilId,
        brigadaId: selectData.brigadaId
      })
    }
  }
  /** Obtener el perfil del usuario o brigada */
  const fetchProfile = () => {
    if (userData.roles[0].authority === 'ROLE_BRIGADE') {
      setSelectData({
        userId: selectData.userId,
        brigadaId: userData.perfilId
      })
      getBrigadaByIdExecute(userData.perfilId)
        .then((res) => {
          setProfile(res.data)
        })
        .catch((err) => {
          M.toast({
            html:
              err.response === undefined
                ? 'Hubo un error con la conexión'
                : err.response.data.description
          })
        })
    }
    if (userData.roles[0].authority === 'ROLE_USER') {
      setSelectData({
        userId: userData.perfilId,
        brigadaId: selectData.brigadaId
      })
      getMemberByIdExecute(userData.perfilId)
        .then((res) => {
          setProfile(res.data)

          getBrigadaByIdExecute(res.data.brigadeId).then((res) => {
            setBrigada(res.data)
          })
        })
        .catch((err) => {
          M.toast({
            html:
              err.response === undefined
                ? 'Hubo un error con la conexión'
                : err.response.data.description
          })
        })

        .catch((err) => {
          M.toast({
            html:
              err.response === undefined
                ? 'Hubo un error con la conexión'
                : err.response.data.description
          })
        })
    }
  }
  useEffect(() => {
    if (userData.perfilId !== null) {
      fetchProfile()
    }
  }, [userData])
  /** Cuando se levanta el componente se instancia el Sidebar para almacenarlo en el estado y manipularlo desde ahi */
  useEffect(() => {
    const elem = document.querySelector('.sidenav')
    /** El sidebar se abre a la izquierda y tiene una transicion de 300 milisegundos */
    const instance = M.Sidenav.init(elem, {
      edge: 'left',
      inDuration: 300
    })
    setInstance(instance)
  }, [])
  /** Abrir o cerrar el sidebar */
  const handleToggle = () => {
    if (instance !== null) {
      if (instance.isOpen) {
        instance.close()
      } else {
        instance.open()
      }
    }
  }
  const handleLogout = () => {
    localStorage.setItem('token', null)
    setIsAutenticate(false)
  }
  const handleDashboard = () => {
    history.push('/dashboard')
  }
  const handleLogin = () => {
    history.push('/login')
  }

  return (
    <>
      {/** Vista del navbar */}
      <nav
        style={{ position: 'fixed', zIndex: 9999, left: 0, right: 0, top: 0 }}
      >
        <div className="nav-wrapper" style={{ backgroundColor: '#0C0019' }}>
          {isAutenticate
            ? (
            <>
              <a href="#" className="brand-logo right">
                <ul>
                  <li>
                    <h6 style={{ marginRight: '2%' }}>
                      {userData.username.replace('null', '')}
                    </h6>
                  </li>
                  <li>
                    <i
                      className="material-icons"
                      onClick={handleLogout}
                      style={{ marginLeft: '2%' }}
                    >
                      exit_to_app
                    </i>
                  </li>
                  <li>
                    {profile !== null
                      ? (
                      <label
                        style={{ marginTop: '3%', width: 50, height: 50 }}
                        htmlFor="file-input"
                        className="btn-floating btn-large"
                      >
                        <img
                          src={profile.image || perfil}
                          style={{ width: '100%' }}
                        />
                      </label>
                        )
                      : (
                      <img
                        className="circle"
                        width={55}
                        height={55}
                        src={perfil}
                        alt=""
                      />
                        )}
                  </li>
                </ul>
              </a>

              <ul id="nav-mobile" className="left">
                <li>
                  <button
                    style={{ backgroundColor: '#0C0019', marginBottom: '3%' }}
                    className="btn btn-large waves-light"
                    onClick={handleToggle}
                  >
                    <i className="material-icons">dehaze</i>
                  </button>
                </li>
                <li>
                <h5>ASO</h5>
                </li>

              </ul>
            </>
              )
            : <a href="#" className="brand-logo right">
            <ul>
               <li>
                <i
                  className="material-icons"
                  onClick={handleLogin}
                  style={{ marginLeft: '2%', fontSize: 45 }}
                >
                  assignment_ind
                </i>
              </li>
            <li>
                <i
                  className="material-icons"
                  onClick={handleDashboard}
                  style={{ marginLeft: '2%', fontSize: 45 }}
                >
                  insert_chart
                </i>
              </li>
              </ul>
              </a>
              }
        </div>
      </nav>
      {/** Vista del Sidebar */}
      <div>
        <ul id="slide-out" className="sidenav ulSidenav">
          <li>
            <Link
              to={{
                pathname: '/',
                member: profile,
                brigada: brigada
              }}
              onClick={() => {
                instance.close()
              }}
            >
              <i className="medium material-icons white-text">home</i>
              <span style={{ color: 'white', fontSize: 16 }}>Home</span>
            </Link>
          </li>
          <li>
            <Link
              to={{
                pathname: '/dashboard'
              }}
              onClick={() => {
                instance.close()
              }}
            >
              <i className="medium material-icons white-text">insert_chart</i>
              <span style={{ color: 'white', fontSize: 16 }}>Dashboard</span>
            </Link>
          </li>
          {/** Link del perfil del usuario */}
          {/** Si el usuario es SUPERUSER no tiene perfil */}
          {userData.roles !== null
            ? userData.roles[0].authority !== 'ROLE_SUPERUSER'
              /** si es usuario le mando a perfil de usuario */
              ? userData.roles[0].authority === 'ROLE_USER'
                ? <li>
                  <Link
                    to={{}}
                    onClick={() => {
                      handleSelectMyProfile()
                      history.push('/perfil')
                      instance.close()
                    }}
                  >
                    <i className="medium material-icons white-text">group</i>
                    <span style={{ color: 'white', fontSize: 16 }}>
                      Mi perfil
                    </span>
                  </Link>
                </li>
              /** Si es BRIGADA le mando a perfil de BRIGADA */
                : userData.roles[0].authority === 'ROLE_BRIGADE'
                  ? (
                <li>
                  <Link
                    to={{}}
                    onClick={() => {
                      handleSelectMyProfile()
                      history.push('/brigada')
                      instance.close()
                    }}
                  >
                    <i className="medium material-icons white-text">group</i>
                    <span style={{ color: 'white', fontSize: 16 }}>
                      Mi perfil
                    </span>
                  </Link>
                </li>
                    )
                  : null
              : null
            : null}

          {/** Link de lisa de brigadas */}
          <li>
            <Link to="/brigadas" onClick={() => instance.close()}>
              <i className="medium material-icons white-text">group</i>
              <span style={{ color: 'white', fontSize: 16 }}>Brigadas</span>
            </Link>
          </li>
          {/** Lista de incidencias */}
          {userData.roles !== null
            ? (
                userData.roles[0].authority === 'ROLE_SUPERUSER'
                  ? (
              <li>
                <Link to="/incidencias" onClick={() => instance.close()}>
                  <i className="medium material-icons white-text">whatshot</i>
                  <span style={{ color: 'white', fontSize: 14 }}>
                    Incidentes
                  </span>
                </Link>
              </li>
                    )
                  : null
              )
            : null}
          {/** Ciudades y departamentos */}
          {userData.roles !== null
            ? (
                userData.roles[0].authority === 'ROLE_SUPERUSER'
                  ? (
              <li>
                <Link to="/cities" onClick={() => instance.close()}>
                  <i className="medium material-icons white-text">location_city</i>
                  <span style={{ color: 'white', fontSize: 14 }}>
                    Ciudades y Departamentos
                  </span>
                </Link>
              </li>
                    )
                  : null
              )
            : null}

          {/** Link de configuraciones */}
          <li>
          <Link to="/configuracion" onClick={() => instance.close()}>
              <i className="medium material-icons white-text">settings</i>
                  <span style={{ color: 'white', fontSize: 14 }}>
                    Configuración
                  </span>
            </Link>
          </li>
        </ul>
      </div>
    </>
  )
}

export default Header
