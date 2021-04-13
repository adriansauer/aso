"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useIsTokenValid = function useIsTokenValid() {
  var execute = function execute(token) {
    return _api["default"].get('/istokenvalid', {
      headers: {
        Authorization: "Bearer ".concat(token)
      }
    });
  };

  return {
    execute: execute
  };
};

var _default = useIsTokenValid;
exports["default"] = _default;