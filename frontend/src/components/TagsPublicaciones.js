import React from 'react'
import PropTypes, { object } from 'prop-types'
import './components.css'
import M from 'materialize-css'
import perfil from '../images/default.jpg'
const TagsPublicaciones = (props) => {
  const elemtsBtn = document.querySelectorAll('.fixed-action-btn')
  const floatingBtn = M.FloatingActionButton.init(elemtsBtn, {
    direction: 'left',
    hoverEnabled: false
  })
  const elems = document.querySelectorAll('.dropdown-trigger')
  const instances = M.Dropdown.init(elems, {
    coverTrigger: false
  })
  console.log(instances)
  console.log(floatingBtn)
  const imgs = props.imagen
  return (
    <div className="container " style={{ marginTop: '8%' }}>
      <div className="card" >
        <div className="divider"/>
        <div className="row">
          <div className="col s3 m2" style={{ textAlign: 'left' }}>
            <img
              src={perfil}
              alt=""
              className="materialboxed"
              style={{ height: 70, width: 70, marginBottom: '5%', marginTop: 5, marginLeft: '5%' }}
            />
          </div>
          <div className="card-content col s6 m9" style={{ textAlign: 'left' }}>
            <h6>{props.name}</h6>
          </div>
          <div className=" card-content col s1 m1 l1">
            <a className='dropdown-trigger' href='#' data-target='dropdown1'>
              <i className="material-icons" style={{ marginRight: '5%', textAlign: 'right' }}>more_vert</i>
            </a>
            <ul id='dropdown1' className='dropdown-content'>
              <li><i className="material-icons" href="#!" style={{ marginTop: 15, marginLeft: 10 }}>edit</i> Editar</li>
              <li className="divider" tabIndex="-1"/>
              <li><i className="material-icons" href="#!" style={{ marginTop: 15, marginLeft: 10 }}>delete</i> Eliminar</li>
            </ul>
          </div>
        </div>
        <div className="divider"/>
        <div>
          <p align="left" style={{ marginLeft: '5%', marginRight: '5%' }}>{props.par}</p>
          <div className="galeria" >
            {imgs.map((imagen) =>
              <img
                key={imagen.id}
                src={imagen.ima}
                alt=""
                className="materialboxed"
              />
            )}
          </div>
        </div>
        <div className="divider"/>
        <div style={{ marginTop: 5, textAlign: 'right', marginRight: '5%' }}>
         <h6 style={{ float: 'right', marginTop: 5, marginLeft: 10 }}>{props.likes}</h6>
         <i className="material-icons">thumb_up</i>
        </div>
      </div>
    </div>
  )
}
TagsPublicaciones.propTypes = {
  name: PropTypes.string,
  par: PropTypes.string,
  imagen: PropTypes.arrayOf(object),
  likes: PropTypes.number
}
export default TagsPublicaciones