import React, { useState } from 'react'
import BrigadaPerfil from '../images/logo1.png'
import Perfil from '../images/perfil.jpg'
const UsuarioPerfil = () => {
  const [image, setImage] = useState(null)
  const [isEditing, setIsEditing] = useState(false)
  const [email, setEmail] = useState('cecilio@gmail.com')
  const [name, setName] = useState('Cecilio')
  const [lastname, setLastname] = useState('Dominguez')
  const [phone, setPhone] = useState('0985728401')
  const [birthday, setBirtday] = useState(new Date())
  const onImageChange = (event) => {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader()
      reader.onload = (e) => {
        setImage(e.target.result)
      }
      reader.readAsDataURL(event.target.files[0])
    }
  }
  return (
    <div className="container" style={{ marginTop: '5%' }}>
      {isEditing
        ? null
        : (
        <div style={{ marginBottom: 0 }} className="row">
          <label
            style={{ marginTop: '3%', width: 100, height: 100 }}
            htmlFor="file-input"
            className="btn-floating btn-large waves-effect waves-light"
          >
            <img
              src={image === null ? Perfil : image}
              className="circle"
              style={{ width: '100%' }}
            />
          </label>

          <input hidden id="file-input" onChange={onImageChange} type="file" />
        </div>
          )}

      <div className="row center-align">
        <h6 style={{ margin: 0 }}>ENC-999</h6>
      </div>

      <div className="row center-align">
        {isEditing
          ? (
          <>
            <div className="row">
              <div className="col m3 s3 offset-s3 offset-m3">
                <input
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  id="name"
                  type="text"
                  className="validate"
                />
              </div>
              <div className="col m3 s3">
                <input
                  value={lastname}
                  onChange={(e) => setLastname(e.target.value)}
                  id="lastname"
                  type="text"
                  className="validate"
                />
              </div>
            </div>
          </>
            )
          : (
          <h4 style={{ margin: 0 }}>
            {name} {lastname}
          </h4>
            )}
      </div>
      <div className="row center-align">
        <div className="col s5 right-align">
          <img
            alt=""
            className="circle"
            src={BrigadaPerfil}
            style={{ width: 50, height: 50 }}
          ></img>
        </div>
        <div className="col s7 left-align">
          <h6>Brigada de Cambyreta</h6>
        </div>
      </div>
      <div className="row center-align">
        <div className="card-panel teal white left-align">
          <ul>
            <li>
              <div className="row left-align">
                <div className="col s2 m1 left-align">
                  <i style={{ fontSize: 36 }} className="material-icons">
                    cake
                  </i>
                </div>
                <div className="col s7 m6 left-align">
                  {isEditing
                    ? (
                    <>
                      <input
                        value={`${birthday.getFullYear()}-${birthday.getMonth() + 1}-${birthday.getDate() + 1}`}
                        onChange={(e) => setBirtday(new Date(e.target.value))}
                        id="date"
                        type="date"
                        className="validate"
                      />
                      <label className="active" htmlFor="email">
                        Birthday
                      </label>
                    </>
                      )
                    : (
                    <span style={{ fontSize: 16 }}>
                      {birthday.getDate() + 1}/{birthday.getMonth() + 1}/
                      {birthday.getFullYear()}
                    </span>
                      )}
                </div>
              </div>
            </li>
            <li>
              <div className="row left-align">
                <div className="col s2 m1 left-align">
                  <i style={{ fontSize: 36 }} className="material-icons">
                    local_phone
                  </i>
                </div>
                <div className="col s7 m6 left-align">
                  {isEditing
                    ? (
                    <>
                      <input
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                        id="phone"
                        type="text"
                        className="validate"
                      />
                      <label className="active" htmlFor="phone">
                        Phone
                      </label>
                    </>
                      )
                    : (
                    <span style={{ fontSize: 16 }}>{phone}</span>
                      )}
                </div>
              </div>
            </li>
            <li>
              <div className="row left-align">
                <div className="col s2 m1 left-align">
                  <i style={{ fontSize: 36 }} className="material-icons">
                    email
                  </i>
                </div>
                <div className="col s7 m6 left-align">
                  {isEditing
                    ? (
                    <>
                      <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        id="email"
                        type="email"
                        className="validate"
                      />
                      <label className="active" htmlFor="email">
                        Email
                      </label>
                    </>
                      )
                    : (
                    <span style={{ fontSize: 16 }}>{phone}</span>
                      )}
                </div>
                <div className="col s3 m5 right-align">
                  {isEditing
                    ? (
                    <button
                      style={{ backgroundColor: '#0C0019' }}
                      className="btn-floating btn-medium"
                      onClick={() => setIsEditing(false)}
                    >
                      <i className="material-icons">check</i>
                    </button>
                      )
                    : (
                    <button
                      style={{ backgroundColor: '#0C0019' }}
                      onClick={() => setIsEditing(true)}
                      className="btn-floating btn-medium"
                    >
                      <i className="material-icons">edit</i>
                    </button>
                      )}
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default UsuarioPerfil
