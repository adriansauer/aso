import React, { useEffect, useState, useContext } from 'react'
import CreateUserForm from './modals/CreateUserForm'
import M from 'materialize-css'
import perfil from '../images/default.jpg'
import PropTypes from 'prop-types'
import { useLocation, useHistory } from 'react-router-dom'
import useGetBrigadaById from '../api/brigada/useGetBrigadaById'
import useUpdateBrigada from '../api/brigada/useUpdateBrigada'
import EditBrigadaForm from './modals/EditBrigadaForm'
import PreLoader from './PreLoader'
import userContext from '../context/userContext'
import BrigadaPublications from './BrigadaPublications'
const BrigadaPerfil = (props) => {
  const { execute: getBrigadaByIdExecute } = useGetBrigadaById()
  const { execute: updateBrigadaExecute } = useUpdateBrigada()
  const [isLoading, setIsLoading] = useState(false)
  const location = useLocation()
  const history = useHistory()
  const [brigada, setBrigada] = useState(null)
  /** INSTANCIA DE LOS MODALES */
  const [editarModal, setEditarModal] = useState(null)
  const [agregarModal, setAgregarModal] = useState(null)
  const { userData } = useContext(userContext)
  useEffect(() => {
    if (brigada !== null) {
      /** INSTANCIA DEL MODAL EDITAR */
      const elem1 = document.getElementById('modal1')
      const editarModalInstance = M.Modal.init(elem1, {
        inDuration: 300
      })
      setEditarModal(editarModalInstance)
    }
  }, [brigada])
  useEffect(() => {
    /** OBTENGO LA BRIGADA DE LAS PROPIEDADES */
    fertchBrigadaById()
    /** INSTANCIA DEL MODAL AGREGAR UN NUEVO MIEMBRO */
    const elem2 = document.getElementById('modal2')
    const agregarModalInstance = M.Modal.init(elem2, {
      inDuration: 300
    })
    setAgregarModal(agregarModalInstance)
    M.AutoInit()
  }, [])
  /** OBTENER LA BRIGADA */
  const fertchBrigadaById = () => {
    setIsLoading(true)

    getBrigadaByIdExecute(location.brigada.id)
      .then((res) => {
        setBrigada(res.data)
        console.log(res.data)
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
    fertchBrigadaById()
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
  return (
    <div className="container" style={{ marginTop: '4%', width: '100%' }}>
      <PreLoader visible={isLoading} />
      <div style={{ margin: 0 }} className="row center">
      {location.brigada === undefined
        ? null
        : (location.brigada.id === userData.perfilId)
            ? (
                brigada != null
                  ? (
                    <div style={{ marginBottom: 0 }} className="row">
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
                    )
                  : null
              )
            : brigada != null
              ? (
                    <div style={{ marginBottom: 0 }} className="row">
                      <label
                        style={{ marginTop: '3%', width: 100, height: 100 }}
                        className="btn-floating btn-large waves-effect waves-light"
                      >
                        <img src={brigada.image || perfil} style={{ width: '100%' }} />
                      </label>

                    </div>
                )
              : null
                }

      </div>
      <div className="row">
        <div className="col s12 m12 center-align">
          <h5 style={{ margin: 0 }}>
            {brigada === null ? null : brigada.name}
          </h5>
          {location.brigada === undefined
            ? null
            : (location.brigada.id === userData.perfilId)
                ? (
            <button
              className="btn-floating btn-small waves-light"
              onClick={() => editarModal.open()}
              style={{
                backgroundColor: '#0C0019',
                marginTop: '1%'
              }}
            >
              <i className="material-icons">edit</i>
            </button>
                  )
                : null}
        </div>
      </div>
      <div className="row center">
        <div className="col s6 right-align">
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
            {brigada === null ? null : brigada.numberMember} MIEMBROS
          </button>
        </div>
        <div className="col s6 left-align">
          <img
            src={perfil}
            alt=""
            className="circle"
            style={{
              height: 40,
              width: 40,
              position: 'absolute'
            }}
          />
          <img
            src={perfil}
            alt=""
            className="circle"
            style={{
              height: 40,
              width: 40,
              position: 'absolute',
              marginLeft: 25
            }}
          />
          <img
            src={perfil}
            alt=""
            className="circle"
            style={{
              height: 40,
              width: 40,
              position: 'absolute',
              marginLeft: 50
            }}
          />
          {location.brigada !== undefined
            ? (
                (userData.roles[0].authority === 'ROLE_SUPERUSER' &&
              userData.perfilId === location.brigada.id) ||
            (userData.roles[0].authority === 'ROLE_BRIGADE' &&
              userData.perfilId === location.brigada.id)
                  ? (
              <button
                className="btn-floating btn-medium waves-light"
                onClick={() => agregarModal.open()}
                style={{
                  backgroundColor: '#0C0019',
                  position: 'absolute',
                  marginLeft: 75
                }}
              >
                <i className="material-icons">add</i>
              </button>
                    )
                  : null
              )
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
            {brigada !== null
              ? (
              <BrigadaPublications userId={brigada.userId} />
                )
              : null}
          </div>
          <div id="test2" className="col s12">
            Dashboard
          </div>
          <div id="test4" className="col s12">
            El C.B.V.P fue fundado el 4 de octubre de 1978. Sus principales
            precursores fueron, el Sr. Miguel Ángel Estigarribia, en aquel
            entonces Presidente del Club de Leones del Barrio La Encarnación, de
            la ciudad de Asunción, el Dr. Gonzalo Figueroa Yáñez, quien fuera el
            Superintendente del Cuerpo de Bomberos Voluntarios de Santiago
            (Chile), el cual recomendaría, en una visita al país, que fuera
            fundado un cuerpo en el territorio nacional, y recibiendo el apoyo
            de muchos otros que concordaron con esta idea.
          </div>
        </div>
      </div>
      <CreateUserForm brigada={location.brigada} close={closeModal} />
      {brigada !== null
        ? (
        <EditBrigadaForm brigada={brigada} close={closeModal} />
          )
        : null}
    </div>
  )
}
BrigadaPerfil.propTypes = {
  brigada: PropTypes.object
}
export default BrigadaPerfil
