import React, { useEffect, useState } from 'react'
import perfil from '../images/default.jpg'
import { useLocation, useHistory } from 'react-router-dom'
import useGetMemberById from '../api/miembros/useGetMemberById'
const UsuarioPerfil = (props) => {
  const location = useLocation()
  const history = useHistory()
  const [image, setImage] = useState(null)
  const { execute: getMemberByIdExecute } = useGetMemberById()
  const [member, setMember] = useState(null)
  const onImageChange = (event) => {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader()
      reader.onload = (e) => {
        setImage(e.target.result)
      }
      reader.readAsDataURL(event.target.files[0])
    }
  }
  useEffect(() => {
    if (location.member === undefined) {
      history.goBack()
    }
    fetchMember()
  }, [])

  const fetchMember = () => {
    console.log(location.member)
    if (location.member !== undefined) {
      getMemberByIdExecute(location.member.id)
        .then((res) => {
          setMember(res.data)
          console.log(res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
  if (member !== null) {
    return (
      <div className="container" style={{ marginTop: '5%' }}>
        <div style={{ marginBottom: 0 }} className="row">
          <label
            style={{ marginTop: '3%', width: 100, height: 100 }}
            htmlFor="file-input"
            className="btn-floating btn-large waves-effect waves-light"
          >
            <img
              src={image === null ? perfil : image}
              className="circle"
              style={{ width: '100%' }}
            />
          </label>

          <input hidden id="file-input" onChange={onImageChange} type="file" />
        </div>

        <div className="row center-align">
          <h6 style={{ margin: 0 }}>
            {member.usercode} {member.rankTitle}
          </h6>
        </div>

        <div className="row center-align">
          <h4 style={{ margin: 0 }}>
            {member.name} {member.lastname}
          </h4>
        </div>

        <div className="row center-align">
          <div className="card-panel teal white left-align">
            <ul>
              <li>
                <div className="row left-align">
                  <div className="col s2 m1 left-align">
                    <img
                      alt=""
                      className="circle"
                      src={perfil}
                      style={{ width: 50, height: 50 }}
                    ></img>
                  </div>
                  <div className="col s7 m6 left-align">
                    <span style={{ fontSize: 16 }}>
                      {location.brigada !== undefined
                        ? `${location.brigada.name}`
                        : null}
                    </span>
                  </div>
                </div>
              </li>
              <li>
                <div className="row left-align">
                  <div className="col s2 m1 left-align">
                    <i style={{ fontSize: 36 }} className="material-icons">
                      location_on
                    </i>
                  </div>
                  <div className="col s7 m6 left-align">
                    <span style={{ fontSize: 16 }}>
                      {member.city}-{member.departament}
                    </span>
                  </div>
                </div>
              </li>
              <li>
                <div className="row left-align">
                  <div className="col s2 m1 left-align">
                    <i style={{ fontSize: 36 }} className="material-icons">
                      cake
                    </i>
                  </div>
                  <div className="col s7 m6 left-align">
                    <span style={{ fontSize: 16 }}>{`${new Date(
                      member.birthday
                    ).getDate()}/${new Date(
                      member.birthday
                    ).getMonth()}/${new Date(
                      member.birthday
                    ).getFullYear()}`}</span>
                  </div>
                </div>
              </li>
              <li>
                <div className="row left-align">
                  <div className="col s2 m1 left-align">
                    <i style={{ fontSize: 36 }} className="material-icons">
                      description
                    </i>
                  </div>
                  <div className="col s7 m6 left-align">
                    <span style={{ fontSize: 16 }}>{member.description}</span>
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
                    <span style={{ fontSize: 16 }}>{member.phone}</span>
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
                    <span style={{ fontSize: 16 }}>{member.email}</span>
                  </div>
                  <div className="col s3 m5 right-align">
                    <button
                      style={{ backgroundColor: '#0C0019' }}
                      className="btn-floating btn-medium"
                    >
                      <i className="material-icons">edit</i>
                    </button>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    )
  } else {
    return (
      <div
        className="preloader-wrapper big active"
        style={{ marginTop: '20%' }}
      >
        <div className="spinner-layer spinner-blue-only">
          <div className="circle-clipper left">
            <div className="circle"></div>
          </div>
          <div className="gap-patch">
            <div className="circle"></div>
          </div>
          <div className="circle-clipper right">
            <div className="circle"></div>
          </div>
        </div>
      </div>
    )
  }
}

export default UsuarioPerfil
