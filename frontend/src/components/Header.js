import React, { useEffect, useState, useContext } from 'react'
import M from 'materialize-css/dist/js/materialize.min.js'
import perfil from '../images/default.jpg'
import './components.css'
import userContext from '../context/userContext'
import { Link } from 'react-router-dom'
const Header = () => {
  const [instance, setInstance] = useState(null)
  const { isAutenticate, setIsAutenticate, userData } = useContext(userContext)
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
  return (
    <>
      {/** Vista del navbar */}
      <nav style={{ position: 'fixed', zIndex: 9999, left: 0, right: 0, top: 0 }}>
        <div className="nav-wrapper" style={{ backgroundColor: '#0C0019' }}>
          {isAutenticate
            ? (
            <>
              <a href="#" className="brand-logo right">
                <ul>
                  <li>
                    <h6 style={{ marginRight: '2%' }}>{userData.username}</h6>
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
                    <img
                      className="circle"
                      width={60}
                      height={60}
                      src={perfil}
                      alt=''
                    />
                  </li>
                </ul>
              </a>

              <ul id="nav-mobile" className="left">
                <li>
                  <button
                    style={{ backgroundColor: '#0C0019' }}
                    className="btn btn-large waves-light"
                    onClick={handleToggle}
                  >
                    <i className="material-icons">dehaze</i>
                  </button>
                </li>
              </ul>
            </>
              )
            : null}
        </div>
      </nav>
      {/** Vista del Sidebar */}
      <div>
        <ul id="slide-out" className="sidenav ulSidenav">
          {/** Link del perfil del usuario */}
          <li>
            <Link
              to="/usuarioperfil"
              onClick={() => instance.close()}
            >
              <i className="medium material-icons white-text">group</i>
              <span style={{ color: 'white' }}>Perfil de un usuario</span>
            </Link>
          </li>
          {/** Link de brigadas */}
          <li>
            <Link
              to="/brigadaperfil"
              onClick={() => instance.close()}
            >
              <i className="medium material-icons white-text">group</i>
              <span style={{ color: 'white' }}>Perfil de una brigada</span>
            </Link>
          </li>
          <li>
            <Link
              to="/usuariolist"
              onClick={() => instance.close()}
            >
              <i className="medium material-icons white-text">group</i>
              <span style={{ color: 'white' }}>Lista de usuarios</span>
            </Link>
          </li>
          <li>
            <Link
              to="/brigadalist"
              onClick={() => instance.close()}
            >
              <i className="medium material-icons white-text">group</i>
              <span style={{ color: 'white' }}>Lista de brigadas</span>
            </Link>
          </li>
          {/** Link de configuraciones */}
          <li>
            <a href="#!" className="white-text">
              <i className="medium material-icons white-text">settings</i>
              Configuraciones
            </a>
          </li>
          {/** Ciudades */}
          <li>
            <Link
              to="/cities"
              onClick={() => instance.close()}
            >
              <i className="medium material-icons white-text">group</i>
              <span style={{ color: 'white' }}>Ciudades y Departamentos</span>
            </Link>
          </li>
        </ul>
      </div>
    </>
  )
}
export default Header
