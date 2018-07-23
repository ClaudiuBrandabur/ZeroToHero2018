colorApp.controller('FormsController',['$scope',function ($scope) {
    $scope.submit = function () {
        if($scope.userType) {
            alert("Information has been submitted");
        }
    }
}]);