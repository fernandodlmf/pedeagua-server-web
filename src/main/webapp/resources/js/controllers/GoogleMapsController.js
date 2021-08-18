angular.module('PedeAgua').controller('googleMapsController', function($scope, $mdDialog, NgMap, agenteEmpresa, agente){


    $scope.googleMapsUrl = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBfUdNnz4qBqdGnV4z9__zRSVtjcE6ox-E";

    $scope.origin = agenteEmpresa.data.endereco+", "+agenteEmpresa.data.cidade+" - "+ agenteEmpresa.data.uf +", "+ agenteEmpresa.data.cep;
    console.log("agenteEmpresa: "+JSON.stringify(agenteEmpresa.data));
    console.log("agente: "+JSON.stringify(agente.data));
    $scope.destination = agente.data.enderecos[0].rua+", "+agente.data.enderecos[0].cidade+" - "+ agente.data.enderecos[0].uf +", "+ agente.data.enderecos[0].cep;





    NgMap.getMap().then(function(map) {

        console.log(map);
        console.log(map.getCenter());
        console.log('markers', map.markers);
        console.log('shapes', map.shapes);
    });


    $scope.showAlert = function(titulo, descricao) {
        alert = $mdDialog.alert({
            title: titulo,
            textContent: descricao,
            ok: 'ENTENDI!'
        });

        $mdDialog.show(alert);
    };

});
