colorApp.controller('ColorsController', ['$scope', function($scope) {

    $scope.colors = [
        {
            "text": "muted",
            "class": "text-muted",
            "type": "strong"
        },
        {
            "text": "primary",
            "class": "text-primary",
            "type": "strong"
        },
        {
            "text": "success",
            "class": "text-success",
            "type": "strong"
        },
        {
            "text": "info",
            "class": "text-info",
            "type": "boring"
        },
        {
            "text": "warning",
            "class": "text-warning",
            "type": "boring"
        },
        {
            "text": "danger",
            "class": "text-danger",
            "type": "boring"
        }
    ];


    $scope.selectedColor ='';
    $scope.changeColor = function () {
        $scope.colorClass = $scope.selectedColor.class;

    };

    $scope.cmp = function (a,b) {
        if(a.text < b.text)
            return -1;
        if(a.text > b.text)
            return 1;
    };
    
    $scope.mysort = function (text2) {
        if (text2 === "a"){
            $scope.colors.sort($scope.cmp);
        }
    };




}]);
