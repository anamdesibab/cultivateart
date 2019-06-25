var app = angular.module("winnerApp", ["ngRoute"]);

app.config(function($routeProvider) {
   $routeProvider
       .when("/", {
           templateUrl : "search.html"
       }).when("/searchresults", {
           templateUrl : "search.html"
       }).when("/schoolProfile/:id", {
           templateUrl : "schoolProfile.html"
       }).when("/studentProfile/:id", {
           templateUrl : "studentProfile.html"
       });
});



app.controller('searchCtl', function($window, $scope, $http, $routeParams) {
    $scope.schoolSearchResult = false;
    $scope.studentSearchResult = false;
    $scope.search = function(searchText){
        console.log("Search text is "+searchText);

         $http.get("school/searchSchool?searchText="+searchText).then(function(response) {
            console.log(response);
            $scope.loaded = 100;
            setTimeout(function() {
                $scope.displayErrorMsg = false;
            }, 1000);
            $scope.schoolsInfo = response.data.schoolsInfo
            $scope.showProgress = false;
            if($scope.schoolsInfo.length > 0){
                $scope.schoolSearchResult = true;
            }
        });

        $http.get("student/searchStudentStudent?searchText="+searchText).then(function(response) {
            console.log(response);
            $scope.loaded = 100;
            setTimeout(function() {
                $scope.displayErrorMsg = false;
            }, 1000);
            $scope.studentsInfo = response.data.studentsInfo;
            $scope.showProgress = false;
            if($scope.studentsInfo.length > 0){
                $scope.studentSearchResult = true;
            }
        });
    }
});

app.controller('schoolProfileCtl', function($window, $scope, $http, $routeParams){
     getEvents($scope, $http);
     if($routeParams.id != undefined){
        $http.get("/school/getSchoolProfile?schoolId="+$routeParams.id).then(function(response) {
            console.log(response);
            $scope.loaded = 100;
            setTimeout(function() {
                $scope.displayErrorMsg = false;
            }, 1000);
            $scope.school = response.data.schoolsInfo;
            $scope.students = response.data.students
            $scope.showProgress = false;
        });
     }
});

app.controller('studentProfileCtl', function($window, $scope, $http, $routeParams){

    $scope.imageSet = [];

    if($routeParams.id != undefined){
        $http.get("/student/getStudentInfo?studentId="+$routeParams.id).then(function(response) {
            console.log(response);
            $scope.loaded = 100;
            setTimeout(function() {
                $scope.displayErrorMsg = false;
            }, 1000);
            $scope.student = response.data;
            $scope.imageSet = getImages($scope.student);
            $scope.showProgress = false;
        });
    }

    // initial image index
        $scope._Index = 0;
        // if a current image is the same as requested image
        $scope.isActive = function (index) {
            $scope.imageCaption = $scope.imageSet[index].imageName;
            return $scope._Index === index;
        };
        // show prev image
        $scope.showPrev = function () {
            $scope._Index = ($scope._Index > 0) ? --$scope._Index : $scope.photos.length - 1;
        };
        // show next image
        $scope.showNext = function () {
            $scope._Index = ($scope._Index < $scope.photos.length - 1) ? ++$scope._Index : 0;
        };
        // show a certain image
        $scope.showPhoto = function (index) {
            $scope._Index = index;
        };
});

function getImages(student){
    var imagesSet = [];
    student.events.forEach(event =>{
        for (image of event.imageSet) {
            imagesSet.push(image);
        }
    })
    return imagesSet;
}

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