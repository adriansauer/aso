import api from '../api'

const useUpdateMember = () => {
  const execute = (member) => {
    const {
      id,
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
      phone,
      rankId,
      usercode
    } = member
    return api.put('api/fireman/' + id, {
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
      phone,
      rankId,
      usercode
    })
  }

  return { execute }
}

export default useUpdateMember
