import api from '../api'

const useCreateBrigada = () => {
  const execute = (brigada) => {
    const {
      address,
      usercode,
      name,
      password,
      cityId,
      creation,
      departamentId,
      description,
      phone,
      repeatPassword,
      email
    } = brigada
    return api.post('api/brigades', {
      address,
      usercode,
      name,
      password,
      cityId,
      creation,
      departamentId,
      description,
      phone,
      repeatPassword,
      email
    })
  }

  return { execute }
}

export default useCreateBrigada
