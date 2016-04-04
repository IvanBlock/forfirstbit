/**
 * Created by ivanb on 31.03.2016.
 */
var module = angular.module('myapp', ['ngResource']);

model.factory('News', function ($resource) {
    return $resource('/');
})
    .controller('NewsController', function ($scope, News) {
        return;
    })

var update = function () {
    $scope.news = News.query(url());
}

