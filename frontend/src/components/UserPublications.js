import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import M from 'materialize-css'
import useGetPublications from '../api/publicaciones/useGetPublications'
import TagsPublicaciones from './TagsPublicaciones'

const UserPublications = (props) => {
  const { execute: getPublicationsExecute } = useGetPublications()
  const [publicaciones, setPublicaciones] = useState(null)
  useEffect(() => {
    getPublicationsExecute()
      .then((res) => {
        setPublicaciones(res.data.content)
      })
      .catch((err) => {
        M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
      })
  }, [])
  return (
      <div className="row">
            Publicaciones
            <div className="collection">
                {publicaciones !== null
                  ? publicaciones.map((p) => (
                    <div key={p.id}>
                    {p.userId === props.userId
                      ? <TagsPublicaciones par={p.body} userId={p.userId} likes={83}/>
                      : null}
                    </div>
                  ))
                  : null}
            </div>
          </div>
  )
}
UserPublications.propTypes = {
  userId: PropTypes.number
}
export default UserPublications
