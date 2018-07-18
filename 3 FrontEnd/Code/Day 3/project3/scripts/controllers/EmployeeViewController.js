hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', 'commonResourcesFactory', '$location',
    function ($scope, $http, $routeParams, commonResourcesFactory, $location) {

        $scope.employee = {};


        // TODO #HR6 get employee by id
        $http.get(commonResourcesFactory.findOneEmployeeUrl, {employeeId: $routeParams.employeeId})
            .success(function (data) {
                $scope.employee = data;
            })
            .error(function (data, status) {
                alert("Error:" + status);
            });
        $scope.back = function () {
            // TODO back to Employee List page
            $location.url('/employeelist');
        }

    }]);