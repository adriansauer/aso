import React, { useEffect, useState, useContext } from 'react'
import perfil from '../images/default.jpg'
import useGetMemberById from '../api/miembros/useGetMemberById'
import useGetBrigadaById from '../api/brigada/useGetBrigadaById'
import M from 'materialize-css'
import EditUserForm from './modals/EditUserForm'
import useUpdateMember from '../api/miembros/useUpdateMember'
import PreLoader from './PreLoader'
import UserContext from '../context/userContext'
import BrigadaPublications from './BrigadaPublications'
const UsuarioPerfil = (props) => {
  const { execute: getMemberByIdExecute } = useGetMemberById()
  const { execute: getBrigadaByIdExecute } = useGetBrigadaById()
  const { execute: updateMemberExecute } = useUpdateMember()
  const [member, setMember] = useState(null)
  const [brigada, setBrigada] = useState(null)
  const [editUserModal, setEditUserModal] = useState(null)
  const [isLoading, setIsLoading] = useState(false)
  const { userData, selectData } = useContext(UserContext)
  const getBase64 = (file) => {
    return new Promise((resolve) => {
      let baseURL = ''
      // Make new FileReader
      const reader = new FileReader()
      // Convert the file to base64 text
      reader.readAsDataURL(file)
      // on reader load somthing...
      reader.onload = () => {
        // Make a fileInfo Object
        baseURL = reader.result
        resolve(baseURL)
      }
    })
  }
  useEffect(() => {
    /** INSTANCIA DEL MODAL EDITAR */
    const elem1 = document.getElementById('modal_edit')
    const editarModalInstance = M.Modal.init(elem1)
    setEditUserModal(editarModalInstance)
  }, [member, brigada])
  const onImageChange = (event) => {
    getBase64(event.target.files[0])
      .then((result) => {
        setIsLoading(true)
        updateMemberExecute({
          id: member.id,
          address: member.address,
          admission: member.admission,
          birthday: member.birthday,
          brigadeId: member.brigadeId,
          ci: member.ci,
          cityId: member.cityId,
          departamentId: member.departamentId,
          description: member.description,
          email: member.email,
          lastname: member.lastname,
          name: member.name,
          phone: member.phone,
          rankId: member.rankId,
          usercode: member.usercode,
          image: result
        })
          .then((res) => {
            setMember(res.data)
            window.location.reload(false)
            setIsLoading(false)
          })
          .catch(() => {
            setIsLoading(false)
          })
      })
      .catch((err) => {
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })

        setIsLoading(false)
      })
  }

  useEffect(() => {
    fetchMember()
  }, [selectData.userId])

  const fetchMember = () => {
    setIsLoading(true)
    getMemberByIdExecute(selectData.userId)
      .then((res) => {
        setMember(res.data)
        getBrigadaByIdExecute(res.data.brigadeId)
          .then(result => {
            setBrigada(result.data)
            setIsLoading(false)
          })
      })
      .catch((err) => {
        M.toast({
          html:
              err.response === undefined
                ? 'Hubo un error con la conexión'
                : err.response.data.description
        })
        setIsLoading(false)
      })
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
  {member !== null & brigada !== null
    ? <>
          {member.id === userData.perfilId
            ? (
            <div style={{ marginBottom: 0 }} className="row">
              <label
                style={{ marginTop: '3%', width: 100, height: 100 }}
                htmlFor="file-input"
                className="btn-floating btn-large waves-effect waves-light"
              >
                <img src={member.image || perfil} style={{ width: '100%' }} />
              </label>

              <input
                hidden
                id="file-input"
                accept="image/png, image/jpeg, image/jpg"
                onChange={(ev) => onImageChange(ev)}
                type="file"
              />
            </div>
              )
            : (
            <div style={{ marginBottom: 0 }} className="row">
              <label
                style={{ marginTop: '3%', width: 100, height: 100 }}
                className="btn-floating btn-large waves-effect waves-light"
              >
                <img src={member.image || perfil} style={{ width: '100%' }} />
              </label>
            </div>
              )
              }

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
                        src={brigada.image || perfil}
                        style={{ width: 50, height: 50 }}
                      ></img>
                    </div>
                    <div className="col s7 m6 left-align">
                      <span style={{ fontSize: 16 }}>
                          { `${brigada.name}`}
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
                      ).getDate()}/${
                        new Date(member.birthday).getMonth() + 1
                      }/${new Date(member.birthday).getFullYear()}`}</span>
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
                    {member.id === userData.perfilId
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
                      : null}
                  </div>
                </li>
              </ul>
            </div>
            {/** RENDERIZAR EL MODAL DE EDITAR EL USUARIO SOLO SI YA SE OBTUVO EL MIEMBRO */}
            {member !== null
              ? (
              <EditUserForm
                usuario={member}
                brigada={brigada.name}
                close={() => closeModal()}
              />
                )
              : null}
          </div>
          {member !== null
            ? (
            <BrigadaPublications userId={member.userId} />
              )
            : null}
        </>
    : <PreLoader visible={isLoading} />
      }
    </div>
  )
}

export default UsuarioPerfil
