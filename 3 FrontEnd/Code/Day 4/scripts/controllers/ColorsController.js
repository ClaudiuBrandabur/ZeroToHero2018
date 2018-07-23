hrApp.controller('ColorsController', ['$scope', function ($scope) {

    $scope.colors = [
        {
            text: "muted",
            class: "text-muted",
            type: "strong"
        },
        {
            text: "primary",
            class: "text-primary",
            type: "strong"
        },
        {
            text: "success",
            class: "text-success",
            type: "strong"
        },
        {
            text: "info",
            class: "text-info",
            type: "boring"
        },
        {
            text: "warning",
            class: "text-warning",
            type: "boring"
        },
        {
            text: "danger",
            class: "text-danger",
            type: "boring"
        }
    ];
    $scope.selectedClass='';
    $scope.changeColor = function () {
        $scope.selected = $scope.selectedClass.class ;
    };
    // $scope.compare = function (a,b) {
    //     if(a.text.toUpperCase() < b.text.toUpperCase())
    //         return -1;
    //     else if(a.text.toUpperCase() > b.text.toUpperCase())
    //         return 1;
    //     else
    //         return 0;
    // };
    // $scope.sortare = function (text2) {
    //     if(text2 == '1'){
    //
    //     }
    // }


}]);