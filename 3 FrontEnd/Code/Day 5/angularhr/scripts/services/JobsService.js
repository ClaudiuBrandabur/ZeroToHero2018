hrApp.service('JobsService',['$http','CommonResourceFactory',function ($http,CommonResourcesFactory) {
    return {
        findByIdJob: function (jobId) {
            return $http.get(CommonResourcesFactory.findAllJobsUrl + jobId)
                .success(function (data) {
                    return data;
                })
                .error(function (data) {
                    return data;
                })
        }

    }
}]);