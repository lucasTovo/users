(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{375:function(t,e,n){"use strict";n.r(e);n(19),n(55),n(59),n(343);var o={mounted:function(){var t=new XMLHttpRequest,e=window.location.href,n="hash="+new URL(e).searchParams.get("hash");t.open("POST","http://localhost:9080/orion-users-service/users/api/v1/confirmHash/?hash=",!0),t.setRequestHeader("Content-type","application/x-www-form-urlencoded"),t.onreadystatechange=function(){4===t.readyState&&200===t.status&&alert(t.responseText)},t.send(n)},methods:{exit:function(){this.$router.push({name:"index"})}}},h=n(36),component=Object(h.a)(o,(function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("button",{on:{click:this.exit}},[this._v("Return to homepage")]),this._v(" "),e("input",{attrs:{id:"hs",name:"hash",type:"hash",hidden:""}}),this._v(" "),e("span",{attrs:{id:"message"}})])}),[],!1,null,null,null);e.default=component.exports;installComponents(component,{Button:n(152).default})}}]);