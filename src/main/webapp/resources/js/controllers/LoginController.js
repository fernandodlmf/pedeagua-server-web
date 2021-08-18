angular.module('PedeAgua').controller('loginController', function($scope, empresaService, $mdDialog){

    var resposta;

    $scope.login = function(empresa) {
        if (validaEmail(empresa.email) == false) {
            $scope.showAlert("Informação", "Ops! Parece que seu email está incorreto.")
        } else {
            empresaService.empresaLogin(empresa.email, empresa.senha).success(function(data) {
                resposta = JSON.stringify(data);
                console.log("resp:" + resposta);

                if (Object.keys(JSON.parse(resposta)).length === 0) {
                    $scope.showAlert("INFORMAÇÃO", "OPS! Parece que seus dados estão incorretos.");
                } else {
                    preencheLocalStorage(resposta);
                }
            });
        }
    }

    function determinaClienteOuVendedor(resposta) {
        var nome = Object.keys(JSON.parse(localStorage.token))[0];
        var obj = JSON.parse(localStorage.token);
        var ultimaLetra = obj[nome].substr(obj[nome].length - 1);

        return ultimaLetra;
    }

    function preencheLocalStorage(resposta) {
        if (Object.keys(JSON.parse(resposta)).length === 0) {
            $scope.showAlert("INFORMAÇÃO", "OPS! Parece que seus dados estão incorretos.");

        } else {
            localStorage.removeItem('token');
            localStorage.setItem('token', resposta);

            //if (determinaClienteOuVendedor(resposta) == "e") {
                window.location.href="#/info/pedidos";
            //} else if (determinaClienteOuVendedor(resposta) == "c") {
                //$scope.showAlert("INFORMAÇÃO", "OPS! Parece que você é um cliente.");
            //}
        }

    }

    function validaEmail(email){
        var str = email;
        var filtro = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if(filtro.test(str)) {
            return true;
        } else {
            return false;
        }
    }

    $scope.showAlert = function(titulo, descricao) {
        alert = $mdDialog.alert({
            title: titulo,
            textContent: descricao,
            ok: 'ENTENDI!'
        });

        $mdDialog.show(alert);
    };

});
