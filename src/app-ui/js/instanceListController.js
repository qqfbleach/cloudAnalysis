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
                if (data[i].host != undefined) {
                    tmp.node = "Core:" + data[i].host.core + " Memory:" + data[i].host.memory;
                }

                if(null == data[i].host)
                {
                    data_instance.push(tmp);
                    continue;
                }
                
                var status=data[i].host.status;
                var processs=data[i].host.process;
               
                if (status == 0) {
                    tmp.notDeployedText = "Not deployed";
                    clear_interval_flag = false;
                } else if (status == 1) {
                    tmp.process = { "width": "25%" };
                    tmp.processText = "Starting " + "20%";
                    clear_interval_flag = false;
                } else if (status == 2) {
                    tmp.notDeployedText = "Deploy success";
                } else if (status == 3) {
                    tmp.notDeployedText = "";
                    var process = "40%";
                    if (processs == null) {
                        tmp.process = { "width": process };
                        clear_interval_flag = false;
                        tmp.processText = "Deploying " + process;
                    } else if (processs != undefined && processs != "") {
                        if (parseFloat(processs) >= 1) {
                            tmp.notDeployedText = "Deploy success";
                        }
                        else {
                            process = parseFloat(processs) * 50 + 50 + "%";
                            tmp.process = { "width": process };
                            clear_interval_flag = false;
                            tmp.processText = "Analysing data " + process;
                        }
                    }

                } else if (status == 8) {
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
        var start_data = { "action": "start", "instanceId": id };
        $http({
            method: 'post',
            url: '/cloud/v1/instance/action/',
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