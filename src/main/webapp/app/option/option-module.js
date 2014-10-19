function OptionListController($scope, OptionFactory) {
    $scope.options = OptionFactory.query();
    $scope.remove = function(option) {
        option.$remove(function() {
            $scope.options = OptionFactory.query();
        });
    };
}

function OptionViewController($scope, $stateParams, OptionFactory) {
    $scope.option = OptionFactory.get({id: $stateParams.id});
}

function OptionCreateController($scope, $location, OptionFactory) {
    $scope.option = {};
    $scope.save = function() {
        OptionFactory.save({}, $scope.option, function() {
            $location.path('/option/list');
        });
    };
}

function OptionEditController($scope, $stateParams, $location, OptionFactory) {
    $scope.option = OptionFactory.get({id: $stateParams.id});
    $scope.save = function() {
        $scope.option.$update(function() {
            $location.path('/option/list');
        });
    };
}

angular
        .module('OptionModule', ['ui.router', 'ngResource'])
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                    .state('option', {
                        url: "/option",
                        templateUrl: "app/option/templates/main.html"
                    })
                    .state('option.list', {
                        url: "/list",
                        templateUrl: "app/option/templates/list.html",
                        controller: 'OptionListController'
                    })
                    .state('option.new', {
                        url: "/new",
                        templateUrl: "app/option/templates/edit.html",
                        controller: 'OptionCreateController'
                    })
                    .state('option.view', {
                        url: "/:id",
                        templateUrl: "app/option/templates/view.html",
                        controller: 'OptionViewController'
                    })
                    .state('option.edit', {
                        url: "/:id/edit",
                        templateUrl: "app/option/templates/edit.html",
                        controller: 'OptionEditController'
                    });
        })
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





