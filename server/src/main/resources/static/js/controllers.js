angular.module('yggdrasil.controllers', [])

.controller('sidenavCtrl', function($scope, $http, $mdDialog) {
    var allProjects = [];

    $scope.currentProject = {title: 'Yggdrasil',
			     description: 'Description here',
			     features: [{title: "List all Projects", complete: true},
					{title: "Comment on project wall", complete: false}]};
    
    $http({
	method: 'GET',
	url: '/projects'
    }).then(function successCallback(response) {
	$scope.allProjects = response.data;
	console.log(allProjects);
    }, function errorCallback(response) {
	console.log("Error fetching data from DB.");
    });

    $scope.displayProject = function(project) {
	$scope.currentProject.title = project.title;
	console.log(project);
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

    function DialogController($scope, $mdDialog, $http, $window) {
      $scope.project = {
	  title: ' ',
	  description: ' '
      };
      
      $scope.create = function(project) {
	  console.log(project.title);
	  console.log(project.description);
	  $http.post('/projects', project).then(function successCallback(response) {
	      $window.location.reload();
	      $mdDialog.hide();
	      
	  }, function errorCallback(response) {
	      console.log("Error while creating project.");
	  });
      }
	
      $scope.closeDialog = function() {
      $mdDialog.hide();
    }
  }
  
});
