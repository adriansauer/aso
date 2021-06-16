import api from '../api'

const useUpdateBrigada = () => {
  const execute = (brigada) => {
    const {
      id,
      name,
      address,
      phone,
      departamentId,
      cityId,
      description,
      email,
      usercode,
      image
    } = brigada
    return api.put('api/brigades/' + id, {
      name,
      address,
      phone,
      departamentId,
      cityId,
      description,
      image,
      email,
      usercode,
      creation: new Date()
    })
  }

  return { execute }
}

export default useUpdateBrigada
