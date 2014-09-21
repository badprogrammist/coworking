function PlaceListController($scope, PlaceFactory) {
    $scope.places = PlaceFactory.query();
    $scope.remove = function(place) {
        place.$remove(function() {
            $scope.places = PlaceFactory.query();
        });
    };
}

function PlaceViewController($scope, $routeParams, PlaceFactory) {
    $scope.place = PlaceFactory.get({id: $routeParams.id});
}

function PlaceCreateController($scope, $location, PlaceFactory) {
    $scope.place = {};
    $scope.save = function() {
        PlaceFactory.save({}, $scope.place, function() {
            $location.path('/place');
        });
    };
}

function PlaceEditController($scope, $routeParams, $location, PlaceFactory) {
    $scope.place = PlaceFactory.get({id: $routeParams.id});
    $scope.save = function() {
        $scope.place.$update(function() {
            $location.path('/place');
        });
    };
}

angular
        .module('PlaceModule', ['ngRoute', 'ngResource'])
        .config(['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/place', {templateUrl: 'app/place/templates/list.html', controller: 'PlaceListController'}).
                        when('/place/new', {templateUrl: 'app/place/templates/edit.html', controller: 'PlaceCreateController'}).
                        when('/place/:id', {templateUrl: 'app/place/templates/view.html', controller: 'PlaceViewController'}).
                        when('/place/:id/edit', {templateUrl: 'app/place/templates/edit.html', controller: 'PlaceEditController'});
            }
        ])
        .factory('PlaceFactory', function($resource) {
            return $resource('place/:id', {}, {
                query: {method: 'GET', isArray: true},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('PlaceListController', PlaceListController)
        .controller('PlaceViewController', PlaceViewController)
        .controller('PlaceEditController', PlaceEditController)
        .controller('PlaceCreateController', PlaceCreateController);





