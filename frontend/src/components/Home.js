import React from 'react'
import image from '../images/construccion.png'
import TagPublicacion from './TagsPublicaciones'
import imagen1 from '../images/imagen1.jpg'
import imagen2 from '../images/imagen2.jpg'
import imagen3 from '../images/imagen3.jpg'
import imagen4 from '../images/imagen4.jpg'
import InputPublicacion from './InputPublicacion'
const Home = () => {
  const imagenes = [{ id: 1, ima: imagen1 }, { id: 2, ima: imagen2 }, { id: 3, ima: imagen3 }, { id: 4, ima: imagen4 }]
  return (
        <div style={{ width: '100%' }}>
            <TagPublicacion
                name={'Nicolas Garcia'}
                par={'Buenos dias a todos... Esta es la primera publicacion de esta red social. Es un orgullo'}
                imagen={imagenes}
                likes={83} />
            <h1 aria-setsize={36}>
                Página de inicio
       </h1>
            <img src={image} alt="Logo" />
        </div>)
  <div>
        <InputPublicacion/>
    </div>)
}
export default Home
