"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useSearchIncident = function useSearchIncident() {
  var execute = function execute(text) {
    return _api["default"].get("/api/incidence-code/description-code/".concat(text));
  };

  return {
    execute: execute
  };
};

var _default = useSearchIncident;
exports["default"] = _default;