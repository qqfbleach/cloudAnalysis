app.controller('logoutController', function ($scope, $http) {

    $http({
        method: 'GET',
        url: '/logout'
    }).then(function successCallback(response) {
        // 请求成功执行代码
        alert("sucess");
    }, function errorCallback(response) {
        // 请求失败执行代码
        alert("error");
    });
});