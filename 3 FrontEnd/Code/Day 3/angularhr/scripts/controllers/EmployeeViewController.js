hrApp.controller('EmployeeViewController', ['$scope', '$http', '$routeParams', 'CommonResourcesFactory', '$location'/*,'employeeId'*/,
    function($scope, $http,CommonResourcesFactory, $routeParams, $location /*,employeeId*/) {

        $scope.employee = {};


        // TODO #HR6 get employee by id
        $http.get(CommonResourcesFactory.findOneEmployeeUrl+$routeParams.employeeid)//.findOneEmployeeUrl)//.viewEmployee())//employeeId.findOneEmployeeUrl)//CommonResourcesFactory.findOneEmployeeUrl)
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