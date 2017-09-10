app.controller("instanceCreateCtrl", function ($scope, $http, $state) {

    $scope.node_options = ["small", "large", "xlarge", "xxlarge"];

    $scope.submitInstance = function () {

        var create_instance_data = {
            "name": "ins1",
            "description": "description",
            "hostConfig": {
                "core": 8,
                "memory": 8096,
                "traffic": 100
            },
            "userId": "0cc3756e-be16-4b83-b027-20cb648d04c0"
        }
        $http({
            method: 'get',
            url: '/data/instance',
            data: create_instance_data
        }).then(function sucessCallback(response) {
            $scope.instanceBtnDisabled = true;
            setTimeout(function(){

                $state.go("user.instance.list");
            }, 1000);
            
        }, function errorCallback(response) {
            alert("fail")

        });




    }

});