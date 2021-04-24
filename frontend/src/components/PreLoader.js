import React from 'react'
import PropTypes from 'prop-types'
const PreLoader = (props) => {
  if (props.visible) {
    return (
      <div
        style={{
          position: 'fixed',
          margin: 'auto',
          left: '25%',
          top: '50%',
          width: '50%',
          height: '50%',
          Zindex: 10000
        }}
      >
        <div className="progress">
          <div className="indeterminate"></div>
        </div>
      </div>
    )
  }
  return null
}
PreLoader.propTypes = {
  visible: PropTypes.bool
}
export default PreLoader
