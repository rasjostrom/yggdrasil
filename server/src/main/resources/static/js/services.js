angular.module('yggdrasil.controllers', [])

.controller('home', function($scope, $http) {
  $http.get('resource/').success(function(data) {
    $scope.greeting = data;
  })
});
