var app=angular.module('cloudApp',['ui.router']);
app.factory('myInterceptor', ['$log', function($log,$q) {
    $log.debug('$log is here to show you that this is a regular factory with injection');

    var myInterceptor = {
        
       // 可选，拦截成功的请求
       request: function(config) {
        // 进行预处理
        // ...
        return config || $q.when(config);
      },
  
      // 可选，拦截失败的请求
     requestError: function(rejection) {
        // 对失败的请求进行处理
        // ...
        if (canRecover(rejection)) {
          return responseOrNewPromise
        }
        return $q.reject(rejection);
      },
  
  
  
      // 可选，拦截成功的响应
      response: function(response) {
        // 进行预处理
        // ....
        return response || $q.when(reponse);
      },
  
      // 可选，拦截失败的响应
     responseError: function(rejection) {
        // 对失败的响应进行处理
        // ...
        if (canRecover(rejection)) {
            $(function () { $('#myModal').modal({
	keyboard: true
})});
          return responseOrNewPromise
        }
        return $q.reject(rejection);

        
      }
      
      
    };

    function canRecover(response){
        var _notStaticUrlReg = /^cloud\/v1/i;
        return response.config.url.match(_notStaticUrlReg)?null:true;
    }
    return myInterceptor;

}]);

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('myInterceptor');
}]);
app.config(function($stateProvider,$urlRouterProvider){
    //默认路由
    $urlRouterProvider.when('/user/instance', '/user/instance/list');
    $urlRouterProvider.when('/user/instance/', '/user/instance/list');
    $urlRouterProvider.when('/user', '/user/instance/list');
    $urlRouterProvider.when('/user/', '/user/instance/list');
    $urlRouterProvider.otherwise("/home");

    //路由状态
    $stateProvider.state(
        "home",{
            url:"/home",
            templateUrl:"/views/home.html" //默认页面
        }
    ).state(
        "user",{
            url:"/user",
            templateUrl:"/views/users/user.html" //用户页面
        }
    ).state(
        "user.instance",{
            url:"/instance",
            
            templateUrl:"/views/users/instance.html" //用户应用页面
            
        }
    ).state(
        "user.instance.list",{
            url:"/list",
            templateUrl:"/views/users/instanceList.html", //用户应用展示页面
            controller:"instanceCtrl"
        }
    ).state(
        "user.instance.create",{
            url:"/create",
            templateUrl:"/views/users/instanceCreate.html",//用户应用创建页面
            controller:"instanceCreateCtrl"
        }
    ).state(
        "user.data-graph",{
            url:"/data-graph",
            templateUrl:"/views/users/tool.html"
        }
    );


    
});

