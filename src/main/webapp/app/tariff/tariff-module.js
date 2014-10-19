var findTariffOption = function($scope, option) {
    if ($scope.tariff.options) {
        for (var i = 0; i < $scope.tariff.options.length; i++) {
            if ($scope.tariff.options[i].id === option.id) {
                return {tariff: $scope.tariff.options[i], index: i};
            }
        }
    }
    return null;
};

var toggleOption = function($scope, option) {
    var tariffOption = findTariffOption($scope, option);
    if (!tariffOption) {
        $scope.tariff.options.push(option);
        console.log("add " + option.title + " " + $scope.tariff.options);
    } else {
        $scope.tariff.options.splice(tariffOption.index, 1);
        console.log("rem " + option.title + " " + $scope.tariff.options);
    }
};

var isCheckedOption = function($scope, tariff) {
    return findTariffOption($scope, tariff) ? true : false;
};



function TariffListController($scope, TariffFactory) {
    $scope.tariffs = TariffFactory.query();
    $scope.remove = function(tariff) {
        tariff.$remove(function() {
            $scope.tariffs = TariffFactory.query();
        });
    };
}

function TariffViewController($scope, $stateParams, TariffFactory) {
    $scope.tariff = TariffFactory.get({id: $stateParams.id});
    $scope.getTotalPrice = function() {
        return getTotalPrice($scope.tariff);
    };
}

function TariffCreateController($scope, $location, TariffFactory, OptionFactory, DurationFactory) {
    $scope.tariff = {};
    $scope.periods = DurationFactory.periods();
    $scope.options = OptionFactory.query();
    $scope.save = function() {
        TariffFactory.save({}, $scope.tariff, function() {
            $location.path('/tariff/list');
        });
    };
    $scope.toggleOption = function(option) {
        return toggleOption($scope, option);
    };
    $scope.isCheckedOption = function(option) {
        return isCheckedOption($scope, option);
    };
    $scope.findTariffOption = function(option) {
        return findTariffOption($scope, option);
    };
}

function TariffEditController($scope, $stateParams, $location, TariffFactory, OptionFactory, DurationFactory) {
    $scope.tariff = TariffFactory.get({id: $stateParams.id});
    $scope.periods = DurationFactory.periods();
    $scope.options = OptionFactory.query();
    $scope.save = function() {
        $scope.tariff.$update(function() {
            $location.path('/tariff/list');
        });
    };
    $scope.toggleOption = function(tariff) {
        return toggleOption($scope, tariff);
    };
    $scope.isCheckedOption = function(tariff) {
        return isCheckedOption($scope, tariff);
    };
    $scope.findTariffOption = function(tariff) {
        return findTariffOption($scope, tariff);
    };
    
}



angular
        .module('TariffModule', ['ui.router', 'ngResource', 'OptionModule', 'DurationModule'])
        .config(function($stateProvider, $urlRouterProvider) {
            $stateProvider
                    .state('tariff', {
                        url: "/tariff",
                        templateUrl: "app/tariff/templates/main.html"
                    })
                    .state('tariff.list', {
                        url: "/list",
                        templateUrl: "app/tariff/templates/list.html",
                        controller: 'TariffListController'
                    })
                    .state('tariff.new', {
                        url: "/new",
                        templateUrl: "app/tariff/templates/edit.html",
                        controller: 'TariffCreateController'
                    })
                    .state('tariff.view', {
                        url: "/:id",
                        templateUrl: "app/tariff/templates/view.html",
                        controller: 'TariffViewController'
                    })
                    .state('tariff.edit', {
                        url: "/:id/edit",
                        templateUrl: "app/tariff/templates/edit.html",
                        controller: 'TariffEditController'
                    });
        })
        .factory('TariffFactory', function($resource) {
            return $resource('tariff/:id', {}, {
                query: {method: 'GET', isArray: true},
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('TariffListController', TariffListController)
        .controller('TariffViewController', TariffViewController)
        .controller('TariffEditController', TariffEditController)
        .controller('TariffCreateController', TariffCreateController);





