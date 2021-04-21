"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateCity = function useCreateCity() {
  var execute = function execute(city) {
    var name = city.name;
    return _api["default"].post('api/cities', {
      name: name
    });
  };

  return {
    execute: execute
  };
};

var _default = useCreateCity;
exports["default"] = _default;