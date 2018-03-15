app.controller("instanceCtrl", function ($scope, $http, $state) {

    //事件绑定
    $scope.createInstance = function () {
        clearInterval(get_instance_time_interval);
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

    var get_instance_time_interval = setInterval(getInstanceData, 200000);

    function getInstanceData() {
        $http({
            method: 'get',
            url: '/cloud/v1/instance/'
        }).then(function sucessCallback(response) {
            var data = response.data.data;
            var data_instance = [];
            var clear_interval_flag = true;

            if (data == null) {
                $scope.instances = [];
                clearInterval(get_instance_time_interval);
                return;
            }
            for (var i = 0; i < data.length; i++) {
                var tmp = {};
                tmp.id = data[i].id;
                tmp.name = data[i].name;
                tmp.description = data[i].description;
                if (data[i].host_config != undefined) {
                    tmp.node = "Core:" + data[i].host_config.core + " Memory:" + data[i].host_config.memory;
                }

                if (data[i].status == 0) {
                    tmp.notDeployedText = "Not deployed";
                    clear_interval_flag = false;
                } else if (data[i].status == 1) {
                    tmp.process = { "width": "25%" };
                    tmp.processText = "Starting " + "20%";
                    clear_interval_flag = false;
                } else if (data[i].status == 2) {
                    tmp.notDeployedText = "Deploy success";
                } else if (data[i].status == 3) {
                    tmp.notDeployedText = "";
                    var process = "40%";
                    if (data[i].process == null) {
                        tmp.process = { "width": process };
                        clear_interval_flag = false;
                        tmp.processText = "Deploying " + process;
                    } else if (data[i].process != undefined && data[i].process != "") {
                        if (parseFloat(data[i].process) >= 1) {
                            tmp.notDeployedText = "Deploy success";
                        }
                        else {
                            process = parseFloat(data[i].process) * 50 + 50 + "%";
                            tmp.process = { "width": process };
                            clear_interval_flag = false;
                            tmp.processText = "Analysing data " + process;
                        }
                    }

                } else if (data[i].status == 8) {
                    tmp.process = { "width": "50%" };
                    tmp.processText = "Host created " + "50%";
                    clear_interval_flag = false;
                }

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

    function start_instance(id) {
        var start_data = { "status": 1 };
        $http({
            method: 'post',
            url: '/cloud/v1/instance/status/' + id,
            data: start_data
        }).then(function sucessCallback(response) {
            getInstanceData();
        }, function errorCallback(response) {
            alert("fail")
        });
    }

    function delete_instance(id) {
        $http({
            method: 'delete',
            url: '/cloud/v1/instance/' + id
        }).then(function sucessCallback(response) {
            getInstanceData();
        }, function errorCallback(response) {
            alert("fail")
        });
    }




});