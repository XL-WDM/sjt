(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6bf7e1fa"],{4279:function(t,e,a){},6300:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"layout"},[a("Layout",[a("Sider",{ref:"side1",attrs:{"hide-trigger":"",collapsible:"","collapsed-width":78},model:{value:t.isCollapsed,callback:function(e){t.isCollapsed=e},expression:"isCollapsed"}},[a("MenuBar",{attrs:{"is-collapsed":t.isCollapsed}})],1),a("Layout",[a("Header",{staticClass:"layout-header-bar",style:{padding:0}},[a("Row",[a("Col",{attrs:{span:"22"}},[a("h1",{staticClass:"log-name"},[t._v("后台管理平台")])]),a("Col",{attrs:{span:"2"}},[a("Poptip",{attrs:{confirm:"",title:"退出登录"},on:{"on-ok":t.signOut}},[a("Avatar",{staticClass:"user-head",attrs:{src:"https://i.loli.net/2017/08/21/599a521472424.jpg"}})],1)],1)],1)],1),a("Content",{staticClass:"layout-content"},[a("div",{staticClass:"view-lable"},t._l(t.tagBars,function(e,s){return a("div",{key:s,class:["ivu-tag","ivu-tag-"+(t.$route.path===e.path?"success":"default"),"ivu-tag-closable","ivu-tag-checked"]},[a("span",{class:["ivu-tag-text",t.$route.path===e.path?"ivu-tag-color-white":""],on:{click:function(a){return t.linkTag(e.path)}}},[t._v("\n              "+t._s(e.name)+"\n            ")]),a("i",{staticClass:"ivu-icon ivu-icon-ios-close",on:{click:function(a){return t.closeTag(e.path)}}})])}),0),a("div",{staticClass:"view-content"},[a("router-view")],1)])],1)],1)],1)},n=[],o=(a("8e6e"),a("ac6a"),a("456d"),a("bd86")),i=a("2f62"),r=a("a18c");function c(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(t);e&&(s=s.filter(function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable})),a.push.apply(a,s)}return a}function l(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?c(a,!0).forEach(function(e){Object(o["a"])(t,e,a[e])}):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):c(a).forEach(function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))})}return t}var u={created:function(){},data:function(){return{isCollapsed:!1}},computed:l({rotateIcon:function(){return["menu-icon",this.isCollapsed?"rotate-icon":""]},menuitemClasses:function(){return["menu-item",this.isCollapsed?"collapsed-menu":""]}},Object(i["c"])(["tagBars"])),methods:l({collapsedSider:function(){this.$refs.side1.toggleCollapse()},closeTag:function(t){console.log(t),this.removeTagBars(t)},linkTag:function(t){r["a"].push({path:t})},signOut:function(){r["a"].push({path:"/sign"})}},Object(i["b"])({pushTagBars:"pushTagBars",removeTagBars:"removeTagBars"})),watch:{$route:function(t,e){this.pushTagBars({path:t.path,name:t.meta.title})}}},p=u,d=(a("b026"),a("2877")),f=Object(d["a"])(p,s,n,!1,null,"147e2cea",null);e["default"]=f.exports},b026:function(t,e,a){"use strict";var s=a("4279"),n=a.n(s);n.a}}]);
//# sourceMappingURL=chunk-6bf7e1fa.c334e666.js.map