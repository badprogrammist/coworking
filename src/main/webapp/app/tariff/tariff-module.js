function TariffListController($scope, TariffFactory) {
    $scope.tariffs = TariffFactory.query();
    $scope.remove = function(tariff) {
        tariff.$remove(function() {
            $scope.tariffs = TariffFactory.query();
        });
    };
}

function TariffViewController($scope, $routeParams, TariffFactory) {
    $scope.tariff = TariffFactory.get({id: $routeParams.id});
}

function TariffCreateController($scope, $location, TariffFactory) {
    $scope.tariff = {};
    $scope.periods = TariffFactory.periods();
    $scope.save = function() {
        TariffFactory.save({}, $scope.tariff, function() {
            $location.path('/tariff');
        });
    };
}

function TariffEditController($scope, $routeParams, $location, TariffFactory, OptionFactory) {
    $scope.tariff = TariffFactory.get({id: $routeParams.id});
    $scope.periods = TariffFactory.periods();
    $scope.options = OptionFactory.query();
    $scope.save = function() {
        $scope.tariff.$update(function() {
            $location.path('/tariff');
        });
    };
    $scope.changeOption = function(option) {
        var contain = false;
        var removeIndex = -1;
        var i = 0;
        for (var i = 0; i < $scope.tariff.options.length; i++) {
            if ($scope.tariff.options[i].id === option.id) {
                contain = true;
                removeIndex = i;
            }
        }
        if (!contain) {
            $scope.tariff.options.push(option);
            console.log("add " + option.title + " " + $scope.tariff.options);
        } else {
            $scope.tariff.options.splice(removeIndex, 1);
            console.log("rem " + option.title + " " + $scope.tariff.options);
        }
    };
    $scope.checkedOption = function(option) {
        if ($scope.tariff.options) {
            for (var i = 0; i < $scope.tariff.options.length; i++) {
                if ($scope.tariff.options[i].id === option.id) {
                    return true;
                }
            }
        }
        return false;
    };

}

angular
        .module('TariffModule', ['ngRoute', 'ngResource', 'OptionModule'])
        .config(['$routeProvider',
            function($routeProvider) {
                $routeProvider.
                        when('/tariff', {templateUrl: 'app/tariff/templates/list.html', controller: 'TariffListController'}).
                        when('/tariff/new', {templateUrl: 'app/tariff/templates/edit.html', controller: 'TariffCreateController'}).
                        when('/tariff/:id', {templateUrl: 'app/tariff/templates/view.html', controller: 'TariffViewController'}).
                        when('/tariff/:id/edit', {templateUrl: 'app/tariff/templates/edit.html', controller: 'TariffEditController'});
            }
        ])
        .factory('TariffFactory', function($resource) {
            return $resource('tariff/:id', {}, {
                query: {method: 'GET', isArray: true},
                periods: {method: 'GET', params: {id: 'period'}, isArray: true},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('TariffListController', TariffListController)
        .controller('TariffViewController', TariffViewController)
        .controller('TariffEditController', TariffEditController)
        .controller('TariffCreateController', TariffCreateController);





