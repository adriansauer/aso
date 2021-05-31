import React, { useEffect, useState, useContext } from 'react'
import M from 'materialize-css'
import userContext from '../context/userContext'
import useCreatePublication from '../api/publicaciones/useCreatePublication'
import PropTypes from 'prop-types'
const InputPublicacion = (props) => {
  const [width, setWidth] = useState(window.innerWidth)
  const { execute: createPublicationExecute } = useCreatePublication()
  const { userData } = useContext(userContext)
  const [body, setBody] = useState('')
  function handleWindowSizeChange () {
    setWidth(window.innerWidth)
  }

  useEffect(() => {
    const elem = document.getElementById('dropdown_destination')
    M.FormSelect.init(elem, {})
    window.addEventListener('resize', handleWindowSizeChange)
    M.AutoInit()
    return () => {
      window.removeEventListener('resize', handleWindowSizeChange)
    }
  }, [])

  const createPublication = (e) => {
    createPublicationExecute({
      body,
      destination: document.getElementById('dropdown_destination').value,
      userId: userData.id
    })
      .then((res) => {
        M.toast({ html: 'Publicacion realizada ' })
        setBody('')
        props.reloadPublications()
      })
      .catch((err) => {
        M.toast({
          html:
              err.response === undefined
                ? 'Hubo un error con la conexión'
                : err.response.data.description
        })
      })

    e.preventDefault()
  }

  return (
    <div
      className="container"
      style={{
        width: width <= 768 ? '100%' : '70%',
        marginTop: '0'
      }}
    >

      <form onSubmit={createPublication}>
        <div className="row">
          <div className="input-field col m6 s6">
            <textarea
              id="textarea1"
              required
              className="materialize-textarea"
              maxLength={255}
              value={body}
              onChange={(e) => {
                setBody(e.target.value)
              }}
            ></textarea>
            <label htmlFor="textarea1">Escribe algo...</label>
          </div>
          <div className="col m2 s2">
            <div className="input-field col m12 s12">
              <select id="dropdown_destination">
                <option value="Todos">Todos</option>
                <option value="Publico">Publico</option>
                <option value="MiBrigada">Mi brigada</option>
              </select>
            </div>
          </div>
          <div className="col m2 s2">
            <button
              className="btn btn-medium waves-light"
              type="submit"
              style={{
                backgroundColor: '#0C0019',
                marginTop: '30%'
              }}
            >
              Publicar
            </button>
          </div>
        </div>
      </form>
    </div>
  )
}
InputPublicacion.propTypes = {
  reloadPublications: PropTypes.func
}
export default InputPublicacion
