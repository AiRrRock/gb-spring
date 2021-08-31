angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';
    let cartId = 1;

    $scope.loadProducts = function () {
        $http({
            url: contextPath + 'api/v1/carts',
            method: 'GET',
            params: {
                cId: cartId
            }
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    };

    $scope.decrement = function (productId){
        $http({
                url: contextPath + 'api/v1/carts/dec/',
                method: 'GET',
                params: {
                    cId: cartId,
                    pId: productId
                }
            }).then(function (response) {
                $scope.loadProducts()
            });
    }

        $scope.increment = function (productId){
            $http({
                    url: contextPath + 'api/v1/carts/add/',
                    method: 'GET',
                    params: {
                        cId: cartId,
                        pId: productId
                    }
                }).then(function (response) {
                    $scope.loadProducts()
                });
        }

        $scope.delete = function (productId){
           $http({
             url: contextPath + 'api/v1/carts/',
             method: 'DELETE',
             params: {
                cId: cartId,
                pId: productId
             }
           }).then(function (response) {
               $scope.loadProducts()
           });
           }

    $scope.loadProducts();
});