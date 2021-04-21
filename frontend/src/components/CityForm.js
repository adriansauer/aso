import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useCreateCity from '../api/city/useCreateCity'
const CityForm = (props) => {
  const [name, setName] = useState('')
  const { execute: createCityExecute } = useCreateCity()
  useEffect(() => {
    M.AutoInit()
  }, [])
  const createCity = (e) => {
    e.preventDefault()
    createCityExecute({ name })
      .then((res) => {
        M.toast({ html: 'Ciudad creada exitosamente' })
        props.close()
      })
      .catch((err) => {
        console.log(err)
      })
  }
  return (
    <div id="modal2" className="modal modal-fixed-footer">
      <form onSubmit={createCity}>
        <div className="modal-content">
          <h4>Agregue una nueva ciudad</h4>
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
CityForm.propTypes = {
  close: PropTypes.func
}
export default CityForm
