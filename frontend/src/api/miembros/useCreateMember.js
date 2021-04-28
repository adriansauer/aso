import api from '../api'

const useCreateMember = () => {
  const execute = (member) => {
    const {
      address,
      admission,
      birthday,
      brigadeId,
      ci,
      cityId,
      departamentId,
      description,
      email,
      lastname,
      name,
      password,
      phone,
      rankId,
      repeatPassword,
      usercode
    } = member
    return api.post('api/fireman', {
      address,
      admission,
      birthday,
      brigadeId,
      ci,
      cityId,
      departamentId,
      description,
      email,
      lastname,
      name,
      password,
      phone,
      rankId,
      repeatPassword,
      usercode
    })
  }

  return { execute }
}

export default useCreateMember
