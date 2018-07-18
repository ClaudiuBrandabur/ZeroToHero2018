hrApp.controller('UserController', ['$scope', '$location', 'userService', function($scope, $location, userService){
    "use strict";
    $scope.back = function() {
        $location.url('/');
    };
    $scope.reset = function() {
        $scope.fname = "";
        $scope.lname = "";
        $scope.id = "";
        $scope.age = "";
    };

    $scope.user = function(fname, lname, age, id) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.id = id;
    };

    $scope.save = function() {
        userService.push( new $scope.user($scope.fname, $scope.lname, $scope.age, $scope.id));
        alert("Saved");
        $scope.users = userService;
    };
    $scope.users = [];
    $scope.showList = false;

    $scope.show = function(){
        if ( $scope.showList === true )
            $scope.showList = false;
        else
            $scope.showList = true;
    };
}]);
