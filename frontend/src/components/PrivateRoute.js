import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import PropTypes from 'prop-types'
const PrivateRoute = ({ component: Component, authed, redirect, ...rest }) => {
  /** Si el usuario no esta autorizado se redirecciona a otra pantalla */
  return (
    <Route
      {...rest}
      render={(props) =>
        authed === true
          ? (
          <Component {...props} />
            )
          : (
          <Redirect
            to={{ pathname: redirect, state: { from: props.location } }}
          />
            )
      }
    />
  )
}
PrivateRoute.propTypes = {
  component: PropTypes.elementType,
  authed: PropTypes.bool,
  redirect: PropTypes.string,
  location
}
export default PrivateRoute
