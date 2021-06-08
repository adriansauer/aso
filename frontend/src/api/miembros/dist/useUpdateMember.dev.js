"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useUpdateMember = function useUpdateMember() {
  var execute = function execute(member) {
    var id = member.id,
        address = member.address,
        admission = member.admission,
        birthday = member.birthday,
        brigadeId = member.brigadeId,
        ci = member.ci,
        cityId = member.cityId,
        departamentId = member.departamentId,
        description = member.description,
        email = member.email,
        lastname = member.lastname,
        name = member.name,
        phone = member.phone,
        rankId = member.rankId,
        usercode = member.usercode,
        image = member.image;
    return _api["default"].put('api/fireman/' + id, {
      address: address,
      admission: admission,
      birthday: birthday,
      brigadeId: brigadeId,
      ci: ci,
      cityId: cityId,
      departamentId: departamentId,
      description: description,
      email: email,
      lastname: lastname,
      name: name,
      phone: phone,
      rankId: rankId,
      usercode: usercode,
      image: image
    });
  };

  return {
    execute: execute
  };
};

var _default = useUpdateMember;
exports["default"] = _default;