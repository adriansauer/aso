import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import UseGetDepartaments from '../api/departamento/useGetDepartament'
import useGetCity from '../api/city/useGetCity'
import useCreateBrigada from '../api/brigada/useCreateBrigada'
const CreateBrigadaForm = (props) => {
  const { execute: getDepartamentsExecute } = UseGetDepartaments()
  const { execute: getCityExecute } = useGetCity()
  const { execute: createBrigadaExecute } = useCreateBrigada()
  const [departaments, setDepartaments] = useState(null)
  const [name, setName] = useState('')
  const [usercode, setUsercode] = useState('')
  const [cities, setCities] = useState(null)
  const [address, setAddress] = useState('')
  const [description, setDescription] = useState('')
  const [email, setEmail] = useState('')
  const [phone, setPhone] = useState('')
  const [password, setPassword] = useState('')
  const [departamentId, setDepartamentId] = useState('null')
  const [cityId, setCityId] = useState('null')
  const [repeatPassword, setRepeatPassord] = useState('')

  useEffect(() => {
    getDepartamentsExecute()
      .then((res) => {
        setDepartaments(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
    getCityExecute()
      .then((res) => {
        setCities(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        console.log(err)
      })
  }, [])
  const createBrigada = (e) => {
    if (departamentId === 'null') {
      M.toast({ html: 'Seleccione un departamento' })
    } else if (cityId === 'null') {
      M.toast({ html: 'Seleccione una ciudad' })
    } else if (repeatPassword !== password) {
      M.toast({ html: 'Las contraseñas no coinciden' })
    } else {
      createBrigadaExecute({
        name,
        address,
        phone,
        password,
        repeatPassword,
        departamentId,
        cityId,
        description,
        email,
        usercode
      })
        .then((res) => {
          M.toast({ html: 'Se ha agregado una nueva brigada' })
          props.close()
        })
        .catch((err) => {
          console.log(err)
        })
    }
    e.preventDefault()
  }

  return (
    <div id="modal1" className="modal modal-fixed-footer">
      <form onSubmit={createBrigada}>
        <div className="modal-content">
          <h4>Agregue una nueva brigada</h4>
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
                  defaultValue={'null'}
                  onChange={(e) => {
                    console.log(
                      e.target.options[e.target.options.selectedIndex].value
                    )
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
                    console.log(
                      e.target.options[e.target.options.selectedIndex].value
                    )
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
              <input
                required
                type="password"
                placeholder="Confirm password"
                value={repeatPassword}
                onChange={(e) => setRepeatPassord(e.target.value)}
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
CreateBrigadaForm.propTypes = {
  close: PropTypes.func
}
export default CreateBrigadaForm
