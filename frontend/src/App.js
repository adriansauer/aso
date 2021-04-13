import React, { useEffect, useState } from 'react'
import './App.css'
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

const App = () => {
  const [isAutenticate, setIsAutenticate] = useState(false)
  const [userData, setUserData] = useState({
    token: null,
    user: {
      displayName: null,
      id: null,
      roles: null
    }
  })

  /** Antes de iniciar la pagina se verifica la autenticacion */
  useEffect(() => {
    /** Si hay token y roles esta logueado */
    if (userData.token !== null & userData.user.roles !== null) {
      setIsAutenticate(true)
      /** Si hay token pero no role se verifica el token */
    } else if (userData.token !== null & userData.user.roles === null) {
      /** Aqui se debe volver a verificar la cuenta */
      /** Si no hay token tiene que loguearse de nuevo */
    } else if (userData.token === null) {
      setIsAutenticate(false)
    }
  }, [userData])
  return (
    <div className="App">
      <UserContext.Provider value={{ userData, setUserData, isAutenticate }}>
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
