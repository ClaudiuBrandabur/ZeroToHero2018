app.controller('DescriptionController', ['$rootScope', '$scope', function ($rootScope, $scope) {
    $scope.title = 'Two Way Binding Demo';
    $scope.childtemplate = 'templates/childscope.html';
    $scope.resetFirstVariable = function() {
        $scope.firstVariable = undefined;
    };
    $scope.setFirstVariable = function(val) {
        $scope.firstVariable = val;
    };
    $scope.toggle = false;
    $scope.toggleDescriptionShow= function () {
        if($scope.toggle == false)
            $scope.toggle = true;
        else if($scope.toggle == true)
            $scope.toggle = false;
        }
}]);


