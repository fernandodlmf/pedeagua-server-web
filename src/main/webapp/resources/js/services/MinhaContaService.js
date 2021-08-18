angular.module('PedeAgua').factory('minhaContaService', function($http) {

    var _getAgentePorEmail = function(email) {
        return $http.get('http://localhost:8080/spring4-1/info/agente?email='+email);
    };

    var _getEmpresaPorEmail = function(email) {
        return $http.get('http://localhost:8080/spring4-1/info/getEmpresaPorEmail?email='+email);
    };

    return {
        getAgentePorEmail: _getAgentePorEmail,
        getEmpresaPorEmail: _getEmpresaPorEmail
    }


});