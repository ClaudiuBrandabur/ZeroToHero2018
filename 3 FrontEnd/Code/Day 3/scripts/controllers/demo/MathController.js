hrApp.controller('MathController', ['$scope', 'MathService', function($scope, MathService){
    $scope.nrA = 0;
    $scope.nrB = 0;
    // $scope.calculate = function() {
    //         $scope.op1 = $scope.nrA + $scope.nrB;
    //         $scope.op2 = $scope.nrA - $scope.nrB;
    //         $scope.op3 = $scope.nrA * $scope.nrB;
    //         $scope.op4 = $scope.nrA / $scope.nrB;


 //   }
        $scope.calculate = function () {
            $scope.op1 = MathService.add($scope.nrA, $scope.nrB);
            $scope.op2 = MathService.substract($scope.nrA, $scope.nrB);
            $scope.op3 = MathService.multiply($scope.nrA, $scope.nrB);
            $scope.op4 = MathService.divide($scope.nrA, $scope.nrB);
        }
}]);
