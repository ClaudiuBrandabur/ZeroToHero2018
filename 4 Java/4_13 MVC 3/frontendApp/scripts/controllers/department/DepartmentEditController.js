'use strict';

hrApp.controller('DepartmentEditController', ['$scope', '$http', '$routeParams', '$filter', '$location', 'LocationService', 'DepartmentService',
    function($scope, $http, $routeParams, $filter, $location, LocationService, DepartmentService) {

        $scope.requiredErrorMessage = 'Please fill out this field';

        LocationService.findAll().then(function(res) {
            $scope.locations = res.data;
        }, function(err) {
            console.log('An error occurred while finding all locations: ' + err.status);
        });

        DepartmentService.findOne($routeParams.departmentId).then(function(res) {
            $scope.department = res.data;
            $scope.myLoc($scope.department.location)
        }, function(err) {
            console.log('An error occurred while finding department: ' + err.status);
        });


        $scope.myLoc = function(locationId) {

            LocationService.findOne(locationId).then(function (res) {
                $scope.location = res.data;
            }, function (err) {
                console.log('An error occurred while finding location: ' + err.status);
            });

        };
        /**
         * Reset department fields
         */
        $scope.reset = function() {
            $scope.department = {};
            $scope.location = {};
        };

        /**
         * Update a department
         * @param department - department to be updated
         */
        $scope.save = function(department) {
            DepartmentService.edit(department).then(function() {
                $location.url('/departmentView/' + $scope.department.departmentId);
            }, function(err) {
                console.log('An error occurred while editing department: ' + err.status);
            });
        };

    }]);