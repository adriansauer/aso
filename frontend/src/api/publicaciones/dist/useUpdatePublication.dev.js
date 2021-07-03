"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useUpdatePublication = function useUpdatePublication() {
  var execute = function execute(id, incidenceCodeId, body, destination) {
    return _api["default"].put("api/publications/".concat(id), {
      body: body,
      destination: destination,
      incidenceCodeId: incidenceCodeId
    });
  };

  return {
    execute: execute
  };
};

var _default = useUpdatePublication;
exports["default"] = _default;