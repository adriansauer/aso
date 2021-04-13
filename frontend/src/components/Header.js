import React, { useEffect, useState, useContext } from 'react'
import M from 'materialize-css/dist/js/materialize.min.js'
import perfil from '../images/perfil.jpg'
import './components.css'
import userContext from '../context/userContext'
const Header = () => {
  const [instance, setInstance] = useState(null)
  const { setUserData, isAutenticate } = useContext(userContext)
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
    setUserData({
      token: null,
      user: {
        displayName: null,
        id: null,
        roles: null
      }
    })
  }
  return (
    <>
      {/** Vista del navbar */}
      <nav>
        <div className="nav-wrapper" style={{ backgroundColor: '#0C0019' }}>
          {isAutenticate
            ? (
            <>
              <a href="#" className="brand-logo right">
                <ul>
                  <li>
                    <h6 style={{ marginRight: '2%' }}>Victor Gimenez</h6>
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
                    />
                  </li>
                </ul>
              </a>

              <ul id="nav-mobile" className="left">
                <li>
                  <button
                    style={{ backgroundColor: '#0C0019' }}
                    className="btn btn-large waves-effect waves-light"
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
            <a href="#!" className="white-text">
              <i className="medium material-icons white-text">person</i>
              Mi perfil
            </a>
          </li>
          {/** Link de brigadas */}
          <li>
            <a href="#!" className="white-text">
              <i className="medium material-icons white-text">group</i>
              Brigadas
            </a>
          </li>
          {/** Link de configuraciones */}
          <li>
            <a href="#!" className="white-text">
              <i className="medium material-icons white-text">settings</i>
              Configuraciones
            </a>
          </li>
        </ul>
      </div>
    </>
  )
}
export default Header
