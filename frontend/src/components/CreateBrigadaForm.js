import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import UseGetDepartaments from '../api/departamento/useGetDepartament'

const CreateBrigadaForm = (props) => {
  const { execute: getDepartamentsExecute } = UseGetDepartaments()
  const [name, setName] = useState('')
  const [usercode, setUsercode] = useState('')
  const [city, setCity] = useState('')
  // const [departament, setDepartament] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')
  // const [date, setDate] = useState('')
  useEffect(() => {
    getDepartamentsExecute()
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    M.AutoInit()
  }, [])
  return (
    <div id="modal1" className="modal modal-fixed-footer">
      <form>
        <div className="modal-content">
          <h4>Agregue una nueva brigada</h4>
          {/** Usercode y Nombre del usaurio */}
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
          {/** Ciudad y Departamento */}
          <div className="row">
            <div className="input-field col m6">
              <label>Ciudad: </label>
              <input
                required
                type="email"
                value={city}
                onChange={(e) => setCity(e.target.value)}
              />
            </div>
            <div className="row">
              <div className="input-field col s6">
                <select>
                  <option value="1">Itapúa</option>
                  <option value="2">Misiones</option>
                  <option value="3">Alto Paraná</option>
                  <option value="1">Itapúa</option>
                  <option value="2">Misiones</option>
                  <option value="3">Alto Paraná</option>
                  <option value="1">Itapúa</option>
                  <option value="2">Misiones</option>
                  <option value="3">Alto Paraná</option>
                  <option value="1">Itapúa</option>
                  <option value="2">Misiones</option>
                  <option value="3">Alto Paraná</option>
                </select>
                <label>Departamento</label>
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
              <input
                required
                type="password"
                placeholder="Confirm password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
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
