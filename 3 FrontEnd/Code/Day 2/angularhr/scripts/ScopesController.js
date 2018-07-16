app.controller('ScopesController',['$rootScope','$scope',function ($scope) {

    $scope.title = 'Two Way Binding Demo';
    $scope.childtemplate ='childscope.html';
    $scope.resetFirstVariable = function() {
        $scope.firstVariable = undefined;
    };
    $scope.setFirstVariable = function(val) {
        $scope.firstVariable = val;
    };

}]);