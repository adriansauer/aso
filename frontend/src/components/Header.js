import React from 'react'
import perfil from '../images/perfil.jpg'
const Header = () => {
  return (
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
            <a href="#">
              <i className="material-icons">dehaze</i>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  )
}
export default Header
