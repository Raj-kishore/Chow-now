(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{"f+ep":function(n,t,e){"use strict";e.r(t);var l,i=e("CcnG"),u=e("mrSG"),o=e("K9Ia"),r=e("pugT"),s=function(n){function t(t,e){var l=n.call(this,t,e)||this;return l.scheduler=t,l.work=e,l}return u.d(t,n),t.prototype.schedule=function(t,e){return void 0===e&&(e=0),e>0?n.prototype.schedule.call(this,t,e):(this.delay=e,this.state=t,this.scheduler.flush(this),this)},t.prototype.execute=function(t,e){return e>0||this.closed?n.prototype.execute.call(this,t,e):this._execute(t,e)},t.prototype.requestAsyncId=function(t,e,l){return void 0===l&&(l=0),null!==l&&l>0||null===l&&this.delay>0?n.prototype.requestAsyncId.call(this,t,e,l):t.flush(this)},t}(function(n){function t(t,e){var l=n.call(this,t,e)||this;return l.scheduler=t,l.work=e,l.pending=!1,l}return u.d(t,n),t.prototype.schedule=function(n,t){if(void 0===t&&(t=0),this.closed)return this;this.state=n;var e=this.id,l=this.scheduler;return null!=e&&(this.id=this.recycleAsyncId(l,e,t)),this.pending=!0,this.delay=t,this.id=this.id||this.requestAsyncId(l,this.id,t),this},t.prototype.requestAsyncId=function(n,t,e){return void 0===e&&(e=0),setInterval(n.flush.bind(n,this),e)},t.prototype.recycleAsyncId=function(n,t,e){if(void 0===e&&(e=0),null!==e&&this.delay===e&&!1===this.pending)return t;clearInterval(t)},t.prototype.execute=function(n,t){if(this.closed)return new Error("executing a cancelled action");this.pending=!1;var e=this._execute(n,t);if(e)return e;!1===this.pending&&null!=this.id&&(this.id=this.recycleAsyncId(this.scheduler,this.id,null))},t.prototype._execute=function(n,t){var e=!1,l=void 0;try{this.work(n)}catch(i){e=!0,l=!!i&&i||new Error(i)}if(e)return this.unsubscribe(),l},t.prototype._unsubscribe=function(){var n=this.id,t=this.scheduler,e=t.actions,l=e.indexOf(this);this.work=null,this.state=null,this.pending=!1,this.scheduler=null,-1!==l&&e.splice(l,1),null!=n&&(this.id=this.recycleAsyncId(t,n,null)),this.delay=null},t}(function(n){function t(t,e){return n.call(this)||this}return u.d(t,n),t.prototype.schedule=function(n,t){return void 0===t&&(t=0),this},t}(r.a))),c=function(){function n(t,e){void 0===e&&(e=n.now),this.SchedulerAction=t,this.now=e}return n.prototype.schedule=function(n,t,e){return void 0===t&&(t=0),new this.SchedulerAction(this,n).schedule(e,t)},n.now=function(){return Date.now()},n}(),a=new(function(n){function t(){return null!==n&&n.apply(this,arguments)||this}return u.d(t,n),t}(function(n){function t(e,l){void 0===l&&(l=c.now);var i=n.call(this,e,(function(){return t.delegate&&t.delegate!==i?t.delegate.now():l()}))||this;return i.actions=[],i.active=!1,i.scheduled=void 0,i}return u.d(t,n),t.prototype.schedule=function(e,l,i){return void 0===l&&(l=0),t.delegate&&t.delegate!==this?t.delegate.schedule(e,l,i):n.prototype.schedule.call(this,e,l,i)},t.prototype.flush=function(n){var t=this.actions;if(this.active)t.push(n);else{var e;this.active=!0;do{if(e=n.execute(n.state,n.delay))break}while(n=t.shift());if(this.active=!1,e){for(;n=t.shift();)n.unsubscribe();throw e}}},t}(c)))(s),h=e("FFOo"),d=e("G5J1"),f=e("F/XL"),p=e("6blF"),b=function(){function n(n,t,e){this.kind=n,this.value=t,this.error=e,this.hasValue="N"===n}return n.prototype.observe=function(n){switch(this.kind){case"N":return n.next&&n.next(this.value);case"E":return n.error&&n.error(this.error);case"C":return n.complete&&n.complete()}},n.prototype.do=function(n,t,e){switch(this.kind){case"N":return n&&n(this.value);case"E":return t&&t(this.error);case"C":return e&&e()}},n.prototype.accept=function(n,t,e){return n&&"function"==typeof n.next?this.observe(n):this.do(n,t,e)},n.prototype.toObservable=function(){var n;switch(this.kind){case"N":return Object(f.a)(this.value);case"E":return n=this.error,new p.a((function(t){return t.error(n)}));case"C":return Object(d.b)()}throw new Error("unexpected notification kind value")},n.createNext=function(t){return void 0!==t?new n("N",t):n.undefinedValueNotification},n.createError=function(t){return new n("E",void 0,t)},n.createComplete=function(){return n.completeNotification},n.completeNotification=new n("C"),n.undefinedValueNotification=new n("N",void 0),n}(),g=function(n){function t(t,e,l){void 0===l&&(l=0);var i=n.call(this,t)||this;return i.scheduler=e,i.delay=l,i}return u.d(t,n),t.dispatch=function(n){n.notification.observe(n.destination),this.unsubscribe()},t.prototype.scheduleMessage=function(n){this.destination.add(this.scheduler.schedule(t.dispatch,this.delay,new v(n,this.destination)))},t.prototype._next=function(n){this.scheduleMessage(b.createNext(n))},t.prototype._error=function(n){this.scheduleMessage(b.createError(n)),this.unsubscribe()},t.prototype._complete=function(){this.scheduleMessage(b.createComplete()),this.unsubscribe()},t}(h.a),v=function(){return function(n,t){this.notification=n,this.destination=t}}(),m=e("8g8A"),y=e("uMaO"),_=function(n){function t(t,e,l){void 0===t&&(t=Number.POSITIVE_INFINITY),void 0===e&&(e=Number.POSITIVE_INFINITY);var i=n.call(this)||this;return i.scheduler=l,i._events=[],i._infiniteTimeWindow=!1,i._bufferSize=t<1?1:t,i._windowTime=e<1?1:e,e===Number.POSITIVE_INFINITY?(i._infiniteTimeWindow=!0,i.next=i.nextInfiniteTimeWindow):i.next=i.nextTimeWindow,i}return u.d(t,n),t.prototype.nextInfiniteTimeWindow=function(t){var e=this._events;e.push(t),e.length>this._bufferSize&&e.shift(),n.prototype.next.call(this,t)},t.prototype.nextTimeWindow=function(t){this._events.push(new I(this._getNow(),t)),this._trimBufferThenGetEvents(),n.prototype.next.call(this,t)},t.prototype._subscribe=function(n){var t,e=this._infiniteTimeWindow,l=e?this._events:this._trimBufferThenGetEvents(),i=this.scheduler,u=l.length;if(this.closed)throw new m.a;if(this.isStopped||this.hasError?t=r.a.EMPTY:(this.observers.push(n),t=new y.a(this,n)),i&&n.add(n=new g(n,i)),e)for(var o=0;o<u&&!n.closed;o++)n.next(l[o]);else for(o=0;o<u&&!n.closed;o++)n.next(l[o].value);return this.hasError?n.error(this.thrownError):this.isStopped&&n.complete(),t},t.prototype._getNow=function(){return(this.scheduler||a).now()},t.prototype._trimBufferThenGetEvents=function(){for(var n=this._getNow(),t=this._bufferSize,e=this._windowTime,l=this._events,i=l.length,u=0;u<i&&!(n-l[u].time<e);)u++;return i>t&&(u=Math.max(u,i-t)),u>0&&l.splice(0,u),l},t}(o.a),I=function(){return function(n,t){this.time=n,this.value=t}}(),w=e("26FU"),O=function(){return function(n){this.lazyLoad=!1,this.providers=new Map;for(var t=0;t<n.length;t++){var e=n[t];this.providers.set(e.id,e.provider),this.lazyLoad=this.lazyLoad||e.lazyLoad}}}(),k=function(){function n(n){this._user=null,this._authState=new _(1),this._readyState=new w.a([]),this.initialized=!1,this.providers=n.providers,n.lazyLoad||this.initialize()}return Object.defineProperty(n.prototype,"authState",{get:function(){return this._authState.asObservable()},enumerable:!0,configurable:!0}),Object.defineProperty(n.prototype,"readyState",{get:function(){return this._readyState.asObservable()},enumerable:!0,configurable:!0}),n.prototype.initialize=function(){var n=this;this.initialized=!0,this.providers.forEach((function(t,e){t.initialize().then((function(){var l=n._readyState.getValue();l.push(e),n._readyState.next(l),t.getLoginStatus().then((function(t){t.provider=e,n._user=t,n._authState.next(t)})).catch((function(t){n._authState.next(null)}))}))}))},n.prototype.signIn=function(t,e){var l=this;return this.initialized||this.initialize(),new Promise((function(i,u){var o=l.providers.get(t);o?o.signIn(e).then((function(n){n.provider=t,i(n),l._user=n,l._authState.next(n)})).catch((function(n){u(n)})):u(n.ERR_LOGIN_PROVIDER_NOT_FOUND)}))},n.prototype.signOut=function(t){var e=this;return void 0===t&&(t=!1),this.initialized||this.initialize(),new Promise((function(l,i){if(e._user){var u=e.providers.get(e._user.provider);u?u.signOut(t).then((function(){l(),e._user=null,e._authState.next(null)})).catch((function(n){i(n)})):i(n.ERR_LOGIN_PROVIDER_NOT_FOUND)}else i(n.ERR_NOT_LOGGED_IN)}))},n.ERR_LOGIN_PROVIDER_NOT_FOUND="Login provider not found",n.ERR_NOT_LOGGED_IN="Not logged in",n}(),N=function(){function n(){}return n.initialize=function(t){return{ngModule:n,providers:[k,{provide:O,useValue:t}]}},n}(),S=function(){return function(){}}(),E=function(){function n(){this._readyState=new w.a(!1)}return n.prototype.onReady=function(){var n=this;return new Promise((function(t,e){n._readyState.subscribe((function(n){n&&t()}))}))},n.prototype.loadScript=function(n,t,e,l,i){if(void 0===l&&(l=!0),"undefined"!=typeof document&&!document.getElementById(n)){var u=document.createElement("script");u.async=l,u.src=t,u.onload=e,document.head.appendChild(u)}},n}(),R=function(){return(R=Object.assign||function(n){for(var t,e=1,l=arguments.length;e<l;e++)for(var i in t=arguments[e])Object.prototype.hasOwnProperty.call(t,i)&&(n[i]=t[i]);return n}).apply(this,arguments)},x=function(n){function t(t,e){void 0===e&&(e={scope:"email"});var l=n.call(this)||this;return l.clientId=t,l.opt=e,l}return Object(u.d)(t,n),t.prototype.initialize=function(){var n=this;return new Promise((function(e,l){n.loadScript(t.PROVIDER_ID,"https://apis.google.com/js/platform.js",(function(){gapi.load("auth2",(function(){n.auth2=gapi.auth2.init(R({},n.opt,{client_id:n.clientId})),n.auth2.then((function(){n._readyState.next(!0),e()})).catch((function(n){l(n)}))}))}))}))},t.prototype.getLoginStatus=function(){var n=this;return new Promise((function(t,e){n.onReady().then((function(){if(n.auth2.isSignedIn.get()){var l=new S,i=n.auth2.currentUser.get().getBasicProfile(),u=n.auth2.currentUser.get().getAuthResponse(!0).access_token,o=n.auth2.currentUser.get().getAuthResponse(!0).id_token;l.id=i.getId(),l.name=i.getName(),l.email=i.getEmail(),l.photoUrl=i.getImageUrl(),l.firstName=i.getGivenName(),l.lastName=i.getFamilyName(),l.authToken=u,l.idToken=o,t(l)}else e("No user is currently logged in.")}))}))},t.prototype.signIn=function(n){var t=this;return new Promise((function(e,l){t.onReady().then((function(){(n&&n.offline_access||t.opt&&t.opt.offline_access?t.auth2.grantOfflineAccess(n):t.auth2.signIn(n)).then((function(n){var l=new S,i=t.auth2.currentUser.get().getBasicProfile(),u=t.auth2.currentUser.get().getAuthResponse(!0).access_token,o=t.auth2.currentUser.get().getAuthResponse(!0).id_token;l.id=i.getId(),l.name=i.getName(),l.email=i.getEmail(),l.photoUrl=i.getImageUrl(),l.firstName=i.getGivenName(),l.lastName=i.getFamilyName(),l.authToken=u,l.idToken=o,n&&n.code&&(l.authorizationCode=n.code),e(l)}),(function(n){l("User cancelled login or did not fully authorize.")})).catch((function(n){l(n)}))}))}))},t.prototype.signOut=function(n){var t=this;return new Promise((function(e,l){t.onReady().then((function(){(n?t.auth2.disconnect():t.auth2.signOut()).then((function(n){n?l(n):e()})).catch((function(n){l(n)}))}))}))},t.PROVIDER_ID="GOOGLE",t}(E),z=(l=function(n,t){return(l=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(n,t){n.__proto__=t}||function(n,t){for(var e in t)t.hasOwnProperty(e)&&(n[e]=t[e])})(n,t)},function(n,t){function e(){this.constructor=n}l(n,t),n.prototype=null===t?Object.create(t):(e.prototype=t.prototype,new e)}),P=(function(n){function t(t,e,l,i,u){void 0===e&&(e={scope:"email,public_profile"}),void 0===l&&(l="en_US"),void 0===i&&(i="name,email,picture,first_name,last_name"),void 0===u&&(u="v4.0");var o=n.call(this)||this;return o.clientId=t,o.opt=e,o.locale=l,o.fields=i,o.version=u,o}z(t,n),t.prototype.initialize=function(){var n=this;return new Promise((function(e,l){n.loadScript(t.PROVIDER_ID,"//connect.facebook.net/"+n.locale+"/sdk.js",(function(){FB.init({appId:n.clientId,autoLogAppEvents:!0,cookie:!0,xfbml:!0,version:n.version}),n._readyState.next(!0),e()}))}))},t.prototype.getLoginStatus=function(){var n=this;return new Promise((function(t,e){n.onReady().then((function(){FB.getLoginStatus((function(l){if("connected"===l.status){var i=l.authResponse;FB.api("/me?fields="+n.fields,(function(n){var e=new S;e.id=n.id,e.name=n.name,e.email=n.email,e.photoUrl="https://graph.facebook.com/"+n.id+"/picture?type=normal",e.firstName=n.first_name,e.lastName=n.last_name,e.authToken=i.accessToken,e.facebook=n,t(e)}))}else e("No user is currently logged in.")}))}))}))},t.prototype.signIn=function(n){var t=this;return new Promise((function(n,e){t.onReady().then((function(){FB.login((function(l){if(l.authResponse){var i=l.authResponse;FB.api("/me?fields="+t.fields,(function(t){var e=new S;e.id=t.id,e.name=t.name,e.email=t.email,e.photoUrl="https://graph.facebook.com/"+t.id+"/picture?type=normal",e.firstName=t.first_name,e.lastName=t.last_name,e.authToken=i.accessToken,e.facebook=t,n(e)}))}else e("User cancelled login or did not fully authorize.")}),t.opt)}))}))},t.prototype.signOut=function(){var n=this;return new Promise((function(t,e){n.onReady().then((function(){FB.logout((function(n){t()}))}))}))},t.PROVIDER_ID="FACEBOOK"}(E),new O([{id:x.PROVIDER_ID,provider:new x("92290574907-41lr26m1hm42i9ghh423lpapmv6pbnfe.apps.googleusercontent.com")}]));function C(){return P}var T=function(){return function(){}}(),D=e("pMnS"),A=e("oBZk"),L=e("ZZ/e"),j=e("Ip0R"),F=function(){function n(n){this.authService=n}return n.prototype.ngOnInit=function(){var n=this;this.authService.authState.subscribe((function(t){n.user=t,console.log(t)}))},n.prototype.signInWithGoogle=function(){this.authService.signIn(x.PROVIDER_ID).then((function(n){return console.log(n)}))},n.prototype.signOut=function(){this.authService.signOut()},n}(),G=i.qb({encapsulation:0,styles:[[".bg-transparent[_ngcontent-%COMP%]{background-color:transparent}[_nghost-%COMP%]{height:100%}.photo[_ngcontent-%COMP%]{-o-object-fit:contain;object-fit:contain}.card[_ngcontent-%COMP%]{width:20rem;margin:0 auto}"]],data:{}});function U(n){return i.Jb(0,[(n()(),i.sb(0,0,null,null,9,"div",[["class","card text-center"]],null,null,null,null,null)),(n()(),i.sb(1,0,null,null,1,"h6",[["class","card-header"]],null,null,null,null,null)),(n()(),i.Ib(-1,null,[" Social Login Demo "])),(n()(),i.sb(3,0,null,null,4,"div",[["class","card-block"]],null,null,null,null,null)),(n()(),i.sb(4,0,null,null,1,"h4",[["class","card-title"]],null,null,null,null,null)),(n()(),i.Ib(-1,null,["Not signed in"])),(n()(),i.sb(6,0,null,null,1,"p",[["class","card-text"]],null,null,null,null,null)),(n()(),i.Ib(-1,null,["Sign in with"])),(n()(),i.sb(8,0,null,null,1,"div",[["class","card-block"]],null,null,null,null,null)),(n()(),i.sb(9,0,null,null,0,"img",[["class","btn btn-social-icon btn-google"],["src","./assets/icon/choice.png"],["width","50%"]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.signInWithGoogle()&&l),l}),null,null))],null,null)}function V(n){return i.Jb(0,[(n()(),i.sb(0,0,null,null,12,"div",[["class","card text-center"]],null,null,null,null,null)),(n()(),i.sb(1,0,null,null,1,"h6",[["class","card-header"]],null,null,null,null,null)),(n()(),i.Ib(-1,null,[" Social Login Demo "])),(n()(),i.sb(3,0,null,null,0,"div",[["class","card-block"]],null,null,null,null,null)),(n()(),i.sb(4,0,null,null,0,"img",[["class","card-img-top img-responsive photo"]],[[8,"src",4]],null,null,null,null)),(n()(),i.sb(5,0,null,null,4,"div",[["class","card-block"]],null,null,null,null,null)),(n()(),i.sb(6,0,null,null,1,"h4",[["class","card-title"]],null,null,null,null,null)),(n()(),i.Ib(7,null,["",""])),(n()(),i.sb(8,0,null,null,1,"p",[["class","card-text"]],null,null,null,null,null)),(n()(),i.Ib(9,null,["",""])),(n()(),i.sb(10,0,null,null,2,"div",[["class","card-block"]],null,null,null,null,null)),(n()(),i.sb(11,0,null,null,1,"button",[["class","btn btn-danger"]],null,[[null,"click"]],(function(n,t,e){var l=!0;return"click"===t&&(l=!1!==n.component.signOut()&&l),l}),null,null)),(n()(),i.Ib(-1,null,["Sign out"]))],null,(function(n,t){var e=t.component;n(t,4,0,i.wb(1,"",e.user.photoUrl,"")),n(t,7,0,e.user.name),n(t,9,0,e.user.email)}))}function B(n){return i.Jb(0,[(n()(),i.sb(0,0,null,null,6,"ion-header",[],null,null,null,A.I,A.k)),i.rb(1,49152,null,0,L.y,[i.h,i.k,i.z],null,null),(n()(),i.sb(2,0,null,0,4,"ion-toolbar",[],null,null,null,A.V,A.x)),i.rb(3,49152,null,0,L.zb,[i.h,i.k,i.z],null,null),(n()(),i.sb(4,0,null,0,2,"ion-title",[],null,null,null,A.U,A.w)),i.rb(5,49152,null,0,L.xb,[i.h,i.k,i.z],null,null),(n()(),i.Ib(-1,0,["Chownow"])),(n()(),i.sb(7,0,null,null,0,"br",[],null,null,null,null,null)),(n()(),i.sb(8,0,null,null,0,"br",[],null,null,null,null,null)),(n()(),i.sb(9,0,null,null,26,"ion-content",[],null,null,null,A.G,A.i)),i.rb(10,49152,null,0,L.r,[i.h,i.k,i.z],null,null),(n()(),i.sb(11,0,null,0,8,"ion-card",[],null,null,null,A.E,A.c)),i.rb(12,49152,null,0,L.j,[i.h,i.k,i.z],null,null),(n()(),i.sb(13,0,null,0,6,"ion-card-content",[],null,null,null,A.A,A.d)),i.rb(14,49152,null,0,L.k,[i.h,i.k,i.z],null,null),(n()(),i.sb(15,0,null,0,4,"div",[["class","jumbotron bg-transparent text-center"]],null,null,null,null,null)),(n()(),i.hb(16777216,null,null,1,null,U)),i.rb(17,16384,null,0,j.h,[i.N,i.K],{ngIf:[0,"ngIf"]},null),(n()(),i.hb(16777216,null,null,1,null,V)),i.rb(19,16384,null,0,j.h,[i.N,i.K],{ngIf:[0,"ngIf"]},null),(n()(),i.sb(20,0,null,0,15,"ion-card",[],null,null,null,A.E,A.c)),i.rb(21,49152,null,0,L.j,[i.h,i.k,i.z],null,null),(n()(),i.sb(22,0,null,0,9,"ion-item",[],null,null,null,A.L,A.n)),i.rb(23,49152,null,0,L.E,[i.h,i.k,i.z],null,null),(n()(),i.sb(24,0,null,0,1,"ion-icon",[["name","pin"],["slot","start"]],null,null,null,A.J,A.l)),i.rb(25,49152,null,0,L.z,[i.h,i.k,i.z],{name:[0,"name"]},null),(n()(),i.sb(26,0,null,0,2,"ion-label",[],null,null,null,A.M,A.o)),i.rb(27,49152,null,0,L.K,[i.h,i.k,i.z],null,null),(n()(),i.Ib(-1,0,["Sai Riddhi Apartment, Airoli sector 9"])),(n()(),i.sb(29,0,null,0,2,"ion-button",[["fill","outline"],["slot","end"]],null,null,null,A.z,A.b)),i.rb(30,49152,null,0,L.h,[i.h,i.k,i.z],{fill:[0,"fill"]},null),(n()(),i.Ib(-1,0,["Locate"])),(n()(),i.sb(32,0,null,0,3,"ion-card-content",[],null,null,null,A.A,A.d)),i.rb(33,49152,null,0,L.k,[i.h,i.k,i.z],null,null),(n()(),i.Ib(-1,0,[" Find and share food nearby"])),(n()(),i.sb(35,0,null,0,0,"br",[],null,null,null,null,null))],(function(n,t){var e=t.component;n(t,17,0,!e.user),n(t,19,0,e.user),n(t,25,0,"pin"),n(t,30,0,"outline")}),null)}function M(n){return i.Jb(0,[(n()(),i.sb(0,0,null,null,1,"app-login",[],null,null,null,B,G)),i.rb(1,114688,null,0,F,[k],null,null)],(function(n,t){n(t,1,0)}),null)}var W=i.ob("app-login",F,M,{},{},[]),J=e("gIcY"),q=e("ZYCi"),Y=function(){return function(){}}();e.d(t,"LoginPageModuleNgFactory",(function(){return K}));var K=i.pb(T,[],(function(n){return i.Bb([i.Cb(512,i.j,i.ab,[[8,[D.a,W]],[3,i.j],i.x]),i.Cb(4608,j.j,j.i,[i.u,[2,j.p]]),i.Cb(4608,J.d,J.d,[]),i.Cb(4608,L.a,L.a,[i.z,i.g]),i.Cb(4608,L.Db,L.Db,[L.a,i.j,i.q]),i.Cb(4608,L.Gb,L.Gb,[L.a,i.j,i.q]),i.Cb(5120,O,C,[]),i.Cb(4608,k,k,[O]),i.Cb(1073742336,j.b,j.b,[]),i.Cb(1073742336,J.c,J.c,[]),i.Cb(1073742336,J.a,J.a,[]),i.Cb(1073742336,L.Bb,L.Bb,[]),i.Cb(1073742336,q.n,q.n,[[2,q.s],[2,q.m]]),i.Cb(1073742336,Y,Y,[]),i.Cb(1073742336,N,N,[]),i.Cb(1073742336,T,T,[]),i.Cb(1024,q.k,(function(){return[[{path:"",component:F}]]}),[])])}))}}]);