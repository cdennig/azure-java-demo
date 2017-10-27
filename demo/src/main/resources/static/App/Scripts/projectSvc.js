'use strict';
angular.module('projectApp')
    .factory('projectSvc', ['$http', function ($http) {
        return {
            getItems: function () {
                return $http.get('/api/projects');
            },
            getItem: function (id) {
                return $http.get('/api/projects/' + id);
            },
            postItem: function (item) {
                return $http.post('/api/projects/', item);
            },
            putItem: function (item) {
                return $http.put('/api/projects/' + item.id, item);
            },
            deleteItem: function (id) {
                return $http({
                    method: 'DELETE',
                    url: '/api/projects/' + id
                });
            }
        };
    }]);