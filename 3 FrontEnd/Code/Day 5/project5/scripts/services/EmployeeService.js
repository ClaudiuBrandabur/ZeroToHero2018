hrApp.service('EmployeeService', ['$http', 'CommonResourcesFactory', function ($http, CommonResourcesFactory) {
    return {
        findById: function (employeeId) {
            return $http.get(CommonResourcesFactory.findOneEmployeeUrl + employeeId)
                .success(function (data) {
                    return data;
                })
                .error(function (err) {
                    return {
                        "id": 100,
                        "firstName": "Steven",
                        "lastName": "King",
                        "email": "SKING",
                        "phoneNumber": "515.123.4567",
                        "hireDate": "1987-06-17",
                        "jobId": 1,
                        "salary": 24000.00,
                        "commissionPct": null,
                        "managerId": null,
                        "departmentId": 90
                    };
                });
        },
        findManagers: function () {
            return $http.get(CommonResourcesFactory.findAllEmployeesUrl)
                .success(function (data) {
                    return data;
                });
        },

        findManagersFromEmployees: function (data) {
            var managersId = {};
            var managerList = [];
            for (var i in data)
                if (data[i].managerId != null)
                    if (managersId[data[i].managerId.employeeId] == undefined) {
                        managersId[data[i].managerId.employeeId] = true;
                        managerList.push(data[i].managerId);
                    }
            return managerList;
        },

        findDepartments: function () {
            return $http.get(CommonResourcesFactory.findAllDepartmentsUrl)
                .success(function (data) {
                    return data;
                });
        },

        findJobs: function () {
            return $http.get(CommonResourcesFactory.findAllJobsUrl)
                .success(function (data) {
                    return data;
                });
        }
    }
}]);