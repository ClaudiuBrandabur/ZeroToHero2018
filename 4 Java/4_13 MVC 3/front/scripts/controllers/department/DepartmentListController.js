'use strict';

hrApp.controller('DepartmentListController', ['$scope', '$http', '$route', '$location', 'DepartmentService', 'LocationService',
    function($scope, $http, $route, $location, DepartmentService, LocationService) {

        DepartmentService.findAll().then(function(res) {
            $scope.departments = res.data;
        }, function(err) {
            console.log('An error occurred while finding all departments: ' + err.status);
        });

        LocationService.findAll()
            .then(function (res) {
                $scope.locations = res.data;
            }, function (err) {
                console.log('An error occurred while finding the location: ' + err.status);
            });
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