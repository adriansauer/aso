import React, { useState, useEffect } from 'react'
import perfil from '../images/default.jpg'
import PropTypes from 'prop-types'
import useGetFileById from '../api/files/useGetFileById'
import M from 'materialize-css'
import pdfImage from '../images/pdf-icon.png'
const PublicationImage = (props) => {
  const { execute: getFileByIdExecute } = useGetFileById()
  const [file, setFile] = useState(null)
  const handleGetImage = () => {
    getFileByIdExecute(props.fileId)
      .then((res) => {
        setFile(res.data.file)
      }).catch(() => {})
  }

  useEffect(() => {
    const elem1 = document.getElementById(props.fileId)
    M.Materialbox.init(elem1, {
      inDuration: 300,
      height: 150,
      width: 150
    })
    M.AutoInit()
  })
  useEffect(() => {
    if (props.fileId) {
      handleGetImage()
    }
  }, [props.fileId])
  console.log(file)

  return (

      <img
        id={file === null ? perfil : file.charAt(5) === 'a' ? '' : props.fileId}
        src={file === null ? perfil : file.charAt(5) === 'a' ? pdfImage : file}
        alt=""
        width={150}
        height={150}
        className={file !== null ? file.charAt(5) === 'a' ? '' : 'materialboxed' : ''}
        onClick={file !== null ? file.charAt(5) === 'a' ? null : null : null}
        style={{ height: 140, width: 140 }}
      />

  )
}
PublicationImage.propTypes = {
  fileId: PropTypes.number
}
export default PublicationImage
