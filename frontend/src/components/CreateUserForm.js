import React, { useState, useEffect } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useCreateMember from '../api/miembros/useCreateMember'
import UseGetDepartaments from '../api/departamento/useGetDepartament'
import useGetCity from '../api/city/useGetCity'
import useGetRangos from '../api/rangos/useGetRangos'
const CreateUserForm = (props) => {
  const { execute: getDepartamentsExecute } = UseGetDepartaments()
  const { execute: getCityExecute } = useGetCity()
  const { execute: createMemberExecute } = useCreateMember()
  const { execute: getRangosExecute } = useGetRangos()
  // LISTA DE DEPARTAMENTOS
  const [departaments, setDepartaments] = useState(null)
  // LISTA DE CIUDADES
  const [cities, setCities] = useState(null)
  // LISTA DE RANGOS
  const [rangos, setRangos] = useState(null)
  // NOMBRE Y APELLIDO
  const [lastname, setLastname] = useState('')
  const [name, setName] = useState('')
  // DIRECCION Y EMAIL
  const [address, setAddress] = useState('')
  const [email, setEmail] = useState('')
  // FECHA DE ADMISION Y CUMPLEANHOS
  const [admission, setAdmission] = useState(new Date())
  const [birthday, setBirthday] = useState(new Date())
  // DEPARTAMENTO Y CIUDAD
  const [cityId, setCityId] = useState(null)
  const [departamentId, setDepartamentId] = useState(null)
  // BRIGADA Y RANGO
  const [brigadeId, setBrigadaId] = useState('')
  const [rankId, setRankId] = useState(null)
  // CELULAR Y CEDULA DE IDENTIDAD
  const [ci, setCi] = useState('')
  const [phone, setPhone] = useState('')
  // UNA DESCRIPCION Y CODIGO DE USUARIO
  const [description, setDescription] = useState('')
  const [usercode, setUsercode] = useState('')
  // CONTRASENHA Y CONFIRMACION DE CONTRASENHA
  const [password, setPassword] = useState('')
  const [repeatPassword, setRepeatPassword] = useState('')
  useEffect(() => {
    // OBTENGO TODOS LOS DEPARTAMENTOS
    getDepartamentsExecute()
      .then((res) => {
        setDepartaments(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
    // OBTENGO TODAS LAS CIUDADES
    getCityExecute()
      .then((res) => {
        setCities(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
    // OBTENGO TODOS LOS RANGOS
    getRangosExecute()
      .then((res) => {
        setRangos(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
    // OBTENGO LA BRIGADA
    if (props.brigada !== undefined) {
      setBrigadaId(props.brigada.id)
    } else {
      setBrigadaId(null)
    }
  }, [])
  const createUser = (e) => {
    e.preventDefault()

    createMemberExecute({
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
      password,
      phone,
      rankId,
      repeatPassword,
      usercode
    })
      .then((res) => {
        M.toast({ html: 'Miembro agregado exitosamente' })
        props.close()
      })
      .catch((err) => {
        console.log(err.response)
      })
  }

  return (
    <div id="modal1" className="modal modal-fixed-footer">
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
                  defaultValue={'null'}
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
                  defaultValue={'null'}
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
                  defaultValue={'null'}
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
              <input
                disabled
                type="text"
                value={props.brigada === undefined ? '' : props.brigada.name}
              />
            </div>
          </div>
          {/** CEDULA DE IDENTIDAD Y CELULAR */}
          <div className="row">
            <div className="input-field col m6">
              <label>CI: </label>
              <input
                required
                type="number"
                value={ci}
                onChange={(e) => setCi(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col m6">
                <label>Celular: </label>
                <input
                  required
                  type="number"
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
          {/** Password y confirmacion de password del usaurio */}
          <div className="row">
            <div className="input-field col m6">
              <label>Password: </label>
              <input
                required
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <div className="input-field col m6">
              <label>Confirmar Password: </label>
              <input
                required
                type="password"
                value={repeatPassword}
                onChange={(e) => setRepeatPassword(e.target.value)}
              />
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
CreateUserForm.propTypes = {
  close: PropTypes.func,
  brigada: PropTypes.object
}

export default CreateUserForm
