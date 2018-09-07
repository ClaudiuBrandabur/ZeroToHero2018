'use strict';

hrApp.controller('DepartmentListController', ['$scope', '$http', '$route', '$location', 'DepartmentService',
    function($scope, $http, $route, $location, DepartmentService) {

        LocationService.findAll()
            .then(function(res) {
                $scope.locations = res.data;
                $scope.findAllDeps();
            }, function(err) {
                console.log('An error occurred while finding all departments: ' + err.status);
            });

        $scope.findAllDeps = function() {
            DepartmentService.findAll().then(function (res) {
                $scope.departments = res.data;
            }, function (err) {
                console.log('An error occurred while finding all departments: ' + err.status);
            });
        };

        /**
         * Navigate to view page of a department
         * @param departmentId - identifier of the department to be viewed
         */
        $scope.view = function(departmentId) {
            $location.url('/departmentView/' + departmentId);
        };

        /**
         * Navigate to edit page of an department
         * @param departmentId - identifier of the department to be edited
         */
        $scope.edit = function(departmentId) {
            $location.url('/departmentEdit/' + departmentId);
        };

        /**
         * Delete a department
         * @param departmentId - identifier of the department to be deleted
         */
        $scope.delete = function(departmentId) {
            DepartmentService.delete(departmentId).then(function() {
                alert('Department has been deleted successfully');
                $route.reload();
            }, function(err) {
                console.log('An error occurred while deleting department: ' + err.status);
            });
        };

    }]);