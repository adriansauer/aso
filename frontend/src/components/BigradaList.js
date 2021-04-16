import React from 'react'
import Logo from '../images/logo1.png'
import Perfil1 from '../images/perfil1.jpg'
import Perfil2 from '../images/perfil2.jpg'
import Perfil3 from '../images/perfil3.jpg'
import './components.css'
const BrigadaList = () => {
  return (
    <div className="container">
      <div className="row">
        <h1>Brigadas</h1>
      </div>
      <div className="row">
        <div className="collection">
          {/** Item collection representa cada una de las brigadas */}
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
              <div className="col s2">
                <img
                  src={Logo}
                  alt=""
                  className="circle"
                  style={{ height: 80, width: 80, marginBottom: '5%' }}
                />
              </div>
              <div className="col s6" style={{ textAlign: 'left' }}>
                <span style={{ fontSize: 24 }} className="title">
                  Brigada de Cambyreta
                </span>
                <br />
                <span>24 Miembros</span>
              </div>
              <div className="col s3">
                <img
                  src={Perfil1}
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
                  src={Perfil2}
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
                  src={Perfil3}
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
        </div>
      </div>
    </div>
  )
}

export default BrigadaList
