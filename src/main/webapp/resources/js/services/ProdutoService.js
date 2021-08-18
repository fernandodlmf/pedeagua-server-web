angular.module('PedeAgua').factory('produtoService', function($http) {

    var _salvarProdutosPorEmpresa = function(produto) {
        return $http.post('http://localhost:8080/spring4-1/info/produtoPorEmpresa', produto);

    };

    var _getProdutos = function() {
        return $http.get('http://localhost:8080/spring4-1/info/produto');

    };

    var _getProdutosPorEmpresa = function(cod_empresa) {
        return $http.get('http://localhost:8080/spring4-1/info/produto?cod_empresa='+cod_empresa);
    };

    return {

        getProdutos: _getProdutos,
        getProdutosPorEmpresa: _getProdutosPorEmpresa,
        salvarProdutosPorEmpresa: _salvarProdutosPorEmpresa

    }

});