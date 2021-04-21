"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateBrigada = function useCreateBrigada() {
  var execute = function execute(city) {
    var name = city.name,
        address = city.address,
        phone = city.phone,
        password = city.password,
        repeatPassword = city.repeatPassword,
        departamentId = city.departamentId,
        cityId = city.cityId,
        description = city.description,
        email = city.email,
        usercode = city.usercode;
    return _api["default"].post('api/brigades', {
      name: name,
      address: address,
      phone: phone,
      password: password,
      repeatPassword: repeatPassword,
      departamentId: departamentId,
      cityId: cityId,
      description: description,
      email: email,
      usercode: usercode,
      creation: new Date()
    });
  };

  return {
    execute: execute
  };
};

var _default = useCreateBrigada;
exports["default"] = _default;