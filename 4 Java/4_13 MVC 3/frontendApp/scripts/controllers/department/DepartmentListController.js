'use strict';

hrApp.controller('DepartmentListController', ['$scope', '$http', '$route', '$location', 'DepartmentService','LocationService',
    function($scope, $http, $route, $location, DepartmentService,LocationService) {


        LocationService.findAll().then(function(res) {
            $scope.locations = res.data;
            $scope.loadDep();
        }, function(err) {
            console.log('An error occurred while finding all locations: ' + err.status);
        });


        $scope.loadDep = function() {
            DepartmentService.findAll().then(function (res) {
                $scope.departments = res.data;
            }, function (err) {
                console.log('An error occurred while finding all departments: ' + err.status);
            });
        };


        $scope.findMyDeps = function(locationId){
            for(var i = 0; i < $scope.locations.length; i++ ){
                if(locationId === $scope.locations[i].locationId){

                    if($scope.locations[i].postalCode !== null && $scope.locations[i].stateProvince !== null ) {
                        return $scope.locations[i].streetAddress + ", " +
                            $scope.locations[i].postalCode + ", " +
                            $scope.locations[i].city + ", " +
                            $scope.locations[i].stateProvince;
                    }else{
                        if($scope.locations[i].postalCode === null && $scope.locations[i].stateProvince === null ) {
                            return $scope.locations[i].streetAddress + ", " +
                                " - , " +
                                $scope.locations[i].city + ", " +
                                " - ";
                        }
                        if($scope.locations[i].postalCode === null){
                            return $scope.locations[i].streetAddress + ", " +
                                " - , " +
                                $scope.locations[i].city + ", " +
                                $scope.locations[i].stateProvince;
                        }
                        if($scope.locations[i].stateProvince === null){
                            return $scope.locations[i].streetAddress + ", " +
                                $scope.locations[i].postalCode + ", " +
                                $scope.locations[i].city + ", " +
                                " - ";
                        }

                    }
                }
            }
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