angular.module('yggdrasil.controllers', [])

.controller('sidenavCtrl', function($scope, $http, $mdDialog) {
  $http.get('resource/').success(function(data) {
    $scope.greeting = data;
  })

  $scope.test = function() {
    console.log("presssss");
  };

  $scope.showDialog = showDialog;

  function showDialog($event) {
    var parentEl = angular.element(document.body);
    $mdDialog.show({
      parent: parentEl,
      targetEvent: $event,    //make the dialog appear to open from the location of the button
      controller: DialogController,
      templateUrl: 'newproject.html',
    });
  }

    function DialogController($scope, $mdDialog, $http) {
      $scope.project = {
	  title: ' ',
	  description: ' '
      };
      
      $scope.create = function(project) {
	  console.log(project.title);
	  console.log(project.description);
	  $http.post('/projects', project);
      }
      $scope.closeDialog = function() {
      $mdDialog.hide();
    }
  }
  
});
