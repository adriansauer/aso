import React, { useEffect, useState } from 'react'
import M from 'materialize-css'
const Dashboard = () => {
  const [year, setYear] = useState(new Date().getFullYear())
  useEffect(() => {
    M.AutoInit()
  }, [])
  useEffect(() => {
    console.log(year)
  }, [year])
  return (
        <div className='container'>
          <br/>
           <div className='row'>
           <div className="input-field col m4 s12 right">
                  <select
                    defaultValue={new Date().getFullYear()}
                    onChange={(e) => {
                      setYear(
                        e.target.options[e.target.options.selectedIndex].value
                      )
                    }}
                  >
                    <option value={new Date().getFullYear()}>{new Date().getFullYear()}</option>
                    <option value={new Date().getFullYear() - 1}>{new Date().getFullYear() - 1}</option>
                    <option value={new Date().getFullYear() - 2}>{new Date().getFullYear() - 2}</option>
                    <option value={new Date().getFullYear() - 3}>{new Date().getFullYear() - 3}</option>
                    <option value={new Date().getFullYear() - 4}>{new Date().getFullYear() - 4}</option>
                    <option value={new Date().getFullYear() - 5}>{new Date().getFullYear() - 5}</option>
                    <option value={new Date().getFullYear() - 6}>{new Date().getFullYear() - 6}</option>
                    <option value={new Date().getFullYear() - 7}>{new Date().getFullYear() - 7}</option>
                    <option value={new Date().getFullYear() - 8}>{new Date().getFullYear() - 8}</option>
                    <option value={new Date().getFullYear() - 9}>{new Date().getFullYear() - 9}</option>
                    <option value={new Date().getFullYear() - 10}>{new Date().getFullYear() - 10}</option>
                    <option value={new Date().getFullYear() - 11}>{new Date().getFullYear() - 11}</option>
                    <option value={new Date().getFullYear() - 12}>{new Date().getFullYear() - 12}</option>
                    <option value={new Date().getFullYear() - 13}>{new Date().getFullYear() - 13}</option>
                    <option value={new Date().getFullYear() - 14}>{new Date().getFullYear() - 14}</option>

                  </select>

                  <label>Año</label>
                </div>
           </div>

        </div>
  )
}

export default Dashboard
