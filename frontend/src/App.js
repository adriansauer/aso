import React from 'react'
import './App.css'
import 'materialize-css/dist/css/materialize.min.css'
import 'materialize-css/dist/js/materialize.min.js'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Home from './components/Home'
import Login from './components/Login'
function App () {
  return (
    <div className="App">
       <Router>
        <>
          <Switch>
          <Route exact path="/" component={Home} />
          <Route exact path="/login" component={Login} />
          </Switch>
        </>
      </Router>
    </div>
  )
}

export default App
