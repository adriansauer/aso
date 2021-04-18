import React from 'react'
import Perfil from '../images/perfil2.jpg'
import Perfil2 from '../images/perfil3.jpg'
const UsuarioList = () => {
  return (
  <div className="container" style={{ marginTop: '5%' }}>
      <div className="row">
        <h3>Miembros de Cambyreta</h3>
      </div>
      <div className="row">
        <div className='collection'>
          {/** Item collection representa cada uno de los usuarios */}
            <div
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
            <div className="row">
              <div className="col s6 m2">
                <img
                  src={Perfil}
                  alt=""
                  className="circle"
                  style={{ height: 80, width: 80, marginBottom: '5%' }}
                />
              </div>
              <div className="col s6 m6" style={{ textAlign: 'left' }}>
                <span style={{ fontSize: 24 }} className="title">
                 Carlos Britez
                </span>
                <br />
                <span>CMP-56</span>
              </div>
            </div>
          </div>
         {/** Item collection representa cada uno de los usuarios */}
         <div
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
            <div className="row">
              <div className="col s6 m2">
                <img
                  src={Perfil2}
                  alt=""
                  className="circle"
                  style={{ height: 80, width: 80, marginBottom: '5%' }}
                />
              </div>
              <div className="col s6 m6" style={{ textAlign: 'left' }}>
                <span style={{ fontSize: 24 }} className="title">
                 Cecilio Dominguez
                </span>
                <br />
                <span>ENC-999</span>
              </div>
            </div>
          </div>
        </div>
      </div>
  </div>
  )
}

export default UsuarioList
