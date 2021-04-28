"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateBrigada = function useCreateBrigada() {
  var execute = function execute(brigada) {
    var address = brigada.address,
        usercode = brigada.usercode,
        name = brigada.name,
        password = brigada.password,
        cityId = brigada.cityId,
        creation = brigada.creation,
        departamentId = brigada.departamentId,
        description = brigada.description,
        phone = brigada.phone,
        repeatPassword = brigada.repeatPassword,
        email = brigada.email;
    return _api["default"].post('api/brigades', {
      address: address,
      usercode: usercode,
      name: name,
      password: password,
      cityId: cityId,
      creation: creation,
      departamentId: departamentId,
      description: description,
      phone: phone,
      repeatPassword: repeatPassword,
      email: email
    });
  };

  return {
    execute: execute
  };
};

var _default = useCreateBrigada;
exports["default"] = _default;