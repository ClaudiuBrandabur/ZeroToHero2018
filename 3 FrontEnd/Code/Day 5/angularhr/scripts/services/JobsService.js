hrApp.service('JobsService',['$http','CommonResourceFactory',function ($http,CommonResourcesFactory) {
    return {
        findByJobs: function () {
            return $http.get(CommonResourcesFactory.findAllJobsUrl)
                .success(function (data) {
                    return data;
                })
                .error(function (data) {
                    return data;
                })
        }

    }
}]);