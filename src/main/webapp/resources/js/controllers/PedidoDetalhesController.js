angular.module('PedeAgua').controller('pedidoDetalhesController', function($scope, pedido){
    var self = this;
    //$scope.listaAgentes = agentes.data;
    //console.log($scope.listaAgentes);
    $scope.pedido = JSON.parse(pedido);
    $scope.pedido.data_pedido = dataAtualFormatada($scope.pedido.data_pedido);
    $scope.pedido.data_entrega = dataAtualFormatada(convertDigitIn($scope.pedido.data_entrega));
    $scope.pedido.hora_pedido = msToTime($scope.pedido.hora_pedido);
    $scope.pedido.hora_entrega = msToTime($scope.pedido.hora_entrega);

    function dataAtualFormatada(data){
        console.log("data antes: "+data);
        var data = new Date(Date.parse(data.replace('-','/','g')));
        var dia = data.getDate();
        if (dia.toString().length == 1)
            dia = "0"+dia;
        var mes = data.getMonth()+1;
        if (mes.toString().length == 1)
            mes = "0"+mes;
        var ano = data.getFullYear();
        return dia+"/"+mes+"/"+ano;
    }

    function convertDigitIn(str){
        return str.replace('-','/','g');
    }

    function msToTime(ms) {
        var d = new Date(null)
        d.setMilliseconds(ms)
        return d.toLocaleTimeString("pt-BR")
    }


});
