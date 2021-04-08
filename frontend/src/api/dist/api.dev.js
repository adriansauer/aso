"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _axios = _interopRequireDefault(require("axios"));

var _baseURL = _interopRequireDefault(require("./baseURL"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

var defaultOptions = {
  baseURL: _baseURL["default"],
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
};

var api = _axios["default"].create(defaultOptions);

var _default = api;
exports["default"] = _default;