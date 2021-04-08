"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _react = require("react");

var _api = _interopRequireDefault(require("../api"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _slicedToArray(arr, i) { return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest(); }

function _nonIterableRest() { throw new TypeError("Invalid attempt to destructure non-iterable instance"); }

function _iterableToArrayLimit(arr, i) { if (!(Symbol.iterator in Object(arr) || Object.prototype.toString.call(arr) === "[object Arguments]")) { return; } var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"] != null) _i["return"](); } finally { if (_d) throw _e; } } return _arr; }

function _arrayWithHoles(arr) { if (Array.isArray(arr)) return arr; }

var useGetUsers = function useGetUsers() {
  var _useState = (0, _react.useState)(null),
      _useState2 = _slicedToArray(_useState, 2),
      data = _useState2[0],
      setData = _useState2[1];

  var _useState3 = (0, _react.useState)(false),
      _useState4 = _slicedToArray(_useState3, 2),
      loading = _useState4[0],
      setLoading = _useState4[1];

  var _useState5 = (0, _react.useState)(null),
      _useState6 = _slicedToArray(_useState5, 2),
      error = _useState6[0],
      setError = _useState6[1];

  var execute = function execute() {
    var page = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : 0;
    var size = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 5;
    if (size < 1) size = 5;
    setError(null);
    setLoading(true);

    _api["default"].get("/users?page=".concat(page, "&size=").concat(size)).then(function (response) {
      setData(response.data.content);
      setError(null);
      setLoading(false);
    })["catch"](function (_error) {
      setData(null);
      setError('No se puede obtener la lista de usuarios');
      setLoading(false);
    });
  };

  return {
    data: data,
    loading: loading,
    error: error,
    execute: execute
  };
};

var _default = useGetUsers;
exports["default"] = _default;