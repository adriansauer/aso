import React, { useState } from 'react'
import perfil from '../images/default.jpg'
import PropTypes from 'prop-types'
import useGetImageById from '../api/images/useGetImages'
import { useEffect } from 'react/cjs/react.development'
const Image = (props) => {
  const { execute: getImageByIdExecute } = useGetImageById()
  const [file, setFile] = useState(null)
  const handleGetImage = () => {
    getImageByIdExecute(props.imageId)
      .then((res) => {
        setFile(res.data.file)
      }).catch(() => {})
  }
  useEffect(() => {
    if (props.imageId) {
      handleGetImage()
    }
  }, [props.imageId])
  return (
        <img
        src={file === null ? perfil : file}
        alt=""
        className="circle"
        style={{ height: 70, width: 70, marginBottom: '5%' }}
      />
  )
}
Image.propTypes = {
  imageId: PropTypes.number
}
export default Image
