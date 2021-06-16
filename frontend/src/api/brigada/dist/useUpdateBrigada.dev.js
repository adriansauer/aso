"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useUpdateBrigada = function useUpdateBrigada() {
  var execute = function execute(brigada) {
    var id = brigada.id,
        name = brigada.name,
        address = brigada.address,
        phone = brigada.phone,
        departamentId = brigada.departamentId,
        cityId = brigada.cityId,
        description = brigada.description,
        email = brigada.email,
        usercode = brigada.usercode,
        image = brigada.image;
    return _api["default"].put('api/brigades/' + id, {
      name: name,
      address: address,
      phone: phone,
      departamentId: departamentId,
      cityId: cityId,
      description: description,
      image: image,
      email: email,
      usercode: usercode,
      creation: new Date()
    });
  };

  return {
    execute: execute
  };
};

var _default = useUpdateBrigada;
exports["default"] = _default;