"use strict";Object.defineProperty(exports,"__esModule",{value:!0}),exports.default=void 0;var _publicApi=_interopRequireDefault(require("../publicApi"));function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var useGetPublicsReportes=function(){return{execute:function(e){return _publicApi.default.post("/api/public/reports/all/year",e)}}},_default=useGetPublicsReportes;exports.default=useGetPublicsReportes;