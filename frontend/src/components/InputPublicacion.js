import React, { useEffect, useState } from 'react'
import M from 'materialize-css'
const InputPublicacion = () => {
  const [width, setWidth] = useState(window.innerWidth)
  function handleWindowSizeChange () {
    setWidth(window.innerWidth)
  }
  useEffect(() => {
    window.addEventListener('resize', handleWindowSizeChange)
    M.AutoInit()
    return () => {
      window.removeEventListener('resize', handleWindowSizeChange)
    }
  }, [])
  return (
    <div
      className="container"
      style={{
        width: width <= 768 ? '100%' : '40%',
        marginTop: '10%'
      }}
    >
      <div className="row">
        <form className="col s8 s8">
          <div className="row">
            <div className="input-field col s12">
              <textarea
                id="textarea1"
                className="materialize-textarea"
              ></textarea>
              <label htmlFor="textarea1">Escribe algo...</label>
            </div>
          </div>
        </form>

        <div className="col m2 s2">
          <button
            className="btn btn-floating btn-medium waves-light"
            style={{
              backgroundColor: '#0C0019',
              marginTop: '30%'
            }}
          >
            <i className="material-icons">insert_drive_file</i>
          </button>
        </div>

        <div className="col m2 s2">
          <button
            className="dropdown-trigger btn btn-floating btn-medium waves-light"
            data-target="dropdown1"
            style={{
              backgroundColor: '#0C0019',
              marginTop: '30%'
            }}
          >
            <i className="material-icons">send</i>
          </button>
          <ul
            id="dropdown1"
            className="dropdown-content"
          >
              <a style={{ color: '#0C0019' }} href="#!">Enviar a</a>
              <li className="divider" tabIndex="-1" />
            <li>
              <a style={{ color: '#0C0019' }} href="#!">Todos</a>
            </li>
            <li>
              <a style={{ color: '#0C0019' }} href="#!">Mi brigada</a>
            </li>
            <li>
              <a style={{ color: '#0C0019' }} href="#!">Público</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default InputPublicacion
