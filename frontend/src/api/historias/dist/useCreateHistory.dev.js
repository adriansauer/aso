"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateHistory = function useCreateHistory() {
  var execute = function execute(brigadaId, history) {
    return _api["default"].put("/api/brigade-history/".concat(brigadaId), history);
  };

  return {
    execute: execute
  };
};

var _default = useCreateHistory;
exports["default"] = _default;