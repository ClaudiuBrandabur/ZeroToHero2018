'use strict';

hrApp.controller('DepartmentViewController', ['$scope', '$http', '$routeParams', '$location', 'DepartmentService', 'LocationService',
    function($scope, $http, $routeParams, $location, DepartmentService, LocationService) {

        /**
         * Retrieve a department
         * @param departmentId - identifier of the department to be retrieved
         */

        DepartmentService.findAll().then(function(res) {
            $scope.departments = res.data;
        }, function(err) {
            console.log('An error occurred while finding all departments: ' + err.status);
        });

        DepartmentService.findOne($routeParams.departmentId)
            .then(function(res) {
                $scope.department = res.data;
            }, function(err) {
                console.log('An error occurred while finding the department: ' + err.status);
            });
        LocationService.findAll()
            .then(function (res) {
                $scope.locations = res.data;
            }, function (err) {
                console.log('An error occurred while finding the location: ' + err.status);
            });

        /**
         * Navigate back to department list page
         */
        $scope.back = function() {
            $location.url('/departmentList');
        };
        $scope.findLocationStreetAddress = function (locationId) {
            for(var i in $scope.locations){
                if($scope.locations[i].locationId === locationId){
                    $scope.streetAddress = $scope.locations[i].streetAddress;
                    return $scope.streetAddress;
                }
            }
        }

        $scope.findLocationPostalCode = function (locationId) {
            for(var i in $scope.locations){
                if($scope.locations[i].locationId === locationId){
                    $scope.postalCode = $scope.locations[i].postalCode;
                    return $scope.postalCode;
                }
            }
        }

        $scope.findLocationCity = function (locationId) {
            for(var i in $scope.locations){
                if($scope.locations[i].locationId === locationId){
                    $scope.city = $scope.locations[i].city;
                    return $scope.city;
                }
            }
        }

        $scope.findStateProvince = function (locationId) {
            for(var i in $scope.locations){
                if($scope.locations[i].locationId === locationId){
                    $scope.stateProvince = $scope.locations[i].stateProvince;
                    return $scope.stateProvince;
                }
            }
        }
    }]);