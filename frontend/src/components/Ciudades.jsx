import React, { useEffect, useState } from 'react'
import useGetDepartaments from '../api/departamento/useGetDepartament'
import useGetCities from '../api/city/useGetCity'
import useDeleteDepartament from '../api/departamento/useDeleteDepartament'
import useDeleteCity from '../api/city/useDeleteCity'
import DepartamentForm from './modals/DepartamentForm'
import CitForm from './modals/CityForm'
import PreLoader from './PreLoader'
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
  const [isLoading, setIsLoading] = useState(false)
  const [totalPagesCiudades, setTotalPagesCiudades] = useState([])
  const [pagActualCiudades, setPagActualCiudades] = useState(1)
  const [totalPagesDepartamentos, setTotalPagesDepartamentos] = useState([])
  const [pagActualDepartamentos, setPagActualDepartamentos] = useState(1)
  const handleIncrementPageCiudades = () => {
    if (pagActualCiudades < totalPagesCiudades.length) {
      setPagActualCiudades(pagActualCiudades + 1)
    }
  }
  const handleDecrementPageCiudades = () => {
    if (pagActualCiudades > 1) {
      setPagActualCiudades(pagActualCiudades - 1)
    }
  }
  const handleIncrementPageDepartamentos = () => {
    if (pagActualDepartamentos < totalPagesDepartamentos.length) {
      setPagActualDepartamentos(pagActualDepartamentos + 1)
    }
  }
  const handleDecrementPageDepartamentos = () => {
    if (pagActualDepartamentos > 1) {
      setPagActualDepartamentos(pagActualDepartamentos - 1)
    }
  }
  useEffect(() => {
    handleLoadCiudades()
  }, [pagActualCiudades])
  useEffect(() => {
    handleLoadDepartamentos()
  }, [pagActualDepartamentos])
  // Reacargar ciudades
  const handleLoadCiudades = () => {
    setCities(null)
    setIsLoading(true)
    getCitiesExecute(pagActualCiudades)
      .then((res) => {
        setTotalPagesCiudades(
          Array.apply(null, { length: res.data.totalPages }).map(
            Number.call,
            Number
          )
        )
        setCities(res.data.content)
        setIsLoading(false)
      })
      .catch((err) => {
        setIsLoading(false)
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
      })
  }
  // Reacargar departamentos
  const handleLoadDepartamentos = () => {
    setDepartaments(null)
    setIsLoading(true)
    getDepartamentsExecute(pagActualDepartamentos)
      .then((res) => {
        setTotalPagesDepartamentos(
          Array.apply(null, { length: res.data.totalPages }).map(
            Number.call,
            Number
          )
        )
        setDepartaments(res.data.content)
        setIsLoading(false)
      })
      .catch((err) => {
        setIsLoading(false)
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
      })
  }
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
    handleLoadCiudades()
    handleLoadDepartamentos()
  }
  useEffect(() => {
    handleLoadCiudades()
    handleLoadDepartamentos()
  }, [])

  const deleteDepartament = (id) => {
    setIsLoading(true)
    deleteDepartamentsExecute(id)
      .then((res) => {
        handleLoadDepartamentos()
        setIsLoading(false)
      })
      .catch((err) => {
        M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
        setIsLoading(false)
      })
  }
  const deleteCity = (id) => {
    setIsLoading(true)
    deleteCityExecute(id)
      .then((res) => {
        handleLoadCiudades()
        setIsLoading(false)
      })
      .catch((err) => {
        M.toast({ html: err.response === undefined ? 'Hubo un error con la conexión' : err.response.data.description })
        setIsLoading(false)
      })
  }
  return (
    <div className="container" style={{ marginTop: '2%' }}>
      <DepartamentForm close={closeModal} />
      <CitForm close={closeModal} />
      <PreLoader visible={isLoading} />
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
            <div>
        <ul className="pagination">
          <li>
            <a href="#!" onClick={handleDecrementPageCiudades}>
              <i className="material-icons">chevron_left</i>
            </a>
          </li>
          {totalPagesCiudades.length !== 0
            ? totalPagesCiudades.map((t) => (
                <li
                key={t} onClick={() => setPagActualCiudades(t + 1)}
                className={pagActualCiudades === (t + 1) ? 'active' : ''}>
                  <a href="#!">{t + 1}</a>
                </li>
            ))
            : null}

          <li>
            <a href="#!" onClick={handleIncrementPageCiudades}>
              <i className="material-icons">chevron_right</i>
            </a>
          </li>
        </ul>
      </div>
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
            <div>
        <ul className="pagination">
          <li>
            <a href="#!" onClick={handleDecrementPageDepartamentos}>
              <i className="material-icons">chevron_left</i>
            </a>
          </li>
          {totalPagesDepartamentos.length !== 0
            ? totalPagesDepartamentos.map((t) => (
                <li
                key={t} onClick={() => setPagActualDepartamentos(t + 1)}
                className={pagActualDepartamentos === (t + 1) ? 'active' : ''}>
                  <a href="#!">{t + 1}</a>
                </li>
            ))
            : null}

          <li>
            <a href="#!" onClick={handleIncrementPageDepartamentos}>
              <i className="material-icons">chevron_right</i>
            </a>
          </li>
        </ul>
      </div>
          </table>
        </div>
      </div>
    </div>
  )
}

export default Ciudades
