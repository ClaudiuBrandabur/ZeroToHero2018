app.controller('OtherController',['$rootScope','$scope',function($rootScope,$scope) {
    $scope.name='SCOPE';
    $scope.setTitle= function() {
        $scope.title=$scope.name;
    }
}]);