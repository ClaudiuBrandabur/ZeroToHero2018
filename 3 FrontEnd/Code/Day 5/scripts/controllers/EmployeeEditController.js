hrApp.controller('EmployeeEditController', ['$scope', '$http', '$routeParams', '$location', 'CommonResourcesFactory',
    function ($scope, $http, $routeParams, $location, CommonResourcesFactory) {
        $scope.requiredErrorMessage = "Please fill out this form!";
        $scope.patternDateNotRespectedMessage = "The date format should be yyyy-mm-dd";
        $scope.patternCommisionNotRespectedMessage = "Commission should be in the format 0.XX";

        //TODO #HR5

        /**
         * Reset form
         */
        $scope.reset = function () {
            $scope.employee = {};
        };

        $http.get(CommonResourcesFactory.findAllDepartmentsUrl)
            .success(function (data, status, header, config) {
                $scope.departaments = data;
            })
            .error(function (data, status, header, config) {
                alert('error:' + status);
            });
        $http.get(CommonResourcesFactory.findAllEmployeesUrl)
            .success(function(data, status, header, config){
                $scope.managers = data;
            })
            .error(function (data, status, header, config) {
                alert('error:' + status);
            });
        $http.get(CommonResourcesFactory.findAllJobsUrl)
            .success(function (data, status, header, config) {
                $scope.jobs = data;
            })
            .error(function (data, status, header, config) {
                alert('error:' + status);
            });


        /**
         * Persist an employee
         * @param addEmployee - employee to be persisted
         */
        $scope.create = function (addEmployee) {
            $http({url: commonResourcesFactory.editEmployeeUrl, method: 'PUT', data: addEmployee})
                .success(function (data) {
                    $scope.employee = data;
                    $location.url('/employeeView/' + $scope.employee.employeeId);
                });
        };

        $scope.datePattern = /^\d{4}-\d{2}-\d{2}$/;
        $scope.commissionPattern =  /^[0]\.\d{1}(\d)?$/;

    }]);