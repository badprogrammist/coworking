function BookingCreateController($scope, $location, BookingFactory, MemberFactory, PlaceFactory,TariffFactory) {
    $scope.booking = {};
    $scope.members = MemberFactory.query();
    $scope.places = PlaceFactory.query();
    $scope.tariffs = TariffFactory.query();
    $scope.save = function() {
        BookingFactory.save({}, $scope.booking, function() {
            $location.path('/booking');
        });
    };
}

angular
        .module('BookingModule', ['ngRoute', 'ngResource', 'MemberModule', 'PlaceModule','TariffModule'])
        .config(['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/booking/new', {templateUrl: 'app/booking/templates/edit.html', controller: 'BookingCreateController'});
            }
        ])
        .factory('BookingFactory', function($resource,$routeParams) {
            return $resource('booking/:id', {}, {
                save: {method: 'POST'},
                memberReservations: {method: 'GET', isArray: true, params : {id:$routeParams.memberId}},
                placeReservations: {method: 'GET', isArray: true, params : {id:$routeParams.placeId}}
            });
        })
        .controller('BookingCreateController', BookingCreateController);





