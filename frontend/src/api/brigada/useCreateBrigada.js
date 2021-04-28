import api from '../api'

const useCreateBrigada = () => {
  const execute = (city) => {
    const {
      name,
      address,
      phone,
      password,
      repeatPassword,
      departamentId,
      cityId,
      description,
      email,
      usercode
    } = city
    return api.post('api/brigades', {
      name,
      address,
      phone,
      password,
      repeatPassword,
      departamentId,
      cityId,
      description,
      email,
      usercode,
      creation: new Date()
    })
  }

  return { execute }
}

export default useCreateBrigada
