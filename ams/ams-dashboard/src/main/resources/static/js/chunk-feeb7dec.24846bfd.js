(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-feeb7dec"],{5606:function(e,t,n){"use strict";n.d(t,"d",(function(){return a})),n.d(t,"c",(function(){return o})),n.d(t,"b",(function(){return s})),n.d(t,"a",(function(){return i})),n.d(t,"g",(function(){return u})),n.d(t,"f",(function(){return l})),n.d(t,"e",(function(){return p}));var c=n("5530"),r=(n("b0c0"),n("b32d"));function a(){return r["a"].get("ams/v1/catalog/metastore/types")}function o(e){return r["a"].get("ams/v1/catalogs/".concat(e))}function s(e){return r["a"].delete("ams/v1/catalogs/".concat(e))}function i(e){return r["a"].get("ams/v1/catalogs/".concat(e,"/delete/check"))}function u(e){var t=e.isCreate,n=e.name;return delete e.isCreate,t?r["a"].post("ams/v1/catalogs",Object(c["a"])({},e)):r["a"].put("ams/v1/catalogs/".concat(n),Object(c["a"])({},e))}function l(){return r["a"].get("ams/v1/settings/system")}function p(){return r["a"].get("ams/v1/settings/containers")}},"61c2":function(e,t,n){"use strict";n.r(t);var c=n("1da1"),r=(n("96cf"),n("d3b7"),n("3ca3"),n("ddb0"),n("b0c0"),n("159b"),n("b64b"),n("7a23")),a=n("47e2"),o=n("5606"),s={class:"setting-wrap"},i={class:"system-setting"},u={class:"g-mb-12"},l={class:"container-setting"},p={class:"g-mb-12"},b={class:"content"},m={class:"item"},d={class:"left"},j={class:"right"},O={class:"item"},f={class:"left"},g={class:"right"},y={class:"g-mb-12 g-mt-12"},v={class:"g-mb-12 g-mt-12"},h=Object(r["defineComponent"])({setup:function(e){var t=Object(a["b"])(),n=t.t,h=Object(r["ref"])(!1),k=Object(r["reactive"])([]),w=Object(r["reactive"])([{name:"",type:"",properties:{},propertiesArray:[],optimizeGroup:[]}]),E=Object(r["reactive"])([{title:n("name"),dataIndex:"name",width:340,ellipsis:!0},{title:n("propertiesMemory",{type:"taskmanager"}),dataIndex:"tmMemory",width:"50%",ellipsis:!0},{title:n("propertiesMemory",{type:"jobmanager"}),dataIndex:"jmMemory",width:"50%",ellipsis:!0}]),N=Object(r["reactive"])([{title:n("key"),dataIndex:"key",width:340,ellipsis:!0},{title:n("value"),dataIndex:"value"}]),V=Object(r["ref"])([]);function x(){return B.apply(this,arguments)}function B(){return B=Object(c["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,Object(o["f"])();case 2:if(t=e.sent,t){e.next=5;break}return e.abrupt("return");case 5:Object.keys(t).forEach((function(e){k.push({key:e,value:t[e]})}));case 6:case"end":return e.stop()}}),e)}))),B.apply(this,arguments)}function C(){return S.apply(this,arguments)}function S(){return S=Object(c["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,Object(o["e"])();case 2:t=e.sent,V.value=[],(t||[]).forEach((function(e,t){e.propertiesArray=[],V.value.push(e.name),w.push(e),Object.keys(e.properties||{}).forEach((function(n){w[t].propertiesArray.push({key:n,value:e.properties[n]})}))}));case 5:case"end":return e.stop()}}),e)}))),S.apply(this,arguments)}return Object(r["onMounted"])(Object(c["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,h.value=!0,e.next=4,Promise.all([x(),C()]);case 4:return e.prev=4,h.value=!1,e.finish(4);case 7:case"end":return e.stop()}}),e,null,[[0,,4,7]])})))),function(e,t){var n=Object(r["resolveComponent"])("a-table"),c=Object(r["resolveComponent"])("a-collapse-panel"),a=Object(r["resolveComponent"])("a-collapse"),o=Object(r["resolveComponent"])("u-loading");return Object(r["openBlock"])(),Object(r["createElementBlock"])(r["Fragment"],null,[Object(r["createElementVNode"])("div",s,[Object(r["createElementVNode"])("div",i,[Object(r["createElementVNode"])("h1",u,Object(r["toDisplayString"])(e.$t("systemSetting")),1),Object(r["unref"])(k).length?(Object(r["openBlock"])(),Object(r["createBlock"])(n,{key:0,rowKey:"key",columns:Object(r["unref"])(N),"data-source":Object(r["unref"])(k),pagination:!1},null,8,["columns","data-source"])):Object(r["createCommentVNode"])("",!0)]),Object(r["createElementVNode"])("div",l,[Object(r["createElementVNode"])("h1",p,Object(r["toDisplayString"])(e.$t("containerSetting")),1),Object(r["createVNode"])(a,{activeKey:V.value,"onUpdate:activeKey":t[0]||(t[0]=function(e){return V.value=e})},{default:Object(r["withCtx"])((function(){return[(Object(r["openBlock"])(!0),Object(r["createElementBlock"])(r["Fragment"],null,Object(r["renderList"])(Object(r["unref"])(w),(function(t){return Object(r["openBlock"])(),Object(r["createBlock"])(c,{key:t.name,header:t.name},{default:Object(r["withCtx"])((function(){return[Object(r["createElementVNode"])("ul",b,[Object(r["createElementVNode"])("li",m,[Object(r["createElementVNode"])("h3",d,Object(r["toDisplayString"])(e.$t("name")),1),Object(r["createElementVNode"])("span",j,Object(r["toDisplayString"])(t.name),1)]),Object(r["createElementVNode"])("li",O,[Object(r["createElementVNode"])("h3",f,Object(r["toDisplayString"])(e.$t("type")),1),Object(r["createElementVNode"])("span",g,Object(r["toDisplayString"])(t.type),1)])]),Object(r["createElementVNode"])("h3",y,Object(r["toDisplayString"])(e.$t("properties")),1),t.propertiesArray.length?(Object(r["openBlock"])(),Object(r["createBlock"])(n,{key:0,rowKey:"key",columns:Object(r["unref"])(N),"data-source":t.propertiesArray,pagination:!1},null,8,["columns","data-source"])):Object(r["createCommentVNode"])("",!0),Object(r["createElementVNode"])("h3",v,Object(r["toDisplayString"])(e.$t("optimzeGroup")),1),Object(r["createVNode"])(n,{rowKey:"name",columns:Object(r["unref"])(E),"data-source":t.optimizeGroup,pagination:!1},null,8,["columns","data-source"])]})),_:2},1032,["header"])})),128))]})),_:1},8,["activeKey"])])]),h.value?(Object(r["openBlock"])(),Object(r["createBlock"])(o,{key:0})):Object(r["createCommentVNode"])("",!0)],64)}}}),k=(n("fd7a"),n("6b0d")),w=n.n(k);const E=w()(h,[["__scopeId","data-v-00a2d8cb"]]);t["default"]=E},b319:function(e,t,n){},fd7a:function(e,t,n){"use strict";n("b319")}}]);