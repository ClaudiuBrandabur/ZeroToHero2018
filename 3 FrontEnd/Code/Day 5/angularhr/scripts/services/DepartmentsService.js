hrApp.service('DepartmentsService',['$http','CommonResourceFactory',function ($http,CommonResourcesFactory) {
    return {
        findByDepartment: function () {
            return $http.get(CommonResourcesFactory.findAllDepartmentsUrl)
                .success(function (data) {
                    return data;
                })
                .error(function (data) {
                    return data;
                })
        }

    }
}]);