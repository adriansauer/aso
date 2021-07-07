import React, { useEffect, useState } from 'react'
import M from 'materialize-css'
import usePublicsGetReports from '../api/reportes/useGetPublicsReports'
import Graphic from './Graphic'
const Dashboard = () => {
  const { execute: getPublicsReportsExecute } = usePublicsGetReports()
  const [year, setYear] = useState(new Date().getFullYear())
  const [reports, setReports] = useState(null)
  useEffect(() => {
    M.AutoInit()
  }, [])
  const fetchReports = () => {
    const data = {
      year: year
    }
    getPublicsReportsExecute(data)
      .then((res) => {
        console.log(res.data)

        if (res.data.length === 0) {
          M.toast({ html: 'No hay datos que mostrar' })
          setReports(null)
        } else {
          setReports(res.data)
        }
      })
      .catch((err) => {
        M.toast({
          html:
            err.response === undefined
              ? 'Hubo un error con la conexión'
              : err.response.data.description
        })
      })
  }
  useEffect(() => {
    fetchReports()
  }, [year])
  return (
    <div className="container">
      <br />
      <div className="row">
        <div className="input-field col m4 s12 right">
          <select
            defaultValue={new Date().getFullYear()}
            onChange={(e) => {
              setYear(e.target.options[e.target.options.selectedIndex].value)
            }}
          >
            <option value={new Date().getFullYear()}>
              {new Date().getFullYear()}
            </option>
            <option value={new Date().getFullYear() - 1}>
              {new Date().getFullYear() - 1}
            </option>
            <option value={new Date().getFullYear() - 2}>
              {new Date().getFullYear() - 2}
            </option>
            <option value={new Date().getFullYear() - 3}>
              {new Date().getFullYear() - 3}
            </option>
            <option value={new Date().getFullYear() - 4}>
              {new Date().getFullYear() - 4}
            </option>
            <option value={new Date().getFullYear() - 5}>
              {new Date().getFullYear() - 5}
            </option>
            <option value={new Date().getFullYear() - 6}>
              {new Date().getFullYear() - 6}
            </option>
            <option value={new Date().getFullYear() - 7}>
              {new Date().getFullYear() - 7}
            </option>
            <option value={new Date().getFullYear() - 8}>
              {new Date().getFullYear() - 8}
            </option>
            <option value={new Date().getFullYear() - 9}>
              {new Date().getFullYear() - 9}
            </option>
            <option value={new Date().getFullYear() - 10}>
              {new Date().getFullYear() - 10}
            </option>
            <option value={new Date().getFullYear() - 11}>
              {new Date().getFullYear() - 11}
            </option>
            <option value={new Date().getFullYear() - 12}>
              {new Date().getFullYear() - 12}
            </option>
            <option value={new Date().getFullYear() - 13}>
              {new Date().getFullYear() - 13}
            </option>
            <option value={new Date().getFullYear() - 14}>
              {new Date().getFullYear() - 14}
            </option>
          </select>

          <label>Año</label>
        </div>
        {reports !== null && reports !== []
          ? (
              reports.map((r) => <Graphic key={r.code} report={r} />)
            )
          : null}
      </div>
    </div>
  )
}

export default Dashboard
