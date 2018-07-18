hrApp.controller('MathController', ['$scope', function ($scope) {
    $scope.nA = 0;
    $scope.nB = 0;
    $scope.calculate = function () {
//        TODO #8 calculate op1, op2, op3, op4
        $scope.op1 = $scope.nA + $scope.nB;
        $scope.op2 = $scope.nA - $scope.nB;
        $scope.op3 = $scope.nA * $scope.nB;
        $scope.op4 = $scope.nA / $scope.nB;
//        TODO #13 refactor your calculations using MathService
//         $scope.op1 = function(a,b) {
//             return MathService.add(a,b);
//         }
//         $scope.op2 = function(a,b) {
//             return MathService.substract(a,b);
//         }
//         MathService.multiply = function(a,b) {
//             return a*b;
//         }
//         $scope.op3 = function(a,b) {
//             return MathService.multiply(a,b);
//         }
//         MathService.divide = function(a,b) {
//             return a/b;
//         }
//         $scope.op4 = function(a,b) {
//             return MathService.divide(a,b);
//         }
    }

}]);
