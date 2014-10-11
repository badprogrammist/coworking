angular
        .module('DurationModule', ['ngRoute', 'ngResource'])
        .factory('DurationFactory', function($resource) {
            return $resource('duration/:id', {}, {
                periods: {method: 'GET', params: {id: 'period'}, isArray: true}
            });
        });





