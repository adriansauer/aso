import React, { useEffect, useState } from 'react'
import './App.css'
/** autenticacion */
import useCheckLoggedIn from './autenticacion/CheckLoggedIn'
/** Materialize */
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
/** Enrutamietno */
import { BrowserRouter as Router, Switch } from 'react-router-dom'
import PrivateRoute from './components/PrivateRoute'
/** Context de usuario */
import UserContext from './context/userContext'
/** Componentes visuales */
import Home from './components/Home'
import Login from './components/Login'
import Header from './components/Header'
import BrigadaPerfil from './components/BrigadaPerfil'
import UsuarioList from './components/UsuarioList'
import UsuarioPerfil from './components/UsuarioPerfil'
import BrigadaList from './components/BigradaList'
import Ciudades from './components/Ciudades'
import Incidencias from './components/ListIncidencias'
const App = () => {
  const [isAutenticate, setIsAutenticate] = useState(false)
  const { execute: checkLoggedIn } = useCheckLoggedIn()
  const [reload, setReload] = useState(false)
  const [userData, setUserData] = useState({
    username: '',
    roles: null,
    id: null,
    perfilId: null
  })
  const verifyAut = async () => {
    const token = localStorage.getItem('token')
    if (token !== null || token !== undefined) {
      await checkLoggedIn(setIsAutenticate, setUserData)
    }
  }
  const handleReload = () => {
    setReload(!reload)
  }
  useEffect(() => {
    verifyAut()
  }, [])
  return (
    <div className="App">
      <UserContext.Provider
        value={{ isAutenticate, setIsAutenticate, userData, setUserData, handleReload, reload }}
      >
        <Router>
          <Header />
          <>
            <Switch>
              <PrivateRoute
                authed={!isAutenticate}
                redirect="/"
                path="/login"
                component={Login}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/cities"
                component={Ciudades}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/incidencias"
                component={Incidencias}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/perfil"
                component={UsuarioPerfil}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/usuarios"
                component={UsuarioList}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/brigada"
                component={BrigadaPerfil}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/brigadas"
                component={BrigadaList}
              />
              <PrivateRoute
                authed={isAutenticate}
                redirect="/login"
                path="/"
                component={Home}
              />
            </Switch>
          </>
        </Router>
      </UserContext.Provider>
    </div>
  )
}

export default App
