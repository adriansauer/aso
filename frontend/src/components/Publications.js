import React from 'react'
import PropTypes from 'prop-types'
import TagsPublicaciones from './TagsPublicaciones'

const PublicationsList = (props) => {
  return (
    <div className="container">
      <div className="row">
        <div className="collection">
          {props.publicaciones !== null
            ? props.publicaciones.map((p) => (
                <div key={p.id}>
                  <TagsPublicaciones
                    description={p.body}
                    userId={p.userId}
                    likes={83}
                    publicationId={p.id}
                    reloadPublications={props.reloadPublications}
                    destination={p.destination}
                  />
                </div>
            ))
            : null}
        </div>
      </div>
    </div>
  )
}
PublicationsList.propTypes = {
  publicaciones: PropTypes.array,
  reloadPublications: PropTypes.func
}
export default PublicationsList
