hrApp.controller('MathController', ['$scope','MathService', function($scope,MathService){
    
    $scope.calculate = function() {
//        TODO #8 calculate op1, op2, op3, op4
//
        $scope.op1 = MathService.add($scope.nr1,$scope.nr2);
        $scope.op2 = MathService.substract($scope.nr1,$scope.nr2);
        $scope.op3 = MathService.multiply($scope.nr1,$scope.nr2);
        $scope.op4 = MathService.divide($scope.nr1,$scope.nr2);

        // TODO #13 refactor your calculations using MathService

        // $scope.op1 = $scope.nr1 + $scope.nr2;
        // $scope.op2 = $scope.nr1 - $scope.nr2;
        // $scope.op3 = $scope.nr1 * $scope.nr2;
        // $scope.op4 = $scope.nr1 / $scope.nr2;


    }



}]);
