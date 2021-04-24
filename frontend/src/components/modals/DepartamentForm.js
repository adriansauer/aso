import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useCreateDepartament from '../../api/departamento/useCreateDepartament'
import PreLoader from '../PreLoader'
const DepartamentForm = (props) => {
  const [name, setName] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const { execute: createDepartamentExecute } = useCreateDepartament()
  useEffect(() => {
    M.AutoInit()
  }, [])
  const createDepartament = (e) => {
    setIsLoading(true)
    e.preventDefault()
    createDepartamentExecute({ name })
      .then((res) => {
        M.toast({ html: 'Departamento creado exitosamente' })
        setIsLoading(false)
        props.close()
      })
      .catch((err) => {
        M.toast({ html: err.response.data.description })
        setIsLoading(false)
      })
  }
  return (
    <div id="modal1" className="modal modal-fixed-footer">
      <PreLoader visible={isLoading}/>
      <form onSubmit={createDepartament}>
        <div className="modal-content">
          <h4>Agregue un nuevo departamento</h4>
          {/** Nombre del departamento */}
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
DepartamentForm.propTypes = {
  close: PropTypes.func
}
export default DepartamentForm
