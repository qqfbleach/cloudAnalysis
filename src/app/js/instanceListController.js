app.controller("instanceCtrl", function ($scope, $http, $state) {
    $scope.createInstance = function () {
        $state.go("user.instance.create");
    }

    $http({ method: "GET", url: "/data/instance" }).then(
        function sucessCallback(response) {

        }, function errorCallback(response) {

        }

    );

    $scope.instanceWidthStyle = {
        "width": "40%"
    };

    var data = [];
    for (var i = 0; i < 5; i++) {
        var tmp = {};
        tmp.name = "qqf";
        tmp.description = "instance1";
        tmp.node = "node1";
        tmp.progress = { "width": "40%" };
        tmp.progressText = "40%";
        data.push(tmp);
    }


    $scope.instances = data;

});