angular.module('hrApp').controller('MainController', ['$rootScope', '$scope', function ($rootScope, $scope) {
    $scope.someValue="aasd";
    console.log($scope.someValue);

}]);