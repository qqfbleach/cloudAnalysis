app.controller("instanceCtrl", function ($scope, $http, $state) {

    //事件绑定
    $scope.createInstance = function () {
        $state.go("user.instance.create");
    }

    $scope.start_instance = function (id) {
        start_instance(id);
    };

    $scope.delete_instance = function (id) {
        delete_instance(id);
    };


    $scope.instanceWidthStyle = {
        "width": "40%"
    };

    getInstanceData();

    var get_instance_time_interval = setInterval(getInstanceData, 2000);

    function getInstanceData() {
        $http({
            method: 'get',
            url: '/data/instance'
        }).then(function sucessCallback(response) {
            var data = response.data.instances;
            var data_instance = [];
            var clear_interval_flag = true;

            for (var i = 0; i < data.length; i++) {
                var tmp = {};
                tmp.id = data[i].id;
                tmp.name = data[i].name;
                tmp.description = data[i].description;
                tmp.node = "";
                var process = "0%";

                if (data[i].process != undefined && data[i].process != "") {
                    if (parseFloat(data[i].process) >= 1) {
                        process = "100%";
                    }
                    else {
                        process = parseFloat(data[i].process) * 100 + "%";
                        clear_interval_flag = false;
                    }
                }
                tmp.process = { "width": process };
                tmp.notDeployedText = "Not deployed";
                tmp.processText = process;
                data_instance.push(tmp);
            }
            if (clear_interval_flag) {
                clearInterval(get_instance_time_interval);
            }
            $scope.instances = data_instance;
        }, function errorCallback(response) {
            alert("fail")
        });
    }

    function start_instance(id){
        $http({
            method: 'get',
            url: '/data/instance'
        }).then(function sucessCallback(response) {
            getInstanceData();
        }, function errorCallback(response) {
            alert("fail")
        });
    }

    function delete_instance(id){
        $http({
            method: 'get',
            url: '/data/instance'
        }).then(function sucessCallback(response) {
            getInstanceData();
        }, function errorCallback(response) {
            alert("fail")
        });
    }




});