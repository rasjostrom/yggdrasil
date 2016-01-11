angular.module('yggdrasil.controllers', [])

    .controller('sidenavCtrl', function($scope, $http, $mdDialog, $window) {
	var allProjects = [];
	
	$scope.currentProject = {title: 'Project Title',
				 description: '',
				 features: [],
				 comments: []};

	$scope.currentFeature = [];
	
	// Fetch all projects from DB and save to 'allProjects'
	$http({
	    method: 'GET',
	    url: '/projects'
	}).then(function successCallback(response) {
	    $scope.allProjects = response.data;
	}, function errorCallback(response) {
	    console.log("Error fetching data from DB.");
	});

	// Update the content view with data from a selected project
	$scope.displayProject = function(project) {
	    $scope.currentProject = project;
	    console.log(project);
	};

	// Update the issues view with data from a selected feature
	$scope.displayIssues = function(feature) {
	    $scope.currentFeature = feature;
	    console.log($scope.currentFeature);
	};

	// Update the currently selected project in the backend
	var updateProject = function() {
	    var putUrl = "/projects/" + $scope.currentProject.id;
	    
	    $http.put(putUrl, $scope.currentProject).then(function successCallback(response) {
		console.log("update successful");
		}, function errorCallback(response) {
		    console.log("Error while updating project.");
		});
	};

	// Add feature to a selected project and update backend
	$scope.addFeature = function(feature) {
	    console.log(feature);
	    var newFt = {id: 0, description: feature, issues: []};
	    $scope.currentProject.features.push(newFt);
	    console.log($scope.currentProject);
	    updateProject();
	};
	
	// Add issue to a selected project and update backend
	$scope.addIssue = function(issue) {
	    console.log(issue);
	    var newIssue = {id: 0, status: false, description: issue};
	    $scope.currentFeature.issues.push(newIssue);
	    console.log($scope.currentProject);
	    updateProject();
	};

	// Toggle the status of an issue
	$scope.toggleIssue = function(issue) {
	    var issueIndex = $scope.currentFeature.issues.indexOf(issue);
	    if ($scope.currentFeature.issues[issueIndex].status == true) {
		$scope.currentFeature.issues[issueIndex].status = false;
		console.log("status changed to false");
	    } else {
		$scope.currentFeature.issues[issueIndex].status = true;
		console.log("status changed to true");
	    }
	    
	    var featureIndex = $scope.currentProject.features.indexOf($scope.currentFeature);
	    $scope.currentProject.features[featureIndex] = $scope.currentFeature;
	    
	    updateProject();
	};
	
	// Remove a project
	$scope.deleteProject = function(project) {
	    var deleteUrl = "/projects/" + $scope.currentProject.id;
	    console.log(deleteUrl);
	    $http.delete(deleteUrl).then(function successCallback(response) {
		$window.location.reload();
		console.log("delete successful");
	    }, function errorCallback(response) {
		console.log("Error while deleting project.");
	    });
	};

	// Remove a feature
	$scope.deleteFeature = function(feature) {
	    for (var i =0; i < $scope.currentProject.features.length; i++)
		if ($scope.currentProject.features[i] === feature) {
		    console.log(feature);
		    $scope.currentProject.features.splice(i,1);
		    break;
		}
	    updateProject();
	};

	// Remove an issue
	$scope.deleteIssue = function(issue) {
	    var featureIndex = $scope.currentProject.features.indexOf($scope.currentFeature);

	    for (var i =0; i < $scope.currentProject.features[featureIndex].issues.length; i++)
		if ($scope.currentProject.features[featureIndex].issues[i] === issue) {
		    console.log(issue);
		    $scope.currentProject.features[featureIndex].issues.splice(i,1);
		    break;
		}
	    
	    updateProject();
	};

	// Post a comment to current project
	$scope.postComment = function(message) {
	    var date  = new Date().toLocaleString();
	    var msgString = "<" + date + "> " + message;
	    var newMsg = {text: msgString};
	    console.log(newMsg);
	    $scope.currentProject.comments.push(newMsg);
	    updateProject();
	    
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
	
	/* Tabs section*/

	$scope.projectTabIndex = 0;
	$scope.switchProjectMode = function(index) {
	    switch(index) {
            case 0: $scope.projectTabIndex = 1;break;
            case 1: $scope.projectTabIndex = 0;break;
	    }
	};

	$scope.featureTabIndex = 0;
	$scope.switchFeatureMode = function(index) {
	    switch(index) {
            case 0: $scope.featureTabIndex = 1;break;
            case 1: $scope.featureTabIndex = 0;break;
	    }
	};

	$scope.issueTabIndex = 0;
	$scope.switchIssueMode = function(index) {
	    switch(index) {
            case 0: $scope.issueTabIndex = 1;break;
            case 1: $scope.issueTabIndex = 0;break;
	    }
	};
	
	$scope.applyEdit = function () {
	    $scope.projectTabIndex = 0;
	    updateProject();
	};
	
    });
