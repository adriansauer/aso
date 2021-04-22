import React, { useEffect, useState } from 'react'
import CreateUserForm from './CreateUserForm'
import M from 'materialize-css'
import perfil from '../images/default.jpg'
import PropTypes from 'prop-types'
import { useLocation, useHistory } from 'react-router-dom'
const BrigadaPerfil = (props) => {
  // Instancia del modal para el formulario de usuario
  const [instance, setInstance] = useState(null)
  const location = useLocation()
  const history = useHistory()
  // Creo la instancia al inicio
  useEffect(() => {
    if (location.brigada === undefined) {
      history.goBack()
    }
    const elem1 = document.querySelector('.modal')
    const instance = M.Modal.init(elem1, {
      inDuration: 300
    })
    M.AutoInit()
    setInstance(instance)
  }, [])
  // Cerrar el modal de agregar usuario
  const closeModal = () => {
    instance.close()
  }

  return (
    <div className="container" style={{ marginTop: '5%' }}>
      <div style={{ margin: 0 }} className="row center">
        <img
          alt=""
          className="circle"
          style={{ width: '15%' }}
          src={perfil}
        ></img>
      </div>
      <div className="row center">
        <h4 style={{ margin: 0 }}>
          {location.brigada === undefined ? null : location.brigada.name}
        </h4>
      </div>
      <div className="row center">
        <div className="col s6 right-align">
          <button
            className="btn btn-small waves-light"
            style={{ backgroundColor: '#0C0019', color: 'white' }}
            onClick={() => {
              history.push({
                pathname: '/usuariolist',
                brigada: location.brigada
              })
            }}
          >
            {location.brigada === undefined ? null : location.brigada.numberMember} MIEMBROS
          </button>
        </div>
        <div className="col s6 left-align">
          <img
            src={perfil}
            alt=""
            className="circle"
            style={{
              height: 40,
              width: 40,
              position: 'absolute'
            }}
          />
          <img
            src={perfil}
            alt=""
            className="circle"
            style={{
              height: 40,
              width: 40,
              position: 'absolute',
              marginLeft: 25
            }}
          />
          <img
            src={perfil}
            alt=""
            className="circle"
            style={{
              height: 40,
              width: 40,
              position: 'absolute',
              marginLeft: 50
            }}
          />

          <button
            className="btn-floating btn-medium waves-light"
            onClick={() => instance.open()}
            style={{
              backgroundColor: '#0C0019',
              position: 'absolute',
              marginLeft: 75
            }}
          >
            <i className="material-icons">add</i>
          </button>
        </div>
      </div>
      <div className="row center" style={{ marginTop: '5%' }}>
        <div className="row">
          <div className="col s12">
            <ul className="tabs">
              <li className="tab col s4">
                <a href="#test1">Publicaciones</a>
              </li>
              <li className="tab col s4">
                <a className="active" href="#test2">
                  Dashboard
                </a>
              </li>
              <li className="tab col s4">
                <a href="#test4">Historia</a>
              </li>
            </ul>
          </div>
          <div id="test1" className="col s12">
            Publicaciones de la brigada
            <br />
          </div>
          <div id="test2" className="col s12">
            Dashboard
          </div>
          <div id="test4" className="col s12">
            El C.B.V.P fue fundado el 4 de octubre de 1978. Sus principales
            precursores fueron, el Sr. Miguel Ángel Estigarribia, en aquel
            entonces Presidente del Club de Leones del Barrio La Encarnación, de
            la ciudad de Asunción, el Dr. Gonzalo Figueroa Yáñez, quien fuera el
            Superintendente del Cuerpo de Bomberos Voluntarios de Santiago
            (Chile), el cual recomendaría, en una visita al país, que fuera
            fundado un cuerpo en el territorio nacional, y recibiendo el apoyo
            de muchos otros que concordaron con esta idea.
          </div>
        </div>
      </div>

      <CreateUserForm close={closeModal} />
    </div>
  )
}
BrigadaPerfil.propTypes = {
  brigada: PropTypes.object
}
export default BrigadaPerfil
