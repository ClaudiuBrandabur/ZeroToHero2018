hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', 'CommonResourcesFactory' ,'$location',
    function($scope, $http, $routeParams, CommonResourcesFactory, $location) {

        $scope.employee = {};


        $http.get(CommonResourcesFactory.findOneEmployeeUrl + "/" + $routeParams.employeeId)
            .success(function(data,status,headers,config){
                $scope.employee = data;
        })
            .error(function (data,status,headers,config) {
                alert("error:" + status);
        });



        $scope.back = function() {
            $location.url("/employeeslist");
        }

    }]);