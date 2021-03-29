import React from 'react'

const Login = () => {
  return (
    <div className="container">
      <div className="row">
        <div className="col m6 offset-m3">
          <h2 className="center-align">Login</h2>
          <div className="row">
            {/** error && (
              <ErrorNotice
                message={error}
                clearError={() => setError(undefined)}
              />
            ) */}
            <form>
              <div className="row">
                <div className="input-field col s12">
                  <label><i className="material-icons">perm_contact_calendar
</i>Código: </label>
                  <input
                    type="text"
                    id="codigo"
                  />
                </div>
              </div>
              <div className="row">
                <div className="input-field col s12">
                <label><i className="material-icons">security
</i>Contraseña: </label>
                  <input type="password" id="password" />
                </div>
              </div>

              <div className="row">
                <div className="col m12">
                  <p className="center-align">
                    <button
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
