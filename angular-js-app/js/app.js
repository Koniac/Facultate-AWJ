var app = angular.module('blog', [ ]);

app.controller('HomeController', ['$scope', '$http', function($scope, $http) {
  $scope.helloWorld = 'Aplicatii Web cu suport Java!';

}]);


app.controller('CarteController', ['$scope', '$http', function($scope, $http) {

  $scope.carti = [];
  $scope.keys = [];

  $scope.carte = {};
  $scope.editCarte = {};
  $scope.carteShow = {};
  $scope.isOpen = false;



  $http.get('http://localhost:8080/carte').then(
    function successCallback(response) {

    $scope.carti = response;
    $scope.keys = Object.keys(response.data[0]);
  });

  $scope.addCarte = function(carteNew) {
    $scope.carti.data.push(carteNew);
    $http.post('http://localhost:8080/carte',carteNew).success(function(response){
      $scope.carti = response;
      $scope.carte = {};
    });

  };

  $scope.showCarte = function(carte) {
    $scope.isOpen = true;
    $http.get('http://localhost:8080/carte/'.concat(carte.id)).then(
    function successCallback(response) {
      $scope.carteShow = response.data;
    });

  };

  $scope.setUpdateCarte = function(carte) {
    $scope.editCarte = carte;
  };

  $scope.updateCarte = function() {
    $http.put('http://localhost:8080/carte', $scope.editCarte);
    $scope.editCarte = {};
  };

  $scope.deleteCarte = function(carte) {
    $http.delete('http://localhost:8080/carte/' + carte.id)
    .then(
      function successCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {

    });
  };

}]);

app.controller('ZiarController', ['$scope', '$http', function($scope, $http) {

  $scope.ziare = [];
  $scope.keys = [];

  $scope.ziar = {};
  $scope.editZiar = {};
  $scope.ziarShow = {};
  $scope.isOpen = false;



  $http.get('http://localhost:8080/ziar').then(
    function successCallback(response) {

    $scope.ziare = response;
    $scope.keys = Object.keys(response.data[0]);
  });



  $scope.addZiar = function(ziar) {
    $scope.ziare.data.push(ziar);
    $http.post('http://localhost:8080/ziar',ziar)
      .success(function(response){
        $scope.ziare = response;
        $scope.ziar = {};
    });

  };

  $scope.showZiar = function(ziar) {
    $scope.isOpen = true;
    $http.get('http://localhost:8080/ziar/'.concat(ziar.id)).then(
      function successCallback(response) {

      $scope.ziarShow = response.data[0];
    });
  };

  $scope.setUpdateZiar = function(ziar) {
    $scope.editZiar = ziar;
  };

  $scope.updateZiar = function() {
    $http.put('http://localhost:8080/ziar', $scope.editZiar);
    $scope.editZiar = {};
  };

  $scope.deleteZiar = function(ziar) {
    $http.delete('http://localhost:8080/ziar/' + ziar.id)
    .then(
      function successCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {

    });
  };

}]);

app.controller('RevistaController', ['$scope', '$http', function($scope, $http) {

  $scope.reviste = [];
  $scope.keys = [];

  $scope.revista = {};
  $scope.editRevista = {};
  $scope.revistaShow = {};
  $scope.isOpen = false;



  $http.get('http://localhost:8080/revista').then(
    function successCallback(response) {

    $scope.reviste = response;
    $scope.keys = Object.keys(response.data[0]);
  });



  $scope.addRevista = function(revista) {
    $scope.reviste.data.push(reviste);
    $http.post('http://localhost:8080/reviste',reviste)
      .success(function(response){
        $scope.reviste = response;
        $scope.revista = {};
    });

  };

  $scope.showRevista = function(revista) {
    $scope.isOpen = true;
    $http.get('http://localhost:8080/revista/'.concat(revista.id)).then(
      function successCallback(response) {

      $scope.revistaShow = response.data[0];
    });
  };

  $scope.setUpdateZiar = function(revista) {
    $scope.editRevista = revista;
  };

  $scope.updateZiar = function() {
    $http.put('http://localhost:8080/revista', $scope.editRevista);
    $scope.editRevista = {};
  };

  $scope.deleteRevista = function(revista) {
    $http.delete('http://localhost:8080/revista/' + revista.id)
    .then(
      function successCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
      },
      function errorCallback(response) {

    });
  };

}]);
