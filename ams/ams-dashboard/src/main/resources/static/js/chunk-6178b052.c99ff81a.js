(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6178b052"],{4394:function(e,t,n){},5606:function(e,t,n){"use strict";n.d(t,"d",(function(){return r})),n.d(t,"c",(function(){return o})),n.d(t,"b",(function(){return i})),n.d(t,"a",(function(){return s})),n.d(t,"g",(function(){return u})),n.d(t,"f",(function(){return l})),n.d(t,"e",(function(){return b}));var c=n("5530"),a=(n("b0c0"),n("b32d"));function r(){return a["a"].get("ams/v1/catalog/metastore/types")}function o(e){return a["a"].get("ams/v1/catalogs/".concat(e))}function i(e){return a["a"].delete("ams/v1/catalogs/".concat(e))}function s(e){return a["a"].get("ams/v1/catalogs/".concat(e,"/delete/check"))}function u(e){var t=e.isCreate,n=e.name;return delete e.isCreate,t?a["a"].post("ams/v1/catalogs",Object(c["a"])({},e)):a["a"].put("ams/v1/catalogs/".concat(n),Object(c["a"])({},e))}function l(){return a["a"].get("ams/v1/settings/system")}function b(){return a["a"].get("ams/v1/settings/containers")}},"61c2":function(e,t,n){"use strict";n.r(t);var c=n("5530"),a=n("1da1"),r=(n("96cf"),n("ac1f"),n("5319"),n("b0c0"),n("d3b7"),n("159b"),n("b64b"),n("7a23")),o=n("6c02"),i=n("47e2"),s=n("5606"),u={class:"setting-wrap"},l={class:"system-setting"},b={class:"container-setting"},p={class:"content"},m={class:"item"},j={class:"left"},d={class:"right"},O={class:"item"},f={class:"left"},y={class:"right"},v={class:"g-mb-12 g-mt-12"},g={class:"g-mb-12 g-mt-12"},k=Object(r["defineComponent"])({setup:function(e){var t=Object(i["b"])(),n=t.t,k=Object(o["e"])(),h=Object(o["d"])(),w=Object(r["ref"])(!1),C=Object(r["reactive"])([]),N=Object(r["reactive"])([]),V=Object(r["reactive"])([{title:n("name"),dataIndex:"name",width:340,ellipsis:!0},{title:n("propertiesMemory",{type:"taskmanager"}),dataIndex:"tmMemory",width:"50%",ellipsis:!0},{title:n("propertiesMemory",{type:"jobmanager"}),dataIndex:"jmMemory",width:"50%",ellipsis:!0}]),E=Object(r["reactive"])([{title:n("key"),dataIndex:"key",width:340,ellipsis:!0},{title:n("value"),dataIndex:"value"}]),B=Object(r["ref"])([]),x={system:{title:n("systemSetting"),key:"system"},container:{title:n("containerSetting"),key:"container"}},K=Object(r["ref"])(x.system.key);function S(){return _.apply(this,arguments)}function _(){return _=Object(a["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,w.value=!0,e.next=4,Object(s["f"])();case 4:if(t=e.sent,t){e.next=7;break}return e.abrupt("return");case 7:C.length=0,Object.keys(t).forEach((function(e){C.push({key:e,value:t[e]})}));case 9:return e.prev=9,w.value=!1,e.finish(9);case 12:case"end":return e.stop()}}),e,null,[[0,,9,12]])}))),_.apply(this,arguments)}function D(){return I.apply(this,arguments)}function I(){return I=Object(a["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,w.value=!0,e.next=4,Object(s["e"])();case 4:t=e.sent,B.value=[],(t||[]).forEach((function(e,t){e.propertiesArray=[],B.value.push(e.name),N.push(e),Object.keys(e.properties||{}).forEach((function(n){N[t].propertiesArray.push({key:n,value:e.properties[n]})}))}));case 7:return e.prev=7,w.value=!1,e.finish(7);case 10:case"end":return e.stop()}}),e,null,[[0,,7,10]])}))),I.apply(this,arguments)}function A(e){var t=Object(c["a"])({},h.query);t.tab=e,k.replace({query:Object(c["a"])({},t)}),M()}function M(){K.value===x.system.key?S():D()}return Object(r["watch"])((function(){return h}),(function(e){var t;K.value=(null===(t=e.query)||void 0===t?void 0:t.tab)||x.system.key,A(K.value)}),{immediate:!0}),function(e,t){var n=Object(r["resolveComponent"])("a-table"),c=Object(r["resolveComponent"])("a-tab-pane"),a=Object(r["resolveComponent"])("a-collapse-panel"),o=Object(r["resolveComponent"])("a-collapse"),i=Object(r["resolveComponent"])("a-tabs"),s=Object(r["resolveComponent"])("u-loading");return Object(r["openBlock"])(),Object(r["createElementBlock"])(r["Fragment"],null,[Object(r["createElementVNode"])("div",u,[Object(r["createVNode"])(i,{activeKey:K.value,"onUpdate:activeKey":t[1]||(t[1]=function(e){return K.value=e}),onChange:A},{default:Object(r["withCtx"])((function(){return[(Object(r["openBlock"])(),Object(r["createBlock"])(c,{key:x["system"].key,tab:x["system"].title},{default:Object(r["withCtx"])((function(){return[Object(r["createElementVNode"])("div",l,[Object(r["unref"])(C).length?(Object(r["openBlock"])(),Object(r["createBlock"])(n,{key:0,rowKey:"key",columns:Object(r["unref"])(E),"data-source":Object(r["unref"])(C),pagination:!1},null,8,["columns","data-source"])):Object(r["createCommentVNode"])("",!0)])]})),_:1},8,["tab"])),(Object(r["openBlock"])(),Object(r["createBlock"])(c,{key:x["container"].key,tab:x["container"].title},{default:Object(r["withCtx"])((function(){return[Object(r["createElementVNode"])("div",b,[Object(r["createVNode"])(o,{activeKey:B.value,"onUpdate:activeKey":t[0]||(t[0]=function(e){return B.value=e})},{default:Object(r["withCtx"])((function(){return[(Object(r["openBlock"])(!0),Object(r["createElementBlock"])(r["Fragment"],null,Object(r["renderList"])(Object(r["unref"])(N),(function(t){return Object(r["openBlock"])(),Object(r["createBlock"])(a,{key:t.name,header:t.name},{default:Object(r["withCtx"])((function(){return[Object(r["createElementVNode"])("ul",p,[Object(r["createElementVNode"])("li",m,[Object(r["createElementVNode"])("h3",j,Object(r["toDisplayString"])(e.$t("name")),1),Object(r["createElementVNode"])("span",d,Object(r["toDisplayString"])(t.name),1)]),Object(r["createElementVNode"])("li",O,[Object(r["createElementVNode"])("h3",f,Object(r["toDisplayString"])(e.$t("type")),1),Object(r["createElementVNode"])("span",y,Object(r["toDisplayString"])(t.type),1)])]),Object(r["createElementVNode"])("h3",v,Object(r["toDisplayString"])(e.$t("properties")),1),t.propertiesArray.length?(Object(r["openBlock"])(),Object(r["createBlock"])(n,{key:0,rowKey:"key",columns:Object(r["unref"])(E),"data-source":t.propertiesArray,pagination:!1},null,8,["columns","data-source"])):Object(r["createCommentVNode"])("",!0),Object(r["createElementVNode"])("h3",g,Object(r["toDisplayString"])(e.$t("optimzeGroup")),1),Object(r["createVNode"])(n,{rowKey:"name",columns:Object(r["unref"])(V),"data-source":t.optimizeGroup,pagination:!1},null,8,["columns","data-source"])]})),_:2},1032,["header"])})),128))]})),_:1},8,["activeKey"])])]})),_:1},8,["tab"]))]})),_:1},8,["activeKey"])]),w.value?(Object(r["openBlock"])(),Object(r["createBlock"])(s,{key:0})):Object(r["createCommentVNode"])("",!0)],64)}}}),h=(n("8378"),n("6b0d")),w=n.n(h);const C=w()(k,[["__scopeId","data-v-b0016d42"]]);t["default"]=C},8378:function(e,t,n){"use strict";n("4394")}}]);