hrApp.controller('RequestController', ['$scope', '$http', function($scope, $http){

    $scope.jobList = [];

        $http.get("http://10.6.198.94:8089/MVCApp/mvc/jobs/all")
            .success(function (data, status, headers, config) {
                $scope.jobList = data;
            })
            .error(function (data, status, headers, config) {
                alert("error:" + status);
            })


}]);
