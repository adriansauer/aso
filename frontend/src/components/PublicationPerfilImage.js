import React from 'react'
import perfil from '../images/default.jpg'
import PropTypes from 'prop-types'

const PublicationPerfilImage = (props) => {
  return (
        <img
        src={props.image === null ? perfil : props.image}
        alt=""
        className="circle"
        style={{
          height: 70,
          width: 70,
          marginBottom: '5%',
          marginTop: 5,
          marginLeft: '5%'
        }}
      />
  )
}
PublicationPerfilImage.propTypes = {
  image: PropTypes.string
}
export default PublicationPerfilImage
