"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var useCreateFile = function useCreateFile() {
  var execute = function execute(f) {
    var file = f.file,
        name = f.name;
    return _api["default"].post('api/files/', {
      file: file,
      name: name
    });
  };

  return {
    execute: execute
  };
};

var _default = useCreateFile;
exports["default"] = _default;