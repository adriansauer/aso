"use strict";Object.defineProperty(exports,"__esModule",{value:!0}),exports.default=void 0;var _api=_interopRequireDefault(require("../api"));function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var UseGetDepartament=function(){return{execute:function(e){return _api.default.get("api/departaments?size=6&page=".concat(e-1))}}},_default=UseGetDepartament;exports.default=UseGetDepartament;