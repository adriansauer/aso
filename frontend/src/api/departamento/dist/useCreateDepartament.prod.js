"use strict";Object.defineProperty(exports,"__esModule",{value:!0}),exports.default=void 0;var _api=_interopRequireDefault(require("../api"));function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var useCreateDepartament=function(){return{execute:function(e){var t=e.name;return _api.default.post("api/departaments",{name:t})}}},_default=useCreateDepartament;exports.default=useCreateDepartament;