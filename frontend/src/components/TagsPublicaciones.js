import React, { useState, useEffect, useContext } from 'react'
import PropTypes, { object } from 'prop-types'
import './components.css'
import M from 'materialize-css'
import userContext from '../context/userContext'
import perfil from '../images/default.jpg'
import useGetUserById from '../api/user/useGetUserById'
import useDeletePublication from '../api/publicaciones/useDeletePublication'
import Swal from 'sweetalert2'
import EditModal from './modals/EditarPublicacionModal'
const TagsPublicaciones = (props) => {
  const { execute: getUserByIdExecute } = useGetUserById(null)
  const { execute: deletePublicationExecute } = useDeletePublication(null)
  const { userData } = useContext(userContext)
  const [users, setUsers] = useState(null)
  const [instance, setInstance] = useState(null)
  const handleDeletePublication = () => {
    Swal.fire({
      title: 'Publicación',
      text: '¿Eliminar publicación?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((resultado) => {
      if (resultado.value) {
        deletePublicationExecute(props.publicationId)
          .then((res) => {
            props.reloadPublications()
            M.toast({ html: 'Su publicación ha sido eliminada' })
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
    })
  }
  useEffect(() => {
    user()
  }, [])
  useEffect(() => {
    if (props.publicationId !== undefined && props.publicationId !== null) {
      const elem1 = document.querySelector(`#modal_edit_pubication${props.publicationId}`)
      const instance = M.Modal.init(elem1, {
        inDuration: 300
      })
      setInstance(instance)
    }
  }, [props])
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

  const closeModal = () => {
    instance.close()
  }
  return (
    props.publicationId !== undefined && props.publicationId !== null
      ? <div className="container " style={{ marginTop: '8%' }}>
      <EditModal close={closeModal} reloadPublications={props.reloadPublications} body={props.description} publicationId={props.publicationId} destination={props.destination}/>
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
              className="card-content col s5 m8"
              style={{ textAlign: 'left' }}
            >
              <h6>
                {users.name} {users.lastname}
              </h6>
            </div>
            {userData.roles[0].authority === 'ROLE_SUPERUSER' ||
            userData.id === props.userId
              ? <a
              style={{ marginRight: '1%', marginTop: '1%' }}
              onClick={() => handleDeletePublication()}
              className="btn-floating btn-small red">
                <i className="material-icons">delete</i>
                </a>
              : null}
             {userData.id === props.userId
               ? <a
               style={{ marginRight: '1%', marginTop: '1%' }}
              onClick={() => instance.open()}
              className="btn-floating btn-small blue lighten-2">
                <i className="material-icons">edit</i>
                </a>
               : null}
          </div>
          <div className="divider" />
          <div>
            <p align="left" style={{ marginLeft: '5%', marginRight: '5%' }}>
              {props.description}
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
      : null
  )
}
TagsPublicaciones.propTypes = {
  userId: PropTypes.number,
  description: PropTypes.string,
  imagen: PropTypes.arrayOf(object),
  likes: PropTypes.number,
  publicationId: PropTypes.number,
  reloadPublications: PropTypes.func,
  destination: PropTypes.string
}
export default TagsPublicaciones
