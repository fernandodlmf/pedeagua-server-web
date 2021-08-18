angular.module('PedeAgua').service('adicionaisService', function(){
	
	var sexos = [
	                {
	                    sexoNome : 'Masculino',
	                    sexoSigla : 'M'
	                },
	                {
	                    sexoNome : 'Feminino',
	                    sexoSigla : 'F'
	                }
	            ];
	
	this.getEstados = function(){
		
//		var estados = [
//		                  {uf : 'PB'},
//		                  {uf : 'PA'},
//		                  {uf : 'CE'}
//		                  
//		                  ];
		
		var estados = ('PB AL AP AM BA CE DF ES GO MA MT MS MG AC PA PR PE PI' +
        ' RJ RN RS RO RR SC SP SE TO').split(' ').map(function(estado) {
                return {uf: estado};
            });
		
		return estados;
	}
	
	
	
	this.getSexos = function(){
		
		return sexos;
	}
	
	var ordemReserva = [
	                       {
	                           tipoNome : 'Todas'
	                       },
	                       {
	                           tipoNome : 'Pendentes'
	                       },
	                       {
	                           tipoNome : 'Aprovadas'
	                       },
	                       {
	                           tipoNome : 'Negadas'
	                       }
	                   ];

	this.getOrdemReserva = function(){
			
			return ordemReserva;
		}
	
    var ordemPesquisa = [
       {
           tipoPesquisa : 'Todos'
       },
       {
           tipoPesquisa : 'Nome'
       },
       {
           tipoPesquisa : 'Placa'
       },
       {
           tipoPesquisa : 'Cambio'
       },
       {
           tipoPesquisa : 'Classe'
       },
       {
           tipoPesquisa : 'Passageiros'
       }
   ];
    
    this.getOrdemPesquisa = function(){
		
		return ordemPesquisa;
	}
    
    

   var ordemPesquisaAgente = [
       {
           tipoPesquisa : 'Todos'
       },
       {
           tipoPesquisa : 'Nome'
       },
       {
           tipoPesquisa : 'email'
       },
       {
           tipoPesquisa : 'Cliente'
       },
       {
           tipoPesquisa : 'Funcion�rio'
       },
       {
           tipoPesquisa : 'Nascimento'
       }
   ];
   
   this.getOrdemPesquisaAgente = function(){
		
		return ordemPesquisaAgente;
	}

   var combustiveis = [

       {
           nome : ' ',
           abrev : 'vazio'
       },
       {
           nome : 'Flex',
           abrev : 'Flex'
       },
       {
           nome : 'Gasolina',
           abrev : 'Gas'
       },
       {
           nome : '�lcool',
           abrev : 'Alcool'
       },
       {
           nome : 'Diesel',
           abrev : 'Diesel'
       }
   ];
   
   this.getCombustiveis = function(){
		
		return combustiveis;
	}

   var cambios = [
       {
           tipoCambio : ' '
       },
       {
           tipoCambio : 'Auto'
       },
       {
           tipoCambio : 'Manual'
       }
   ];
   
   this.getCambios = function(){
		
		return cambios;
	}
   
   var tipoEndereco = [
                  {
                      tipo : 'RUA'
                  },
                  {
                      tipo : 'AV.'
                  },
                  {
                      tipo : 'BR'
                  },
                  {
                      tipo : 'PRA�A'
                  }
              ];
              
      this.getTipoEnderecos = function(){
   		
   		return tipoEndereco;
   	}
      
    
  var tipoStatus = [
                      {
                          status : 'ATIVO',
                          ativo : '1'	  
                      },
                      {
                          status : 'INATIVO',
                          ativo : '0'
                      }                    
                  ];
                  
  this.getStatus = function(){
	
	return tipoStatus;
}  
  
  var cores = [
                    {
                        nome : 'Azul'
                    },
                    {
                    	nome : 'Amarela'
                    },
                    {
                    	nome : 'Branca'
                    },
                    {
                    	nome : 'Prata'
                    },
                    {
                    	nome : 'Vermelha'
                    },
                    {
                    	nome : 'Preta'
                    }  
                    
                ];
                
this.getVeiculoCores = function(){
	
	return cores;
}  

var locais = [	
				 {
				 	nome : 'Locadora',
				 	preco : '0.00'
				 },
	             {
	                 nome : 'Aeroporto',
	                 preco : '30.00'
	             },
	             {
	             	nome : 'Rodoviaria',
	             	preco : '20.00'
	             },
             
             
	         ];
         
this.getLocaisRetirada = function(){

	return locais;
}  

	
});