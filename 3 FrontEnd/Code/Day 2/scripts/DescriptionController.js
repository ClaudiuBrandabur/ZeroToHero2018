angular.module('hrApp').controller('DescriptionController', ['$rootScope','$scope', function ($rootScope, $scope)
{
    $scope.title = 'Two Way Binding Demo';
    $scope.childtemplate = 'templates/childscope.html';
    $scope.resetFirstVariable = function () {
        $scope.resetFirstVariable = undefined;
    }
    $scope.resetFirstVariable = function (val) {
        $scope.firstVariable = val;
    }
}]);