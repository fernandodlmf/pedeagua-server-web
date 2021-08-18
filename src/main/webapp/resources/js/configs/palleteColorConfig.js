angular
    .module('PedeAgua')
    .config(function($mdThemingProvider, $mdIconProvider ){
        $mdThemingProvider.theme('default').primaryPalette('blue').accentPalette('red');
        $mdIconProvider.icon('menu', "../resources/assets/svg/menu.svg" , 24);
        $mdIconProvider.icon('home','../resources/assets/svg/key.svg', 24);
        $mdIconProvider.icon('empresa','../resources//assets/svg/empresa1.svg', 24);
        $mdIconProvider.icon('veiculo','../resources/assets/svg/cart.svg', 24);
        $mdIconProvider.icon('contato','../resources/assets/svg/package.svg', 24);
        $mdIconProvider.icon('plus', "../resources/assets/svg/plus.svg" , 16);
    });