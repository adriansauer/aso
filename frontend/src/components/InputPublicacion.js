import React, { useEffect, useState, useContext } from 'react'
import M from 'materialize-css'
import userContext from '../context/userContext'
import useCreatePublication from '../api/publicaciones/useCreatePublication'
import PreLoader from './PreLoader'
const InputPublicacion = () => {
  const [width, setWidth] = useState(window.innerWidth)
  const { execute: createPublicationExecute } = useCreatePublication()
  const { userData } = useContext(userContext)
  const [body, setBody] = useState('')
  const [userId, setUserId] = useState('')
  const [destination, setDestination] = useState('null')
  const [isLoading, setIsLoading] = useState(false)
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
  const createPublication = (e) => {
    setIsLoading(true)
    if (body === '') {
      M.toast({ html: 'Agregue algo que publicar' })
      setIsLoading(false)
    } else {
      createPublicationExecute({
        body,
        destination,
        userId
      })
        .then((res) => {
          M.toast({ html: 'Publicacion realizada ' })
          setIsLoading(false)
        })
        .catch((err) => {
          M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
          setIsLoading(false)
        })
    }
    e.preventDefault()
  }
  return (
    <div
      className="container"
      style={{
        width: width <= 768 ? '100%' : '40%',
        marginTop: '10%'
      }}
    >
      <PreLoader visible={isLoading}/>
      <div className="row">
        <form onSubmit = {createPublication} className="col s8 s8">
          <div className="row">
            <div className="input-field col s12">
              <textarea
                id="textarea1"
                className="materialize-textarea"
                onChange={(e) => {
                  setBody(e.target.value)
                  setUserId(userData.userId)
                }}
              ></textarea>
              <label htmlFor="textarea1">Escribe algo...</label>
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
            <i className="material-icons">group</i>
          </button>
          <ul
            id="dropdown1"
            className="dropdown-content"
          >
              <a style={{ color: '#0C0019' }} href="#!">Enviar a</a>
              <li className="divider" tabIndex="-1"/>
            <li onClick={(e) => setDestination('Todos')}>
              <a style={{ color: '#0C0019' }} href="#!">Todos</a>
            </li>
            <li onClick={(e) => setDestination('MiBrigada')}>
              <a style={{ color: '#0C0019' }} href="#!">Mi brigada</a>
            </li>
            <li onClick={(e) => setDestination('Publico')}>
              <a style={{ color: '#0C0019' }} href="#!">Público</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default InputPublicacion
