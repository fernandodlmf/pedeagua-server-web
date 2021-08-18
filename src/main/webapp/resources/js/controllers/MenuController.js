angular.module('PedeAgua').controller('MenuController', function($scope, $mdSidenav, $http, menuService){
    var self = this;

    self.selected     = null;
    self.menus        = [ ];
    self.selectMenu   = selectMenu;
    self.toggleList   = toggleMenuList;

    menuService.loadAllMenu().then( function( menu ) {
            self.menus = [].concat(menu);
            self.selected = menu[0];
        });

    function toggleMenuList() {
        $mdSidenav('left').toggle();
    }

    self.logout = function(){
        var Chave = Object.keys(JSON.parse(localStorage.token))[0];
        var obj = JSON.parse(localStorage.token);
        var Valor = obj[Chave];

        $http.get('http://localhost:8080/spring4-1/info/deslogarEmpresa?chave='+Chave+'&valor='+Valor).success(function(result){
            localStorage.removeItem('token');
            window.location.href="#/info/inicial"
        });

    };

    self.redirecionaPerfil = function() {

        $http.get('http://localhost:8080/spring4-1/info/getEmpresaPorEmail?email='+Object.keys(JSON.parse(localStorage.token))[0]).success(function(result){
            console.log(result);
            window.location.href="#/info/pedidos"

        });
    };

    self.existeAgenteLogado = function() {
        if (localStorage.getItem("token") != null) {
            self.nomePerfil = Object.keys(JSON.parse(localStorage.token))[0];
            //console.log("logado")
            return true;

        } else {
            //console.log("nao logado");
            self.nomePerfil="";
            return false;
        }

    }

    self.showAlert = function(titulo, descricao) {
        alert = $mdDialog.alert({
            title: titulo,
            textContent: descricao,
            ok: 'ETENDI!'
        });

        $mdDialog.show(alert);
    };

    /**
     * Select the current avatars
     * @param menuId
     */
    function selectMenu ( menu ) {
        self.selected = angular.isNumber(menu) ? $scope.menus[menu] : menu;
    }

});
