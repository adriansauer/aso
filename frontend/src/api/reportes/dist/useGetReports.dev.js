"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetReportes = function useGetReportes() {
  var execute = function execute(data) {
    return _api["default"].post('/api/reports/year-user', data);
  };

  return {
    execute: execute
  };
};

var _default = useGetReportes;
exports["default"] = _default;