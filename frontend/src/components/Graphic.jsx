import React, { useEffect, useState } from 'react'
import AnyChart from 'anychart-react'
import PropTypes from 'prop-types'

const Graphic = (props) => {
  const [report, setReport] = useState(
    {
      width: '80%',
      height: 200,
      type: 'column',
      data: `Enero,${props.report.months.january}\nFebrero,${props.report.months.february}\nMarzo,${props.report.months.april}\nAbril,${props.report.months.may}\nMayo,${props.report.months.june}\nJunio,${props.report.months.july}\nJulio,${props.report.months.august}\nAgosto,${props.report.months.september}\nSeptiembre,${props.report.months.october}\nOctubre,${props.report.months.november}\nNoviembre,${props.report.months.december}\nDiciembre,${props.report.months.december}`,
      title: `${props.report.code}-${props.report.description}`
    }
  )
  useEffect(() => {
    setReport({
      width: '80%',
      height: 200,
      type: 'column',
      data: `Enero,${props.report.months.january}\nFebrero,${props.report.months.february}\nMarzo,${props.report.months.april}\nAbril,${props.report.months.may}\nMayo,${props.report.months.june}\nJunio,${props.report.months.july}\nJulio,${props.report.months.august}\nAgosto,${props.report.months.september}\nSeptiembre,${props.report.months.october}\nOctubre,${props.report.months.november}\nNoviembre,${props.report.months.december}\nDiciembre,${props.report.months.december}`,
      title: `${props.report.code}-${props.report.description}`
    })
  }, [props.report])
  return (
    <div className="row" align="center">
      <AnyChart {...report} />
    </div>
  )
}
Graphic.propTypes = {
  report: PropTypes.object
}
export default Graphic
