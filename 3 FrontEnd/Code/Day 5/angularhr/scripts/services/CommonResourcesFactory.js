'use strict';
hrApp.factory('CommonResourcesFactory', function() {
        var baseUrl = "http://10.6.198.94:8089/MVCApp/mvc/";
        return {
            findAllDepartmentsUrl: baseUrl + "departments/all",
            findAllEmployeesUrl: baseUrl + "employees/all",
            findAllJobsUrl: baseUrl + "jobs/all",
            findAllLocationsUrl: baseUrl + "locations/all",
            findOneDepartmentUrl: baseUrl + "departments/one",
            findOneEmployeeUrl: baseUrl + "employees/one",
            findOneJobUrl: baseUrl + "jobs/one",
            findOneLocationUrl: baseUrl + "locations/one",
            deleteDepartmentUrl: baseUrl + "departments/one",
            deleteEmployeeUrl: baseUrl + "employees/one",
            deleteJobUrl: baseUrl + "jobs/one",
            deleteLocationUrl: baseUrl + "locations/one",
            addDepartmentUrl: baseUrl + "departments/create",
            addEmployeeUrl: baseUrl + "employees/create",
            addJobUrl: baseUrl + "jobs/create",
            addLocationUrl: baseUrl + "locations/create",
            editDepartmentUrl: baseUrl + "departments/edit",
            editEmployeeUrl: baseUrl + "employees/edit",
            editJobUrl: baseUrl + "jobs/edit",
            editLocationUrl: baseUrl + "locations/edit"
        };
    }
);

// hrApp.factory('CommonResourcesFactoryBackup', function () {
//         var baseUrl = "http://10.16.8.77:8181/hrapp/";
//         return {
//             findAllDepartmentsUrl: baseUrl + "departments",
//             findAllEmployeesUrl: baseUrl + "employees",
//             findAllJobsUrl: baseUrl + "jobs",
//             findAllLocationsUrl: baseUrl + "locations",
//             findOneDepartmentUrl: baseUrl + "departments/",
//             findOneEmployeeUrl: baseUrl + "employees/",
//             findOneJobUrl: baseUrl + "jobs/",
//             findOneLocationUrl: baseUrl + "locations/",
//             deleteDepartmentUrl: baseUrl + "departments",
//             deleteEmployeeUrl: baseUrl + "employees",
//             deleteJobUrl: baseUrl + "jobs",
//             deleteLocationUrl: baseUrl + "locations",
//             addDepartmentUrl: baseUrl + "departments",
//             addEmployeeUrl: baseUrl + "employees",
//             addJobUrl: baseUrl + "jobs",
//             addLocationUrl: baseUrl + "locations",
//             editDepartmentUrl: baseUrl + "departments",
//             editEmployeeUrl: baseUrl + "employees",
//             editJobUrl: baseUrl + "jobs",
//             editLocationUrl: baseUrl + "locations"
//         };
//     }
// );