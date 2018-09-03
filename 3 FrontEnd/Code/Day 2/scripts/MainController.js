angular.module('hrApp').controller('MainController', ['$rootScope', '$scope', function ($rootScope, $scope) {
    $scope.someValue="someValue";
    console.log($scope.someValue);

}]);