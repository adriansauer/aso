"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetBrigadas = function useGetBrigadas() {
  var execute = function execute() {
    _api["default"].get('api/brigades');
  };

  return {
    execute: execute
  };
};

var _default = useGetBrigadas;
exports["default"] = _default;