'use strict';

hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', '$location', 'EmployeeService','DepartmentService','JobService',
    function($scope, $http, $routeParams, $location, EmployeeService,DepartmentService,JobService) {

        /**
         * Retrieve an employee
         * @param employeeId - identifier of the employee to be retrieved
         */

        DepartmentService.findAll().then(function (res) {
            $scope.deps = res.data;
            $scope.myOneEmp();
        },function (err) {
            console.log('An error occurred while finding all deps: ' + err.status);
        });

        $scope.myOneEmp = function() {

            EmployeeService.findOne($routeParams.employeeId)
                .then(function (res) {
                    $scope.employee = res.data;
                }, function (err) {
                    console.log('An error occurred while finding the employee: ' + err.status);
                });

        };

        $scope.findOneDepName = function(departmentId){
            if($scope.deps === undefined || $scope.deps.length === 0){
                return '';
            }
            for(var i = 0; i < $scope.deps.length; i++){
                if(departmentId === $scope.deps[i].departmentId){
                    return $scope.deps[i].departmentName;
                }
            }
        };

        JobService.findAll().then(function (res) {
            $scope.jobs = res.data;
            $scope.myOneEmp();
        },function (err) {
            console.log('An error occurred while finding all deps: ' + err.status);
        });

        $scope.findOneJobName = function(jobId){
            if($scope.jobs === undefined || $scope.jobs.length === 0){
                return '';
            }
            for(var i = 0; i < $scope.jobs.length; i++){
                if(jobId === $scope.jobs[i].jobId){
                    return $scope.jobs[i].jobTitle;
                }
            }
        };

        EmployeeService.findAll().then(function(res) {
            $scope.employees = res.data;
        }, function(err) {
            console.log('An error occurred while finding all employees: ' + err.status);
        });

        $scope.findOneManName = function(managerId){
            if($scope.employees === undefined || $scope.employees.length === 0){
                return '';
            }

            for(var i = 0; i < $scope.employees.length; i++){
                if(managerId === $scope.employees[i].managerId){
                    return $scope.employees[i].firstName +" "+ $scope.employees[i].lastName ;
                }
            }
        };



        /**
         * Navigate back to employee list page
         */
        $scope.back = function() {
            $location.url('/employeeList');
        }

    }]);