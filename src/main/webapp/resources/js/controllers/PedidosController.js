(function() {
    angular.module('PedeAgua').controller('pedidosController',function(Notification, $scope, pedidos, pedidoService, $mdToast, $mdDialog, $mdMedia, $timeout){

        console.log(pedidos.data);


        $scope.pedidos = pedidos.data;
        $scope.pedido = {};
        $scope.data_atual = dataAtualFormatada();
        $scope.novosPedidos = null;
        $scope.codUltimoPedido = null;


        var promise;
        var URL = "http://localhost:8080/spring4-1/info/pedido?cod_empresa=";
        var aberto = false;
        var contador = 5;

        $scope.defineCodUltimoPedido = function() {
            for(var i = 0; i < $scope.pedidos.length; i++ ) {
                $scope.codUltimoPedido = $scope.pedidos[i].cod_pedido;
            }
            console.log("cod ultimo pedido: "+$scope.codUltimoPedido);

        };
        $scope.defineCodUltimoPedido();

        function dataAtualFormatada(){
            var data = new Date();
            var dia = data.getDate();
            if (dia.toString().length == 1)
                dia = "0"+dia;
            var mes = data.getMonth()+1;
            if (mes.toString().length == 1)
                mes = "0"+mes;
            var ano = data.getFullYear();
            return dia+"/"+mes+"/"+ano;
        };


        var sair = function () {
            $timeout.cancel(promise);
            aberto = false;
        }

        function ativarRefresh() {
            contador--;
            if (contador === 0) {
                pedidoService.getPedidosPorEmpresa(1)
                    .success(function(data) {
                        console.log("sucess")
                        if($scope.pedidos.length != data.length) {
                            $scope.novosPedidos = [];
                            for(var i = 0; i < data.length; i++ ) {
                                if(data[i].cod_pedido > $scope.codUltimoPedido) {
                                    $scope.novosPedidos.push(data[i]);
                                    $scope.codUltimoPedido = data[i].cod_pedido;

                                }
                            }2
                            $scope.showNovoPedidoNotification();
                        }

                        $scope.pedidos = data;
                    });
                contador = 5;
            }
            promise = $timeout(ativarRefresh, 1000);
        };

        ativarRefresh();

        $scope.showSimpleToast = function(string) {
            //var pinTo = $scope.getToastPosition();
            $mdToast.show(
                $mdToast.simple()
                    .textContent(string)
                    .position("right && top")
                    .hideDelay(3000)
            );

        };

        $scope.showNovoPedidoNotification = function() {
            Notification.primary({message: "Novo pedido", templateUrl: "novoPedido.html", scope: $scope});
        };


        $scope.salvarPedidosPorEmpresa = function(index, status) {
            $scope.pedido = angular.copy($scope.pedidos[index]);

            if ($scope.pedido.status == "Aprovado" && status == "Negado") {
                $scope.showAlert("Informação", "Ops! Não é possível negar um pedido já aprovado.");
            } else if($scope.pedido.status == status) {
                $scope.showAlert("Informação", "Ops! Esse pedido já está "+status+".");
            } else if($scope.pedido.status == "Pendente" && status == "Negado") {
                $scope.showConfirm("Deseja realmente negar esse pedido?", index, status);
            } else {
                console.log(index)
                console.log($scope.pedidos);
                console.log($scope.pedido);
                $scope.pedido.status = status;
                pedidoService.salvarPedidosPorEmpresa($scope.pedido).success(function(data){
                    $scope.pedidos[index] = $scope.pedido;
                    $scope.showSimpleToast("Pedido "+status+" com sucesso!")
                    console.log("data:" + data)
                });
            }
        };

        $scope.abreModalGoogleMaps = function(ev){
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullScreen;
            console.log("Chegou aqui");
            $mdDialog.show({
                controller : DialogController,
                templateUrl : '/info/dialogGoogleMaps',
                parent : angular.element(document.body),
                targetEvent : ev,
                clickOutsideToClose : true,
                fullscreen : useFullScreen


            }).then(function(resposta) {
                    $scope.status = 'a resposta é "' + resposta + '".';
                    console.log($scope.status);
                },
                function() {
                    $scope.status = 'Não foi';
                    console.log($scope.status);
                });

            $scope.$watch(function() {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function(wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });
        };

        $scope.showAlert = function(titulo, descricao) {
            alert = $mdDialog.alert({
                title: titulo,
                textContent: descricao,
                ok: 'ENTENDI!'
            });

            $mdDialog.show(alert);
        };

        $scope.showConfirm  = function(descricao, index, status) {
            var confirm = $mdDialog.confirm({
                title: 'CONFIRMAÇÃO',
                textContent: descricao,
                ok: 'SIM',
                cancel: 'Não'
            });

            $mdDialog.show(confirm).then(function() {
                $scope.pedido.status = status;
                pedidoService.salvarPedidosPorEmpresa($scope.pedido).success(function(data){
                    $scope.pedidos[index] = $scope.pedido;
                    $scope.showSimpleToast("Pedido "+status+" com sucesso!")
                    console.log("data:" + data)
                });

            }, function() {
                //$scope.status = 'You decided to keep your debt.';
            });
        };

    });

})();
