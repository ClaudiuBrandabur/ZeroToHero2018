hrApp.controller('MathController', ['$scope', function($scope) {

    $scope.calculate = function () {
        $scope.op1 = $scope.nrA + $scope.nrB;
        $scope.op2 = $scope.nrA - $scope.nrB;
        $scope.op3 = $scope.nrA * $scope.nrB;
        $scope.op4 = $scope.nrA / $scope.nrB;
    }
}]);
//        TODO #8 calculate op1, op2, op3, op4

//        TODO #13 refactor your calculations using MathService



