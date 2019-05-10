var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
   $routeProvider
       .when("/", {
           templateUrl : "login.html"
       })
       .when("/createSchool/:id", {
           templateUrl : "createschool.html"
       })
       .when("/createSchool", {
           templateUrl : "createschool.html"
       })
       .when("/manageSchool", {
           templateUrl : "manageschool.html"
       })
       .when("/createEvent", {
           templateUrl : "createevent.html"
       })
       .when("/manageEvent", {
           templateUrl : "manageevent.html"
       })
       .when("/createStudent", {
           templateUrl : "createstudent.html"
       })
       .when("/manageStudent", {
           templateUrl : "managestudent.html"
       });
});



app.controller('manageSchoolCtl', function($scope, $http) {
    $scope.isOpen = false;
    $scope.showProgress = true;
    $scope.loaded = 50;

    $http.get("/cultivatingart/manageSchool").then(function(response) {
        console.log(response);
        $scope.loaded = 100;
        setTimeout(function() {
            $scope.displayErrorMsg = false;
        }, 1000);
        $scope.schoolsInfo = response.data.schoolsInfo;
        $scope.showProgress = false;
    });
});


app.controller('loginCtl', function($scope, $http) {

    $scope.login = function(){
        if($scope.userName == undefined || $scope.userName.length <= 0){
            alert("User Name and Password fields are mandatory ");
        }else{
            $http.get("cultivatingart/login?userId="+$scope.userName+"&password=password"+$scope.password).then(function(response) {
                 console.log(response);
                 window.location.href='#';
                 setTimeout(function() {
                     $scope.displayErrorMsg = false;
                 }, 1000);
                 //$router.navigateByUrl("#!createSchool");
            },function (response){
                     alert("Error logging in please contact Support team");
                     $scope.showProgress = false;
            });
        }
    };

});

app.controller('manageEventCtl', function($scope, $http) {
    $scope.isOpen = false;
    $scope.showProgress = true;
    $scope.loaded = 50;

    $http.get("/event/manageEvent").then(function(response) {
        console.log(response);
        $scope.loaded = 100;
        setTimeout(function() {
            $scope.displayErrorMsg = false;
        }, 1000);
        $scope.eventInfo = response.data.eventInfo;
        $scope.showProgress = false;
    });
});

app.controller('createEventCtl', function($scope, $http, $routeParams) {
    $scope.changeLabel = "Create"
    if($routeParams.id != undefined){
        $scope.changeLabel = "Update"
        $http.get("/event/getEventInfo?eventId="+$routeParams.id).then(function(response) {
                console.log(response);
                $scope.loaded = 100;
                setTimeout(function() {
                    $scope.displayErrorMsg = false;
                }, 1000);
                $scope.school = response.data;
                $scope.showProgress = false;
            });
    }
    $scope.createEvent = function(event){
       var url = '/event/createEvent';
       var config = 'content-type:application/json';
           $http.post(url, event, config).then(function (response) {
               alert("Create school for "+school.name);
           }, function (response) {
               alert("exception.")
           });
       };
 });


app.controller('createSchoolCtl', function($scope, $http, $routeParams) {
    $scope.changeLabel = "Create"
    if($scope.logo != undefined && $scope.logo.length <= 0){
        $scope.hideThis = true;
    }
    if($routeParams.id != undefined){
        $scope.changeLabel = "Update"
        $http.get("/cultivatingart/getSchoolInfo?schoolId="+$routeParams.id).then(function(response) {
                console.log(response);
                $scope.loaded = 100;
                setTimeout(function() {
                    $scope.displayErrorMsg = false;
                }, 1000);
                $scope.school = response.data;
                $scope.showProgress = false;
            });
    }
    $scope.createSchool = function(school){
       var file = $scope.myFile;
       school.logo = file.name;
       console.log('file is ' +school.logo);
       console.dir(file);
       var uploadUrl = "/imageUpload/school";

        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined, "Content-Disposition" : school.name}
        }).then(function (response) {
            alert("Image uploaded for  "+school.name +", location "+response);
            console.log(response.data)
        }, function (response) {
            alert("exception while uploading image.")
        });


       var url = '/cultivatingart/createSchool';
       var config = 'content-type:application/json';
           $http.post(url, school, config).then(function (response) {
               alert("Create school for "+school.name);
           }, function (response) {
               alert("exception.")
           });
       };
 });

app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;

          element.bind('change', function() {
             scope.$apply(function() {
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
 }]);


 app.service('fileUpload', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl) {
       var fd = new FormData();
       fd.append('file', file);

       $http.post(uploadUrl, fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
       })
       .success(function() {
       })
       .error(function() {
       });
    }
 });


/*
app.controller('createSchoolCtl', function($scope, $http) {
    var url = '/cultivatingart/createSchool';
    var config = 'content-type:application/json';
    $scope.createSchool = function(school){
        alert("create school in progress ... "+school.name);
        $http.post(url, school, config).then(function (response) {
            alert("Create school for "+school.name);
        }, function (response) {
            alert("exception.")
        });
    };
});*/
