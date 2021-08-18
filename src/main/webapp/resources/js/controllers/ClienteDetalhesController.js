angular.module('PedeAgua').controller('clienteDetalhesController', function($scope, agentes){
    var self = this;
    //$scope.listaAgentes = agentes.data;
    //console.log($scope.listaAgentes);
    $scope.cliente = agentes.data;
    console.log($scope.cliente);

});
