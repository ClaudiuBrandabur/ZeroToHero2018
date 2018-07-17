var hrApp = angular.module('hrApp', ['ngRoute']);

// TODO #3 add default route for main page

hrApp.config(['$routeProvider'], function($routeProvider){
    $routeProvider.when('/', {
        templeteUrl: 'views/main.html',
        controller: 'MainController'
    })
    $routeProvider.when('/numbers', {
        redirectTo: 'math'
    })
    $routeProvider.when('/math', {
        templateUrl: 'views/demo/math.html',
        controller: 'MathController'
    })

})


// TODO #6 add route for mathematical operations
    .when('/demoRequest', {
        templateUrl: 'views/demo/request.html',
        controller: 'RequestController'
    })


// TODO #HR1 add routes for Employees List page and Employee View page

