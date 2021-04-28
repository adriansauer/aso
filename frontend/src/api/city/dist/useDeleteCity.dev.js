"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useDeleteCity = function useDeleteCity() {
  var execute = function execute(id) {
    return _api["default"]["delete"]("api/cities/".concat(id));
  };

  return {
    execute: execute
  };
};

var _default = useDeleteCity;
exports["default"] = _default;