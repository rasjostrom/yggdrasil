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

  function DialogController($scope, $mdDialog) {
      $scope.project = {
	  projectTitle: ' ',
	  description: ' '
      };
      
      $scope.create = function(project) {
	  console.log(project.projectTitle);
	  console.log(project.description);
      }
      $scope.closeDialog = function() {
      $mdDialog.hide();
    }
  }
  
});
