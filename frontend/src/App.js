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

const App = () => {
  const [isAutenticate, setIsAutenticate] = useState(false)
  const { execute: checkLoggedIn } = useCheckLoggedIn()

  const verifyAut = () => {
    const token = localStorage.getItem('token')
    if (token !== null || token !== undefined) {
      checkLoggedIn(setIsAutenticate)
    }
  }

  useEffect(() => {
    verifyAut()
  }, [])
  return (
    <div className="App">
      <UserContext.Provider value={{ isAutenticate, setIsAutenticate }}>
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
