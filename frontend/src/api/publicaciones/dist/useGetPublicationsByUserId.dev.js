"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useGetPublicationsByUserId = function useGetPublicationsByUserId() {
  var execute = function execute(pag, id) {
    return _api["default"].get("api/publications/byuser/".concat(id, "?page=").concat(pag - 1, "&size=5&sort=created_at,desc"));
  };

  return {
    execute: execute
  };
};

var _default = useGetPublicationsByUserId;
exports["default"] = _default;