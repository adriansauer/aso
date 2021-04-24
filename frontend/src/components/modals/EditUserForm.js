import React, { useState, useEffect } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useUpdateMember from '../../api/miembros/useUpdateMember'
import UseGetDepartaments from '../../api/departamento/useGetDepartament'
import useGetCity from '../../api/city/useGetCity'
import useGetRangos from '../../api/rangos/useGetRangos'
import PreLoader from '../PreLoader'
const EditUserForm = (props) => {
  const { execute: getDepartamentsExecute } = UseGetDepartaments()
  const { execute: getCityExecute } = useGetCity()
  const { execute: updateMemberExecute } = useUpdateMember()
  const { execute: getRangosExecute } = useGetRangos()
  // SI ESTA CARGANDO
  const [isLoading, setIsLoading] = useState(false)
  // LISTA DE DEPARTAMENTOS
  const [departaments, setDepartaments] = useState(null)
  // LISTA DE CIUDADES
  const [cities, setCities] = useState(null)
  // LISTA DE RANGOS
  const [rangos, setRangos] = useState(null)
  // NOMBRE Y APELLIDO
  const [lastname, setLastname] = useState(props.usuario.lastname)
  const [name, setName] = useState(props.usuario.name)
  // DIRECCION Y EMAIL
  const [address, setAddress] = useState(props.usuario.address)
  const [email, setEmail] = useState(props.usuario.email)
  // FECHA DE ADMISION Y CUMPLEANHOS
  const [admission, setAdmission] = useState(props.usuario.admission)
  const [birthday, setBirthday] = useState(props.usuario.birthday)
  // DEPARTAMENTO Y CIUDAD
  const [cityId, setCityId] = useState(props.usuario.cityId)
  const [departamentId, setDepartamentId] = useState(
    props.usuario.departamentId
  )
  // BRIGADA Y RANGO
  const [brigadeId] = useState(props.usuario.brigadeId)
  const [rankId, setRankId] = useState(props.usuario.rankId)
  // CELULAR Y CEDULA DE IDENTIDAD
  const [ci, setCi] = useState(props.usuario.ci)
  const [phone, setPhone] = useState(props.usuario.phone)
  // UNA DESCRIPCION Y CODIGO DE USUARIO
  const [description, setDescription] = useState(props.usuario.description)
  const [usercode, setUsercode] = useState(props.usuario.usercode)

  useEffect(() => {
    setIsLoading(true)
    // OBTENGO TODOS LOS DEPARTAMENTOS
    getDepartamentsExecute()
      .then((res) => {
        setDepartaments(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        M.toast({ html: err.response.data.description })
      })
    // OBTENGO TODAS LAS CIUDADES
    getCityExecute()
      .then((res) => {
        setCities(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        M.toast({ html: err.response.data.description })
      })
    // OBTENGO TODOS LOS RANGOS
    getRangosExecute()
      .then((res) => {
        setRangos(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        M.toast({ html: err.response.data.description })
      })
    setIsLoading(false)
  }, [])
  const createUser = (e) => {
    setIsLoading(true)
    e.preventDefault()
    updateMemberExecute({
      id: props.usuario.id,
      address,
      admission,
      birthday,
      brigadeId,
      ci,
      cityId,
      departamentId,
      description,
      email,
      lastname,
      name,
      phone,
      rankId,
      usercode
    })
      .then((res) => {
        M.toast({ html: 'Miembro modificado exitosamente' })
        setIsLoading(false)
        props.close()
      })
      .catch((err) => {
        M.toast({ html: err.response.data.description })
        setIsLoading(false)
      })
  }

  return (
    <div id="modal" className="modal modal-fixed-footer">
      <PreLoader visible={isLoading}/>
      <form onSubmit={createUser}>
        <div className="modal-content">
          <h4>Agregue un miembro a la brigada</h4>

          {/** NOMBRE Y APELLIDO */}
          <div className="row">
            <div className="input-field col m6">
              <label>Nombre </label>
              <input
                required
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col m6">
                <label>Apellido: </label>
                <input
                  required
                  type="text"
                  value={lastname}
                  onChange={(e) => setLastname(e.target.value)}
                />
              </div>
            </div>
          </div>
          {/** EMAIL Y DIRECCION */}
          <div className="row">
            <div className="input-field col m6">
              <label>Email: </label>
              <input
                required
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col m6">
                <label>Direccion: </label>
                <input
                  type="text"
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                />
              </div>
            </div>
          </div>
          {/** FECHA DE NACIMIENTO Y FECHA DE ADMISION */}
          <div className="row">
            <div className="input-field col m6">
              <label>Fecha de nacimiento: </label>
              <input
                required
                type="date"
                value={birthday}
                onChange={(e) => setBirthday(e.target.value)}
              />
            </div>
            <div className="input-field col m6">
              <label>Fecha de admision: </label>
              <input
                required
                type="date"
                value={admission}
                onChange={(e) => setAdmission(e.target.value)}
              />
            </div>
          </div>
          {/** Ciudad y Departamento */}
          <div className="row">
            {cities !== null
              ? (
              <div className="input-field col s6">
                <select
                  defaultValue={cityId}
                  onChange={(e) => {
                    setCityId(
                      e.target.options[e.target.options.selectedIndex].value
                    )
                  }}
                >
                  <option value={'null'}>Seleccione una ciudad</option>
                  {cities.map((city) => (
                    <option key={city.id} value={city.id}>
                      {city.name}
                    </option>
                  ))}
                </select>

                <label>City</label>
              </div>
                )
              : null}

            {departaments !== null
              ? (
              <div className="input-field col s6">
                <select
                  defaultValue={departamentId}
                  onChange={(e) => {
                    setDepartamentId(
                      e.target.options[e.target.options.selectedIndex].value
                    )
                  }}
                >
                  <option value={'null'}>Seleccione un departamento</option>
                  {departaments.map((depto) => (
                    <option key={depto.id} value={depto.id}>
                      {depto.name}
                    </option>
                  ))}
                </select>

                <label>Departamento</label>
              </div>
                )
              : null}
          </div>
          {/** RANGO Y BRIGADA */}
          <div className="row">
            {rangos !== null
              ? (
              <div className="input-field col s6">
                <select
                  defaultValue={rankId}
                  onChange={(e) => {
                    setRankId(
                      e.target.options[e.target.options.selectedIndex].value
                    )
                  }}
                >
                  <option value={'null'}>Seleccionar un rango</option>
                  {rangos.map((rango) => (
                    <option key={rango.id} value={rango.id}>
                      {rango.title}
                    </option>
                  ))}
                </select>

                <label>Rango</label>
              </div>
                )
              : null}
            <div className="input-field col m6">
              <input disabled type="text" value={props.brigada} />
            </div>
          </div>
          {/** CEDULA DE IDENTIDAD Y CELULAR */}
          <div className="row">
            <div className="input-field col m6">
              <label>CI: </label>
              <input
                required
                type="text"
                value={ci}
                onChange={(e) => setCi(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col m6">
                <label>Celular: </label>
                <input
                  required
                  type="text"
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
            </div>
          </div>
          {/** USERCODE Y DESCRIPCION */}
          <div className="row">
            <div className="input-field col m6">
              <label>Usercode: </label>
              <input
                required
                type="text"
                value={usercode}
                onChange={(e) => setUsercode(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col m6">
                <label>Descripcion: </label>
                <input
                  required
                  type="text"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </div>
            </div>
          </div>
        </div>
        {/** Footer del modal */}
        <div className="modal-footer">
          <button
            className="btn-small waves-effect red darken-4"
            style={{ marginRight: '5%' }}
            onClick={() => props.close()}
          >
            Cancelar
          </button>
          <button
            className="btn btn-small waves-effect"
            style={{ backgroundColor: '#0C0019', marginRight: '5%' }}
            type="submit"
          >
            Agregar
          </button>
        </div>
      </form>
    </div>
  )
}
EditUserForm.propTypes = {
  close: PropTypes.func,
  brigada: PropTypes.string,
  usuario: PropTypes.object
}

export default EditUserForm
