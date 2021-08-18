angular.module('PedeAgua').controller('minhaContaController',function($scope, empresa, adicionaisService, empresaService, $mdDialog){
    $scope.estados = adicionaisService.getEstados();
    $scope.sexos = adicionaisService.getSexos();
    $scope.tipoEnderecos = adicionaisService.getTipoEnderecos();

    $scope.empresa = empresa.data;
    $scope.empresaUsuario = {};

    $scope.salvarEmpresaUsuario= function() {
        empresaService.salvarEmpresaUsuario(preparaEmpresaUsuario()).success(function(dados){
        	if (dados.resposta == "true"){
        		$scope.showAlert("Informação", "Dados salvos com sucesso!");	
        	} else if(dados.resposta == "false") {
        		$scope.showAlert("Informação", "Ops! Parece que sua senha atual está inválida.");
        	}
            
            console.log(dados);
        });

    };

    function preparaEmpresaUsuario() {
        $scope.empresaUsuario.empresa = $scope.empresa;
        $scope.empresaUsuario.email = $scope.empresa.email;
        $scope.empresaUsuario.senha_anterior= $scope.novaSenha;
        console.log($scope.empresa);
        return $scope.empresaUsuario;
    };

    $scope.showAlert = function(titulo, descricao) {
        alert = $mdDialog.alert({
            title: titulo,
            textContent: descricao,
            ok: 'ENTENDI!'
        });

        $mdDialog.show(alert);
    };


    $scope.listaPlanos = [
        {
            cod_plano: "1",
            nome: "Free",
            preco: "0.00",
            image_url: "../resources/assets/png/bg_plano_free.png",
            detalhes: [
                {detalhe: "100 entregas"},
                {detalhe: "Detalhe 2"},
                {detalhe: "Detalhe 3"}
            ]

        },
        {
            cod_plano: "2",
            nome: "Silver",
            preco: "30.00",
            image_url: "../resources/assets/png/bg_plano_silver.png",
            detalhes: [
                {detalhe: "100 entregas"},
                {detalhe: "Detalhe 2"},
                {detalhe: "Detalhe 3"}
            ]

        },
        {
            cod_plano: "3",
            nome: "Gold",
            preco: "50.00",
            image_url: "../resources/assets/png/bg_plano_gold.png",
            detalhes: [
                {detalhe: "100 entregas"},
                {detalhe: "Detalhe 2"},
                {detalhe: "Detalhe 3"}
            ]

        }
    ];

});