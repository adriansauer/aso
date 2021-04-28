import React, { useEffect, useState, useContext } from 'react'
import perfil from '../images/default.jpg'
import { useLocation, useHistory } from 'react-router-dom'
import useGetMemberById from '../api/miembros/useGetMemberById'
import M from 'materialize-css'
import EditUserForm from './modals/EditUserForm'
import PreLoader from './PreLoader'
import UserContext from '../context/userContext'
const UsuarioPerfil = (props) => {
  const location = useLocation()
  const history = useHistory()
  const [image, setImage] = useState(null)
  const { execute: getMemberByIdExecute } = useGetMemberById()
  const [member, setMember] = useState(null)
  const [editUserModal, setEditUserModal] = useState(null)
  const [isLoading, setIsLoading] = useState(false)
  const { userData } = useContext(UserContext)
  useEffect(() => {
    if (member !== null) {
      /** INSTANCIA DEL MODAL EDITAR */
      const elem1 = document.getElementById('modal')
      const editarModalInstance = M.Modal.init(elem1, {
        inDuration: 300
      })
      setEditUserModal(editarModalInstance)
    }
  }, [member])
  const onImageChange = (event) => {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader()
      reader.onload = (e) => {
        setImage(e.target.result)
      }
      reader.readAsDataURL(event.target.files[0])
    }
  }
  useEffect(() => {
    if (location.member === undefined) {
      history.goBack()
    } else {
      fetchMember()
    }
  }, [])

  const fetchMember = () => {
    setIsLoading(true)
    if (location.member !== undefined) {
      getMemberByIdExecute(location.member.id)
        .then((res) => {
          setMember(res.data)
          setIsLoading(false)
        })
        .catch((err) => {
          M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
          setIsLoading(false)
        })
    }
  }
  /** CERRAR MODAL DE EDITAR USUARIO */
  const closeModal = () => {
    if (editUserModal.isOpen) {
      editUserModal.close()
    }
    fetchMember()
  }
  return (
    <div className="container" style={{ marginTop: '4%' }}>
      {member !== null
        ? <>
          <div style={{ marginBottom: 0 }} className="row">
            <label
              style={{ marginTop: '3%', width: 100, height: 100 }}
              htmlFor="file-input"
              className="btn-floating btn-large waves-effect waves-light"
            >
              <img
                src={image === null ? perfil : image}
                className="circle"
                style={{ width: '100%' }}
              />
            </label>

            <input
              hidden
              id="file-input"
              onChange={onImageChange}
              type="file"
            />
          </div>

          <div className="row center-align">
            <h6 style={{ margin: 0 }}>
              {member.usercode} {member.rankTitle}
            </h6>
          </div>

          <div className="row center-align">
            <h4 style={{ margin: 0 }}>
              {member.name} {member.lastname}
            </h4>
          </div>

          <div className="row center-align">
            <div className="card-panel teal white left-align">
              <ul>
                <li>
                  <div className="row left-align">
                    <div className="col s3 m1 left-align">
                      <img
                        alt=""
                        className="circle"
                        src={perfil}
                        style={{ width: 50, height: 50 }}
                      ></img>
                    </div>
                    <div className="col s7 m6 left-align">
                      <span style={{ fontSize: 16 }}>
                        {location.brigada !== undefined
                          ? `${location.brigada.name}`
                          : null}
                      </span>
                    </div>
                  </div>
                </li>
                <li>
                  <div className="row left-align">
                    <div className="col s2 m1 left-align">
                      <i style={{ fontSize: 36 }} className="material-icons">
                        location_on
                      </i>
                    </div>
                    <div className="col s7 m6 left-align">
                      <span style={{ fontSize: 16 }}>
                        {member.city}-{member.departament}
                      </span>
                    </div>
                  </div>
                </li>
                <li>
                  <div className="row left-align">
                    <div className="col s2 m1 left-align">
                      <i style={{ fontSize: 36 }} className="material-icons">
                        cake
                      </i>
                    </div>
                    <div className="col s7 m6 left-align">
                      <span style={{ fontSize: 16 }}>{`${new Date(
                        member.birthday
                      ).getDate()}/${new Date(
                        member.birthday
                      ).getMonth()}/${new Date(
                        member.birthday
                      ).getFullYear()}`}</span>
                    </div>
                  </div>
                </li>
                <li>
                  <div className="row left-align">
                    <div className="col s2 m1 left-align">
                      <i style={{ fontSize: 36 }} className="material-icons">
                        description
                      </i>
                    </div>
                    <div className="col s7 m6 left-align">
                      <span style={{ fontSize: 16 }}>{member.description}</span>
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
                      <span style={{ fontSize: 16 }}>{member.phone}</span>
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
                      <span style={{ fontSize: 16 }}>{member.email}</span>
                    </div>
                    {userData.roles[0].authority === 'ROLE_SUPERUSER' || userData.id === member.userId || userData.roles[0].authority === 'ROLE_BRIGADE'
                      ? <div className="col s3 m5 right-align">
                   {/** BOTON PARA ABRIR EL MODAL DE EDITAR USUARIO */}
                   <button
                     onClick={() => editUserModal.open()}
                     style={{ backgroundColor: '#0C0019' }}
                     className="btn-floating btn-medium"
                   >
                     <i className="material-icons">edit</i>
                   </button>
                 </div>
                      : null
                    }

                  </div>
                </li>
              </ul>
            </div>
            {/** RENDERIZAR EL MODAL DE EDITAR EL USUARIO SOLO SI YA SE OBTUVO EL MIEMBRO */}
            {member !== null || location.brigada !== undefined
              ? <EditUserForm
                usuario={member}
                brigada={location.brigada.name}
                close={closeModal}
              />

              : null}
          </div>
        </>
        : <PreLoader visible={isLoading} />
      }
    </div>
  )
}

export default UsuarioPerfil
