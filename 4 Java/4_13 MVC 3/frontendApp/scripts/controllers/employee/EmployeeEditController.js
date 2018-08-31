'use strict';

hrApp.controller('EmployeeEditController', ['$scope', '$http', '$routeParams', '$filter', '$location', 'DepartmentService', 'JobService', 'EmployeeService',
    function($scope, $http, $routeParams, $filter, $location, DepartmentService, JobService, EmployeeService) {

        $scope.datePattern = /^\d{4}-\d{2}-\d{2}$/;
        $scope.commissionPattern = /^[0]\.\d{2}?$/;

        $scope.requiredErrorMessage = 'Please fill out this field';
        $scope.patternDateNotRespectedMessage = 'The date format should be yyyy-mm-dd';
        $scope.patternCommisionNotRespectedMessage = 'Commission should be in the format 0.XX';

        DepartmentService.findAll().then(function(res) {
            $scope.departments = res.data;
            $scope.findOneEmp();
        }, function(err) {
            console.log('An error occurred while finding all departments: ' + err.status);
        });

        JobService.findAll().then(function(res) {
            $scope.jobs = res.data;
            $scope.findOneEmp();
        }, function(err) {
            console.log('An error occurred while finding all jobs: ' + err.status);
        });

        EmployeeService.findAll().then(function(res) {
            $scope.managers = res.data;
            $scope.findOneEmp();
        }, function(err) {
            console.log('An error occurred while finding all employees: ' + err.status);
        });


        $scope.findOneEmp = function() {

            EmployeeService.findOne($routeParams.employeeId).then(function (res) {
                $scope.employee = res.data;
                $scope.employee.hireDate = $filter('date')($scope.employee.hireDate, 'yyyy-MM-dd');
                $scope.idTitle = $scope.findMyJob($scope.employee.jobId);
                $scope.DepName = $scope.findMyDep($scope.employee.departmentId);
                $scope.ManName = $scope.findMyMan($scope.employee.managerId)
            }, function (err) {
                console.log('An error occurred while finding employee: ' + err.status);
            });

        };


        $scope.findMyJob = function(jobId) {
            if ($scope.jobs === undefined || $scope.jobs.length === 0) {
                return '';
            }
            for (var i = 0; i < $scope.jobs.length; i++) {
                if (jobId === $scope.jobs[i].jobId) {
                    return $scope.jobs[i];
                }
            }
        };

        $scope.findMyDep = function(departmentId){
            if($scope.departments === undefined || $scope.departments.length === 0){
                return '';
            }
            for(var i = 0; i < $scope.departments.length; i++){
                if(departmentId === $scope.departments[i].departmentId){
                    return $scope.departments[i];
                }
            }
        };


        $scope.findMyMan = function(managerId){
            if($scope.managers === undefined || $scope.managers.length === 0){
                return '';
            }

            for(var i = 0; i < $scope.managers.length; i++){
                if(managerId === $scope.managers[i].managerId){
                    return $scope.managers[i];
                }
            }
        };
        /**
         * Reset employee fields
         */
        $scope.reset = function() {
            $scope.employee = {};
            $scope.idTitle = {};
            $scope.DepName = {};
            $scope.ManName = {};

        };

        /**
         * Update an employee
         * @param employee - employee to be updated
         */
        $scope.save = function(employee) {

            employee.jobId = $scope.idTitle.jobId;
            employee.departmentId = $scope.DepName.departmentId;
            employee.managerId = $scope.ManName.managerId;


            EmployeeService.edit(employee).then(function() {
                $location.url('/employeeView/' + $scope.employee.employeeId);
            }, function(err) {
                console.log('An error occurred while editing employee: ' + err.status);
            });
        };

    }]);