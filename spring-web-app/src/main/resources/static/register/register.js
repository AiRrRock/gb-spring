angular.module('market-front').controller('registerController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.register = function () {
        if ($scope.new_user == null) {
            alert('Форма не заполнена');
            return;
        }
        $http.post(contextPath + 'api/v1/users', $scope.new_user)
            .then(function successCallback (response) {
                $scope.new_user = null;
                $location.path('/store');
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
            });
    }
});