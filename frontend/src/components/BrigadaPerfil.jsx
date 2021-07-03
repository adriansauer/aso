import React, { useEffect, useState, useContext } from 'react'
import CreateUserForm from './modals/CreateUserForm'
import HistoryModal from './modals/HistoryModal'
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
import useGetHistoryByBrigadeId from '../api/historias/useGetHistoryByBrigadeId'
import useGetReports from '../api/reportes/useGetReports'
import Graphic from './Graphic'
const BrigadaPerfil = (props) => {
  const { execute: getBrigadaByIdExecute } = useGetBrigadaById()
  const { execute: getHistoryByBrigadeIdExecute } = useGetHistoryByBrigadeId()
  const { execute: getReportsExecute } = useGetReports()
  const { execute: updateBrigadaExecute } = useUpdateBrigada()
  const [isLoading, setIsLoading] = useState(false)
  const [brigada, setBrigada] = useState(null)
  const [historia, setHistoria] = useState(null)
  const history = useHistory()
  const [isInPublication, setIsInPublication] = useState(false)
  /** INSTANCIA DE LOS MODALES */
  const [editarModal, setEditarModal] = useState(null)
  const [agregarModal, setAgregarModal] = useState(null)
  const [historyModal, setHistoryModal] = useState(null)
  const [year, setYear] = useState(new Date().getFullYear())
  const { userData, selectData } = useContext(userContext)
  const [reports, setReports] = useState(null)
  const fetchHistory = () => {
    if (brigada !== null) {
      getHistoryByBrigadeIdExecute(brigada.id)
        .then((res) => {
          setHistoria(res.data.text)
        })
        .catch()
    }
  }
  const fetchReports = () => {
    if (brigada !== null) {
      const data = {
        userId: brigada.userId,
        year: 2020
      }
      getReportsExecute(data)
        .then((res) => {
          setReports(res.data)
          console.log(reports)
        })
        .catch()
    }
  }
  useEffect(() => {
    console.log(year)
  }, [year])
  useEffect(() => {
    fetchHistory()
    fetchReports()
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
  useEffect(() => {
    /** INSTANCIA DEL MODAL AGREGAR HISTORIA */
    const elem3 = document.getElementById('history_modal')
    const historyModalInstance = M.Modal.init(elem3, {
      inDuration: 300
    })
    setHistoryModal(historyModalInstance)
    M.AutoInit()
  }, [historia])
  useEffect(() => {
    /** OBTENGO LA BRIGADA DE LAS PROPIEDADES */
    fetchBrigadaById()
  }, [])

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
      fetchBrigadaById()
    }
    if (agregarModal.isOpen) {
      agregarModal.close()
      fetchBrigadaById()
    }
    if (historyModal.isOpen) {
      historyModal.close()
      fetchHistory()
    }
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
    ? (
    <div className="container" style={{ marginTop: '4%', width: '100%' }}>
      <div style={{ margin: 0 }} className="row center">
        {selectData.brigadaId === userData.perfilId
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
          : (
          <div style={{ marginBottom: 0 }} className="row">
            <label
              style={{ marginTop: '3%', width: 100, height: 100 }}
              className="btn-floating btn-large waves-effect waves-light"
            >
              <img src={brigada.image || perfil} style={{ width: '100%' }} />
            </label>
          </div>
            )}
      </div>
      <div className="row">
        <div className="col s12 m12 center-align">
          <h5 style={{ margin: 0 }}>{brigada.name}</h5>
        </div>
      </div>
      <div className="row center">
        <div className="col m12 s12 center-align">
          {selectData.brigadaId === userData.perfilId
            ? (
            <button
              className="btn-floating btn-medium waves-light"
              onClick={() => editarModal.open()}
              style={{
                backgroundColor: '#0C0019',
                marginRight: '2%'
              }}
            >
              <i className="material-icons">edit</i>
            </button>
              )
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
            ? (
            <button
              className="btn-floating btn-medium waves-light"
              onClick={() => agregarModal.open()}
              style={{
                backgroundColor: '#0C0019',
                marginLeft: '2%'
              }}
            >
              <i className="material-icons">add</i>
            </button>
              )
            : null}
          {selectData.brigadaId === userData.perfilId
            ? (
            <button
              className="btn-floating btn-medium waves-light"
              onClick={() => historyModal.open()}
              style={{
                backgroundColor: '#0C0019',
                marginLeft: '2%'
              }}
            >
              <i className="material-icons">library_books</i>
            </button>
              )
            : null}
        </div>
      </div>
      <div className="row center" style={{ marginTop: '5%' }}>
        <div className="row">
          <div className="col s12">
            <ul className="tabs">
              <li className="tab col s4" onClick={() => setIsInPublication(true)}>
                <a href="#test1">Publicaciones</a>
              </li>
              <li className="tab col s4" onClick={() => setIsInPublication(false)}>
                <a className="active" href="#test2">
                  Dashboard
                </a>
              </li>
              <li className="tab col s4" onClick={() => setIsInPublication(false)}>
                <a href="#test4">Historia</a>
              </li>
            </ul>
          </div>
          <div id="test1" className="col s12">
            {isInPublication
              ? <BrigadaPublications userId={brigada.userId} />
              : null
          }

          </div>
          <div id="test2" className="col s12">
          <div className="input-field col m4 s12 right">
                  <select
                    defaultValue={new Date().getFullYear()}
                    onChange={(e) => {
                      setYear(
                        e.target.options[e.target.options.selectedIndex].value
                      )
                    }}
                  >
                    <option value={new Date().getFullYear()}>{new Date().getFullYear()}</option>
                    <option value={new Date().getFullYear() - 1}>{new Date().getFullYear() - 1}</option>
                    <option value={new Date().getFullYear() - 2}>{new Date().getFullYear() - 2}</option>
                    <option value={new Date().getFullYear() - 3}>{new Date().getFullYear() - 3}</option>
                    <option value={new Date().getFullYear() - 4}>{new Date().getFullYear() - 4}</option>
                    <option value={new Date().getFullYear() - 5}>{new Date().getFullYear() - 5}</option>
                    <option value={new Date().getFullYear() - 6}>{new Date().getFullYear() - 6}</option>
                    <option value={new Date().getFullYear() - 7}>{new Date().getFullYear() - 7}</option>
                    <option value={new Date().getFullYear() - 8}>{new Date().getFullYear() - 8}</option>
                    <option value={new Date().getFullYear() - 9}>{new Date().getFullYear() - 9}</option>
                    <option value={new Date().getFullYear() - 10}>{new Date().getFullYear() - 10}</option>
                    <option value={new Date().getFullYear() - 11}>{new Date().getFullYear() - 11}</option>
                    <option value={new Date().getFullYear() - 12}>{new Date().getFullYear() - 12}</option>
                    <option value={new Date().getFullYear() - 13}>{new Date().getFullYear() - 13}</option>
                    <option value={new Date().getFullYear() - 14}>{new Date().getFullYear() - 14}</option>

                  </select>

                  <label>Año</label>
                </div>
            <Graphic />
          </div>
          <div id="test4" className="col s12">
            {historia !== null
              ? (
              <div
                style={{
                  textAlign: 'justify',
                  fontSize: 24,
                  marginLeft: '15%',
                  marginRight: '10%'
                }}
              >

                <b>{historia}</b>
              </div>
                )
              : null}
          </div>
        </div>
      </div>
      <CreateUserForm brigada={brigada} close={closeModal} />
      <EditBrigadaForm brigada={brigada} close={closeModal} />
      {historia !== null
        ? (
        <HistoryModal
          brigadeId={brigada.id}
          text={historia}
          close={closeModal}
        />
          )
        : null}
    </div>
      )
    : (
    <PreLoader visible={isLoading} />
      )
}
BrigadaPerfil.propTypes = {
  brigada: PropTypes.object
}
export default BrigadaPerfil
