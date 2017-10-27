'use strict';
angular.module('projectApp', ['ngRoute', 'AdalAngular'])
    .config(['$routeProvider', '$httpProvider', 'adalAuthenticationServiceProvider', function ($routeProvider, $httpProvider, adalProvider) {

        $routeProvider.when("/Home", {
            controller: "homeCtrl",
            templateUrl: "/App/Views/Home.html",
        }).when("/ProjectList", {
            controller: "projectCtrl",
            templateUrl: "/App/Views/ProjectList.html",
            requireADLogin: true,
        }).when("/UserData", {
            controller: "userDataCtrl",
            templateUrl: "/App/Views/UserData.html",
        }).otherwise({redirectTo: "/Home"});

        adalProvider.init(
            {
                instance: 'https://login.microsoftonline.com/',
                tenant: 'javadevdemo.onmicrosoft.com',
                clientId: '18eb0b2e-bebc-4cea-aaae-3d2d77d21c6f',
                extraQueryParameter: 'nux=1',
                cacheLocation: 'localStorage',
            },
            $httpProvider
        );

    }]);
