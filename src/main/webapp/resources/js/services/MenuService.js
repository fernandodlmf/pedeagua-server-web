(function(){
  'use strict';

  angular.module('PedeAgua')
         .service('menuService', ['$q', MenuServices]);

  /**
   * Users DataService
   * Uses embedded, hard-coded data model; acts asynchronously to simulate
   * remote data service call(s).
   *
   * @returns {{loadAll: Function}}
   * @constructor
   */
  function MenuServices($q){
    var menus = [
      {
        name: 'Minha Conta ',
        icon: 'home',
        url : '#/info/minhaConta'
      },
      {
        name: 'Pedidos',
        icon: 'veiculo',
        url : '#/info/pedidos'
      },
      {
        name: 'Produtos',
        icon: 'contato',
        url : '#/info/produtos'
      },
      {
        name: 'Financeiro',
        icon: 'empresa',
        url : '#'
      }
    ];
	
	var _x = function() {
		return $q.when(menus);
	};

    // Promise-based API
    return {
      loadAllMenu : _x
    };
  }

})();
