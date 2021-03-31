import React, { useEffect, useState } from 'react'
import M from 'materialize-css/dist/js/materialize.min.js'
import perfil from '../images/perfil.jpg'
import './components.css'
const Header = () => {
  const [instance, setInstance] = useState(null)
  useEffect(() => {
    const elem = document.querySelector('.sidenav')
    const instance = M.Sidenav.init(elem, {
      edge: 'left',
      inDuration: 300
    })
    setInstance(instance)
  }, [])
  const handleToggle = () => {
    if (instance !== null) {
      if (instance.isOpen) {
        instance.close()
      } else {
        instance.open()
      }
    }
  }

  return (
    <>
      <nav>
        <div className="nav-wrapper" style={{ backgroundColor: '#0C0019' }}>
          <a href="#" className="brand-logo right">
            <ul>
              <li>
                <h6 style={{ marginRight: '2%' }}>Victor Gimenez</h6>
              </li>
              <li>
                <i className="material-icons" style={{ marginLeft: '2%' }}>
                  exit_to_app
                </i>
              </li>
              <li>
                <img className="circle" width={60} height={60} src={perfil} />
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

        </div>
      </nav>
      <div>
        <ul id="slide-out" className="sidenav ulSidenav">
          <li>
            <a href="#!" className="white-text">
              <i className="medium material-icons white-text">person</i>
              Mi perfil
            </a>
          </li>

          <li>
            <a href="#!" className="white-text">
              <i className="medium material-icons white-text">group</i>
              Brigadas
            </a>
          </li>

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
