import React, { useState, useContext } from 'react'
import PreLoader from './PreLoader'
import useSetPassword from '../api/user/useSetPasswordUser'
import UserContext from '../context/userContext'
import M from 'materialize-css'
const Configuracion = () => {
  const [oldPassword, setOldPassword] = useState('')
  const [newPassword, setNewPassword] = useState('')
  const [repeatPassword, setRepeatPassword] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const { userData } = useContext(UserContext)
  const { execute: setPasswordExecute } = useSetPassword()

  const submit = (e) => {
    setIsLoading(true)
    setPasswordExecute({ oldPassword, newPassword, repeatPassword }, userData.id)
      .then(() => {
        M.toast({ html: 'La contraseña ha sido modificada exitosamente' })
        setIsLoading(false)
      })
      .catch((err) => {
        M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
        setIsLoading(false)
      })
    e.preventDefault()
  }
  return (
    <div className="container">
      <PreLoader visible={isLoading} />
      <div className="row">
        <div className="col m6 offset-m3 s6 offset-s3">
          <h2 className="center-align">Reestablecer contraseña</h2>
          <div className="row">
            <form onSubmit={submit}>
              <div className="row">
                <div className="input-field col s12 m12">
                  <label>
                    <i className="material-icons">security</i>
                    Contraseña:{' '}
                  </label>
                  <input
                    required
                    type="password"
                    value={oldPassword}
                    onChange={(e) => {
                      setOldPassword(e.target.value)
                    }}
                  />
                </div>
              </div>
              <div className="row">
                <div className="input-field col s12 m12">
                  <label>
                    <i className="material-icons">security</i>New Passeword:{' '}
                  </label>
                  <input
                    required
                    type="password"
                    value={newPassword}
                    onChange={(e) => {
                      setNewPassword(e.target.value)
                    }}
                  />
                </div>
              </div>
              <div className="row">
                <div className="input-field col s12 m12">
                  <label>
                    <i className="material-icons">security</i>Repeat Passeword:{' '}
                  </label>
                  <input
                    required
                    type="password"
                    value={repeatPassword}
                    onChange={(e) => {
                      setRepeatPassword(e.target.value)
                    }}
                  />
                </div>
              </div>
              <div className="row">
                <div className="col m12 s12">
                  <p className="center-align">
                    <button
                      style={{ backgroundColor: '#0C0019' }}
                      className="btn btn-large waves-effect waves-light"
                      type="submit"
                      name="action"
                    >
                      Reestablecer
                    </button>
                  </p>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Configuracion
