import React, { useState } from 'react'
import useLogin from '../api/user/useLogin'
const Login = () => {
  const [password, setPassword] = useState('')
  const [usercode, setUsercode] = useState('')
  const { execute: loginExecute } = useLogin()
  const submit = (e) => {
    loginExecute({ password, usercode })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err.response)
      })
    e.preventDefault()
  }

  return (
    <div className="container">
      <div className="row">
        <div className="col m6 offset-m3">
          <h2 className="center-align">Login</h2>
          <div className="row">
            <form onSubmit={submit}>
              <div className="row">
                <div className="input-field col s12">
                  <label>
                    <i className="material-icons">perm_contact_calendar</i>
                    Código:{' '}
                  </label>
                  <input
                  type="text"
                  id="codigo"
                  value={usercode}
                  onChange={(e) => { setUsercode(e.target.value) }}
                  />
                </div>
              </div>
              <div className="row">
                <div className="input-field col s12">
                  <label>
                    <i className="material-icons">security</i>Contraseña:{' '}
                  </label>
                  <input
                  type="password"
                  id="password"
                  value={password}
                  onChange={(e) => { setPassword(e.target.value) }}
                  />
                </div>
              </div>

              <div className="row">
                <div className="col m12">
                  <p className="center-align">
                    <button
                      style={{ backgroundColor: '#0C0019' }}
                      className="btn btn-large waves-effect waves-light"
                      type="submit"
                      name="action"
                    >
                      Login
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
export default Login
