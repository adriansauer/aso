"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetHistoryByBrigadeId = function useGetHistoryByBrigadeId() {
  var execute = function execute(brigadeId) {
    return _api["default"].get("/api/brigade-history/bybrigadeid/".concat(brigadeId));
  };

  return {
    execute: execute
  };
};

var _default = useGetHistoryByBrigadeId;
exports["default"] = _default;