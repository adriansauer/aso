import React from 'react'
import PropTypes from 'prop-types'
import TagsPublicaciones from './TagsPublicaciones'

const PublicationsList = (props) => {
  return (
    <div className="container" style={{ width: '100%' }}>
      <div className="row">
        <div className="collection">
          {props.publicaciones !== null
            ? props.publicaciones.map((p) => (
                <div key={p.id}>
                  <TagsPublicaciones
                    description={p.body}
                    userId={p.userId}
                    likes={p.likeQuantity}
                    ilike={p.ilike}
                    publicationId={p.id}
                    reloadPublications={props.reloadPublications}
                    destination={p.destination}
                    date={p.createAt}
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
