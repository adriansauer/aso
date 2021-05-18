import React, { useEffect, useState } from 'react'
import M from 'materialize-css'
import useGetPublications from '../api/publicaciones/useGetPublications'
import TagsPublicaciones from './TagsPublicaciones'

const PublicationsList = () => {
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
      <div className="container">
          <div className="row">
            <div className="collection">
                {publicaciones !== null
                  ? publicaciones.map((p) => (
                        <div key={p.id}>
                          <TagsPublicaciones par={p.body} userId={p.userId} likes={83} publicationId={p.id}/>
                        </div>
                  ))
                  : null}
            </div>
          </div>
      </div>
  )
}

export default PublicationsList