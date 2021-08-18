(function() {
    angular.module('PedeAgua').factory('pedidoService', function($http) {

        var _salvarPedidosPorEmpresa = function(pedido) {
            return $http.post('http://localhost:8080/spring4-1/info/pedidoPorEmpresa', pedido);
        };

        var _getPedidos = function() {
            return $http.get('http://localhost:8080/spring4-1/info/pedido');
        };

        var _getPedidosPorEmpresa = function(cod_empresa) {
            return $http.get('http://localhost:8080/spring4-1/info/pedido?cod_empresa='+cod_empresa);
        };

        return {
            getPedidos: _getPedidos,
            getPedidosPorEmpresa: _getPedidosPorEmpresa,
            salvarPedidosPorEmpresa: _salvarPedidosPorEmpresa
        }


    });

})();