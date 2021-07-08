"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _publicApi = _interopRequireDefault(require("../publicApi"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetPublicsReportes = function useGetPublicsReportes() {
  var execute = function execute(data) {
    return _publicApi["default"].post('/api/public/reports/all/year', data);
  };

  return {
    execute: execute
  };
};

var _default = useGetPublicsReportes;
exports["default"] = _default;