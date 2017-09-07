var app=angular.module('cloudApp',['ui.router']);
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
            
            templateUrl:"/views/users/instance.html", //用户应用页面
            controller:"instanceCtrl"
        }
    ).state(
        "user.instance.list",{
            url:"/list",
            templateUrl:"/views/users/instanceList.html" //用户应用展示页面
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

