angular.module('PedeAgua').factory('agenteService', ['$http', '$q', function($http, $q) {

    var _salvarAgenteUsuario = function(agenteUsuario) {
        return $http.post('http://localhost:8080/spring4-1/info/agenteUsuario', agenteUsuario);
    };

    var _getAgentes = function() {
        return $http.get('http://localhost:8080/spring4-1/info/agente');
    };

    var _getAgentePorCodigo = function(cod_agente) {
        return $http.get('http://localhost:8080/spring4-1/info/getAgentePorCodigo?cod_agente='+cod_agente);
    };

    var _getAgentePorEmail = function(email) {
        return $http.get('http://localhost:8080/spring4-1/info/getAgentePorEmail?email='+email);
    };

    var _agenteLogin = function(email, senha) {
        return $http.get('http://localhost:8080/spring4-1/info/agenteLogin?email='+email+'&senha='+senha);
    };

    var _deslogarAgente = function(chave, valor) {
        return $http.get('http://localhost:8080/spring4-1/info/deslogarAgente?chave='+chave+'&valor='+valor);

    };

    return {
        getAgentes: _getAgentes,
        getAgentePorCodigo: _getAgentePorCodigo,
        salvarAgenteUsuario: _salvarAgenteUsuario,
        agenteLogin: _agenteLogin,
        deslogarAgente: _deslogarAgente,
        getAgentePorEmail: _getAgentePorEmail
    }


}]);