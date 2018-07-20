hrApp.controller('EmployeeListController', ['$scope', '$http', '$location', 'CommonResourcesFactory',
    function ($scope, $http, $location, CommonResourcesFactory) {

        $scope.employees = []; // Employee list


        $http.get(CommonResourcesFactory.findAllEmployeesUrl)
            .success(function (data, status, headers, config) {
                $scope.employees = data;
            })
            .error(function (data, status, headers, config) {
                alert("error:" + status);
            });

        $scope.viewEmployee = function (employeeId) {
            $location.url('/employeeview/' + employeeId);
        };


    }]);