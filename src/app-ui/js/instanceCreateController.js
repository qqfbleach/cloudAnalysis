app.controller("instanceCreateCtrl", function ($scope, $http, $state) {

    $scope.node_options = ["small", "large", "xlarge", "xxlarge"];

    $scope.submitInstance = function () {
        var create_instance_data = {
            "name": $scope.instanceName,
            "description": $scope.instanceDes,
            "host_config": {
                "core": 8,
                "memory": 8096,
                "traffic": 100
            },
            "userId": "0cc3756e-be16-4b83-b027-20cb648d04c0"
        }
        $http({
            method: 'post',
            url: '/cloud/v1/instance/',
            data: create_instance_data
        }).then(function sucessCallback(response) {
            $scope.instanceBtnDisabled = true;
            $state.go("user.instance.list");
            // setTimeout(function(){

                
            // }, 5000);
            
        }, function errorCallback(response) {
            alert("fail")

        });




    }

});