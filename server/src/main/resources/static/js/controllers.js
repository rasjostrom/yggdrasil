angular.module('yggdrasil.controllers', [])

.controller('sidenavCtrl', function($scope, $http, $mdDialog) {
    var allProjects = [];
    
    $scope.currentProject = {title: 'Yggdrasil',
			     description: 'Description here',
			     features: [{description: "List all Projects", complete: true},
					{description: "Comment on project wall", complete: false}]};

    $scope.currentIssues = [];

    // Fetch all projects from DB and save to 'allProjects'
    $http({
	method: 'GET',
	url: '/projects'
    }).then(function successCallback(response) {
	$scope.allProjects = response.data;
	console.log(allProjects);
    }, function errorCallback(response) {
	console.log("Error fetching data from DB.");
    });

    // Update the content view with data from a selected project
    $scope.displayProject = function(project) {
	$scope.currentProject = project;
	console.log(project);
    };

    // Update the issues view with data from a selected feature
    $scope.displayIssues = function(issues) {
	$scope.currentIssues = issues;
	console.log(issues);
    };

    // Remove a project
    $scope.deleteProject = function(project, $window) {
	var projectId = project.id;
	var deleteUrl = "/projects/" + projectId;
	console.log(deleteUrl);
	$http.delete(deleteUrl).then(function successCallback(response) {
	    $window.location.reload();
	    console.log("delete successful");
	}, function errorCallback(response) {
	    console.log("Error while deleting project.");
	});
    };
    
   // Show 'new project' dialog
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

    // Controller for the 'new project' dialog
    function DialogController($scope, $mdDialog, $http, $window) {
      $scope.project = {
	  title: ' ',
	  description: ' ',
	  features: [],
	  comments: []
      };
      // Creates a new project and posts it to the backend
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
