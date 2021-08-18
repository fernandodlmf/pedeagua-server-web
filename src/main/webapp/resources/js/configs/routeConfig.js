angular.module('PedeAgua').config(function($routeProvider){

    $routeProvider.when("/info/inicial", {
        templateUrl: "inicial"
    });

    $routeProvider.when("/info/login", {
        templateUrl: "login",
        controller: "loginController"
    });

    $routeProvider.when("/info/minhaConta", {
        templateUrl: "minhaConta",
        controller: "minhaContaController",
        resolve: {
            empresa: function(minhaContaService){
                return minhaContaService.getEmpresaPorEmail(Object.keys(JSON.parse(localStorage.token))[0]);
            }
        }
    });

    $routeProvider.when("/info/produtos", {
        templateUrl: "produtos",
        controller: "produtosController",
        resolve: {
            produtos: function(produtoService) {
                return produtoService.getProdutos();
            },
            empresa: function(produtoService) {
                return produtoService.getProdutosPorEmpresa(1);
            }
        }
    });

    $routeProvider.when("/info/pedidos", {
        templateUrl: "pedidos",
        controller: "pedidosController",
        resolve: {
            pedidos: function(pedidoService) {
                return pedidoService.getPedidosPorEmpresa(1);
            }
        }
    });

    $routeProvider.when("/info/pedidoDetalhes/:pedido", {
        templateUrl: "pedidoDetalhes",
        controller: "pedidoDetalhesController",
        resolve: {
            pedido: function($route){
                return $route.current.params.pedido;
            }
        }
    });


    $routeProvider.when("/info/clienteDetalhes/:cod_cliente", {
        templateUrl: "clienteDetalhes",
        controller: "clienteDetalhesController",
        resolve: {
            agentes: function($route, agenteService){
                return agenteService.getAgentePorCodigo($route.current.params.cod_cliente);
            }
        }
    });

    $routeProvider.when("/info/googleMaps/:cod_cliente", {
        templateUrl: "googleMaps",
        controller: "googleMapsController",
        resolve: {
            agente: function($route, agenteService){
                return agenteService.getAgentePorCodigo($route.current.params.cod_cliente);
            },
            agenteEmpresa: function(empresaService){
                return empresaService.getEmpresaPorEmail(Object.keys(JSON.parse(localStorage.token))[0]);
            }
        }
    });

    $routeProvider.otherwise({
        redirectTo: "/info/inicial"
    });


});
