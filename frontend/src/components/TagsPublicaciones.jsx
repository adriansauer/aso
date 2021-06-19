import React, { useState, useEffect, useContext } from 'react'
import PropTypes from 'prop-types'
import './components.css'
import M from 'materialize-css'
import userContext from '../context/userContext'
import useGetUserById from '../api/user/useGetUserById'
import useDeletePublication from '../api/publicaciones/useDeletePublication'
import Swal from 'sweetalert2'
import EditModal from './modals/EditarPublicacionModal'
import useGetMemberById from '../api/miembros/useGetMemberById'
import useGetBrigadaById from '../api/brigada/useGetBrigadaById'
import PublicationPerfilImage from './PublicationPerfilImage'
import useGetPulicationById from '../api/publicaciones/GetPublicationById'
import useLike from '../api/likes/useLike'
import useDeleteLike from '../api/likes/useDeleteLike'
import PublicationImage from './PublicationImage'
const TagsPublicaciones = (props) => {
  const { execute: getUserByIdExecute } = useGetUserById(null)
  const { execute: deletePublicationExecute } = useDeletePublication(null)
  const { execute: likeExecute } = useLike()
  const { execute: deleteLikeExecute } = useDeleteLike()
  const { execute: getMemberByIdExecute } = useGetMemberById()
  const { execute: getPublicationByIdExecute } = useGetPulicationById()
  const { execute: getBrigadaByIdExecute } = useGetBrigadaById()
  const { userData } = useContext(userContext)
  const [users, setUsers] = useState(null)
  const [publication, setPublication] = useState(null)
  const [instance, setInstance] = useState(null)
  const [isLike, setIsLike] = useState(props.ilike)
  const [likeNumber, setLikeNumber] = useState(props.likes)
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
    handleGetPublication()
  }, [])
  const handleLike = () => {
    if (!isLike) {
      likeExecute(props.publicationId)
        .then((res) => {
          setIsLike(true)
          setLikeNumber(likeNumber + 1)
        })
        .catch((err) => {
          M.toast({
            html:
              err.response === undefined
                ? 'Hubo un error con la conexión'
                : err.response.data.description
          })
        })
    } else {
      deleteLikeExecute(props.publicationId)
        .then((res) => {
          setIsLike(false)
          setLikeNumber(likeNumber - 1)
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
  useEffect(() => {
    if (props.publicationId !== undefined && props.publicationId !== null) {
      const elem1 = document.querySelector(
        `#modal_edit_pubication${props.publicationId}`
      )
      const instance = M.Modal.init(elem1, {
        inDuration: 300
      })
      setInstance(instance)
    }
  }, [props])
  const handleGetPublication = () => {
    if (props.publicationId !== null) {
      getPublicationByIdExecute(props.publicationId)
        .then((res) => {
          setPublication(res.data)
        })
        .catch(() => {})
    }
  }
  const user = () => {
    if (props.userId !== null) {
      getUserByIdExecute(props.userId)
        .then((res) => {
          if (res.data.roles[0].authority === 'ROLE_USER') {
            getMemberByIdExecute(res.data.detailId).then((r) => {
              setUsers(r.data)
            })
          } else if (res.data.roles[0].authority === 'ROLE_BRIGADE') {
            getBrigadaByIdExecute(res.data.detailId).then((r) => {
              setUsers(r.data)
            })
          }
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
  return props.publicationId !== undefined && props.publicationId !== null
    ? (
    <div className="container " style={{ marginTop: '8%' }}>
      <EditModal
        close={closeModal}
        reloadPublications={props.reloadPublications}
        body={props.description}
        publicationId={props.publicationId}
        destination={props.destination}
      />
      {users !== null
        ? (
        <div className="card">
          <div className="divider" />
          <div className="row">
            <div className="col s3 m2" style={{ textAlign: 'left' }}>
              {users !== null
                ? (
                <PublicationPerfilImage image={users.image} />
                  )
                : (
                <PublicationPerfilImage image={null} />
                  )}
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
              ? (
              <a
                style={{ marginRight: '1%', marginTop: '1%' }}
                onClick={() => handleDeletePublication()}
                className="btn-floating btn-small red"
              >
                <i className="material-icons">delete</i>
              </a>
                )
              : null}
            {userData.id === props.userId
              ? (
              <a
                style={{ marginRight: '1%', marginTop: '1%' }}
                onClick={() => instance.open()}
                className="btn-floating btn-small blue lighten-2"
              >
                <i className="material-icons">edit</i>
              </a>
                )
              : null}
          </div>
          <div className="divider" />
          <div>
            <p align="left" style={{ marginLeft: '5%', marginRight: '5%' }}>
              {props.description}
            </p>
            <div className="row" align="left" style={{ margin: 0 }}>
              {publication !== null
                ? publication.files !== undefined
                  ? publication.files.length > 0
                    ? publication.files.map((f) => (
                        <div className="col s6 m2" key={f.id}>
                          <PublicationImage fileId={f.id} />
                        </div>
                    ))
                    : null
                  : null
                : null}
            </div>
          </div>
          <div className="divider" />
          <div
                style={{ textAlign: 'left', marginLeft: '1%' }}
              >
                <h6 style={{ float: 'left', opacity: 0.8 }}>
                  {new Date(publication.createAt).getDate()}/
                  {new Date(publication.createAt).getMonth()}/
                  {new Date(publication.createAt).getFullYear()}

                </h6>
              </div>
          <div style={{ marginTop: 5, textAlign: 'right', marginRight: '5%' }}>
            <h6 style={{ float: 'right', marginTop: '2%', marginLeft: 10 }}>
              {likeNumber}
            </h6>
            <button
              className="btn-floating btn-medium waves-light"
              onClick={() => handleLike()}
              style={{
                backgroundColor: 'white'
              }}
            >
              {isLike
                ? (
                <i style={{ color: 'red' }} className="material-icons">
                  favorite
                </i>
                  )
                : (
                <i style={{ color: '#0C0019' }} className="material-icons">
                  favorite
                </i>
                  )}
            </button>
          </div>
        </div>
          )
        : null}
    </div>
      )
    : null
}
TagsPublicaciones.propTypes = {
  userId: PropTypes.number,
  description: PropTypes.string,
  likes: PropTypes.number,
  publicationId: PropTypes.number,
  reloadPublications: PropTypes.func,
  destination: PropTypes.string,
  ilike: PropTypes.number,
  date: PropTypes.string
}
export default TagsPublicaciones
