import React, { useEffect, useState } from 'react'
import CreateUserForm from './CreateUserForm'
import M from 'materialize-css'

const BrigadaPerfil = () => {
  // Instancia del modal para el formulario de usuario
  const [instance, setInstance] = useState(null)

  // Creo la instancia al inicio
  useEffect(() => {
    const elem = document.querySelector('.modal')
    const instance = M.Modal.init(elem, {
      inDuration: 300
    })
    setInstance(instance)
  }, [])
  // Cerrar el modal de agregar usuario
  const closeModal = () => {
    instance.close()
  }

  return (
        <div>

            <h2>Aqui se ve el perfil de una brigada</h2>
            <button
                className="btn-floating btn-large waves-light"
                onClick={() => instance.open()}
                style={{ backgroundColor: '#0C0019' }}
            >
                <i className="material-icons">add</i>
            </button>
            <CreateUserForm close={closeModal} />
        </div>
  )
}

export default BrigadaPerfil
