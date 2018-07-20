colorApp.controller('FormsController', ['$scope', function ($scope) {
    $scope.state = "You have not yet submitted the form!";
    $scope.submitFunction = function () {
        $scope.state = "YOU HAVE SUBMITTED THE FORM!!!!";
    }
}]);