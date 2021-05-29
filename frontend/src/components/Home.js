import React from 'react'
import useGetUserById from '../api/user/useGetUserById'
import Publications from './Publications'
import InputPublicacion from './InputPublicacion'
const Home = () => {
  const user = useGetUserById(1)
  console.log(user)
  return (
        <div style={{ width: '100%' }}>
            <div>
                <InputPublicacion/>
            </div>
            <div>
                <Publications/>
            </div>
        </div>)
}
export default Home
