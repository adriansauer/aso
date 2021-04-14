"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateUser = function useCreateUser() {
  var execute = function execute(user) {
    var email = user.email,
        lastname = user.lastname,
        name = user.name,
        password = user.password,
        roles = user.roles,
        usercode = user.usercode;
    return _api["default"].post('api/users', {
      email: email,
      lastname: lastname,
      name: name,
      password: password,
      roles: roles,
      usercode: usercode
    });
  };

  return {
    execute: execute
  };
};

var _default = useCreateUser;
exports["default"] = _default;