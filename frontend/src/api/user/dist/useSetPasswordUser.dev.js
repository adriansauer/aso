"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useSetPassword = function useSetPassword() {
  var execute = function execute(password, id) {
    return _api["default"].put("api/users/pass/".concat(id), password);
  };

  return {
    execute: execute
  };
};

var _default = useSetPassword;
exports["default"] = _default;