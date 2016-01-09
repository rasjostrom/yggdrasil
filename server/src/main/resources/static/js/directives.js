angular.module('.directives', [])

    .directive('projectList', function(){
        return {
            restrict: 'E',
            templateUrl: 'templates/project/projectlist.html'
        };
    })
