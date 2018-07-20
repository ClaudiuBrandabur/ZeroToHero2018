var hrApp = angular.module('hrApp', ['ngRoute']);


hrApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
        templeteUrl: 'views/main.html',
        controller: 'MainController'
        })
        .when('/numbers', {
            redirectTo: '/math'
        })
        .when('/math', {
        templateUrl: 'views/demo/math.html',
        controller: 'MathController'
        })

        .when('/demoRequest', {
            templateUrl: 'views/demo/request.html',
            controller: 'RequestController'
        })
        .when('/employeeslist', {
        templateUrl: 'views/employeelist.html',
        controller: 'EmployeeListController'
    })
        .when('/employeeview/:employeeId', {
            templateUrl: 'views/employeeview.html',
            controller: 'EmployeeViewController'
        })
        .when('/user', {
            templateUrl: 'views/user.html',
            controller: 'UserController'
        });

}]);


