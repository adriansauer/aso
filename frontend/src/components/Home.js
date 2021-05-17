import React from 'react'
import useGetUserById from '../api/user/useGetUserById'
import Publications from './Publications'
const Home = () => {
  const user = useGetUserById(1)
  console.log(user)
  return (
        <div style={{ width: '100%' }}>
        <h1 aria-setsize={36}>
            Publicaciones
        </h1>
                <div>
                    <Publications/>
                </div>
          </div>)
}
export default Home
