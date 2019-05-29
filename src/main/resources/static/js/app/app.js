var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
   $routeProvider
       .when("/", {
           templateUrl : "login.html"
       })
       .when("/createSchool/:id", {
           templateUrl : "createschool.html"
       })
       .when("/createEvent/:id", {
           templateUrl : "createevent.html"
       })
       .when("/createStudent/:id", {
           templateUrl : "createstudent.html"
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

app.controller('createStudentCtl', function($scope, $http, $routeParams) {
    $scope.categories = ["1", "2", "3", "4", "5", "6", "7"];
    $scope.events = $scope.eventInfoList;
    $scope.changeLabel = "Create"
    getEvents($scope, $http);

    if($scope.logo != undefined && $scope.logo.length <= 0){
        $scope.hideThis = true;
    }
    if($routeParams.id != undefined){
        $scope.changeLabel = "Update"
        $http.get("/student/getStudentInfo?studentId="+$routeParams.id).then(function(response) {
                console.log(response);
                $scope.loaded = 100;
                setTimeout(function() {
                    $scope.displayErrorMsg = false;
                }, 1000);
                $scope.student = response.data;
                $scope.showProgress = false;
            });
    }else{
        getStudentEvents($scope, $http);
    }
    $scope.createStudent = function(student){
        var file = $scope.myFile;
        student.photo = file.name;
        console.log('file is ' +student.photo);
        console.dir(file);
        var uploadUrl = "/imageUpload/studentPassport";

        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined, "Content-Disposition" : student.photo}
        }).then(function (response) {
            console.log(response.data)
        }, function (response) {
            alert("exception while uploading image.")
        });


        var url = '/student/createStudent';
        var config = 'content-type:application/json';
            $http.post(url, student, config).then(function (response) {
            }, function (response) {
               alert("exception.")
            });
        };

        $scope.addMoreImages = function(event){
            event.imageSet.push(getImageFields());
        }
        $scope.removeImages = function(event, image){
            var index = event.imageSet.indexOf(image);
            event.imageSet.splice(index, 1);
        }
 });


app.controller('manageStudentsCtl', function($scope, $http) {
    $scope.isOpen = false;
    $scope.showProgress = true;
    $scope.loaded = 50;

    $http.get("/student/manageStudents").then(function(response) {
        console.log(response);
        $scope.loaded = 100;
        setTimeout(function() {
            $scope.displayErrorMsg = false;
        }, 1000);
        $scope.studentsInfo = response.data.studentsInfo;
        $scope.showProgress = false;
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
    getEvents($scope, $http);
});

function getEvents($scope, $http){
    $http.get("/event/manageEvent").then(function(response) {
        console.log(response);
        $scope.loaded = 100;
        setTimeout(function() {
            $scope.displayErrorMsg = false;
        }, 1000);
        $scope.eventInfoList = response.data.eventInfoList;
        $scope.showProgress = false;
    });
}

function getStudentEvents($scope, $http){
    var image1Fields = getImageFields();
    var image2Fields = getImageFields();
    var imageSet = [image1Fields, image2Fields];
    $scope.studentEvents = [{
        eventId : 1, category : 1, prize : "", imageSet : imageSet
    }];
}

function getImageFields(){
  return {image: "",  imageName: ""};
}

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
                $scope.event = response.data;
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
            console.log(response.data)
        }, function (response) {
            alert("exception while uploading image.")
        });


       var url = '/cultivatingart/createSchool';
       var config = 'content-type:application/json';
           $http.post(url, school, config).then(function (response) {
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


