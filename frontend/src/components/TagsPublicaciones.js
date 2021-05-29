import React, { useState, useEffect, useContext } from 'react'
import PropTypes, { object } from 'prop-types'
import './components.css'
import M from 'materialize-css'
import userContext from '../context/userContext'
import perfil from '../images/default.jpg'
import useGetUserById from '../api/user/useGetUserById'
import useDeletePublication from '../api/publicaciones/useDeletePublication'
import Swal from 'sweetalert2'
const TagsPublicaciones = (props) => {
  const { execute: getUserByIdExecute } = useGetUserById(null)
  const { execute: deletePublicationExecute } = useDeletePublication(null)
  const { userData } = useContext(userContext)
  const [users, setUsers] = useState(null)
  const elemtsBtn = document.querySelectorAll('.fixed-action-btn')
  const floatingBtn = M.FloatingActionButton.init(elemtsBtn, {
    direction: 'left',
    hoverEnabled: false
  })
  const elems = document.querySelectorAll('.dropdown-trigger')
  const instances = M.Dropdown.init(elems, {
    coverTrigger: false
  })
  const handleDeletePublication = () => {
    Swal.fire({
      title: 'Publicación',
      text: '¿Eliminar publlicación?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((resultado) => {
      if (resultado.value) {
        deletePublicationExecute(props.publicationId)
          .then(res => {
            props.reloadPublications()
            M.toast({ html: 'Su publicación ha sido eliminada' })
          }).catch((err) => {
            M.toast({
              html:
          err.response === undefined
            ? 'Hubo un error con la conexión'
            : err.response.data.description
            })
          })
      }
    })
  }
  useEffect(() => {
    if (instances !== null && floatingBtn !== null) {
      user()
    }
  }, [])
  const user = () => {
    if (props.userId !== null) {
      getUserByIdExecute(props.userId)
        .then((res) => {
          setUsers(res.data)
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
  return (
    <div className="container " style={{ marginTop: '8%' }}>
      {users !== null
        ? <div className="card">
          <div className="divider" />
          <div className="row">
            <div className="col s3 m2" style={{ textAlign: 'left' }}>
              <img
                src={perfil}
                alt=""
                className="materialboxed"
                style={{
                  height: 70,
                  width: 70,
                  marginBottom: '5%',
                  marginTop: 5,
                  marginLeft: '5%'
                }}
              />
            </div>
            <div
              className="card-content col s6 m9"
              style={{ textAlign: 'left' }}
            >
              <h6>
                {users.name} {users.lastname}
              </h6>
            </div>
            {userData.roles[0].authority === 'ROLE_SUPERUSER' ||
            userData.id === users.id ||
            userData.roles[0].authority === 'ROLE_BRIGADE'
              ? <button
                className="dropdown-trigger btn btn-floating btn-medium waves-light"
                data-target="dropdown2"
                style={{
                  backgroundColor: 'white',
                  marginTop: '4%'
                }}
              >
                <i
                  className="medium material-icons"
                  style={{
                    color: '#0C0019'
                  }}
                >
                  expand_more
                </i>
              </button>

              : null}
            <ul id="dropdown2" className="dropdown-content">
              <li onClick={handleDeletePublication}>
                <a style={{ color: '#0C0019' }}>Eliminar</a>
              </li>
              <li>
                <a style={{ color: '#0C0019' }}>Editar</a>
              </li>
            </ul>
          </div>
          <div className="divider" />
          <div>
            <p align="left" style={{ marginLeft: '5%', marginRight: '5%' }}>
              {props.par}
            </p>
            <div className="galeria">
              {/* imgs.map((imagen) =>
              <img
                key={imagen.id}
                src={imagen.ima}
                alt=""
                className="materialboxed"
              />
            ) */}
            </div>
          </div>
          <div className="divider" />
          <div style={{ marginTop: 5, textAlign: 'right', marginRight: '5%' }}>
            <h6 style={{ float: 'right', marginTop: 5, marginLeft: 10 }}>
              {props.likes}
            </h6>
            <i className="material-icons">thumb_up</i>
          </div>
        </div>
        : null}
    </div>
  )
}
TagsPublicaciones.propTypes = {
  userId: PropTypes.number,
  par: PropTypes.string,
  imagen: PropTypes.arrayOf(object),
  likes: PropTypes.number,
  publicationId: PropTypes.number,
  reloadPublications: PropTypes.func
}
export default TagsPublicaciones
