import React from 'react'
import Perfil from '../images/perfil3.jpg'
import BrigadaPerfil from '../images/logo1.png'
const UsuarioPerfil = () => {
  return (
    <div className="container" style={{ marginTop: '5%' }}>
      <div style={{ marginBottom: 0 }} className="row">
        <img
          alt=""
          className="circle"
          src={Perfil}
          style={{ width: '15%', marginTop: '1%' }}
        ></img>
      </div>
      <div className="row center-align">
        <h6 style={{ margin: 0 }}>ENC-999</h6>
      </div>

      <div className="row center-align">
        <h4 style={{ margin: 0 }}>Cecilio Dominguez</h4>
      </div>
      <div className="row center-align">
        <div className="col s5 right-align">
          <img
            alt=""
            className="circle"
            src={BrigadaPerfil}
            style={{ width: 50, height: 50 }}
          ></img>
        </div>
        <div className="col s7 left-align">
          <h6>Brigada de Cambyreta</h6>
        </div>
      </div>
      <div className="row center-align">
        <div className="card-panel teal white left-align">
          <ul>
            <li>
              <div className="row left-align">
                <div className="col s2 m1 left-align">
                  <i style={{ fontSize: 36 }} className="material-icons">
                    cake
                  </i>
                </div>
                <div className="col s7 m6 left-align">
                  <span style={{ fontSize: 16 }}>23/03/1995</span>
                </div>
              </div>
            </li>
            <li>
              <div className="row left-align">
                <div className="col s2 m1 left-align">
                  <i style={{ fontSize: 36 }} className="material-icons">
                    local_phone
                  </i>
                </div>
                <div className="col s7 m6 left-align">
                  <span style={{ fontSize: 16 }}>0985 728 401</span>
                </div>
              </div>
            </li>
            <li>
              <div className="row left-align">
                <div className="col s2 m1 left-align">
                  <i style={{ fontSize: 36 }} className="material-icons">
                    email
                  </i>
                </div>
                <div className="col s7 m6 left-align">
                  <span style={{ fontSize: 16 }}>cecilio@gmail.com</span>
                </div>
                <div className="col s3 m5 right-align">
                  <button
                    style={{ backgroundColor: '#0C0019' }}
                    className="btn-floating btn-medium waves-effect waves-light"
                  >
                    <i className="material-icons">edit</i>
                  </button>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default UsuarioPerfil
