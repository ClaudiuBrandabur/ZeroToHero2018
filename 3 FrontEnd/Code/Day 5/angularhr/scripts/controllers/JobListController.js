hrApp.controller('JobListController', ['$scope', '$http', '$location', 'CommonResourcesFactory',
    function ($scope, $http, $location, CommonResourcesFactory) {

        $scope.jobs = [];


        /*$scope.viewEmployee = function (employeeId) {
            $location.url('/employeeView/' + employeeId);
        };

        $scope.editEmployee = function(employeeId) {
            $location.url('/employeeEdit/' + employeeId);
        };

        $scope.employee = [];

        $scope.deleteEmployee = function (employee) {
            $http({url: CommonResourcesFactory.deleteEmployeeUrl, method: 'DELETE',headers: {
                    "Content-Type": "application/json"}, data: employee})
                .success(function (data) {
                    $scope.employee = data;
                    $location.url('/employeeView/' + $scope.employee.employeeId);
                });
        }*/
    }]);