"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetRoles = function useGetRoles() {
  var execute = function execute() {
    return _api["default"].get('api/roles');
  };

  return {
    execute: execute
  };
};

var _default = useGetRoles;
exports["default"] = _default;