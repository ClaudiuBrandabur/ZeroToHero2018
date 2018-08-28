'use strict';

hrApp.controller('EmployeeListController', ['$scope', '$http', '$route', '$location', 'EmployeeService','JobService','DepartmentService',
    function($scope, $http, $route, $location, EmployeeService, JobService, DepartmentService) {

        JobService.findAll().then(function(res) {
            $scope.jobs = res.data;
            $scope.getAllEmp() ;
        }, function(err) {
            console.log('An error occurred while finding all employees: ' + err.status);
        });

        $scope.getAllEmp = function(){

            EmployeeService.findAll().then(function(res) {
                $scope.employees = res.data;
            }, function(err) {
                console.log('An error occurred while finding all employees: ' + err.status);
            });

        };

        $scope.findJobName = function(jobId){
            for(var i = 0; i < $scope.jobs.length; i++){
                if(jobId === $scope.jobs[i].jobId){
                    return $scope.jobs[i].jobTitle;
                }
            }
        };

        DepartmentService.findAll().then(function (res) {
            $scope.dep = res.data;
            $scope.getAllEmp();
        },function (err) {
            console.log('An error occurred while finding all deps: ' + err.status);
        });


        $scope.findDepName = function(departmentId){
            for(var i = 0; i < $scope.dep.length; i++){
                if(departmentId === $scope.dep[i].departmentId){
                    return $scope.dep[i].departmentName;
                }
            }
        };

        $scope.findManName = function(managerId){

            for(var i = 0; i < $scope.employees.length; i++){
                if(managerId === $scope.employees[i].managerId){
                    return $scope.employees[i].firstName +" "+ $scope.employees[i].lastName ;
                }
            }
        };

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
    }]);