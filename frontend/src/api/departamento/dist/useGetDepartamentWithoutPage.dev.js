"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var UseGetDepartamentWithoutPage = function UseGetDepartamentWithoutPage() {
  var execute = function execute(pag) {
    return _api["default"].get("api/departaments?size=99&page=".concat(pag - 1));
  };

  return {
    execute: execute
  };
};

var _default = UseGetDepartamentWithoutPage;
exports["default"] = _default;