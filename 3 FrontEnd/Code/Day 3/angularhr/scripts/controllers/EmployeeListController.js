hrApp.controller('EmployeeListController', ['$scope', '$http', '$location','commonResourcesFactory',
    function ($scope, $http, $location,commonResourcesFactory) {
// TODO #HR2 - inject commonResourcesFactory

        $scope.employees = []; // Employee list


        //TODO #HR3 Load employee list from server using commonResourcesFactory
        // $scope.employees = commonResourcesFactory.findAllEmployeesUrl;

        $http.get(commonResourcesFactory.findAllEmployeesUrl)
            .success(function (data,status,headers,config) {
                $scope.employees = data;
            })
            .error(function (data,status,headers,config) {
                alert("Error:" + status);
            });

        $scope.viewEmployee = function (employeeId) {
            $location.url('/employeeview/' + employeeId);
        };


    }]);