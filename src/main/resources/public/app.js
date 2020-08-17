var app = angular.module('app',[]);

app.controller('UserCRUDCtrl', ['$scope','UserCRUDService', function ($scope,UserCRUDService) {
	
	$scope.users;
 
    $scope.updateUser = function () {
        UserCRUDService.updateUser( $scope.user.id, $scope.user.nome,$scope.user.email,$scope.user.sexo,$scope.user.dataNascimento,$scope.user.naturalidade,$scope.user.nacionalidade,$scope.user.cpf)
          .then(function success(response){
              $scope.message = 'Pessoa atualizada com sucesso!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error na atualizacao da Pessoa!' + response.data;
              $scope.message = '';
          });
    }
    
    $scope.getUser = function () {
        var id = $scope.user.id;
        UserCRUDService.getUser($scope.user.id)
          .then(function success(response){
              $scope.user = response.data;
              $scope.user.id = id;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'Pessoa nao encontrada com o id: ' + id;
              }
              else {
                  $scope.errorMessage = "Error na busca da pessoa com id: " + id;
              }
          });
    }
    
    $scope.addUser = function () {
        if ($scope.user != null && $scope.user.nome) {
        UserCRUDService.addUser($scope.user.nome,$scope.user.email,$scope.user.sexo,$scope.user.dataNascimento,$scope.user.naturalidade,$scope.user.nacionalidade,$scope.user.cpf)	 

          .then (function success(response){
              $scope.message = 'Sucesso ao adicionar a Pessoa.';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error ao adicionar pessoa! ' + response.data;
              $scope.message = '';
        });
	}
	else {
            $scope.errorMessage = 'Por favor, digite os campos obrigatorios';
            $scope.message = '';
        }
        
    }
    
    $scope.deleteUser = function () {
        UserCRUDService.deleteUser($scope.user.id)
          .then (function success(response){
              $scope.message = 'Pessoa deletada com sucess!';
              $scope.user = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error ao deletar a pessoa!';
              $scope.message='';
          })
    }
    
    $scope.getAllUsers = function () {
        UserCRUDService.getAllUsers()
          .then(function success(response){
              $scope.users = response.data;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error buscar todos as pessoas !' + response;
          });
    }

}]);

app.service('UserCRUDService',['$http', function ($http) {
	
    this.getUser = function getUser(userId){
        return $http({
          method: 'GET',
          url: 'v1/pessoas/'+userId
        });
	}
	
    this.addUser = function addUser(nome,email,sexo,dataNascimento,naturalidade,nacionalidade,cpf){
        return $http({
          method: 'POST',
          url: 'v1/pessoas',
          data: {nome:nome,email:email,sexo:sexo,dataNascimento:dataNascimento,naturalidade:naturalidade,nacionalidade:nacionalidade,cpf:cpf}
        });
    }
	
    this.deleteUser = function deleteUser(id){
        return $http({
          method: 'DELETE',
          url: 'v1/pessoas/'+id
        })
    }
	
    this.updateUser = function updateUser(id,nome,email,sexo,dataNascimento,naturalidade,nacionalidade,cpf){
        return $http({
          method: 'PUT',
          url: 'v1/pessoas/',
          data: {id:id,nome:nome,email:email,sexo:sexo,dataNascimento:dataNascimento,naturalidade:naturalidade,nacionalidade:nacionalidade,cpf:cpf}
        })
    }
	
    this.getAllUsers = function getAllUsers(){
        return $http({
          method: 'GET',
          url: 'v1/pessoas'
        });
    }

}]);