function PlaceListController($scope, PlaceFactory) {
    $scope.places = PlaceFactory.query();
    $scope.remove = function(place) {
        place.$remove(function() {
            $scope.places = PlaceFactory.query();
        });
    };
}

function PlaceViewController($scope, $stateParams, PlaceFactory, BookingFactory) {
    $scope.place = PlaceFactory.get({id: $stateParams.id});
    $scope.bookings = BookingFactory.userReservations({placeId: $stateParams.id});
}

function PlaceCreateController($scope, $location, PlaceFactory) {
    $scope.place = {};
    $scope.save = function() {
        PlaceFactory.save({}, $scope.place, function() {
            $location.path('/place/list');
        });
    };
}

function PlaceEditController($scope, $stateParams, $location, PlaceFactory) {
    $scope.place = PlaceFactory.get({id: $stateParams.id});
    $scope.save = function() {
        $scope.place.$update(function() {
            $location.path('/place/list');
        });
    };
}

angular
        .module('PlaceModule', ['ui.router', 'ngResource','BookingModule'])
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                    .state('place', {
                        url: "/place",
                        templateUrl: "app/place/templates/main.html"
                    })
                    .state('place.list', {
                        url: "/list",
                        templateUrl: "app/place/templates/list.html",
                        controller: 'PlaceListController'
                    })
                    .state('place.new', {
                        url: "/new",
                        templateUrl: "app/place/templates/edit.html",
                        controller: 'CreateController'
                    })
                    .state('place.view', {
                        url: "/:id",
                        templateUrl: "app/place/templates/view.html",
                        controller: 'PlaceViewController'
                    })
                    .state('place.edit', {
                        url: "/:id/edit",
                        templateUrl: "app/place/templates/edit.html",
                        controller: 'PlaceEditController'
                    });
        })
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





