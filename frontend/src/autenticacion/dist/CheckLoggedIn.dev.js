"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _react = require("react");

var _useIsTokenValid2 = _interopRequireDefault(require("../api/user/useIsTokenValid"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _slicedToArray(arr, i) { return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest(); }

function _nonIterableRest() { throw new TypeError("Invalid attempt to destructure non-iterable instance"); }

function _iterableToArrayLimit(arr, i) { if (!(Symbol.iterator in Object(arr) || Object.prototype.toString.call(arr) === "[object Arguments]")) { return; } var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"] != null) _i["return"](); } finally { if (_d) throw _e; } } return _arr; }

function _arrayWithHoles(arr) { if (Array.isArray(arr)) return arr; }

var CheckLoggedIn = function CheckLoggedIn() {
  var _useState = (0, _react.useState)(null),
      _useState2 = _slicedToArray(_useState, 2),
      error = _useState2[0],
      setError = _useState2[1];

  var _useState3 = (0, _react.useState)(false),
      _useState4 = _slicedToArray(_useState3, 2),
      loading = _useState4[0],
      setLoading = _useState4[1];

  var _useIsTokenValid = (0, _useIsTokenValid2["default"])(),
      isTokenValidExecute = _useIsTokenValid.execute;

  var execute = function execute(setIsAutenticate, setUserData) {
    setLoading(true);
    var token = localStorage.getItem('token');

    if (token !== 'null' && token !== undefined && token !== null) {
      isTokenValidExecute(token).then(function (res) {
        setUserData({
          username: "".concat(res.data.name, " ").concat(res.data.lastname),
          roles: res.data.roles
        });
        setIsAutenticate(true);
      })["catch"](function (err) {
        setError(err);
        setIsAutenticate(false);
      });
    }

    setLoading(false);
  };

  return {
    execute: execute,
    error: error,
    loading: loading
  };
};

var _default = CheckLoggedIn;
exports["default"] = _default;