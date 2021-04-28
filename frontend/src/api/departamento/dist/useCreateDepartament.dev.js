"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateDepartament = function useCreateDepartament() {
  var execute = function execute(depto) {
    var name = depto.name;
    return _api["default"].post('api/departaments', {
      name: name
    });
  };

  return {
    execute: execute
  };
};

var _default = useCreateDepartament;
exports["default"] = _default;