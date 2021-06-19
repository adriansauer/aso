"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreatePublication = function useCreatePublication() {
  var execute = function execute(publication) {
    return _api["default"].post('api/publications', publication);
  };

  return {
    execute: execute
  };
};

var _default = useCreatePublication;
exports["default"] = _default;