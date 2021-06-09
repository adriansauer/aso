"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useDeleteLike = function useDeleteLike() {
  var execute = function execute(publicationId) {
    return _api["default"]["delete"]("api/likes/".concat(publicationId));
  };

  return {
    execute: execute
  };
};

var _default = useDeleteLike;
exports["default"] = _default;