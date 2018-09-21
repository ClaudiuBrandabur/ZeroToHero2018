'use strict';

hrApp.controller('EmployeeListController', ['$scope', '$http', '$route', '$routeParams','$location', 'EmployeeService', 'JobService', 'DepartmentService',
    function($scope, $http, $route, $routeParams, $location, EmployeeService, JobService, DepartmentService) {

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
         * Navigate to view page of an employee
         * @param employeeId - identifier of the employee to be viewed
         */
        $scope.view = function(employeeId) {
            $location.url('/employeeView/' + employeeId);
        };

        /**
         * Navigate to edit page of an employee
         * @param employeeId - identifier of the employee to be edited
         */
        $scope.edit = function(employeeId) {
            $location.url('/employeeEdit/' + employeeId);
        };

        /**
         * Delete an employee
         * @param employeeId - identifier of the employee to be deleted
         */
        $scope.delete = function(employeeId) {
            EmployeeService.delete(employeeId).then(function() {
                alert('Employee has been deleted successfully');
                $route.reload();
            }, function(err) {
                console.log('An error occurred while deleting employee: ' + err.status);
            });
        }

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
    }]);