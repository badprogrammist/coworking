function UserListController($scope, UserFactory) {
    $scope.users = UserFactory.query();
    $scope.remove = function(user) {
        user.$remove(function() {
            $scope.users = UserFactory.query();
        });
    };
}

function UserViewController($scope, $stateParams, UserFactory, BookingFactory) {
    $scope.user = UserFactory.get({id: $stateParams.id});
    $scope.bookings = BookingFactory.userReservations({userId: $stateParams.id});
}

function UserCreateController($scope, $location, UserFactory) {
    $scope.user = {};
    $scope.save = function() {
        UserFactory.save({}, $scope.user, function() {
            $location.path('/user/list');
        });
    };
}

function UserEditController($scope, $stateParams, $location, UserFactory) {
    $scope.user = UserFactory.get({id: $stateParams.id});
    $scope.save = function() {
        $scope.user.$update(function() {
            $location.path('/user/list');
        });
    };
}

angular
        .module('UserModule', ['ui.router', 'ngResource', 'BookingModule'])
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                    .state('user', {
                        url: "/user",
                        templateUrl: "app/user/templates/main.html"
                    })
                    .state('user.list', {
                        url: "/list",
                        templateUrl: "app/user/templates/list.html",
                        controller: 'UserListController'
                    })
                    .state('user.new', {
                        url: "/new",
                        templateUrl: "app/user/templates/edit.html",
                        controller: 'UserCreateController'
                    })
                    .state('user.view', {
                        url: "/:id",
                        templateUrl: "app/user/templates/view.html",
                        controller: 'UserViewController'
                    })
                    .state('user.edit', {
                        url: "/:id/edit",
                        templateUrl: "app/user/templates/edit.html",
                        controller: 'UserEditController'
                    });
        })
        .factory('UserFactory', function($resource) {
            return $resource('user/:id', {}, {
                query: {method: 'GET', isArray: true},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('UserListController', UserListController)
        .controller('UserViewController', UserViewController)
        .controller('UserEditController', UserEditController)
        .controller('UserCreateController', UserCreateController);





