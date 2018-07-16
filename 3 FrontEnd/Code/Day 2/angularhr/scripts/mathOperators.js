app.controller('mathOperators', ['$rootScope', '$scope', function ($rootScope, $scope) {
    $scope.sum = $scope.numA + $scope.numB;
    $scope.dif = $scope.numA - $scope.numB;
    $scope.prod = $scope.numA * $scope.numB;
    $scope.div = $scope.numA / $scope.numB;
}]);