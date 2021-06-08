"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetPublications = function useGetPublications() {
  var execute = function execute(pag) {
    return _api["default"].get("api/publications?size=5&sort=desc&page=".concat(pag - 1));
  };

  return {
    execute: execute
  };
};

var _default = useGetPublications;
exports["default"] = _default;