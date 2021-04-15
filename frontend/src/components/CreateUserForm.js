import React, { useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useCreateUser from '../api/user/useCreateUser'

const CreateUserForm = (props) => {
  const { execute: createUserExecute } = useCreateUser()
  const [name, setName] = useState('')
  const [lastname, setLastname] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [usercode, setUsercode] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')

  const createUser = (e) => {
    e.preventDefault()
    if (password !== confirmPassword) {
      M.toast({ html: 'Las contraseñas no coinciden' })
    } else {
      createUserExecute({
        roles: [{ authority: 'ROLE_USER' }],
        name,
        lastname,
        email,
        password,
        usercode
      }).then((res) => {
        props.close()
      }).catch((err) => {
        M.toast({ html: err.response.message })
      })
    }
  }

  return (
    <div id="modal1" className="modal modal-fixed-footer">
      <form onSubmit={createUser}>
        <div className="modal-content">
          <h4>Agregue un miembro a la brigada</h4>

          {/** Email y Nombre del usaurio */}
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
          {/** Apellido y Codigo del usaurio */}
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
                <label>Código </label>
                <input
                  type="text"
                  value={usercode}
                  onChange={(e) => setUsercode(e.target.value)}
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
CreateUserForm.propTypes = {
  close: PropTypes.func
}
export default CreateUserForm
