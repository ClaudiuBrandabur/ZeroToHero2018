angular.module('hrApp').controller('MainController', ['$rootScope', '$scope', function ($rootScope, $scope) {
    $scope.someValue= undefined;
    console.log($scope.someValue);

}]);