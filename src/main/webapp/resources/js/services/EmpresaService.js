angular.module('PedeAgua').factory('empresaService', ['$http', '$q', function($http, $q) {

    var _salvarEmpresaUsuario = function(empresaUsuario) {
        return $http.post('http://localhost:8080/spring4-1/info/addEmpresaUsuario', empresaUsuario);
    };

    var _getEmpresas = function() {
        return $http.get('http://localhost:8080/spring4-1/info/getAllEmpresas');
    };

    var _getEmpresaPorCodigo = function(cod_empresa) {
        return $http.get('http://localhost:8080/spring4-1/info/getEmpresaPorCodigo?cod_empresa='+cod_empresa);
    };

    var _getEmpresaPorEmail = function(email) {
        return $http.get('http://localhost:8080/spring4-1/info/getEmpresaPorEmail?email='+email);
    };

    var _empresaLogin = function(email, senha) {
        return $http.get('http://localhost:8080/spring4-1/info/empresaLogin?email='+email+'&senha='+senha);
    };

    var _deslogarEmpresa = function(chave, valor) {
        return $http.get('http://localhost:8080/spring4-1/info/deslogarempresa?chave='+chave+'&valor='+valor);
    };

    return {
        getEmpresas: _getEmpresas,
        getEmpresaPorCodigo: _getEmpresaPorCodigo,
        salvarEmpresaUsuario: _salvarEmpresaUsuario,
        empresaLogin: _empresaLogin,
        deslogarEmpresa: _deslogarEmpresa,
        getEmpresaPorEmail: _getEmpresaPorEmail
    }


}]);