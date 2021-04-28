"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useDeleteDepartament = function useDeleteDepartament() {
  var execute = function execute(id) {
    return _api["default"]["delete"]("api/departaments/".concat(id));
  };

  return {
    execute: execute
  };
};

var _default = useDeleteDepartament;
exports["default"] = _default;