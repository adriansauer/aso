import React from 'react'
import './App.css'
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Home from './components/Home'
import Login from './components/Login'
import Header from './components/Header'
import PrivateRoute from './components/PrivateRoute'
const App = () => {
  return (
    <div className="App">
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
    </div>
  )
}

export default App
