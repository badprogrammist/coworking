function BookingCreateController($scope, $location, BookingFactory, UserFactory, PlaceFactory, TariffFactory) {
    $scope.booking = {};
    $scope.users = UserFactory.query();
    $scope.places = PlaceFactory.query();
    $scope.tariffs = TariffFactory.query();
    $scope.save = function() {
        BookingFactory.save({}, $scope.booking, function() {
            $location.path('/booking');
        });
    };
}

angular
        .module('BookingModule', ['ui.router', 'ngResource', 'UserModule', 'PlaceModule', 'TariffModule'])
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                    .state('booking', {
                        url: "/booking",
                        templateUrl: "app/booking/templates/main.html"
                    })
                    .state('booking.new', {
                        url: "/new",
                        templateUrl: "app/booking/templates/edit.html",
                        controller: 'BookingCreateController'
                    });
        })
        .factory('BookingFactory', function($resource, $stateParams) {
            return $resource('booking/:id', {}, {
                save: {method: 'POST'},
                userReservations: {method: 'GET', isArray: true, params: {id: $stateParams.userId}},
                placeReservations: {method: 'GET', isArray: true, params: {id: $stateParams.placeId}}
            });
        })
        .controller('BookingCreateController', BookingCreateController);





