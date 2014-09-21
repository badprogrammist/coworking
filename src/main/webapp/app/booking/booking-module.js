function BookingCreateController($scope, $location, BookingFactory, MemberFactory, PlaceFactory) {
    $scope.booking = {};
    $scope.members = MemberFactory.query();
    $scope.places = PlaceFactory.query();
    $scope.save = function() {
        BookingFactory.save({}, $scope.booking, function() {
            $location.path('/booking');
        });
    };
}

angular
        .module('BookingModule', ['ngRoute', 'ngResource', 'MemberModule', 'PlaceModule'])
        .config(['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/booking/new', {templateUrl: 'app/booking/templates/edit.html', controller: 'BookingCreateController'});
            }
        ])
        .factory('BookingFactory', function($resource,$routeParams) {
            return $resource('booking/:id', {}, {
                save: {method: 'POST'},
                memberReservations: {method: 'GET', isArray: true, params : {memberId:$routeParams.memberId}}
            });
        })
        .controller('BookingCreateController', BookingCreateController);





