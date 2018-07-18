hrApp.controller('EmployeeListController', ['$scope', '$http', '$location','commonResourcesFactory',
    function ($scope, $http, $location,commonResourcesFactory) {
// TODO #HR2 - inject commonResourcesFactory

        $scope.employees = []; // Employee list
        $http.get(commonResourcesFactory.findAllEmployeesUrl)
            .success(function(data) {
                $scope.employees = data;
            })
            .error (function(data,status) {
                alert("Error:"+status);
            })

        //TODO #HR3 Load employee list from server using commonResourcesFactory


        $scope.viewEmployee = function (employeeId) {
            $location.url('/employeeview/' + employeeId);
        };


    }]);