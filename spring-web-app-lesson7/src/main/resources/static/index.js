angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPage = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPage = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.generatePageIndexes = function(startPage, endPage){
       let arr =[];
       for(let i = startPage; i < endPage + 1; i++){
           arr.push(i);
       }
       return arr;
    }

    $scope.next = function () {
        $scope.pageIndex++;
        if($scope.productsPage.totalPages < currentPage){
            currentPage = $scope.productsPage.totalPages;
        } else {
            $scope.loadProducts(currentPage)
        }
    };

    $scope.previous = function () {
        currentPage--;
        if(currentPage < 1){
           currentPage = 1;
        } else {
           $scope.loadProducts(currentPage)
        }
    };

    $scope.delete = function (product) {
        $http.delete(contextPath + 'api/v1/products/' + product.id)
             .then(function (response) {
                 $scope.loadProducts(currentPage);
        });
    };

    $scope.createNewProduct = function (product) {
    console.log($scope.new_product)
        $http.post(contextPath + 'api/v1/products', $scope.new_product)
             .then(function successCallback (response) {
                 $scope.loadProducts(currentPage);
                 $scope.new_product = null;
        }, function failureCallback(response){
            alert(response.data.message);
        });
    };


        $scope.updateProduct = function (product) {
        console.log($scope.new_product)
            $http.put(contextPath + 'api/v1/products', $scope.old_product)
                 .then(function successCallback (response) {
                     $scope.loadProducts(currentPage);
                     $scope.old_product = null;
            }, function failureCallback(response){
                alert(response.data.message);
            });
        };

    $scope.loadProducts(1);

});