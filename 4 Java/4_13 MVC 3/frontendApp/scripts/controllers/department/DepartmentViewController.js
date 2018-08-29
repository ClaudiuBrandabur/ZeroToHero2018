'use strict';

hrApp.controller('DepartmentViewController', ['$scope', '$http', '$routeParams', '$location', 'DepartmentService','LocationService',
    function($scope, $http, $routeParams, $location, DepartmentService,LocationService) {

        /**
         * Retrieve a department
         * @param departmentId - identifier of the department to be retrieved
         */
        DepartmentService.findOne($routeParams.departmentId)
            .then(function(res) {
                $scope.department = res.data;
                $scope.myFunc($scope.department.location);
            }, function(err) {
                console.log('An error occurred while finding the department: ' + err.status);
            });


        $scope.myFunc = function(location) {

            LocationService.findOne(location)
                .then(function (res) {
                    $scope.location = res.data;
                }, function (err) {
                    console.log('An error occurred while finding the location: ' + err.status);
                });

        };




        /**
         * Navigate back to department list page
         */
        $scope.back = function() {
            $location.url('/departmentList');
        };

    }]);