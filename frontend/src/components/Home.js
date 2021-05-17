import React from 'react'
import image from '../images/construccion.png'
import useGetUserById from '../api/user/useGetUserById'
import Publications from './Publications'
const Home = () => {
  const user = useGetUserById(1)
  console.log(user)
  return (
        <div style={{ width: '100%' }}>
                <div>
                    <Publications/>
                </div>
            <h1 aria-setsize={36}>
                Página de inicio
            </h1>
            <img src={image} alt="Logo" />
        </div>)
}
export default Home
