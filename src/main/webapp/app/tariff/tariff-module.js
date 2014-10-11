var findTariffOption = function($scope, option) {
    if ($scope.tariff.options) {
        for (var i = 0; i < $scope.tariff.options.length; i++) {
            if ($scope.tariff.options[i].id === option.id) {
                return {option: $scope.tariff.options[i], index: i};
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

var isCheckedOption = function($scope, option) {
    return findTariffOption($scope, option) ? true : false;
};

var getTotalPrice = function(tariff) {
    var total = parseFloat(tariff.price);
    if (tariff.options) {
        for (var i = 0; i < tariff.options.length; i++) {
            total +=parseFloat(tariff.options[i].price);
        }
    }
    return total;
};

var changeTariffPrice = function($scope, option, price) {
    for (var i = 0; i < $scope.tariff.options.length; i++) {
        if ($scope.tariff.options[i].id === option.id) {
            $scope.tariff.options[i].price = price;
        }
    }
};


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
    $scope.getTotalPrice = function() {
        return getTotalPrice($scope.tariff);
    };
}

function TariffCreateController($scope, $location, TariffFactory, OptionFactory, DurationFactory) {
    $scope.tariff = {};
    $scope.periods = DurationFactory.periods();
    $scope.options = OptionFactory.query();
    $scope.save = function() {
        $scope.tariff.$update(function() {
            $location.path('/tariff');
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
    $scope.changeTariffPrice = function(option, price) {
        return changeTariffPrice($scope, option, price);
    };
}

function TariffEditController($scope, $routeParams, $location, TariffFactory, OptionFactory, DurationFactory) {
    $scope.tariff = TariffFactory.get({id: $routeParams.id});
    $scope.periods = DurationFactory.periods();
    $scope.options = OptionFactory.query();
    $scope.save = function() {
        $scope.tariff.$update(function() {
            $location.path('/tariff');
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
    $scope.changeTariffPrice = function(option, price) {
        return changeTariffPrice($scope, option, price);
    };
    $scope.getTotalPrice = function() {
        return getTotalPrice($scope.tariff);
    };
}



angular
        .module('TariffModule', ['ngRoute', 'ngResource', 'OptionModule', 'DurationModule'])
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
                save: {method: 'POST'},
                update: {method: 'PUT', params: {id: '@id'}},
                remove: {method: 'DELETE', params: {id: '@id'}}
            });
        })
        .controller('TariffListController', TariffListController)
        .controller('TariffViewController', TariffViewController)
        .controller('TariffEditController', TariffEditController)
        .controller('TariffCreateController', TariffCreateController);





