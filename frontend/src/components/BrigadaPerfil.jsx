import React, { useEffect, useState, useContext } from 'react'
import CreateUserForm from './modals/CreateUserForm'
import M from 'materialize-css'
import perfil from '../images/default.jpg'
import { useHistory } from 'react-router-dom'
import PropTypes from 'prop-types'
import useGetBrigadaById from '../api/brigada/useGetBrigadaById'
import useUpdateBrigada from '../api/brigada/useUpdateBrigada'
import EditBrigadaForm from './modals/EditBrigadaForm'
import PreLoader from './PreLoader'
import userContext from '../context/userContext'
import BrigadaPublications from './BrigadaPublications'
import Graphic from './Graphic'
const BrigadaPerfil = (props) => {
  const { execute: getBrigadaByIdExecute } = useGetBrigadaById()
  const { execute: updateBrigadaExecute } = useUpdateBrigada()
  const [isLoading, setIsLoading] = useState(false)
  const [brigada, setBrigada] = useState(null)
  const history = useHistory()
  /** INSTANCIA DE LOS MODALES */
  const [editarModal, setEditarModal] = useState(null)
  const [agregarModal, setAgregarModal] = useState(null)
  const { userData, selectData } = useContext(userContext)

  useEffect(() => {
    /** OBTENGO LA BRIGADA DE LAS PROPIEDADES */
    fetchBrigadaById()
    /** INSTANCIA DEL MODAL AGREGAR UN NUEVO MIEMBRO */
    const elem2 = document.getElementById('modal2')
    const agregarModalInstance = M.Modal.init(elem2, {
      inDuration: 300
    })
    setAgregarModal(agregarModalInstance)
    /** INSTANCIA DEL MODAL EDITAR */
    const elem1 = document.getElementById('modal1')
    const editarModalInstance = M.Modal.init(elem1, {
      inDuration: 300
    })
    setEditarModal(editarModalInstance)
    M.AutoInit()
  }, [brigada])

  /** OBTENER LA BRIGADA */
  const fetchBrigadaById = () => {
    setIsLoading(true)

    getBrigadaByIdExecute(selectData.brigadaId)
      .then((res) => {
        setBrigada(res.data)
        setIsLoading(false)
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
  /** CERRA CUALQUIERA DE LOS MODALES Y ACTUALIZAR LA BRIGADA */
  const closeModal = () => {
    if (editarModal.isOpen) {
      editarModal.close()
    }
    if (agregarModal.isOpen) {
      agregarModal.close()
    }
    fetchBrigadaById()
  }
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
  const onImageChange = (event) => {
    getBase64(event.target.files[0])
      .then((result) => {
        setIsLoading(true)
        updateBrigadaExecute({
          id: brigada.id,
          name: brigada.name,
          address: brigada.address,
          phone: brigada.phone,
          departamentId: brigada.departamentId,
          cityId: brigada.cityId,
          description: brigada.description,
          email: brigada.email,
          usercode: brigada.usercode,
          image: result
        })
          .then((res) => {
            setBrigada(res.data)
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
  return brigada !== null
    ? <div className="container" style={{ marginTop: '4%', width: '100%' }}>
      <div style={{ margin: 0 }} className="row center">
        {selectData.brigadaId === userData.perfilId
          ? <div style={{ marginBottom: 0 }} className="row">
            <label
              style={{ marginTop: '3%', width: 100, height: 100 }}
              htmlFor="file-input"
              className="btn-floating btn-large waves-effect waves-light"
            >
              <img src={brigada.image || perfil} style={{ width: '100%' }} />
            </label>

            <input
              hidden
              id="file-input"
              accept="image/png, image/jpeg, image/jpg"
              onChange={(ev) => onImageChange(ev)}
              type="file"
            />
          </div>

          : <div style={{ marginBottom: 0 }} className="row">
            <label
              style={{ marginTop: '3%', width: 100, height: 100 }}
              className="btn-floating btn-large waves-effect waves-light"
            >
              <img src={brigada.image || perfil} style={{ width: '100%' }} />
            </label>
          </div>
            }
      </div>
      <div className="row">
        <div className="col s12 m12 center-align">
          <h5 style={{ margin: 0 }}>{brigada.name}</h5>
        </div>
      </div>
      <div className="row center">
        <div className="col m12 s12 center-align">

          {selectData.brigadaId === userData.perfilId
            ? <button
              className="btn-floating btn-medium waves-light"
              onClick={() => editarModal.open()}
              style={{
                backgroundColor: '#0C0019',
                marginRight: '2%'
              }}
            >
              <i className="material-icons">edit</i>
            </button>

            : null}
                      <button
            className="btn btn-small waves-light"
            style={{ backgroundColor: '#0C0019', color: 'white' }}
            onClick={() => {
              history.push({
                pathname: '/usuarios',
                brigada: brigada
              })
            }}
          >
            {brigada.numberMember} MIEMBROS
          </button>
          {(userData.roles[0].authority === 'ROLE_SUPERUSER' &&
            userData.perfilId === selectData.brigadaId) ||
          (userData.roles[0].authority === 'ROLE_BRIGADE' &&
            userData.perfilId === selectData.brigadaId)
            ? <button
              className="btn-floating btn-medium waves-light"
              onClick={() => agregarModal.open()}
              style={{
                backgroundColor: '#0C0019',
                marginLeft: '2%'
              }}
            >
              <i className="material-icons">add</i>
            </button>

            : null}
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
            <BrigadaPublications userId={brigada.userId} />
          </div>
          <div id="test2" className="col s12">
            <Graphic />
          </div>
          <div id="test4" className="col s12">
            {/** Historia del BRIGADA */}
          </div>
        </div>
      </div>
      <CreateUserForm brigada={brigada} close={closeModal} />
      <EditBrigadaForm brigada={brigada} close={closeModal} />
    </div>
    : <PreLoader visible={isLoading} />
}
BrigadaPerfil.propTypes = {
  brigada: PropTypes.object
}
export default BrigadaPerfil
