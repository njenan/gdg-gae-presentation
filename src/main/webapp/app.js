angular.module('gdg-gae-presentation', []).controller('controller', function ($scope, $http) {
    $scope.text = "Loading...";
    $http.get('/_ah/api/jobs/v1/hello').then(function (resp) {
        $scope.text = resp.data.text;
    });
});