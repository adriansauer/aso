import React, { useEffect, useState } from 'react'
import perfil from '../images/default.jpg'
import useGetMembers from '../api/miembros/useGetMembers'
import { useLocation, useHistory } from 'react-router-dom'
const UsuarioList = () => {
  const location = useLocation()
  const history = useHistory()
  const [members, setMembers] = useState(null)
  const { execute: getMembersExecute } = useGetMembers()
  useEffect(() => {
    if (location.brigada === undefined) {
      history.goBack()
    }
    fetchMembers()
  }, [])

  const fetchMembers = () => {
    if (location.brigada !== undefined) {
      getMembersExecute(location.brigada.id)
        .then((res) => {
          setMembers(res.data.content)
          console.log(res.data.content)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
  return (
    <div className="container" style={{ marginTop: '4%' }}>
      <div className="row">
        <h3>
          Miembros de{' '}
          {location.brigada === undefined ? null : location.brigada.name}
        </h3>
      </div>
      {members !== null
        ? <div className="row">
          <div className="collection">
            {/** Item collection representa cada uno de los usuarios */}
            {members.map((member) => (
              <div
                key={member.id}
                onClick={() => {
                  history.push({
                    pathname: '/perfil',
                    member: member,
                    brigada: location.brigada
                  })
                }}
                className="btn btn-large collection-item avatar brigada_button"
                style={{
                  margin: '4%',
                  color: 'black',
                  paddingTop: 0,
                  paddingLeft: 0,
                  marginTop: '2%',
                  width: '90%',
                  borderRadius: '1%'
                }}
              >
                {/** Foto de perfil */}
                <div className="row">
                  <div className="col s6 m2">
                    <img
                      src={perfil}
                      alt=""
                      className="circle"
                      style={{ height: 80, width: 80, marginBottom: '5%' }}
                    />
                  </div>
                  <div className="col s6 m6" style={{ textAlign: 'left' }}>
                    <span style={{ fontSize: 24 }} className="title">
                      {member.name} {member.lastname}
                    </span>
                    <br />
                    <span>{member.usercode}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
        : null}
    </div>
  )
}

export default UsuarioList
