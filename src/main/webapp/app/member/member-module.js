function MemberListController($scope, MemberFactory) {
    $scope.members = MemberFactory.query();
    $scope.remove = function(member) {
        member.$remove(function() {
            $scope.members = MemberFactory.query();
        });
    };
}

function MemberViewController($scope, $routeParams, MemberFactory) {
    $scope.member = MemberFactory.get({id: $routeParams.id});
}

function MemberCreateController($scope, $location, MemberFactory) {
    $scope.member = {};
    $scope.save = function() {
        MemberFactory.save({}, $scope.member, function() {
            $location.path('/member');
        });
    };
}

function MemberEditController($scope, $routeParams, $location, MemberFactory) {
    $scope.member = MemberFactory.get({id: $routeParams.id});
    $scope.save = function() {
        $scope.member.$update(function() {
            $location.path('/member');
        });
    };
}

angular
        .module('MemberModule', ['ngRoute', 'ngResource'])
        .config(['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/member', {templateUrl: 'app/member/templates/list.html', controller: 'MemberListController'}).
                        when('/member/new', {templateUrl: 'app/member/templates/edit.html', controller: 'MemberCreateController'}).
                        when('/member/:id', {templateUrl: 'app/member/templates/view.html', controller: 'MemberViewController'}).
                        when('/member/:id/edit', {templateUrl: 'app/member/templates/edit.html', controller: 'MemberEditController'});
            }
        ])
        .factory('MemberFactory', function($resource) {
            return $resource('member/:id', {}, {
                query: {method: 'GET', isArray: true},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('MemberListController', MemberListController)
        .controller('MemberViewController', MemberViewController)
        .controller('MemberEditController', MemberEditController)
        .controller('MemberCreateController', MemberCreateController);





