import React from 'react'
import AnyChart from 'anychart-react'

const complexSettings = {
  width: '80%',
  height: 200,
  type: 'column',
  data: 'Enero,5\nFebrero,3\nMarzo,6\nAbril,4\nMayo,4\nJunio,4\nJulio,4\nAgosto,4\nSeptiembre,4\nOctubre,9\nNoviembre,4\nDiciembre,4',
  title: 'Incendios'
}
const Graphic = () => {
  return (
    <div className="row" align="center">
      <AnyChart {...complexSettings} />
    </div>
  )
}

export default Graphic
