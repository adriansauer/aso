import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useCreateHistory from '../../api/historias/useCreateHistory'
import PreLoader from '../PreLoader'
const HistoryModal = (props) => {
  const [text, setText] = useState(props.text)
  const [isLoading, setIsLoading] = useState(false)
  const { execute: createHistoryExecute } = useCreateHistory()
  useEffect(() => {
    M.AutoInit()
  }, [])
  const createHistory = (e) => {
    setIsLoading(true)
    e.preventDefault()
    createHistoryExecute(props.brigadeId, { text })
      .then((res) => {
        M.toast({ html: 'Historia agregada exitosamente' })
        setIsLoading(false)
        props.close()
      })
      .catch((err) => {
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
        setIsLoading(false)
      })
  }
  return (
    <div id="history_modal" className="modal">
      <PreLoader visible={isLoading} />
      <form onSubmit={createHistory}>
        <div className="modal-content">
          <h4>Historia de su brigada</h4>
          {/** Nombre del departamento */}
          <div className="row">
            <div className="input-field col s12">
              <textarea
                required
                value={text}
                onChange={(e) => setText(e.target.value)}
                id="textarea1"
                className="materialize-textarea"
              ></textarea>
              <label htmlFor="textarea1">Historia</label>
            </div>
          </div>
        </div>
        {/** Footer del modal */}
        <div className="modal-footer">
          <button
            className="btn-small waves-effect red darken-4"
            type="button"
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
HistoryModal.propTypes = {
  close: PropTypes.func,
  text: PropTypes.string,
  brigadeId: PropTypes.number
}
export default HistoryModal
