angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    $scope.pageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
        });
    };

    $scope.next = function () {
        $scope.pageIndex++;
        if($scope.productsPage.totalPages < $scope.pageIndex){
            alert("Already at last page");
        } else {
            $scope.loadProducts($scope.pageIndex)
        }
    };

    $scope.previous = function () {
        $scope.pageIndex--;
        if($scope.pageIndex < 1){
           $scope.pageIndex = 1;
        } else {
           $scope.loadProducts($scope.pageIndex)
        }
    };

    $scope.delete = function (product) {
        $http.get(contextPath + 'products/delete/'+product.id)
             .then(function (response) {
                 $scope.loadProducts($scope.pageIndex)
        });
    };

    $scope.loadProducts(1);

});