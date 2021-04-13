"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetUsers = function useGetUsers() {
  var execute = function execute() {
    _api["default"].get('api/users');
  };

  return {
    execute: execute
  };
};

var _default = useGetUsers;
exports["default"] = _default;