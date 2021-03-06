import React, { useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useUpdatePublication from '../../api/publicaciones/useUpdatePublication'
const EditarPublicacionModal = (props) => {
  const [description, setDescription] = useState(props.body)
  const { execute: updatePublicationExecute } = useUpdatePublication()
  const handleEditPublication = (e) => {
    updatePublicationExecute(props.publicationId, description, parseInt(props.destination) === 0 ? 'Todos' : (parseInt(props.destination) > 0 ? 'Mi Brigada' : 'Publico'))
      .then((res) => {
        M.toast({ html: 'Publicacion modificada ' })
        props.close()
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
    props.publicationId !== undefined && props.publicationId !== null
      ? <div id={`modal_edit_pubication${props.publicationId}`} className="modal">
      <form onSubmit={handleEditPublication}>
        <div className="modal-content">
          <h4>Editar publicación</h4>

          {/** Description */}
          <div className="row">
            <div className="input-field col m12 s12">
              <input
                required
                type="text"
                maxLength={255}
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
            style={{ marginRight: '5%' }}
            type='button'
            onClick={() => props.close()}
          >
            Cancelar
          </button>
          <button
            className="btn btn-small waves-effect"
            style={{ backgroundColor: '#0C0019', marginRight: '5%' }}
            type="submit"
          >
            Editar
          </button>
        </div>
      </form>
    </div>
      : null
  )
}
EditarPublicacionModal.propTypes = {
  close: PropTypes.func,
  publicationId: PropTypes.number,
  destination: PropTypes.string,
  body: PropTypes.string,
  reloadPublications: PropTypes.func
}

export default EditarPublicacionModal
