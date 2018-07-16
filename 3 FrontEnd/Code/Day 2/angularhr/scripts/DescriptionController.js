app.controller('DescriptionController',['$rootScope','$scope',function ($scope) {
    $scope.title = 'Two Way Binding Demo';
    $scope.childtemplate = 'templates/childscope.html';
    $scope.resetFirstVariable = function() {
        $scope.firstVariable = undefined;
    };
    $scope.setFirstVariable = function(val) {
        $scope.firstVariable = val;
    };

    $scope.descriptionShow = true;

    $scope.toggleDescriptionShow = function() {

        if($scope.descriptionShow === true){
            $scope.descriptionShow = false;
        }else{
            $scope.descriptionShow = true;
        }
    }

}]);
