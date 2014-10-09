function OptionListController($scope, OptionFactory) {
    $scope.options = OptionFactory.query();
    $scope.remove = function(option) {
        option.$remove(function() {
            $scope.options = OptionFactory.query();
        });
    };
}

function OptionViewController($scope, $routeParams, OptionFactory) {
    $scope.option = OptionFactory.get({id: $routeParams.id});
}

function OptionCreateController($scope, $location, OptionFactory) {
    $scope.option = {};
    $scope.save = function() {
        OptionFactory.save({}, $scope.option, function() {
            $location.path('/option');
        });
    };
}

function OptionEditController($scope, $routeParams, $location, OptionFactory) {
    $scope.option = OptionFactory.get({id: $routeParams.id});
    $scope.save = function() {
        $scope.option.$update(function() {
            $location.path('/option');
        });
    };
}

angular
        .module('OptionModule', ['ngRoute', 'ngResource'])
        .config(['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/option', {templateUrl: 'app/option/templates/list.html', controller: 'OptionListController'}).
                        when('/option/new', {templateUrl: 'app/option/templates/edit.html', controller: 'OptionCreateController'}).
                        when('/option/:id', {templateUrl: 'app/option/templates/view.html', controller: 'OptionViewController'}).
                        when('/option/:id/edit', {templateUrl: 'app/option/templates/edit.html', controller: 'OptionEditController'});
            }
        ])
        .factory('OptionFactory', function($resource) {
            return $resource('option/:id', {}, {
                query: {method: 'GET', isArray: true},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('OptionListController', OptionListController)
        .controller('OptionViewController', OptionViewController)
        .controller('OptionEditController', OptionEditController)
        .controller('OptionCreateController', OptionCreateController);





