angular.module('PedeAgua').controller('produtosController', function($scope, empresa, produtos, $http, produtoService){
    var self = this;

    console.log(produtos.data);
    console.log(empresa.data);

    $scope.ListprodutoAgua = [];
    $scope.ListprodutoGas = [];
    $scope.produtoAgua = {};
    $scope.produtoGas = {};
    var countAgua = 0;
    var countGas = 0;


    for(var i = 0; i < (produtos.data.length - 1); i++) {

        for(var e = 0; e < (empresa.data.length); e++) {
            if(produtos.data[i].cod_produto == empresa.data[e].cod_produto) {
                produtos.data[i].cod_empresa_produto = empresa.data[e].cod_empresa_produto;
                produtos.data[i].preco = empresa.data[e].preco;
                produtos.data[i].estoque = empresa.data[e].estoque;
            }
        }
    }


    for (var i = 0; i < produtos.data.length; i++){
        if (produtos.data[i].tipo == 0) {
            $scope.ListprodutoAgua.push(produtos.data[i]);
            $scope.produtoAgua = angular.copy($scope.ListprodutoAgua[countAgua]);
        } else if (produtos.data[i].tipo == 1) {
            $scope.ListprodutoGas.push(produtos.data[i]);
            $scope.produtoGas = angular.copy($scope.ListprodutoGas[countGas]);
        }
    }

    $scope.proximoProduto = function (tipo) {
        if(tipo == 0) {
            if (countAgua < ($scope.ListprodutoAgua.length - 1)) {
                countAgua += 1;
                $scope.produtoAgua = angular.copy($scope.ListprodutoAgua[countAgua]);

                console.log("count:"+countAgua)

            } else if(countAgua == ($scope.ListprodutoAgua.length - 1)) {
                countAgua = 0
                $scope.produtoAgua = angular.copy($scope.ListprodutoAgua[countAgua]);
            }

        } else if(tipo == 1) {
            if (countGas < ($scope.ListprodutoGas.length - 1)) {
                countGas += 1;
                $scope.produtoGas = angular.copy($scope.ListprodutoGas[countGas]);
                console.log("count:"+countGas)

            } else if(countGas == ($scope.ListprodutoGas.length - 1)) {
                countGas = 0
                $scope.produtoGas = angular.copy($scope.ListprodutoGas[countGas]);
            }
        }

    };

    $scope.anteriorProduto = function (tipo) {
        if(tipo == 0) {
            if ((countAgua <= ($scope.ListprodutoAgua.length - 1)) && (countAgua > 0 )) {
                countAgua -= 1;
                $scope.produtoAgua = angular.copy($scope.ListprodutoAgua[countAgua]);
                console.log("count:"+countAgua)

            } else if(countAgua == 0) {
                countAgua = ($scope.ListprodutoAgua.length - 1);
                $scope.produtoAgua = angular.copy($scope.ListprodutoAgua[countAgua]);
            }

        } else if(tipo == 1) {
            if ((countGas <= ($scope.ListprodutoGas.length - 1)) && (countGas > 0 )) {
                countGas -= 1;
                $scope.produtoGas = angular.copy($scope.ListprodutoGas[countGas]);
                console.log("count:"+countGas)

            } else if(countGas == 0) {
                countGas = ($scope.ListprodutoGas.length - 1);
                $scope.produtoGas = angular.copy($scope.ListprodutoGas[countGas]);
            }
        }

    };

    $scope.salvarProdutosPorEmpresa = function(tipo) {


        if(tipo == 0) {
            console.log("$scope.produtoAgua:" + $scope.produtoAgua);
            $scope.produtoAgua.cod_empresa = 1;
            $scope.produtoAgua.cod_produto = $scope.ListprodutoAgua[countAgua].cod_produto;
            console.log($scope.produtoAgua);
            produtoService.salvarProdutosPorEmpresa($scope.produtoAgua).success(function(data){
                console.log("data:" + data);
                $scope.ListprodutoAgua[countAgua] = angular.copy($scope.produtoAgua);
            });
        } else if(tipo == 1) {
            console.log("$scope.produtoGas:" + $scope.produtoGas);
            $scope.produtoGas.cod_empresa = 1;
            $scope.produtoGas.cod_produto = $scope.ListprodutoGas[countGas].cod_produto;
            produtoService.salvarProdutosPorEmpresa($scope.produtoGas).success(function(data){
                console.log("data:" + data)
                $scope.ListprodutoGas[countGas] = angular.copy($scope.produtoGas);

            });
        }

    }

});
