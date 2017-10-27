'use strict';
angular.module('projectApp')
    .controller('projectCtrl', ['$scope', '$location', 'projectSvc', 'adalAuthenticationService', function ($scope, $location, projectListSvc, adalService) {
        $scope.error = "";
        $scope.loadingMessage = "";
        $scope.projectList = null;
        $scope.editingInProgress = false;
        $scope.newProjectCaption = "";


        $scope.editInProgressProject = {
            Description: "",
            ID: 0
        };


        $scope.editSwitch = function (project) {
            project.edit = !project.edit;
            if (project.edit) {
                $scope.editInProgressProject.name = project.name;
                $scope.editInProgressProject.description = project.description;
                $scope.editInProgressProject.estimatedCost = project.estimatedCost;
                $scope.editInProgressProject.status = project.status;
                $scope.editInProgressProject.customerName = project.customerName;
                $scope.editInProgressProject.projectLead = project.projectLead;
                $scope.editInProgressProject.id = project.id;
                $scope.editingInProgress = true;
            } else {
                $scope.editingInProgress = false;
            }
        };

        $scope.populate = function () {
            projectListSvc.getItems().success(function (results) {
                $scope.projectList = results;
            }).error(function (err) {
                $scope.error = err;
                $scope.loadingMessage = "";
            })
        };
        $scope.delete = function (id) {
            projectListSvc.deleteItem(id).success(function (results) {
                $scope.populate();
                $scope.loadingMessage = "deleteItem: " + id;
            }).error(function (err) {
                $scope.error = err;
                $scope.loadingMessage = "";
            })
        };
        $scope.update = function (project) {
            projectListSvc.putItem($scope.editInProgressProject).success(function (results) {
                $scope.populate();
                $scope.editSwitch(project);
                $scope.loadingMessage = "putItem: " + results.name;
            }).error(function (err) {
                $scope.error = err;
                $scope.loadingMessage = "";
            })
        };
        $scope.add = function () {

            projectListSvc.postItem({
                'name': $scope.newProjectCaption,
                'projectLead': adalService.userInfo.userName
            }).success(function (results) {
                $scope.newProjectCaption = "";
                $scope.populate();
                $scope.loadingMessage = "postItem: " + results.name;
            }).error(function (err) {
                $scope.error = err;
                $scope.loadingMsg = "";
            })
        };
    }]);