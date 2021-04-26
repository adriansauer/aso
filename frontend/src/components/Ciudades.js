import React, { useEffect, useState } from 'react'
import useGetDepartaments from '../api/departamento/useGetDepartament'
import useGetCities from '../api/city/useGetCity'
import useDeleteDepartament from '../api/departamento/useDeleteDepartament'
import useDeleteCity from '../api/city/useDeleteCity'
import DepartamentForm from './modals/DepartamentForm'
import CitForm from './modals/CityForm'
import M from 'materialize-css'
const Ciudades = () => {
  const [cities, setCities] = useState(null)
  const [departaments, setDepartaments] = useState(null)
  const { execute: getCitiesExecute } = useGetCities()
  const { execute: getDepartamentsExecute } = useGetDepartaments()
  const { execute: deleteDepartamentsExecute } = useDeleteDepartament()
  const { execute: deleteCityExecute } = useDeleteCity()
  const [departamentModal, setDepartamentModal] = useState(null)
  const [cityModal, setCityModal] = useState(null)
  useEffect(() => {
    const elem1 = document.getElementById('modal1')
    const departamentModal = M.Modal.init(elem1, {
      inDuration: 300
    })
    setDepartamentModal(departamentModal)
    const elem2 = document.getElementById('modal2')
    const cityModal = M.Modal.init(elem2, {
      inDuration: 300
    })
    setCityModal(cityModal)
    M.AutoInit()
  }, [])
  // Cerrar el modal de agregar usuario
  const closeModal = () => {
    cityModal.close()
    departamentModal.close()
    fetchCities()
    fetchDepartaments()
  }
  useEffect(() => {
    fetchCities()
    fetchDepartaments()
  }, [])
  const fetchDepartaments = () => {
    getDepartamentsExecute()
      .then((res) => {
        setDepartaments(res.data.content)
      })
      .catch((err) => {
        console.log(err)
      })
  }
  const fetchCities = () => {
    getCitiesExecute()
      .then((res) => {
        setCities(res.data.content)
      })
      .catch((err) => {
        console.log(err)
      })
  }
  const deleteDepartament = (id) => {
    deleteDepartamentsExecute(id)
      .then((res) => {
        fetchDepartaments()
      })
      .catch((err) => {
        console.log(err)
      })
  }
  const deleteCity = (id) => {
    deleteCityExecute(id)
      .then((res) => {
        fetchCities()
      })
      .catch((err) => {
        console.log(err)
      })
  }
  return (
    <div className="container" style={{ marginTop: '2%' }}>
      <DepartamentForm close={closeModal} />
      <CitForm close={closeModal} />
      <div className="row">
        <div className="col s6 m6">
          <blockquote style={{ borderColor: '#0C0019' }}>
            Ciudades
            <button
              className="btn-floating waves-effect"
              style={{ marginLeft: '20px', backgroundColor: '#0C0019' }}
              onClick={() => cityModal.open()}
            >
              <i className="material-icons">add</i>
            </button>
          </blockquote>
          <table>
            <thead>
              <tr>
                <th>Ciudad</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {cities
                ? cities.map((city) => {
                  return (
                      <tr key={city.id}>
                        <td>{city.name}</td>
                        <td>
                          <button
                            className="btn-floating btn-small"
                            style={{ backgroundColor: '#0C0019' }}
                            onClick={() => deleteCity(city.id)}
                          >
                            <i className="material-icons">delete</i>
                          </button>
                        </td>
                      </tr>
                  )
                })
                : null}
            </tbody>
          </table>
        </div>
        <div className="col s6 m6">
          <blockquote style={{ borderColor: '#0C0019' }}>
            Departamentos
            <button
              className="btn-floating waves-effect"
              style={{ marginLeft: '20px', backgroundColor: '#0C0019' }}
              onClick={() => departamentModal.open()}
            >
              <i className="material-icons">add</i>
            </button>
          </blockquote>
          <table>
            <thead>
              <tr>
                <th>Departamento</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {departaments
                ? departaments.map((depto) => {
                  return (
                      <tr key={depto.id}>
                        <td>{depto.name}</td>
                        <td>
                          {' '}
                          <button
                            className="btn-floating btn-small"
                            style={{ backgroundColor: '#0C0019' }}
                            onClick={() => deleteDepartament(depto.id)}
                          >
                            <i className="material-icons">delete</i>
                          </button>
                        </td>
                      </tr>
                  )
                })
                : null}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default Ciudades
