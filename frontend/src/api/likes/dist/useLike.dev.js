"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useLike = function useLike() {
  var execute = function execute(publicationId) {
    return _api["default"].post('api/likes', {
      publicationId: publicationId
    });
  };

  return {
    execute: execute
  };
};

var _default = useLike;
exports["default"] = _default;