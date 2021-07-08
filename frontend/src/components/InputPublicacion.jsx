import React, { useEffect, useState, useContext } from 'react'
import M from 'materialize-css'
import userContext from '../context/userContext'
import useCreatePublication from '../api/publicaciones/useCreatePublication'
import useCreateFile from '../api/files/useCreateFile'
import useSearchIncidencia from '../api/incidencias/useSearchIncident'
import PropTypes from 'prop-types'
import PreviewImage from './PreviewImage'
const InputPublicacion = (props) => {
  const [width, setWidth] = useState(window.innerWidth)
  const { execute: createPublicationExecute } = useCreatePublication()
  const { execute: searchIncidenciasExecute } = useSearchIncidencia()
  const { execute: createFileExecute } = useCreateFile()
  const [body, setBody] = useState('')
  const [files, setFiles] = useState([])
  const [incidencias, setIncidencias] = useState([])
  const [incidenciaId, setIncidenciaId] = useState(null)
  const [text, setText] = useState('')
  const { userData } = useContext(userContext)
  function handleWindowSizeChange () {
    setWidth(window.innerWidth)
  }

  const handleLoadIncidencia = () => {
    setIncidencias(null)
    searchIncidenciasExecute(text)
      .then((res) => {
        setIncidencias(res.data.content)
        M.AutoInit()
      })
      .catch((err) => {
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
      })
  }
  useEffect(() => {
    if (text.length > 2) {
      handleLoadIncidencia()
    }
  }, [text])
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
    let publication = {}
    if (incidenciaId === null) {
      publication = {
        body,
        destination: document.getElementById('dropdown_destination').value
      }
    } else {
      publication = {
        body,
        destination: document.getElementById('dropdown_destination').value,
        incidenceCodeId: incidenciaId
      }
    }
    createPublicationExecute(publication)
      .then((res) => {
        // publicar archivos
        if (files.length > 0) {
          for (let i = 0; i < files.length; i++) {
            getBase64(files[i])
              .then((result) => {
                createFileExecute({
                  publicationId: res.data.id,
                  name: files[i].name,
                  file: result
                })
                  .then(() => {
                    if (i === files.length - 1) {
                      M.toast({ html: 'Gracias por publicar ' })
                      setBody('')
                      props.reloadPublications()
                    }
                  })
                  .catch()
              })
              .catch()
          }
        } else {
          M.toast({ html: 'Gracias por publicar ' })
          setBody('')
          props.reloadPublications()
        }
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
  const getBase64 = (file) => {
    return new Promise((resolve) => {
      let baseURL = ''
      // Make new FileReader
      const reader = new FileReader()
      // Convert the file to base64 text
      reader.readAsDataURL(file)
      // on reader load somthing...
      reader.onload = () => {
        // Make a fileInfo Object
        baseURL = reader.result
        resolve(baseURL)
      }
    })
  }
  const handleChangeFile = (event) => {
    setFiles([])
    if ([...event.target.files].length > 6) {
      M.toast({
        html: 'Se permite un máximo de 6 archivos'
      })
    } else {
      setFiles([...event.target.files])
    }
  }
  return (
    <div
      className="container"
      style={{
        width: width <= 768 ? '100%' : '70%',
        marginTop: '0'
      }}
    >
      <br />
      <form onSubmit={createPublication}>

        {userData.roles[0].authority === 'ROLE_BRIGADE'
          ? (
          <div className='row'>
            <div className="input-field col m6">
              <label>Buscar Incidente: </label>
              <input
                type="text"
                value={text}
                onChange={(e) => setText(e.target.value)}
              />
            </div>
            <div className="col m4 s4">
              {incidencias !== null
                ? (
                <div className="input-field col s12">
                  <select
                    defaultValue={null}
                    onChange={(e) => {
                      setIncidenciaId(
                        e.target.options[e.target.options.selectedIndex].value
                      )
                    }}
                  >
                    <option value={null}>Incidentes</option>
                    {incidencias.map((incidencia) => (
                      <option key={incidencia.id} value={incidencia.id}>
                        {incidencia.code}-{incidencia.description}
                      </option>
                    ))}

                  </select>

                  <label>Incidencia</label>
                </div>

                  )
                : null}
            </div>
            </div>
            )
          : null}

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
                <option value="Mi Brigada">Mi brigada</option>
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
        <div className="row">
          <div className="file-field input-field col m12 s12">
            <div
              className="btn btn-medium"
              style={{
                backgroundColor: '#0C0019'
              }}
            >
              <i className="material-icons">insert_drive_file</i>

              <input
                type="file"
                multiple
                accept="image/png, image/jpeg, image/jpg, application/pdf"
                onChange={handleChangeFile}
              />
            </div>
            <div className="file-path-wrapper">
              <div className="row">
                {files.length > 0
                  ? files.map((f) => (
                      <div key={f.name} className="col m3 s3">
                        <PreviewImage key={f.name} file={f} />
                      </div>
                  ))
                  : null}
              </div>
            </div>
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
