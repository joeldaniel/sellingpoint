/**
 * 
 */
angular.module("selling-point.loginModule",['checklist-model'])
.controller('LoginCtrl',function($log,$scope,$rootScope,$location,AuthenticationService,dataServices,userService){
	AuthenticationService.clearCredentials();
	 
	$rootScope.preferenceProjectName="";
	$rootScope.preferenceWorkspaceName=""
	$rootScope.preworkspaceid="";
	$rootScope.preprojectid="";	
	$scope.successStatus = '';
	
	$scope.forgotPass=function(){
		$scope.dataLoading = true;
		if($scope.forgotemail){
			userService.forgotpasssent($scope.forgotemail).then(function(response){
				
				//alert(response.message);
				//$log.info(response);
				if(response.status==200){
					$scope.successStatus=response.data.message;
					$scope.forgotemail="";
				}
				
				//alert($scope.successStatus);
				
			},function(response){
				if (response.status == 500) {
					$scope.errorStatus = response.statusText;
				}
			})
		}
		
	
	}
	$scope.userDetails={};
    $scope.login = function () {
        $scope.dataLoading = true;
        var userinfoData=JSON.stringify($scope.userDetails);
      
       
        AuthenticationService.Login(userinfoData, function(response) {
        	
        
            if(response.statusCode==200) {
            	
            	var usercredential=response.result;
            	
            	//$log.info(response.result);
            	//alert(response.result);
                AuthenticationService.setCredentials(usercredential.userName,usercredential.password,usercredential.email,usercredential.firstName,usercredential.cartCount);
              //alert(usercredential.cartCount);
                $('#loginModal').modal('toggle');
               // $location.path('/userManagement/userList');
                $location.path('/productManagement/productList');
                $scope.dataLoading = false;
            	$scope.reset();
            	/*dataServices.getPreferenceId(usercredential.userName).then(function(response){
            		
            		if(response.data.preferenceId){
        			      				
        				$rootScope.preworkspaceid=response.data.workspaceId;
        				$rootScope.preprojectid=response.data.projectId;	
        				$rootScope.preferenceProjectName=response.data.projectName;
        				$rootScope.preferenceWorkspaceName=response.data.workspaceName;
        				
        				 $('#loginModal').modal('toggle');
        	                $location.path('/services');
        	                $scope.dataLoading = false;
        	            	$scope.reset();
            		}else{
            			 $('#loginModal').modal('toggle');
                         $location.path('/services');
                         $scope.dataLoading = false;
                     	$scope.reset();
            		}
            	},function(response){
            		if (response.status == 500) {
						$scope.errorStatus = response.statusText;
					}
            	})*/
               
            	
            } else {
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        });
    };
    $scope.reset=function(){
    	$scope.username="";
    	$scope.password="";
    	$scope.form = {};
    }
})

.controller("createuserCtrl", function($scope,$http,$log,$timeout,$routeParams,userService) {
		
		$scope.editUserid="";
		$scope.editUserid=$routeParams.showId;
		$scope.userData = {};
		$scope.successStatus = '';
		$scope.successStatuscode = '';
		$scope.errorStatus = '';
		if($scope.editUserid){
		   userService.editUserAccount($scope.editUserid).then(function(response){
			$log.info(response);
			$scope.userData=response.data;
		},function(response){
			$log.info(response);
				if (response.status == 500) {
					$scope.errorStatus = response.statusText;
				}
		})
	}
		$scope.AddUser = function() {
			 if ($scope.adduserFrm.$invalid) {
	             angular.forEach($scope.adduserFrm.$error, function (field) {
	               angular.forEach(field, function(errorField){
	                 errorField.$setTouched();
	               })
	             });
	           
	         }
			if ($scope.adduserFrm.$valid) {
				var userdataInfos = JSON.stringify($scope.userData);
				var userdataInfo=userdataInfos;
				$log.info(userdataInfo);
				// alert(userdataInfo);
				userService.insertUser(userdataInfo).success(function(response) {
					$log.info(response);
				  if(response.statusCode==201){
						$scope.successStatus = response.message
						 $scope.userData={};
				  }else if(response.statusCode==400){
					  $scope.errorStatus = response.message
				  }else if(response.statusCode==206){
					 $scope.errorStatus=response.message
					 
				 }
					
				}).error(function(response) {
					$log.info(response);
					if (response.status == 500) {
						$scope.errorStatus = response.statusText;
					}
					
					
				});

				$timeout(callAtTimeout, 10000)

			}
			
			
		}
		$scope.EditUser = function() {
			if ($scope.adduserFrm.$valid) {
				var userdataInfo = JSON.stringify($scope.userData);
				$log.info("JSON"+userdataInfo);
				$log.info(userdataInfo);
				// alert(userdataInfo);
				userService.updateUser(userdataInfo).success(function(response) {
					$log.info(response);
				  if(response.statusCode==200){
					  $scope.successStatus = response.message
				  }else if(response.statusCode==400){
					  $scope.errorStatus = response.message
				  }else if(response.statusCode==206){
						 $scope.errorStatus=response.message
					 }
				}).error(function(response) {
					$log.info(response);
					if (response.status == 500) {
						$scope.errorStatus = response.statusText;
					}
				}) 
				$timeout(callAtTimeout, 5000)

			}
		}
		//alert($scope.editUserid);
	
		$scope.reset = function() {
			$scope.userData = {};
		}
		function callAtTimeout() {
			$scope.successStatus = '';
			$scope.successStatuscode = '';
			$scope.errorStatus = '';
		}

	})
.controller("userlistCtrl",function($log,$scope,$rootScope,$location,userService, $http,$timeout,$routeParams){
	
	userService.usrList().then(function(response){
		 $log.info(response);
		 $log.info(response.data);
		// alert(response.data);
		 //alert(response);
	//	 alert(response.statusCode);
		// if(response.statusCode==201){
			$scope.alluserlst=response.data;
			
			$scope.userlen=$scope.alluserlst.length;
		//	alert("inside");
		// }
		
		
	},function(response){
		 if (response.status == 500) {
				$scope.errorStatus = response.statusText;
			}
	});
	
	$scope.deleteUser=function(dltuserid,idx){
		if(dltuserid){
			userService.deleteuserAccount(dltuserid).success(function(response){
				if(response.statusCode==200){
					$scope.successStatus = response.message;
					$scope.alluserlst.splice(idx,1);
					
				}else if(response.status==400){
					  $scope.errorStatus = response.message
				  }
			}).error(function(response){
				if (response.status == 500) {
					$scope.errorStatus = response.statusText;
				}
			})
		}
	}
	
})
.controller('forgotCtrl',function($log,$scope,$rootScope,$location,userService, $http,$timeout,$routeParams){
	$scope.randid=$routeParams.showId;
	if($scope.randid){
		
		userService.vaildNotifyid($scope.randid).then(function(response){
			//alert(response.status);
			$log.info(response);
			if(response.status==200){
			//	alert(response.data.statusCode);
				if(response.data.statusCode==200){
					
					$scope.userName=response.data.message;
				}
				else if(response.data.statusCode==400){
					$scope.errorStatus =response.data.message

				}
			}else if(response.status==400){
				 $scope.errorStatus = response.message
			}
			
		},function(response){
			if (response.status == 500) {
				$scope.errorStatus = response.statusText;
			}
		})
		
	}else{
		   $location.path('/');
	}
	
	$scope.pass={};
	$scope.resetpasswordUpdate=function(){


	//	alert($scope.usernames);
		$scope.pass.userName=$scope.userName;
		var datas=JSON.stringify($scope.pass);
		if($scope.pass.password){
			userService.updatePassword($scope.randid,datas).then(function(response){
				if(response.status==200){
					$scope.successStatus = response.data.message;
					$scope.pass={};
				}
				
				
			},function(response){
				if (response.status == 500) {
					$scope.passerrorStatus = response.statusText;
				}
			})
		}
		
	}
	//alert($scope.randid);
	
})

 .directive("checkboxGroup", function() {
        return {
            restrict: "A",
            link: function(scope, elem, attrs) {
                // Determine initial checked boxes
            	//alert(scope.role.name);
            //	alert("ok");
            	//alert(scope.role.name);
            //	alert(scope.array);
            	alert(scope.array.indexOf(scope.role.name))
            	alert(elem[0]);
                if (scope.array.indexOf(scope.role.name) !== -1) {
                    elem[0].checked = true;
                }

                // Update array on click
             
            }
        }
    })


  .directive('validPasswordC', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function (viewValue, $scope) {
                var noMatch = viewValue != scope.myForm.password.$viewValue
                ctrl.$setValidity('noMatch', !noMatch)
            })
        }
    }
})