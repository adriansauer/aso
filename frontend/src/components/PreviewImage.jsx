import React, { useState, useEffect } from 'react'
import empty from '../images/image_empty.png'
import PropTypes from 'prop-types'
import pdfImage from '../images/pdf-icon.png'
const PreviewImage = (props) => {
  const [file, setFile] = useState(null)

  useEffect(() => {
    getBase64(props.file)
      .then((result) => {
        setFile(result)
      })
      .catch(() => {})
  }, [])
  const getBase64 = (file) => {
    return new Promise((resolve) => {
      let baseURL = ''
      // Make new FileReader
      const reader = new FileReader()
      // Convert the file to base64 text
      reader.readAsDataURL(file)
      // on reader load somthing...
      reader.onload = () => {
        // Make a fileInfo Object
        baseURL = reader.result
        resolve(baseURL)
      }
    })
  }
  return (

      <img
        src={file === null ? empty : file.charAt(5) === 'a' ? pdfImage : file}
        alt={props.file.name}
        width={150}
        height={150}
        style={{ height: 80, width: 80 }}
      />

  )
}
PreviewImage.propTypes = {
  file: PropTypes.object
}
export default PreviewImage
