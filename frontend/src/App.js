import React, { useEffect, useState } from 'react'
import './App.css'
/** Materialize */
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
/** Enrutamietno */
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
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
      role: null
    }
  })
  useEffect(() => {
    setIsAutenticate(true)
  }, [])
  return (
    <div className="App">
      <UserContext.Provider value={{ userData, setUserData, isAutenticate }}>
      <Router>
        <Header />
        <>
          <Switch>
            <Route exact path="/login" component={Login} />
            <PrivateRoute
              authed={true}
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
