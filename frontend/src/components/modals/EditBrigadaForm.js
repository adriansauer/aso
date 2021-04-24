import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import UseGetDepartaments from '../../api/departamento/useGetDepartament'
import useGetCity from '../../api/city/useGetCity'
import useUpdateBrigada from '../../api/brigada/useUpdateBrigada'
const EditBrigadaForm = (props) => {
  const { execute: getDepartamentsExecute } = UseGetDepartaments()
  const { execute: getCityExecute } = useGetCity()
  const { execute: updateBrigadaExecute } = useUpdateBrigada()
  /** LISTA DE CIUDADES Y DEPARTAMENTOS DISPONIBLES */
  const [departaments, setDepartaments] = useState(null)
  const [cities, setCities] = useState(null)
  /** NOBRE Y USERCODE DE LA BRIGADA */
  const [name, setName] = useState(props.brigada.name)
  const [usercode, setUsercode] = useState(props.brigada.usercode)
  /** DIRECCION Y EMAIL DE LA BRIGADA */
  const [address, setAddress] = useState(props.brigada.address)
  const [email, setEmail] = useState(props.brigada.email)
  /** DEPARTAMENTO Y CIUDAD DE LA BRIGADA */
  const [cityId, setCityId] = useState(props.brigada.cityId)
  const [departamentId, setDepartamentId] = useState(
    props.brigada.departamentId
  )
  /** NRO TELEFONICO Y DESCRIPCION DE LA BRIGADA */
  const [description, setDescription] = useState(props.brigada.description)
  const [phone, setPhone] = useState(props.brigada.phone)

  useEffect(() => {
    /** OBTENGO LA LISTA DE DEPARTAMENTOS DISPONIBLES */
    getDepartamentsExecute()
      .then((res) => {
        setDepartaments(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
    /** OBTENGO LA LISTA DE CIUDADES DISPONIBLES */
    getCityExecute()
      .then((res) => {
        setCities(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
  }, [])
  /** EDITAR LA BRIGADA */
  const updateBrigada = (e) => {
    if (departamentId === 'null') {
      M.toast({ html: 'Seleccione un departamento' })
    } else if (cityId === 'null') {
      M.toast({ html: 'Seleccione una ciudad' })
    } else {
      updateBrigadaExecute({
        id: props.brigada.id,
        name,
        address,
        phone,
        departamentId,
        cityId,
        description,
        email,
        usercode
      })
        .then((res) => {
          M.toast({ html: 'Se ha modificado la brigada' })
          props.close()
        })
        .catch((err) => {
          M.toast({ html: err.response.data.description })
        })
    }
    e.preventDefault()
  }

  return (
    <div id="modal1" className="modal modal-fixed-footer">
      <form onSubmit={updateBrigada}>
        <div className="modal-content">
          <h4>Editar brigada</h4>
          {/** Usercode y Nombre */}
          <div className="row">
            <div className="input-field col m6">
              <label>Nombre: </label>
              <input
                required
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col m6">
                <label>Código: </label>
                <input
                  required
                  type="text"
                  value={usercode}
                  onChange={(e) => setUsercode(e.target.value)}
                />
              </div>
            </div>
          </div>
          {/** Email y Telefono */}
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
                <label>Telefono: </label>
                <input
                  required
                  type="text"
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
            </div>
          </div>
          {/** Direccion y descripcion */}
          <div className="row">
            <div className="input-field col m6">
              <label>Direccion: </label>
              <input
                required
                type="text"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
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
EditBrigadaForm.propTypes = {
  close: PropTypes.func,
  brigada: PropTypes.object
}
export default EditBrigadaForm
