'use strict';

hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', '$location', 'EmployeeService', 'DepartmentService', 'JobService',
    function($scope, $http, $routeParams, $location, EmployeeService, DepartmentService, JobService) {

        /**
         * Retrieve an employee
         * @param employeeId - identifier of the employee to be retrieved
         */
        EmployeeService.findAll($routeParams.employeeId)
            .then(function(res) {
                $scope.employees = res.data;
            }, function(err) {
                console.log('An error occurred while finding the employee: ' + err.status);
            });


        EmployeeService.findOne($routeParams.employeeId)
            .then(function(res) {
                $scope.employee = res.data;
            }, function(err) {
                console.log('An error occurred while finding the employee: ' + err.status);
            });

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
        /**
         * Navigate back to employee list page
         */

        $scope.findJobTitle = function (jobId){
            for(var i in $scope.jobs){
                if($scope.jobs[i].jobId === jobId){
                    $scope.jobTitle = $scope.jobs[i].jobTitle;
                    return $scope.jobTitle;
                }
            }
        }

        $scope.findDepName = function(departmentId){
            for(var i in $scope.departments){
                if($scope.departments[i].departmentId === departmentId){
                    $scope.depName = $scope.departments[i].departmentName;
                    return $scope.depName;
                }
            }
        }

        $scope.findManagerFirstName = function(managerId){
            for(var i in $scope.employees){
                if($scope.employees[i].managerId === managerId){
                    $scope.managerFirstName = $scope.employees[i].firstName;
                    return $scope.managerFirstName;
                }
            }
        }

        $scope.findManagerLastName = function(managerId){
            for(var i in $scope.employees){
                if($scope.employees[i].managerId === managerId){
                    $scope.managerLastName = $scope.employees[i].lastName;
                    return $scope.managerLastName;
                }
            }
        }
        $scope.back = function() {
            $location.url('/employeeList');
        }

    }]);