app.controller("instanceCreateCtrl", function ($scope, $http,$state) {

    $scope.submitInstance = function () {
        $state.go("user.instance.list");
    }
    
});