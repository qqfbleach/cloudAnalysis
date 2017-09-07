app.controller("instanceCtrl", function ($scope, $http, $state) {
    $scope.createInstance = function () {
        $state.go("user.instance.create");
    }

    $http({ method: "GET", url: "/data/instance" }).then(
        function sucessCallback(response) {

        }, function errorCallback(response) {

        }

    );

});