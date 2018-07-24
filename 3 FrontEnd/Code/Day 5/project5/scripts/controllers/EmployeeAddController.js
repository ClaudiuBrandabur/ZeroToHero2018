hrApp.controller('EmployeeAddController', ['$scope', '$http', '$location', 'CommonResourcesFactory', 'EmployeeService',
    function($scope, $http, $location, CommonResourcesFactory, EmployeeService) {
        $scope.employee = {};
        $scope.managers = [];
        $scope.jobs = [];
        $scope.departments = [];
        $scope.requiredErrorMessage = "Please fill out this form!";
        $scope.patternDateNotRespectedMessage = "The date format should be yyyy-mm-dd";
        $scope.patternCommisionNotRespectedMessage = "Commission should be in the format 0.XX";

        //TODO #HR1
        // $http.get(CommonResourcesFactoryBackup.findAllDepartmentsUrl)
        //     .success(function (data) {
        //         $scope.departments = data;
        //     });
        //
        // $http.get(CommonResourcesFactoryBackup.findAllEmployeesUrl)
        //     .success(function (data) {
        //         $scope.managers = data;
        //     });
        //
        // $http.get(CommonResourcesFactoryBackup.findAllJobsUrl)
        //     .success(function (data) {
        //         $scope.jobs = data;
        //     })
        /**
         * Reset form
         */
        $scope.reset = function () {
            this.employee = {};
        };


        /**
         * Persist an employee
         * @param addEmployee - employee to be persisted
         */
        $scope.create = function (addEmployee) {
            $http({url: CommonResourcesFactory.addEmployeeUrl, method: 'POST', data: addEmployee})
                .success(function (data) {
                    $scope.employee = data;
                    $location.url('/employeeView/' + $scope.employee.employeeId);
                });
        };

        EmployeeService.findManagers()
            .then(function (res) {
                $scope.managers = EmployeeService.findManagersFromEmployees(res.data);
            }, function (err) {
                console.log("Error "+err);
            });
        EmployeeService.findDepartments()
            .then(function (res) {
                if(!$scope.departments.hasOwnProperty($scope.departments.departmentId))
                    $scope.departments = res.data;
            }, function (err) {
                console.log("Error: " + err);
            });

        EmployeeService.findJobs()
            .then(function (res) {
                if(!$scope.jobs.hasOwnProperty($scope.jobs.jobId))
                    $scope.jobs = res.data;
            }, function (err) {
                console.log("Error: " + err);
            });

        $scope.datePattern = /^\d{4}-\d{2}-\d{2}$/;
        $scope.commissionPattern = /^[0]\.\d{1}(\d)?$/;
    }]);