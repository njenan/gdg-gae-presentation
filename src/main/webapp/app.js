var scope;

function init() {
    scope.ready = true;
    gapi.client.setApiKey('AIzaSyAXX7EAaYOEVLmShbAWuwTlS33b8I1i1Mk');
    gapi.client.load('jobs', 'v1', function () {
        scope.loaded = true;
    }, 'https://gdg-gae-presentation.appspot.com/_ah/api');
}


angular.module('gdg-gae-presentation', []).controller('controller', function ($scope) {
    scope = $scope;

    $scope.load = function () {
        gapi.client.jobs.getJobs().then(function (resp) {
            $scope.employees = resp.result.items;
            $scope.$apply();
        });
    };

    $scope.add = function (name, age, job) {
        gapi.client.jobs.createJob({
            name: name,
            age: age,
            job: job
        }).then(function (resp) {
            $scope.name = undefined;
            $scope.age = undefined;
            $scope.job = undefined;

            gapi.client.jobs.getJobs().then(function (resp) {
                $scope.employees = resp.result.items;
                $scope.$apply();
            });
        });
    };

    $scope.login = function () {
        function handleClientLoad() {
            gapi.client.setApiKey(apiKey);
            window.setTimeout(checkAuth, 1);
        }

        function checkAuth() {
            gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
        }

        function handleAuthResult(authResult) {
            var authorizeButton = document.getElementById('authorize-button');
            if (authResult && !authResult.error) {
                authorizeButton.style.visibility = 'hidden';
                makeApiCall();
            } else {
                authorizeButton.style.visibility = '';
                authorizeButton.onclick = handleAuthClick;
            }
        }

        gapi.auth.authorize({
            client_id: '36625316854-48fa4cqv522tt775f1dkiq83no3dcsct.apps.googleusercontent.com',
            scope: ['https://www.googleapis.com/auth/userinfo.email'],
            immediate: false
        }, $scope.load);
    }
});