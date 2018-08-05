app.controller("instanceCreateCtrl", function ($scope, $http, $state) {

    $scope.node_options = [{ "value": "0", "size": "small(1U 1G)" }, { "value": "1", "size": "large(2U 4G)" }, { "value": "2", "size": "xlarge(4U 8G)" }, { "value": "3", "size": "xxlarge(8U 16G)" }];

    $scope.submitInstance = function () {

        var host = {
            "core": 1,
            "memory": 1024,
            "traffic": 100
        };
        if ($scope.selectedObj.value == 0) {
            host = {
                "core": 1,
                "memory": 1024,
                "traffic": 100
            }
        }
        else if ($scope.selectedObj.value == 1) {
            host = {
                "core": 2,
                "memory": 4096,
                "traffic": 100
            }
        } else if ($scope.selectedObj.value == 2) {
            host = {
                "core": 8,
                "memory": 4096,
                "traffic": 100
            }
        } else if ($scope.selectedObj.value == 3) {
            host = {
                "core": 8,
                "memory": 8096,
                "traffic": 100
            }
        }
        var create_instance_data = {
            "name": $scope.instanceName,
            "description": $scope.instanceDes,
            "host": host,
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