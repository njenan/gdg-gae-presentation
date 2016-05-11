angular.module('gdg-gae-presentation', []).controller('controller', function ($scope, $http) {
    $http.get('/_ah/api/jobs/v1/employees').then(function (resp) {
        $scope.employees = resp.data.items;

    });

    $scope.add = function (name, age, job) {
        $http.post('/_ah/api/jobs/v1/employees', {
            name: name,
            age: age,
            job: job
        }).then(function () {
            $scope.name = '';
            $scope.age = '';
            $scope.job = '';

            $http.get('/_ah/api/jobs/v1/employees').then(function (resp) {
                $scope.employees = resp.data.items;
            });
        });
    }
});