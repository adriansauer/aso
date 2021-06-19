import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useCreateIncidencia from '../../api/incidencias/useCreateIncidencia'
import PreLoader from '../PreLoader'
const CityForm = (props) => {
  const [code, setCode] = useState('')
  const [description, setDescription] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const { execute: createIncidenciaExecute } = useCreateIncidencia()
  useEffect(() => {
    M.AutoInit()
  }, [])
  const createIncidencia = (e) => {
    setIsLoading(true)
    e.preventDefault()
    createIncidenciaExecute({ code, description })
      .then((res) => {
        M.toast({ html: 'Ciudad creada exitosamente' })
        setIsLoading(false)
        props.close()
      })
      .catch((err) => {
        M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
        setIsLoading(false)
      })
  }
  return (
    <div id="modal_incidence_form" className="modal">
    <PreLoader visible={isLoading} />
      <form onSubmit={createIncidencia}>
        <div className="modal-content">
          <h4>Agregue una nueva incidencia</h4>

          <div className="row">
               {/** Codigo de la incidencia */}
            <div className="input-field col m6 s6">
              <label>Código: </label>
              <input
                required
                type="text"
                value={code}
                onChange={(e) => setCode(e.target.value)}
              />
            </div>
             {/** Descripcion de la incidencia */}
             <div className="input-field col m6 s6">
              <label>Descripción: </label>
              <input
                required
                type="text"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              />
            </div>
          </div>
        </div>
        {/** Footer del modal */}
        <div className="modal-footer">
          <button
            className="btn-small waves-effect red darken-4"
            type='button'
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
CityForm.propTypes = {
  close: PropTypes.func
}
export default CityForm
