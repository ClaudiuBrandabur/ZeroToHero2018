'use strict';

hrApp.controller('EmployeeAddController', ['$scope', '$http', '$location', 'DepartmentService', 'JobService', 'EmployeeService',
    function($scope, $http, $location, DepartmentService, JobService, EmployeeService) {

        $scope.employee = {};
        $scope.employee1 = {};

        $scope.datePattern = /^\d{4}-\d{2}-\d{2}$/;
        $scope.commissionPattern = /^[0]\.\d{2}?$/;

        $scope.requiredErrorMessage = 'Please fill out this field';
        $scope.patternDateNotRespectedMessage = 'The date format should be yyyy-mm-dd';
        $scope.patternCommisionNotRespectedMessage = 'Commission should be in the format 0.XX';

        DepartmentService.findAll().then(function(res) {
            $scope.departments = res.data;
        }, function(err) {
            console.log('An error occurred while finding all departments: ' + err.status);
        });

        JobService.findAll().then(function(res) {
            $scope.jobs = res.data;
        }, function(err) {
            console.log('An error occurred while finding all jobs: ' + err.status);
        });

        EmployeeService.findAll().then(function(res) {
            $scope.managers = res.data;
        }, function(err) {
            console.log('An error occurred while finding all managers: ' + err.status);
        });

        /**
         * Reset employee fields
         */
        $scope.reset = function() {
            this.employee = {};
        };

        /**
         * Persist an employee
         * @param employee - employee to be persisted
         */
        $scope.save = function(employee) {

            $scope.employee.firstName = employee.firstName;
            $scope.employee.lastName = employee.lastName;
            $scope.employee.email = employee.email;
            $scope.employee.phoneNumber = employee.phoneNumber;
            $scope.employee.hireDate = employee.hireDate;
            $scope.employee.jobId = employee.jobId.jobId;
            $scope.employee.salary = employee.salary;
            $scope.employee.commissionPct = employee.commissionPct;
            $scope.employee.departmentId = employee.departmentId.departmentId;
            $scope.employee.managerId = employee.managerId.employeeId;

            EmployeeService.add(employee).then(function(res) {
                $scope.employee = res.data;
                $location.url('/employeeView/' + $scope.employee.employeeId);
            }, function(err) {
                console.log('An error occurred while adding employee: ' + err.status);
            });
        };

        ///dddddddddddddd

}]);