hrApp.controller('EmployeeListController', ['$scope', '$http', '$location','CommonResourcesFactory',
    function ($scope, $http, $location,CommonResourcesFactory) {
// TODO #HR2 - inject commonResourcesFactory

        $scope.employees = []; // Employee list


        //TODO #HR3 Load employee list from server using commonResourcesFactory
        
        $http.get(CommonResourcesFactory.findAllEmployeesUrl)
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