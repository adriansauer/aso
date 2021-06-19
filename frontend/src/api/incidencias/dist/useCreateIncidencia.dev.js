"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateIncidencia = function useCreateIncidencia() {
  var execute = function execute(incidencia) {
    return _api["default"].post('api/incidence-code', incidencia);
  };

  return {
    execute: execute
  };
};

var _default = useCreateIncidencia;
exports["default"] = _default;