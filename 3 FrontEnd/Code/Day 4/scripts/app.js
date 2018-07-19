var hrApp = angular.module('hrApp', []);

hrApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/colors', {
            templateUrl: 'views / colors.html' ,
            controller:'ColorsController'
});

}])