hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', 'CommonResourcesFactory', '$location',
    function($scope, $http, $routeParams,CommonResourcesFactory, $location ) {

        $scope.employee = {};


        // TODO #HR6 get employee by id
        $http.get(CommonResourcesFactory.findOneEmployeeUrl+"/"+$routeParams.employeeId)
            .success(function (data,status,headers,config) {
                $scope.employee = data;
            })
            .error(function (data,status,headers,config) {
                alert("Error:" + status);
            });



        $scope.back = function() {
            // TODO back to Employee List page
           $location.url('/employeelist');
        }



    }]);